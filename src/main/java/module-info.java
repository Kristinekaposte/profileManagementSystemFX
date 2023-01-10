module com.example.profilemanagementsystemfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.profilemanagementsystemfx to javafx.fxml;
    exports com.example.profilemanagementsystemfx;
}