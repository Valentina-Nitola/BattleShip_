module com.example.battleship_ {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.battleship_ to javafx.fxml;
    exports com.example.battleship_;
}