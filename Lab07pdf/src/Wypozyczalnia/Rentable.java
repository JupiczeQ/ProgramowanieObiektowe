package Wypozyczalnia;

public interface Rentable {
    double calculateRentalCost(int hours);
    void rent();
    void returnVehicle();
    boolean isRented();
}
