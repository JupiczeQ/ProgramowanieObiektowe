package RunInput;

import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);

    public int inputInt(){
        return scanner.nextInt();
    }

    public float inputFloat(){
        return scanner.nextFloat();
    }

    public double inputDouble(){
        return scanner.nextDouble();
    }

    public String inputString(){
        return scanner.next();
    }

    public String inputLine(){
        return scanner.nextLine();
    }
}
