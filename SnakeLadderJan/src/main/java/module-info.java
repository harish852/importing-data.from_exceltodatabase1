module com.example.snakeladderjan {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeladderjan to javafx.fxml;
    exports com.example.snakeladderjan;
}