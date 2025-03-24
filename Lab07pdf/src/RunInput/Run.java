package RunInput;

import java.util.Scanner;

import RunInput.Input;
import Wypozyczalnia.RentalSystem;
import Wypozyczalnia.VehicleTypes.Bicycle;
import Wypozyczalnia.VehicleTypes.Car;
import Wypozyczalnia.VehicleTypes.Scooter;

public class Run {
    public void RunTask(){
        RentalSystem rentalSystem = new RentalSystem();
        rentalSystem.addVehicle(new Car("C1"));
        rentalSystem.addVehicle(new Bicycle("B1"));
        rentalSystem.addVehicle(new Scooter("S1"));

        Input input = new Input();

        boolean isRunning = true;

        while(isRunning){
            System.out.println("\n *********** Menu *********** ");
            System.out.println("1. Wyświetl dostepne pojazdy");
            System.out.println("2. Wypozycz pojazd");
            System.out.println("3. Zwroc pojazd");
            System.out.println("4. Policz koszt wypozyczenia");
            System.out.println("5. Wyjscie");
            System.out.println("Twoj wybor: ");

            int choice = input.InputInt();
            String idChoice;
            switch(choice){
                case 1:
                    rentalSystem.printAvailableVehicles();
                    break;
                case 2:
                    System.out.println("Podaj id pojazdu do wypozyczenia: ");
                    idChoice = input.InputString();
                    rentalSystem.rentVehicle(idChoice);
                    break;
                case 3:
                    System.out.println("Podaj id pojazdu do zwrocenia: ");
                    idChoice = input.InputString();
                    rentalSystem.returnVehicle(idChoice);
                    break;
                case 4:
                    System.out.println("Podaj id pojazdu, ktorego koszt wypozyczenia chcesz policzyc: ");
                    idChoice = input.InputString();
                    System.out.println("Podaj ilosc godzin na jaka chcesz ten pojazd wypozyczyc: ");
                    int h = input.InputInt();
                    rentalSystem.calculateCost(idChoice,h);
                    break;
                case 5:
                    isRunning=false;
                    break;
                default:
                    System.out.println("Błedne dane... Spróbuj jeszcze raz.");
            }
        }
    }
}