package RunInput;

import java.util.Scanner;

public class Input {
    Scanner scan = new Scanner(System.in);
    public int InputInt()
    {
        return scan.nextInt();
    }
    public float InputFloat()
    {
        return scan.nextFloat();
    }
    public String InputString(){
        return scan.next();
    }
}