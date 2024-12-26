module oose2324.group5.dispensex.platform {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;

    opens oose2324.group5.dispensex.platform to javafx.fxml;
    opens oose2324.group5.dispensex.platform.controllers to javafx.fxml;

    exports oose2324.group5.dispensex.platform;
    exports oose2324.group5.dispensex.platform.classes;
    opens oose2324.group5.dispensex.platform.classes to javafx.base, javafx.fxml;
    exports oose2324.group5.dispensex.platform.httpRequest;
    opens oose2324.group5.dispensex.platform.httpRequest to javafx.fxml;
}
