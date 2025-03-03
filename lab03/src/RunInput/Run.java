package RunInput;

import Task.Tasks;

public class Run {

    public void RunTask(){
        //obiekt klas
        Tasks tasks = new Tasks();
        Input input = new Input();

        System.out.println("Laboratorium 2\n");
        System.out.println("Zadanie 1");
        System.out.println("Zadanie 2");
        System.out.println("Zadanie 3");
        System.out.println("Zadanie 4");
        System.out.println("Zadanie 5");
        System.out.println("Wybierz zadanie:");

        int wybor = input.InputInt();
        switch (wybor){
            case 1:
                tasks.zad1();
                break;
            case 2:
                tasks.zad2();
                break;
            case 3:
                tasks.zad3();
                break;
            case 4:
                tasks.zad4();
                break;
            case 5:
                tasks.zad5();
                break;
            default:
                System.out.println("Nie ma takiego zadania");

        }
    }

}
