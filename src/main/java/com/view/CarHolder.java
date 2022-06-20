package com.view;

import model.CarType.Car;

public final class CarHolder {
    private Car car;
    private final static CarHolder INSTANCE = new CarHolder();

    private CarHolder(){};

    public static CarHolder getInstance(){
        return INSTANCE;
    }

    public void setCar(Car car){
        this.car = car;
    }

    public Car getCar(){
        return car;
    }
}
