module com.github.mariosplen.dotsandboxes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.mariosplen.dotsandboxes to javafx.fxml;
    exports com.github.mariosplen.dotsandboxes;
}