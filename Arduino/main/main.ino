#include "WiFiS3.h"
#include "secrets.h"
#include "HX711.h"
#include <Servo.h>
#define SPEAKER_PIN 7

Servo servo10;
Servo servo11;
Servo servo12;
Servo servo13;
int angle = 103;

const int DOUT_PIN = 2;
const int SCK_PIN = 3;
HX711 scale;
const float calibration_factor = 6500;

char ssid[] = SECRET_SSID;
char password[] = SECRET_PASSWORD;
IPAddress SECRET_SERVER;

int status = WL_IDLE_STATUS;

WiFiClient client;

int httpPort = 8080;

void setup() {
  // Baud rate: 9600 is the maximum amount of bits per second that can be transferred.
  // See: https://docs.arduino.cc/software/ide-v2/tutorials/ide-v2-serial-monitor
  Serial.begin(9600);

  while (!Serial) {
    ;
  }

  servo10.attach(10);
  servo11.attach(11);
  servo12.attach(12);
  servo13.attach(13);
  servo10.write(angle);
  servo11.write(angle);
  servo12.write(angle);
  servo13.write(angle);

  if (WiFi.status() == WL_NO_MODULE) {
    Serial.println("Communication with WiFi module failed!");
    while (true);
  }

  if (WiFi.firmwareVersion() < WIFI_FIRMWARE_LATEST_VERSION) {
    Serial.println("Please upgrade firmware!");
  }

  connectToSsid();
  connectToApi();

  scale.begin(DOUT_PIN, SCK_PIN);
  scale.set_scale(calibration_factor);
  delay(1000);
  Serial.println("Ready!");
}

void loop() {
  scale.tare();                           // Reset scale
  client.connect(server, httpPort);
  createHttpRequest("/check-medicine-removal", "GET");
  delay(100);
  
  String apiResponse = client.readString();
  int intApiResponse = apiResponse.toInt();
  client.stop();

  switch (intApiResponse) {
    case 1:
      motorOpenClose(servo10);
      break;
    case 2:
      motorOpenClose(servo11);
      break;
    case 3:
      motorOpenClose(servo12);
      break;
    case 4:
      motorOpenClose(servo13);
      break;    
    default:
      intApiResponse = 0;    
  }

  if(intApiResponse != 0) {
    delay(2000);
    measureWeight();
    delay(50000);
  }
  delay(10000);
}

void connectToSsid() {
  while (status != WL_CONNECTED) {
    Serial.print("Attempting to connect to SSID: ");
    Serial.println(ssid);

    status = WiFi.begin(ssid, password);

    if (status == WL_CONNECTED) {
      Serial.println("Successfully connected to SSID!");
    }

    delay(10000);
  }
}

void connectToApi() {
  Serial.println("\nStarting connection to Api...");

  if (client.connect(server, httpPort)) {
    Serial.println("Connected to Api!");

  }
}

void createHttpRequest(String path, String method){
  client.println(method + " " + path);
  client.println("Host: http://" + server.toString());
  client.println("Connection: close");
  client.println();
}

void measureWeight() {
  unsigned long startTime = millis();
  while(true){
    float weight = scale.get_units(10);

    if(weight < 0.1) {
      Serial.println("Making API request...");
      client.connect(server, httpPort);
      createHttpRequest("/medicine-disposed", "POST");
      client.stop();
      break;
    }
    unsigned long elapsedTime = millis() - startTime;
    unsigned int elapsedMinutes = elapsedTime / (1000 * 60);
    if(elapsedMinutes >= 1) {
      alarmSound();
    }
    delay(100);
  }
}

void alarmSound(){
  float frequencyList[8] = {261.63, 293.66, 329.63, 349.23, 392.00, 440.00, 493.88, 523.25};
  int dl = 100;

  for(int i = 0; i < 3; i++) {
    for(int j = 0; j < 8; j++) {
      tone(SPEAKER_PIN, frequencyList[j]);
      delay(dl);
      noTone(SPEAKER_PIN);
      delay(dl);
    }      
  }
}

void motorOpenClose(Servo motor) {
  // Open servo
  for (int i = angle; i < 193; i++) {
    motor.write(i);
    delay(2);
  }
  delay(1000);
  
  // Close servo
  for (int j = 193; j > angle; j--) {
    motor.write(j);
    delay(2);
    }
}
