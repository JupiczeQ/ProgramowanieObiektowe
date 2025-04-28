import com.sun.security.auth.UnixNumericUserPrincipal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO dao = new UserDAO();

        printBanner("ADMIN", "=");
    }

    private static void adminMenu(UserDAO dao, Scanner scanner) throws Exception{
        while(true){

        }
    }

    private static void printBanner(String title, String mark){
        System.out.println("\n" + mark.repeat(40));
        System.out.print(" ".repeat((40-title.length())/2)+ title);
        System.out.println("\n" + mark.repeat(40));
    }
}