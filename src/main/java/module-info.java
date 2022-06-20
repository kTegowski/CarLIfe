module com.carlifeproo22l {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.json;
    requires org.apache.commons.io;

//    opens com.carlifeproo22l to javafx.fxml;
//    exports com.carlifeproo22l;
    exports com.view;
    opens com.view to javafx.fxml;
    exports model;
    opens model to javafx.fxml;
    exports model.CarType;
    opens model.CarType to javafx.fxml;
    exports model.Incident;
    opens model.Incident to javafx.fxml;

}