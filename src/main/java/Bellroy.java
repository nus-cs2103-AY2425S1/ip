import java.util.Scanner;

public class Bellroy {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String message = "____________________________________________________________\n" +
                " Hello! I'm Bellroy\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String userInput = "";

        System.out.println(message);
        while (true) {

            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");

                break;
            }

            System.out.println("____________________________________________________________\n" +
                    userInput + "\n" +
                    "    ____________________________________________________________");
        }

        scanner.close();

    }
}
