import java.util.Objects;
import java.util.Scanner;

public class Cloudy {
    public static void main(String[] args) {
        Scanner echo = new Scanner(System.in);

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
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(userInput);
                System.out.println("____________________________________________________________");

                userInput = echo.nextLine();
            }
        }



    }
}
