package model.CarType;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import model.Incident.CarIncident;
import model.CarRoutine;
import model.FuelType;

import java.util.ArrayDeque;
import java.util.ArrayList;

@Builder
@Data
@Getter
public class VanCar extends Car {
    @Builder.Default
    private BodyType bodyType = BodyType.VAN;
    private String carBrand;
    private String carModel;
    private int productionYear;
    private String vinNumber;
    private String registrationNumber;
    private FuelType fuelType;
    private int engineCapacity;
    @Builder.Default
    private ArrayList<CarIncident> incidentTable = new ArrayList<CarIncident>();
    private CarRoutine carRoutine;
}
