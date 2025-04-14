import RunInput.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class zad3 {
    Input input = new Input();
    Map<String, Double> karta = new HashMap<>();
    public zad3(){
        while(true){
            System.out.println("Co chciałbyś zrobić? ");
            System.out.println("1. dodaj danie do karty");
            System.out.println("2. wyswietl karte dan");
            System.out.println("3. usun danie z karty");
            System.out.println("4. oblicz rachunek");
            System.out.println("5. wyjscie");
            int opt = input.inputInt();
            input.inputLine();
            switch (opt){
                case 1:
                    addDish();
                    break;
                case 2:
                    showMenu();
                    break;
                case 3:
                    delDish();
                    break;
                case 4:
                    calcBill();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Nieprawidlowa opcja.");
            }
        }
    }

    public void addDish(){
        System.out.println("Podaj nazwe dania: ");
        String name = input.inputLine();
        System.out.println("Podaj jego cene: ");
        Double price = input.inputDouble();
        name = name.trim();
        if(karta.containsKey(name)){
            System.out.println("Danie o podanej nazwie znajduje sie juz w karcie");
        }else{
            karta.put(name,price);
            System.out.println("Danie dodane do karty");
        }
    }

    public void showMenu(){
        System.out.println("====== MENU ======");
        for(String s : karta.keySet()){
            System.out.println(s + " - " + karta.get(s));
        }
    }

    public void delDish(){
        System.out.println("Podaj nazwe dania: ");
        String name = input.inputLine();
        if(karta.containsKey(name)){
            karta.remove(name);
            System.out.println("Danie usuniete z karty");
        }else{
            System.out.println("Danie o podanej nazwie nie znajduje sie w karcie");
        }
    }

    public void calcBill(){
        System.out.println("Podaj nazwy dań oddzielone spacją");
        String opt = input.inputLine();
        String[] zamowienie = opt.split("\\s+");
        double w = 0;
        List<String> dania = new ArrayList<>();
        for(String danie : zamowienie){
            if(karta.containsKey(danie)){
                w+=karta.get(danie);
                dania.add(danie);
            }else{
                System.out.println("Nie ma dania " + danie);
            }
        }
        System.out.println("Rachunek za " + dania + ": " + w + "zł");
    }
}
