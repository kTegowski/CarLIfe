package model;

public class CarRoutine {
    private final int oilInterval;
    private final int carReview;

    private CarRoutine(CarRoutineBuilder builder){
        oilInterval = builder.oilInterval;
        carReview = builder.carReview;
    }

    public static class CarRoutineBuilder {
        private int oilInterval;
        private int carReview;

        public CarRoutineBuilder oilInterval(int oilInterval){
            this.oilInterval = oilInterval;
            return this;
        }

        public CarRoutineBuilder carReview(int carReview){
            this.carReview = carReview;
            return this;
        }

        public CarRoutine build(){
            CarRoutine carRoutine = new CarRoutine(this);
            return carRoutine;
        }

    }
}
