package joe;
import java.util.Scanner;

public class Parser {
    private Controller controller;
    private Scanner scanner;

    public Parser (Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }
    
    public boolean parse() {
        String input = scanner.nextLine();
        if (input.equals("bye")) {
            scanner.close();
            return false;
        }
        else if (input.contains("|")) {
            System.out.println("| is a special character and cannot be used.");
        }
        else if (input.equals("list")) {
            controller.handleList();
        }
        else if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            controller.handleDone(index);
        }
        else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            controller.handleUndone(index);
        }
        else if (input.startsWith("todo")) {
            controller.handleTodo(input);
        }
        else if (input.startsWith("deadline")) {
            controller.handleDeadline(input);
        }
        else if (input.startsWith("event")) {
            controller.handleEvent(input);
        }
        else if (input.startsWith("delete")) {
            controller.handleDelete(Integer.parseInt(input.split(" ")[1]) - 1);
        }
        else if (input.equals("help")) {
            controller.handleHelp();
        }
        else {
            System.out.println("Give me a valid command!");
        }
        return true;
    }
}
