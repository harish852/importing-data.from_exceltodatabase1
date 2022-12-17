module com.example.gitdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gitdemo to javafx.fxml;
    exports com.example.gitdemo;
}