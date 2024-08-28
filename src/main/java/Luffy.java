import java.util.Scanner;

public class Luffy {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Luffy");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {

            Scanner echo = new Scanner(System.in);
            String echoPhrase = echo.nextLine();

            if (echoPhrase.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again!");
                System.out.println("____________________________________________________________");
                return;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(echoPhrase);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
