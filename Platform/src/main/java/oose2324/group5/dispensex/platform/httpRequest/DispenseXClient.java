package oose2324.group5.dispensex.platform.httpRequest;

import oose2324.group5.dispensex.platform.classes.Interval;
import oose2324.group5.dispensex.platform.classes.MedicineTakeoutStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DispenseXClient {
    public static final String BASE_URL = "http://192.168.193.224:8080";

    public static boolean buildCreateIntervalRequest(Interval interval) {
        JSONObject json = new JSONObject();
        json.put("binNumber", interval.getBinNumber());
        json.put("timeOfDisposal", interval.getTimeOfDisposal());
        json.put("medicineName", interval.getNameOfMedicine());
        json.put("numberOfMedicines", interval.getNumberOfMedicines());

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/create-interval"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
            .build();

        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("Something went wrong, could not send API request");
            return false;
        }

        return response != null;
    }

    public static List<MedicineTakeoutStatus> buildGetAllMedicineTakeoutStatusesRequest() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/get-medicine-takeout-statuses"))
            .header("Content-Type", "application/json")
            .GET()
            .build();

        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("Something went wrong, could not send API request");
        }

        if (response == null) {
            return new ArrayList<>();
        }

        JSONArray json = new JSONArray(response.body());
        List<MedicineTakeoutStatus> medicineTakeoutStatuses = new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {
            JSONObject object = json.getJSONObject(i);
            medicineTakeoutStatuses.add(new MedicineTakeoutStatus(object.getInt("id"), object.getString("timeOf_takeout"), object.getString("originalTimeOfDisposal"), object.getString("originalDayOfWeek"), object.getString("originalMedicineName"), object.getInt("originalNumberOfMedicines")));
        }

        return medicineTakeoutStatuses;
    }
}
