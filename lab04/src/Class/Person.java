package Class;

public class Person {
    String firstName, lastName;
    int age;
    String nrAlbumu, kierunek;

    Adres adres;

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public String getNrAlbumu() {return nrAlbumu;}

    public void setNrAlbumu(String nrAlbumu) {this.nrAlbumu = nrAlbumu;}

    public String getKierunek() {return kierunek;}

    public void setKierunek(String kierunek) {this.kierunek = kierunek;}

    public void View(){
        System.out.println("Imię: " + firstName + " Nazwisko: " + lastName + " Kierunek studiów: " + kierunek + " Nr albumu: " + nrAlbumu);
    }
    public String PersonData(){
        return "Witaj: " + firstName + " " + lastName + ", twój wiek: " + age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", nrAlbumu='" + nrAlbumu + '\'' +
                ", kierunek='" + kierunek + '\'' +
                '}';
    }

    public Person(String firstName, String lastName, int age, String nrAlbumu, String kierunek) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.nrAlbumu = nrAlbumu;
        this.kierunek = kierunek;
    }
    public Person(){
        this.firstName = null;
        this.lastName = null;
        this.age = 0;
        this.nrAlbumu = null;
        this.kierunek = null;
    }
}
