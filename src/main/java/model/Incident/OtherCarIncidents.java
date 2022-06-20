package model.Incident;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.Incident.CarIncident;

import java.time.LocalDate;
@Builder
@Getter
@Setter
public class OtherCarIncidents extends CarIncident {
    private String name;
    private LocalDate date;
    private int millage;
    private int cost;
    private String  carRepairShop;
    private String description;

    @Override
    public int compareTo(CarIncident o) {
        return getDate().compareTo(o.getDate());
    }
}
