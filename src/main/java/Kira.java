import java.util.Scanner;

public class Kira {

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        Scanner scanner = new Scanner(System.in);

        String line = "____________________________________________________________\n";

        System.out.println(line +
                " Hello! I'm Kira\n" +
                " What can I do for you?\n" +
                line);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
                break;
            }

            Echo echo = new Echo(userInput);
            echo.output();

        }



    }
}
