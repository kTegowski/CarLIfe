package model.CarType;



import lombok.Getter;
import model.Incident.CarIncident;
import model.CarRoutine;
import model.FuelType;
import model.exportClass;

import java.util.*;

@Getter
public abstract class Car implements exportClass {

    private String carBrand;
    private String carModel;
    private int productionYear;
    private String vinNumber;
    private String registrationNumber;
    private BodyType bodyType;
    private FuelType fuelType;
    private int engineCapacity;
    private final ArrayList<CarIncident> incidentTable = new ArrayList<CarIncident>();
    private CarRoutine carRoutine;

    public void addCarIncident(CarIncident incident){
        getIncidentTable().add(incident);
        sortIncidentTable();
    }
    public ArrayList<CarIncident> getIncidentTable(){return incidentTable;}

    public void sortIncidentTable(){
        Comparator<CarIncident> compareByData = Comparator.comparing(CarIncident::getDate);
        getIncidentTable().sort(compareByData);

    }

}

