module com.github.mariosplen.dotsandboxes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.github.mariosplen.dotsandboxes to javafx.fxml;
    exports com.github.mariosplen.dotsandboxes;
    exports com.github.mariosplen.dotsandboxes.models;
    opens com.github.mariosplen.dotsandboxes.models to javafx.fxml;
}