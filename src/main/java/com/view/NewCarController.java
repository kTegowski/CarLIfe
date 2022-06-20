package com.view;

import com.view.CarHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
import model.CarType.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewCarController {

    private static Car car;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField VINNumberTextArea;

    @FXML
    private ComboBox<BodyType> bodyTypeComboBox;

    @FXML
    private TextField carBrandTextArea;

    @FXML
    private TextField carModelTextArea;

    @FXML
    private TextField engineCapacityTextArea;

    @FXML
    private ComboBox<FuelType> fuelTypeComboBox;

    @FXML
    private TextField procutionYearTextArea;

    @FXML
    private TextField registrationNumberTextArea;

    @FXML
    void initialize() {
        fuelTypeComboBox.getItems().setAll(FuelType.values());
        bodyTypeComboBox.getItems().setAll(BodyType.values());
    }

    @FXML
    void comeBack(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welcome_screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    Car buildCar(BodyType bodyType) {
        Car car = null;
        try{
        switch (bodyType) {//dopisz tutaj mape
            case COUPE:
                car = CoupeCar.builder()
                        .carBrand(carBrandTextArea.getText())
                        .carModel(carModelTextArea.getText())
                        .productionYear(Integer.parseInt(procutionYearTextArea.getText()))
                        .vinNumber(VINNumberTextArea.getText())
                        .registrationNumber(registrationNumberTextArea.getText())
                        .fuelType(fuelTypeComboBox.getValue())
                        .engineCapacity(Integer.parseInt(engineCapacityTextArea.getText()))
                        .build();
                break;
            case SUV:
                car = SUVCar.builder()
                        .carBrand(carBrandTextArea.getText())
                        .carModel(carModelTextArea.getText())
                        .productionYear(Integer.parseInt(procutionYearTextArea.getText()))
                        .vinNumber(VINNumberTextArea.getText())
                        .registrationNumber(registrationNumberTextArea.getText())
                        .fuelType(fuelTypeComboBox.getValue())
                        .engineCapacity(Integer.parseInt(engineCapacityTextArea.getText()))
                        .build();
                break;
            case VAN:
                car = VanCar.builder()
                        .carBrand(carBrandTextArea.getText())
                        .carModel(carModelTextArea.getText())
                        .productionYear(Integer.parseInt(procutionYearTextArea.getText()))
                        .vinNumber(VINNumberTextArea.getText())
                        .registrationNumber(registrationNumberTextArea.getText())
                        .fuelType(fuelTypeComboBox.getValue())
                        .engineCapacity(Integer.parseInt(engineCapacityTextArea.getText()))
                        .build();
                break;
            case COMBI:
                car = CombiCar.builder()
                        .carBrand(carBrandTextArea.getText())
                        .carModel(carModelTextArea.getText())
                        .productionYear(Integer.parseInt(procutionYearTextArea.getText()))
                        .vinNumber(VINNumberTextArea.getText())
                        .registrationNumber(registrationNumberTextArea.getText())
                        .fuelType(fuelTypeComboBox.getValue())
                        .engineCapacity(Integer.parseInt(engineCapacityTextArea.getText()))
                        .build();
                break;
            case SEDAN:
                car = SedanCar.builder()
                        .carBrand(carBrandTextArea.getText())
                        .carModel(carModelTextArea.getText())
                        .productionYear(Integer.parseInt(procutionYearTextArea.getText()))
                        .vinNumber(VINNumberTextArea.getText())
                        .registrationNumber(registrationNumberTextArea.getText())
                        .fuelType(fuelTypeComboBox.getValue())
                        .engineCapacity(Integer.parseInt(engineCapacityTextArea.getText()))
                        .build();
                break;
            case TRACK:
                car = TrackCar.builder()
                        .carBrand(carBrandTextArea.getText())
                        .carModel(carModelTextArea.getText())
                        .productionYear(Integer.parseInt(procutionYearTextArea.getText()))
                        .vinNumber(VINNumberTextArea.getText())
                        .registrationNumber(registrationNumberTextArea.getText())
                        .fuelType(fuelTypeComboBox.getValue())
                        .engineCapacity(Integer.parseInt(engineCapacityTextArea.getText()))
                        .build();
                break;
            case CABRIO:
                car = CabrioCar.builder()
                        .carBrand(carBrandTextArea.getText())
                        .carModel(carModelTextArea.getText())
                        .productionYear(Integer.parseInt(procutionYearTextArea.getText()))
                        .vinNumber(VINNumberTextArea.getText())
                        .registrationNumber(registrationNumberTextArea.getText())
                        .fuelType(fuelTypeComboBox.getValue())
                        .engineCapacity(Integer.parseInt(engineCapacityTextArea.getText()))
                        .build();
                break;
            case CROSSOVER:
                car = CrossOverCar.builder()
                        .carBrand(carBrandTextArea.getText())
                        .carModel(carModelTextArea.getText())
                        .productionYear(Integer.parseInt(procutionYearTextArea.getText()))
                        .vinNumber(VINNumberTextArea.getText())
                        .registrationNumber(registrationNumberTextArea.getText())
                        .fuelType(fuelTypeComboBox.getValue())
                        .engineCapacity(Integer.parseInt(engineCapacityTextArea.getText()))
                        .build();
                break;
            case HATCHBACK:
                car = HatchbackCar.builder()
                        .carBrand(carBrandTextArea.getText())
                        .carModel(carModelTextArea.getText())
                        .productionYear(Integer.parseInt(procutionYearTextArea.getText()))
                        .vinNumber(VINNumberTextArea.getText())
                        .registrationNumber(registrationNumberTextArea.getText())
                        .fuelType(fuelTypeComboBox.getValue())
                        .engineCapacity(Integer.parseInt(engineCapacityTextArea.getText()))
                        .build();
                break;
            default:
                break;

        }

        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Błędne dane");
            alert.setContentText("Wpisałeś błędne dane, popraw je aby kontynuować!");
            alert.setHeaderText(null);
            alert.showAndWait();

        }
        return car;
    }

    public void goToPreviousPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welcome_screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Car getCar() {
        return car;
    }

    @FXML
    void nextButtonPressed(ActionEvent event) throws IOException {

        CarHolder holder = CarHolder.getInstance();
        holder.setCar(buildCar(bodyTypeComboBox.getValue()));
        root = FXMLLoader.load(getClass().getResource("car_history_screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}