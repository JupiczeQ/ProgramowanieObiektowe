package Task;

import RunInput.Input;

import java.util.Random;

public class Tasks {

    Input input = new Input();
    public void zad1()
    {
        System.out.println("Podaj liczbe osob w grupie: ");
        int n = input.InputInt();
        while(n<0)
        {
            System.out.println("Grupa musi skladac sie z przynajmniej jednej osoby");
            n = input.InputInt();
        }
        float wyn = 0;
        int il = 0;
        while(il<n) {
            System.out.println("Podaj poprawna ilosc punktow [0,10] (podano " + il + " z " + n + " wynikow)");
            int pkt = input.InputInt();
            if(pkt<0 || pkt>10)
            {
                System.out.println("Liczba punktow nie nalezy do wymaganego zakresu.");
                continue;
            }
            wyn+=pkt;
            il++;
        }
        wyn/=n;
        System.out.println("Srednia liczba punktow to: " + wyn);
    }

    public void zad2(){
        int iluj = 0;
        int ildod = 0;
        float sumdod = 0;
        float sumuj = 0;
        float temp = 0;
        System.out.println("Podaj teraz 10 liczb: ");
        for(int i=0; i<10; i++)
        {
            temp = input.InputFloat();
            if(temp<0) {
                iluj++;
                sumuj+=temp;
            } else if (temp>0) {
                ildod++;
                sumdod+=temp;
            }
        }
        System.out.println("Ilosc liczb dodatnich: " + ildod);
        System.out.println("Suma liczb dodatnich: " + sumdod);
        System.out.println("Ilosc liczb ujemnych: " + iluj);
        System.out.println("Suma liczb ujemnych: " + sumuj);
    }

    public void zad3(){
        System.out.println("Podaj ilosc liczb: ");
        int il = input.InputInt();
        int sum = 0;
        System.out.println("Podaj teraz " + il + " liczb: ");
        for(int i=0; i<il; i++)
        {
            int temp = input.InputInt();
            if(temp%2==0)
                sum+=temp;
        }
        System.out.println("Suma liczb parzystych: " + sum);
    }

    public void zad4() {
        Random rand = new Random();
        System.out.println("Podaj ilosc liczb: ");
        int il = input.InputInt();
        int sum = 0;
        for(int i=1; i<=il; i++)
        {
            int los = rand.nextInt(56)-10;
            System.out.println("Wylosowana liczba (" + i + ") to: " + los);
            if(los%2==0)
                sum+=los;
        }
        System.out.println("Suma liczba parzystych: " + sum);
    }

    public void zad5() {
        System.out.println("Podaj slowo: ");
        String a = input.InputString();
        for(int i = 0; i<a.length(); i++)
        {
            if(a.charAt(i)!=a.charAt(a.length()-1-i))
            {
                System.out.println("Słowo " + a + " nie jest palindromem");
                return;
            }
        }
        System.out.println("Słowo " + a + " jest palindromem.");
    }
}
