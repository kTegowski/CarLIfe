package model.Incident;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public abstract class CarIncident implements Comparable<CarIncident>{
    private String name;
    private LocalDate date;
    private int millage;
    private int cost;
    private String carRepairShop;
    private String description;
}

