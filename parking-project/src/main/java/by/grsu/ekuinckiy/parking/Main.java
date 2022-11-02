package by.grsu.ekuinckiy.parking;

import by.grsu.ekuinckiy.parking.db.model.Car;

public class Main {
    public static void main (String[] args){
        Car car = new Car();
        car.setId(1);
        car.setModelId(2);
        car.setOwnerId(1);
        car.setVin("QWERtyu76");
        System.out.println(car);
    }
}
