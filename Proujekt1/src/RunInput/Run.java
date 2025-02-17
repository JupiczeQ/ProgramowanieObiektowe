package RunInput;

import java.util.Scanner;

import RunInput.Input;

public class Run {
    public void RunTask(){
        Task.Tasks tasks = new Task.Tasks();
        Input input = new Input();
        System.out.println("Laboratiorium 1\n");
        System.out.println("Zadanie 1\n");
        System.out.println("Zadanie 2\n");
        System.out.println("Zadanie 3\n");
        System.out.println("Zadanie 4\n");
        System.out.println("Zadanie 5\n");
        System.out.println("Zadanie 6\n");
        System.out.println("Zadanie 7\n");
        System.out.println("Zadanie 8\n");
        System.out.println("Wybierz zadanie: ");

        int wybor = input.InputInt();
        switch(wybor){
            case 1:
                System.out.println(tasks.DataUser()); break;
            case 2:
                System.out.println("Podaj liczbe a: ");
                float a = input.InputFloat();
                System.out.println("Podaj liczbe b: ");
                float b = input.InputFloat();
                tasks.MathOperations(a,b);
                break;
            default:
                System.out.println("Nie znaleziono zadania");
        }
    }
}
