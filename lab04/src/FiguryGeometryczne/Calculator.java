package FiguryGeometryczne;

import RunInput.Input;

public class Calculator {
    public void RunCalc(){
        Input input = new Input();

        System.out.println("Wybierz figurę\n");
        System.out.println("1.Koło");
        System.out.println("2.Kwadrat");
        System.out.println("3.Prostokąt");
        System.out.println("4.Sześcian");
        System.out.println("5.Prostopadłościan");
        System.out.println("6.Kula");
        System.out.println("7.Stożek");

        int wybor = input.InputInt();
        switch (wybor){
            case 1:
                System.out.println("Podaj promień: ");
                double r = input.InputDouble();
                Circle circle = new Circle(r);
                circle.show();
                break;
            case 2:
                System.out.println("Podaj dł. boku: ");
                double a = input.InputDouble();
                Square square = new Square(a);
                square.show();
                break;
            case 3:
                System.out.println("Podaj dł. pierwszego bok: ");
                a = input.InputDouble();
                System.out.println("Podaj dł. drugiego boku: ");
                double b = input.InputDouble();
                Rectangle rectangle = new Rectangle(a,b);
                rectangle.show();
                break;

            default: System.out.println("Nie ma takiego zadania");

        }
    }

}
