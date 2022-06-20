package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.CarType.*;
import model.Incident.CarIncident;
import model.Incident.CarReview;
import model.Incident.OilChange;
import model.Incident.OtherCarIncidents;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadFromJSON {
    public static Car car;
    String day;
    public static ArrayList<String> list;
    public static Car run(File file) throws IOException, JSONException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream input = new FileInputStream(file);
        String jsonTxt = IOUtils.toString(input, "UTF-8");
        JSONObject json = new JSONObject(jsonTxt);
        buildCar(json);
        JSONArray jsonArray = new JSONArray( json.get("incidentTable").toString());
        list = new ArrayList();
        for(int i =0; i< jsonArray.length();i++){
            String[] parts = jsonArray.get(i).toString().split(",");
            for(int j = 0; j< parts.length; j++){
                String[] parts2 = parts[j].split(";");
                for (int k = 0; k< parts2.length; k++){
                    String[] parts3 = parts2[k].split(":");
                    if(parts3[1] != null){
                    list.add(parts3[1]);}
                }

            }

            car.addCarIncident(addIncident(list));

        }

        return car;

    }
    public static model.Incident.CarIncident addIncident(ArrayList<String> list) throws ParseException {
        CarIncident incident;
        String day;
        String month;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (Integer.parseInt(list.get(3)) > 9){
            day = list.get(3);
        }
        else{
            day = "0" + list.get(3);
        }
        if (Integer.parseInt(list.get(6)) > 9){
             month = list.get(6);
        }
        else{
            month = "0" + list.get(6);
        }
        String date = day + "/" + month + "/" + list.get(2);
        LocalDate localDate = LocalDate.parse(date, formatter);
        if (list.get(12).substring(1, list.get(12).length() - 1).equals("Car Review")) {
             incident = CarReview.builder()
                    .date(localDate)
                    .carRepairShop(list.get(11).substring(1, list.get(11).length() - 1))
                    .description(list.get(13).substring(1, list.get(13).length() - 1))
                    .millage(Integer.parseInt(list.get(14).substring(0,list.get(14).length()-1)))
                    .cost(Integer.parseInt(list.get(10)))
                    .build();
        } else if (list.get(12).substring(1, list.get(12).length() - 1).equals("Oil Change")) {
            incident = OilChange.builder()
                    .date(localDate)
                    .carRepairShop(list.get(11).substring(1, list.get(11).length() - 1))
                    .description(list.get(13).substring(1, list.get(13).length() - 1))
                    .millage(Integer.parseInt(list.get(14).substring(0,list.get(14).length()-1)))
                    .cost(Integer.parseInt(list.get(10)))
                    .build();
        } else {
            incident = OtherCarIncidents.builder()
                    .name(list.get(12).substring(1, list.get(12).length() - 1))
                    .date(localDate)
                    .carRepairShop(list.get(11).substring(1, list.get(11).length() - 1))
                    .description(list.get(13).substring(1, list.get(13).length() - 1))
                    .millage(Integer.parseInt(list.get(14).substring(0,list.get(14).length()-1)))
                    .cost(Integer.parseInt(list.get(10)))
                    .build();
        }
        list.removeAll(list);
        return incident;
    }


    public static void buildCar(JSONObject jsonObject) throws JSONException {
            switch ( jsonObject.get("bodyType").toString()) {
                case "COUPE":
                    car = CoupeCar.builder()
                            .carBrand(jsonObject.get("carBrand").toString())
                            .carModel(jsonObject.get("carModel").toString())
                            .productionYear(Integer.parseInt(jsonObject.get("productionYear").toString()))
                            .vinNumber(jsonObject.get("vinNumber").toString())
                            .registrationNumber(jsonObject.get("registrationNumber").toString())
                            .fuelType(changeToEnum(jsonObject.get("fuelType").toString()))
                            .engineCapacity(Integer.parseInt(jsonObject.get("engineCapacity").toString()))
                            .build();
                    break;
                case "SUV":
                    car = SUVCar.builder()
                            .carBrand(jsonObject.get("carBrand").toString())
                            .carModel(jsonObject.get("carModel").toString())
                            .productionYear(Integer.parseInt(jsonObject.get("productionYear").toString()))
                            .vinNumber(jsonObject.get("vinNumber").toString())
                            .registrationNumber(jsonObject.get("registrationNumber").toString())
                            .fuelType(changeToEnum(jsonObject.get("fuelType").toString()))
                            .engineCapacity(Integer.parseInt(jsonObject.get("engineCapacity").toString()))
                            .build();
                    break;
                case "VAN":
                    car = VanCar.builder()
                            .carBrand(jsonObject.get("carBrand").toString())
                            .carModel(jsonObject.get("carModel").toString())
                            .productionYear(Integer.parseInt(jsonObject.get("productionYear").toString()))
                            .vinNumber(jsonObject.get("vinNumber").toString())
                            .registrationNumber(jsonObject.get("registrationNumber").toString())
                            .fuelType(changeToEnum(jsonObject.get("fuelType").toString()))
                            .engineCapacity(Integer.parseInt(jsonObject.get("engineCapacity").toString()))
                            .build();
                    break;
                case "COMBI":
                    car = CombiCar.builder()
                            .carBrand(jsonObject.get("carBrand").toString())
                            .carModel(jsonObject.get("carModel").toString())
                            .productionYear(Integer.parseInt(jsonObject.get("productionYear").toString()))
                            .vinNumber(jsonObject.get("vinNumber").toString())
                            .registrationNumber(jsonObject.get("registrationNumber").toString())
                            .fuelType(changeToEnum(jsonObject.get("fuelType").toString()))
                            .engineCapacity(Integer.parseInt(jsonObject.get("engineCapacity").toString()))
                            .build();
                    break;
                case "SEDAN":
                    car = SedanCar.builder()
                            .carBrand(jsonObject.get("carBrand").toString())
                            .carModel(jsonObject.get("carModel").toString())
                            .productionYear(Integer.parseInt(jsonObject.get("productionYear").toString()))
                            .vinNumber(jsonObject.get("vinNumber").toString())
                            .registrationNumber(jsonObject.get("registrationNumber").toString())
                            .fuelType(changeToEnum(jsonObject.get("fuelType").toString()))
                            .engineCapacity(Integer.parseInt(jsonObject.get("engineCapacity").toString()))
                            .build();
                    break;
                case "TRACK":
                    car = TrackCar.builder()
                            .carBrand(jsonObject.get("carBrand").toString())
                            .carModel(jsonObject.get("carModel").toString())
                            .productionYear(Integer.parseInt(jsonObject.get("productionYear").toString()))
                            .vinNumber(jsonObject.get("vinNumber").toString())
                            .registrationNumber(jsonObject.get("registrationNumber").toString())
                            .fuelType(changeToEnum(jsonObject.get("fuelType").toString()))
                            .engineCapacity(Integer.parseInt(jsonObject.get("engineCapacity").toString()))
                            .build();
                    break;
                case "CABRIO":
                    car = CabrioCar.builder()
                            .carBrand(jsonObject.get("carBrand").toString())
                            .carModel(jsonObject.get("carModel").toString())
                            .productionYear(Integer.parseInt(jsonObject.get("productionYear").toString()))
                            .vinNumber(jsonObject.get("vinNumber").toString())
                            .registrationNumber(jsonObject.get("registrationNumber").toString())
                            .fuelType(changeToEnum(jsonObject.get("fuelType").toString()))
                            .engineCapacity(Integer.parseInt(jsonObject.get("engineCapacity").toString()))
                            .build();
                    break;
                case "CROSSOVER":
                    car = CrossOverCar.builder()
                            .carBrand(jsonObject.get("carBrand").toString())
                            .carModel(jsonObject.get("carModel").toString())
                            .productionYear(Integer.parseInt(jsonObject.get("productionYear").toString()))
                            .vinNumber(jsonObject.get("vinNumber").toString())
                            .registrationNumber(jsonObject.get("registrationNumber").toString())
                            .fuelType(changeToEnum(jsonObject.get("fuelType").toString()))
                            .engineCapacity(Integer.parseInt(jsonObject.get("engineCapacity").toString()))
                            .build();
                    break;
                case "HATCHBACK":
                    car = HatchbackCar.builder()
                            .carBrand(jsonObject.get("carBrand").toString())
                            .carModel(jsonObject.get("carModel").toString())
                            .productionYear(Integer.parseInt(jsonObject.get("productionYear").toString()))
                            .vinNumber(jsonObject.get("vinNumber").toString())
                            .registrationNumber(jsonObject.get("registrationNumber").toString())
                            .fuelType(changeToEnum(jsonObject.get("fuelType").toString()))
                            .engineCapacity(Integer.parseInt(jsonObject.get("engineCapacity").toString()))
                            .build();
                    break;
                default:
                    break;

            }



    }


    public static model.FuelType changeToEnum(String text) {
        switch (text){
            case ("GASOLINE"):
                return FuelType.GASOLINE;

            case ("DIESEL"):
                return FuelType.DIESEL;

            case ("ELECTRIC"):
                return FuelType.ELECTRIC;

            case ("HYBDRID"):
                return FuelType.HYBRID;

            case ("LPG"):
                return FuelType.LPG;

            case ("HYDROGEN"):
                return FuelType.HYDROGEN;

            default:
                break;
        }
        return null;
    }
}
