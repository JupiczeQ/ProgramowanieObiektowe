package Wypozyczalnia.VehicleTypes;

import Wypozyczalnia.Vehicle;

public class Car extends Vehicle {
    public Car(String id){
        super(id);
    }
    @Override
    public double calculateRentalCost(int hours) {
        return hours * 20.0;
    }
}
