package Wypozyczalnia;

import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public void rentVehicle(String id){
        for(Vehicle v : vehicles){
            if(v.id.equals(id)){
                v.rent();
                return;
            }
        }
        System.out.println("Pojazd nie znaleziony.");
    }

    public void returnVehicle(String id){
        for(Vehicle v : vehicles){
            if(v.id.equals(id)){
                v.returnVehicle();
                return;
            }
        }
        System.out.println("Pojazd nie znaleziony.");
    }

    public void calculateCost(String id, int hours){
        for(Vehicle v : vehicles){
            if(v.id.equals(id)){
                System.out.println("Koszt wypozyczenia: " + id + " na " + hours + " godzin to: " + v.calculateRentalCost(hours));
                return;
            }
        }
        System.out.println("Pojazd nie znaleziony.");
    }

    public void printAvailableVehicles(){
        for(Vehicle v : vehicles){
            System.out.println(v.id + ", koszt na godzine: " + v.calculateRentalCost(1) + (v.rented ? ", niedostepny" : ", dostepny"));
        }
    }
}
