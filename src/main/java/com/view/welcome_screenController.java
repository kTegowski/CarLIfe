package com.view;

import com.view.CarHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CarType.Car;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class welcome_screenController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem aboutApp;

    @FXML
    private ImageView imageViewerLogo;

    @FXML
    private Button loadCarButton;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button newCarButton;

    @FXML
    private MenuItem Close;

    @FXML
    private MenuItem newItem;

    @FXML
    private MenuItem openItem;

    @FXML
    void LoadButtonPressed(ActionEvent event) throws IOException, JSONException, ParseException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        Car carFromFile = model.ReadFromJSON.run(file);
        CarHolder.getInstance().setCar(carFromFile);
        if(CarHolder.getInstance().getCar() != null){
            switchToMainWindow();}
    }


    void switchToMainWindow() throws IOException {
        root = FXMLLoader.load(getClass().getResource("car_history_screen.fxml"));
        stage = (Stage) ((Node)loadCarButton).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToScene2(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("newcarview.fxml"));
        //stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage = (Stage) ((Node)newCarButton).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void AboutApp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacje o aplikacji");
        alert.setHeaderText(null);
        alert.setContentText("Aplikacja symuluje klasyczną ksiązkę serwisową auta! \nDesigned by: Konrad Tęgowski");
        alert.showAndWait();
    }

    @FXML
    void Quit(ActionEvent event){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
