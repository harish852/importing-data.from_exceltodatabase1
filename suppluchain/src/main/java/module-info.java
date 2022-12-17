module com.example.suppluchain {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.suppluchain to javafx.fxml;
    exports com.example.suppluchain;
}