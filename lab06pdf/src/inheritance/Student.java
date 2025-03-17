package inheritance;

public class Student extends Osoba{
    String nrAlbumu, kierunek, rokStudiow;

    public Student(String fullName, Adres adres, String nrAlbumu, String kierunek, String rokStudiow) {
        super(fullName, adres);
        this.nrAlbumu = nrAlbumu;
        this.kierunek = kierunek;
        this.rokStudiow = rokStudiow;
    }

    public void view(){
        System.out.println("Student: ");
        super.view();
        System.out.println("nr albumu: " + nrAlbumu + " kierunek: " + kierunek + " rok studiów: " + rokStudiow) ;
    }
}
