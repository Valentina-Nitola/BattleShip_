module com.example.battleship_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.example.battleship_ to javafx.fxml;
    exports com.example.battleship_;
    exports com.example.battleship_.controller;
    opens com.example.battleship_.controller to javafx.fxml;
}