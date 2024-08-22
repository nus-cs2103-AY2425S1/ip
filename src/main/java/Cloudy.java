import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Cloudy {
    public static void main(String[] args) {
        Scanner echo = new Scanner(System.in);
        ArrayList<String> userList = new ArrayList<String>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Cloudy.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String userInput = echo.nextLine();

        while (true) {
            if (Objects.equals(userInput, "bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (Objects.equals(userInput, "list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println((i + 1) + ". " + userList.get(i));
                }
                System.out.println("____________________________________________________________");
                userInput = echo.nextLine();
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                userList.add(userInput);
                System.out.println("____________________________________________________________");


                userInput = echo.nextLine();
            }
        }



    }
}
