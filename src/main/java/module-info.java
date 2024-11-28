module com.example.username {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.username to javafx.fxml;
    exports com.example.username;
}