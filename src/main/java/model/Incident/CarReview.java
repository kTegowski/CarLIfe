package model.Incident;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.Incident.CarIncident;

import java.time.LocalDate;


@Builder
@Setter
@Getter
public class CarReview extends CarIncident {
    @Builder.Default
    private String name = "Car Review";
    private LocalDate date;
    private int millage;
    private int cost;
    private String carRepairShop;
    private String description;

    @Override
    public int compareTo(CarIncident o) {
        return getDate().compareTo(o.getDate());
    }
}
