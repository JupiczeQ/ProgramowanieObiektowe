package Utils;

import java.util.Scanner;

public class Input {

    Scanner scanner = new Scanner(System.in);
    //metoda do wczytywania typu int
    public int InputInt(){
        return scanner.nextInt();
    }
    //metoda do wczytywania typu float
    public float InputFloat(){
        return scanner.nextFloat();
    }
    //metoda do wczytywania typu string
    public String InputString(){
        return scanner.next();
    }
    //metoda do wczytywania typu string ze spacjami
    public String InputLine(){
        String line;
        do{
            line = scanner.nextLine();
        }while (line.trim().isEmpty());
        return line;
    }
    //metoda do czyszczenia bufora
    public void clear(){
        if(scanner.hasNextLine()){
            scanner.nextLine();
        }
    }
}
