import java.sql.SQLOutput;
import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        String hLine = "____________________________________________________________\n";
        String greeting = "Hello! I'm Bob\nWhat can I do for you?\n";
        String bye = "Bye. Hope to see you again soon!\n";
        Scanner myScanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetingMessage = hLine + greeting + hLine;
        String byeMessage = hLine + bye + hLine;

        // Greets user
        System.out.println(greetingMessage);
        // Await user input
        String userInput = myScanner.nextLine();
        // Loop when user input != "bye"
        while (!userInput.toLowerCase().strip().equals("bye")) {
            System.out.println(hLine + userInput + "\n" + hLine);
            userInput = myScanner.nextLine();
        }
        // User input == "bye"
        System.out.println(byeMessage);
    }
}
