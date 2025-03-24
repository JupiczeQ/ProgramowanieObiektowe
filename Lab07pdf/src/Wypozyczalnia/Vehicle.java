package Wypozyczalnia;

public abstract class Vehicle implements Rentable{
    protected String id;
    protected boolean rented;

    public Vehicle(String id){
        this.id = id;
        this.rented = false;
    }

    public boolean isRented(){
        return rented;
    }

    public void rent(){
        if(!rented){
            rented = true;
            System.out.println(id + " został wypozyczony.");
        }else{
            System.out.println(id + " jest juz wypozyczony.");
        }
    }

    public void returnVehicle(){
        if(rented){
            rented = false;
            System.out.println(id + " został zwrocony.");
        }else{
            System.out.println(id + " nie byl wypozyczony.");
        }
    }

    public abstract double calculateRentalCost(int hours);
}
