import java.util.Scanner;

public class Parser {

    public int parseInputFromUser(String command) {
        if (command.equals("list")) {
            return 1;
        } else if (command.contains("mark")) {
            return 2;
        }
        else if (command.contains("unmark")) {
            return 3;
        }
        else if (command.contains("todo")) {
            return 4;
        }
        else if (command.contains("deadline")) {
            return 5;
        }
        else if (command.contains("event")) {
            return 6;
        }
        else if (command.contains("bye")) {
            return 0;
        }
        else {
            return -1;
        }
    }

    public void scanInputs() {
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            String input = inputScanner.nextLine();
            int command = parseInputFromUser(input);
            if (command == 0) {
                break;
            } else {
                System.out.println(command);
            }
        }
    }

}
