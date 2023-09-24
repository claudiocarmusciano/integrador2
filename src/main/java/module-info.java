module com.example.tpintegrador2 {
//    requires javafx.controls;
//    requires javafx.fxml;
    requires java.persistence;


    opens com.example.tpintegrador2 to javafx.fxml;
//    exports com.example.tpintegrador2;
    exports com.example.tpintegrador2.Entidades;
    opens com.example.tpintegrador2.Entidades to javafx.fxml;
}