module com.github.mariosplen.dotsandboxes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.mariosplen.dotsandboxes to javafx.fxml;
    exports com.github.mariosplen.dotsandboxes;
    exports com.github.mariosplen.dotsandboxes.logic;
    opens com.github.mariosplen.dotsandboxes.logic to javafx.fxml;
}