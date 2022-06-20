package model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToJSON {

public static void run(){
    Object mapper = new ObjectMapper();
    try {
        String json = ((ObjectMapper) mapper).writeValueAsString(com.view.CarHolder.getInstance().getCar());
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(new Stage());
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(json);

        writer.close();
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
