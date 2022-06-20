package com.view;

import com.view.CarHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import model.CarType.Car;
import model.Incident.CarIncident;
import model.Incident.CarReview;
import model.Incident.OilChange;
import model.Incident.OtherCarIncidents;

import java.net.URL;
import java.util.ResourceBundle;

public class CarIncidentController implements Initializable {
    private Car car;
    private CarIncident incident;
    private CarRepairShop repairShop;

    @FXML
    private TextField CarReapirShop;

    @FXML
    private Label MarkLabel;

    @FXML
    private TextField Millage;

    @FXML
    private Button add;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TextField description;

    @FXML
    private TextField cost;

    @FXML
    private Button endProcces;

    @FXML
    private ComboBox<String> name;

    @FXML
    void createNew(ActionEvent event) {
        car = CarHolder.getInstance().getCar();
        car.addCarIncident(addIncident());
        CarHolder.getInstance().setCar(car);
        Stage stage = (Stage) endProcces.getScene().getWindow();
        stage.close();



    }

    @FXML
    void closeAll(ActionEvent event){
        Stage stage = (Stage) endProcces.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    name.getItems().addAll("Wymiana oleju", "Przegląd auta");
    }

    CarIncident addIncident(){
        try {
            if (name.getValue().equals("Wymiana oleju")) {
                incident = OilChange.builder()
                        //.name(name.getValue())
                        .date(dataPicker.getValue())
                        .description(description.getText())
                        .millage(Integer.parseInt(Millage.getText()))
                        .carRepairShop(CarReapirShop.getText())
                        .cost(Integer.parseInt(cost.getText()))
                        .build();
            } else if (name.getValue().equals("Przegląd auta")) {
                incident = CarReview.builder()
                        //.name(name.getValue())
                        .date(dataPicker.getValue())
                        .description(description.getText())
                        .millage(Integer.parseInt(Millage.getText()))
                        .carRepairShop(CarReapirShop.getText())
                        .cost(Integer.parseInt(cost.getText()))
                        .build();
            } else {
                incident = OtherCarIncidents.builder()
                        .name(name.getValue())
                        .date(dataPicker.getValue())
                        .description(description.getText())
                        .millage(Integer.parseInt(Millage.getText()))
                        .carRepairShop(CarReapirShop.getText())
                        .cost(Integer.parseInt(cost.getText()))
                        .build();
            }
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błędne dane");
            alert.setContentText("Wpisałeś błędne dane, popraw je aby kontynuować!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    return incident;
    }

}
