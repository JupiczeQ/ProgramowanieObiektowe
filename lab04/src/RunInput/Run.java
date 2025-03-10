package RunInput;

import Task.Tasks;
import java.util.Scanner;

public class Run {

    public void RunTask(){
        //obiekt klas
        Tasks tasks = new Tasks();
        Input input = new Input();

        System.out.println("Laboratorium 1\n");
        System.out.println("Zadanie 1");
        System.out.println("Zadanie 2");
        System.out.println("Zadanie 3");
        System.out.println("Zadanie 4");
        System.out.println("Zadanie 5");
        System.out.println("Zadanie 6");
        System.out.println("Zadanie 8");
        System.out.println("Wybierz zadananie:");

        int wybor = input.InputInt();
        switch (wybor){
            case 1:
                System.out.println(tasks.DataUser()); break;
            // kolejne case ....
            default: System.out.println("Nie ma takiego zadania");

        }
    }

}
