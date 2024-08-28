import java.util.ArrayList;
import java.util.Scanner;

public class Luffy {
    public static void main(String[] args) {

        // Variables
        ArrayList<String> textList = new ArrayList<>();

        // Interactions
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Luffy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");


        while (true) {

            Scanner echo = new Scanner(System.in);
            String echoPhrase = echo.nextLine();

            if (echoPhrase.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again!");
                System.out.println("____________________________________________________________");
                return;
            } else if (echoPhrase.equals("list")) {
                for (int i = 0; i < textList.size(); i++) {
                    System.out.println(String.format(" %d. %s", i + 1, textList.get(i)));
                }
                System.out.println("____________________________________________________________");
            } else {
                textList.add(echoPhrase);
                System.out.println("____________________________________________________________");
                System.out.println(String.format(" added: %s",echoPhrase));
                System.out.println("____________________________________________________________");
            }
        }
    }
}
