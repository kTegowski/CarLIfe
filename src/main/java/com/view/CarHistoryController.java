package com.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import model.CarType.Car;
import model.Incident.CarIncident;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CarHistoryController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Thread thread;
    private Car car;

    @FXML
    private Label BodyTypeLabel;

    @FXML
    private Label OilLabel;

    @FXML
    static CategoryAxis xAxis = new CategoryAxis();

    @FXML
    static NumberAxis yAxis = new NumberAxis();

    @FXML
    public final XYChart.Series series= new XYChart.Series();

    @FXML
    private LineChart<String, Number> Chart = new LineChart<String, Number>(xAxis, yAxis);

    @FXML
    private Label EngineCapacityLabel;

    @FXML
    private Label FUelTypeLabel;

    @FXML
    private Button addNew;

    @FXML
    private Label MarkLabel;

    @FXML
    private Label ModelLabel;

    @FXML
    private Label ProductionLabel;

    @FXML
    private Label RegistrationLabel;

    @FXML
    private Label VINLabel;

    @FXML
    private Label PrzegladLast;

    @FXML
    private Label FirstEvent;

    @FXML
    private Label SecondEvent;

    @FXML
    private Label ThirdEvent;

    @FXML
    private Label fourthEvent;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        car = CarHolder.getInstance().getCar();
        setLabel(car);
        Chart.getData().add(initializeLineChart());
        setLastone();
        setLastEvent();
    }

    public void setLabel(Car car) {
        RegistrationLabel.setText(car.getRegistrationNumber());
        VINLabel.setText(car.getVinNumber());
        ProductionLabel.setText(String.valueOf(car.getProductionYear()));
        ModelLabel.setText(car.getCarModel());
        MarkLabel.setText(car.getCarBrand());
        FUelTypeLabel.setText(car.getFuelType().toString());
        EngineCapacityLabel.setText(String.valueOf(car.getEngineCapacity()));
        BodyTypeLabel.setText(car.getBodyType().toString());
    }

    @FXML
    void addNewAction(ActionEvent event) throws Exception {
        start();

    }

    @FXML
    void saveToJson(ActionEvent event) {
        model.SaveToJSON.run();
    }

    void setLastEvent() {
        int size = car.getIncidentTable().size();
        if (size > 0) {
            if (size > 4) {
                FirstEvent.setText(car.getIncidentTable().get(size - 4).getDate()+ " " + car.getIncidentTable().get(size - 4).getName());
                SecondEvent.setText(car.getIncidentTable().get(size - 3).getDate() + " "+ car.getIncidentTable().get(size - 3).getName());
                ThirdEvent.setText(car.getIncidentTable().get(size - 2).getDate() + " "+ car.getIncidentTable().get(size - 2).getName());
                fourthEvent.setText(car.getIncidentTable().get(size-1).getDate()+ " " + car.getIncidentTable().get(size - 1).getName());
            }
            else{
                switch (size){
                    case 3:
                        FirstEvent.setText(car.getIncidentTable().get(size - 3).getDate()+ " " + car.getIncidentTable().get(size - 3).getName());
                        SecondEvent.setText(car.getIncidentTable().get(size - 2).getDate() + " "+ car.getIncidentTable().get(size - 2).getName());
                        ThirdEvent.setText(car.getIncidentTable().get(size - 1).getDate()+ " " + car.getIncidentTable().get(size - 1).getName());
                        break;
                    case 2:
                        FirstEvent.setText(car.getIncidentTable().get(size - 2).getDate() + " "+ car.getIncidentTable().get(size - 2).getName());
                        SecondEvent.setText(car.getIncidentTable().get(size - 1).getDate()+ " " + car.getIncidentTable().get(size - 1).getName());
                        break;
                    case 1:
                        FirstEvent.setText(car.getIncidentTable().get(size - 1).getDate()+ " " + car.getIncidentTable().get(size - 1).getName());
                    case 0:
                        FirstEvent.setText("Dodaj swoje pierwsze wydarzenie");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    void setLastone(){
        String lastOil ="";
        String lastReview="";
        for(CarIncident olc: car.getIncidentTable()){
            if(olc.getName().equals("Oil Change")) {
                lastOil = olc.getDate().toString();
            }
            else if (olc.getName().equals("Car Review")){
                lastReview = olc.getDate().toString();
            }
        }
        OilLabel.setText(lastOil);
        PrzegladLast.setText(lastReview);
    }


    XYChart.Series initializeLineChart() {
        series.setName("Przebieg");
        car = CarHolder.getInstance().getCar();
        car.sortIncidentTable();
        for (CarIncident incident : car.getIncidentTable()) {
            series.getData().add(new XYChart.Data(incident.getDate().toString(), incident.getMillage()));
        }

        return series;
    }

    public void updateChart(){
        series.getData().clear();
        Car car = CarHolder.getInstance().getCar();
        car.sortIncidentTable();
        for(CarIncident incident: car.getIncidentTable()){
            series.getData().add(new XYChart.Data(incident.getDate().toString(),incident.getMillage()));
        }
        Chart.getData().add(series);
        setLastone();
        setLastEvent();
    }

    void runNewIncidentWindow() throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addNewIncident.fxml")));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Dodaj nowe wydarzenie!");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Ikona.png")));
        stage.show();
    }

    public void start() throws Exception {
        Runnable task = () -> {
            Platform.runLater(() -> {
                try {
                    runNewIncidentWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        };
        thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }
}