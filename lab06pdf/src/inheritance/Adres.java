package inheritance;

public class Adres {
    String ulica, nrDomu, nrMieszkania;
    String kodPocztowy, miasto;

    public Adres(String ulica, String nrDomu, String nrMieszkania, String kodPocztowy, String miasto) {
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.nrMieszkania = nrMieszkania;
        this.kodPocztowy = kodPocztowy;
        this.miasto = miasto;
    }

    public String viewAdress(){
        return ("Adres: " + ulica + " " + nrDomu + "/" + nrMieszkania + " " + miasto + ", " + kodPocztowy);
    }
}
