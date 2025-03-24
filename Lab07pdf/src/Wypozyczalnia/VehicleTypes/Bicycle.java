package Wypozyczalnia.VehicleTypes;

import Wypozyczalnia.Vehicle;

public class Bicycle extends Vehicle {
    public Bicycle(String id){
        super(id);
    }
    @Override
    public double calculateRentalCost(int hours){
        return hours * 5.0;
    }
}
