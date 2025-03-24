package Wypozyczalnia.VehicleTypes;

import Wypozyczalnia.Vehicle;

public class Scooter extends Vehicle {
    public Scooter(String id) {
        super(id);
    }

    @Override
    public double calculateRentalCost(int hours) {
        return hours * 10.0;
    }
}
