import Figures.*;
import RunInput.Run;
import inheritance.Adres;
import inheritance.Osoba;
import inheritance.Student;

public class Main {
    public static void main(String[] args) {

        //obiekt klasy run
//        Run run = new Run();
//        run.RunTask();
//        Student student = new Student("Froggo Baggins",
//                new Adres("Kiepowicza","4", "20", "02-137", "Mjanma"),
//                "111222", "Mykologia", "1");
//        student.view();
//
//        Adres[] adresy = {
//                new Adres("Kiepowicza","4", "20", "02-137", "Mjanma"),
//                new Adres("Kiepowicza","3", "10", "03-347", "MammaMia"),
//                new Adres("Kiepowicza","2", "5", "04-257", "Motywacja")
//        };
//
//        Osoba[] osoby = {
//                new Osoba("Jan Nowak", adresy[0]),
//                new Osoba("Janina Nowak", adresy[1]),
//                new Osoba("Janusz Nowak", adresy[2])
//        };
//
//        Student[] studenci = {
//                new Student(osoby[0].fullName, osoby[0].adres, "213465", "Kolejny", "1"),
//                new Student(osoby[1].fullName, osoby[1].adres, "212351", "Kolejny1", "2"),
//                new Student(osoby[2].fullName, osoby[2].adres, "213365", "Kolejny2", "3")
//        };
//
//        for(Student item : studenci){
//            item.view();
//            System.out.println();
//        }
        Point point = new Point(1,2);
        point.przesun(1,-1);
        point.opis();
        point.zeruj();
        point.opis();

        Point point1 = new Point(4.999999,0);
        Okrag okrag = new Okrag(point,5);
        System.out.println(okrag.wSrodku(point1));

        Prostokat prostokat = new Prostokat("bialy",5,10, point);
        prostokat.przesun(3,5);
        System.out.println(prostokat.opis());

        Kwadrat kwadrat = new Kwadrat("bialy", 5, point);
        System.out.println(kwadrat.opis());

        Trojkat trojkat = new Trojkat("czarny",5,5);
        System.out.println(trojkat.opis());
    }

}
