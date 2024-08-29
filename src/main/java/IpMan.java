import commands.*;
import models.TaskList;

import java.util.Scanner;

public class IpMan {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final TaskList items = new TaskList("./data/ipman.txt");

    public static void main(String[] args) {
        // Hello
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Ip Man.");
        System.out.println("What can I do for you?");

        // Load tasks
        items.loadFromFile();

        // Main loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String message = scanner.nextLine();
            Command command;

            try {
                if (message.equals("bye")) {
                    break;
                } else if (message.equals("list")) {
                    command = new ListCommand(items);
                } else if (message.startsWith("mark")) {
                    command = new MarkCommand(items, message);
                } else if (message.startsWith("unmark")) {
                    command = new UnmarkCommand(items, message);
                } else if (message.startsWith("todo")) {
                    command = new CreateToDoCommand(items, message);
                } else if (message.startsWith("deadline")) {
                    command = new CreateDeadlineCommand(items, message);
                } else if (message.startsWith("event")) {
                    command = new CreateEventCommand(items, message);
                } else if (message.startsWith("delete")) {
                    command = new DeleteCommand(items, message);
                } else {
                    System.out.println("Sorry, I don't recognise that keyword. Try again!");
                    continue;
                }

                command.execute();
            } catch (Exception e) {
                System.out.println("Oh no! Something went wrong:");
                System.out.println(e.getMessage());
            }
        }

        // Goodbye
        System.out.println(HORIZONTAL_LINE);
        System.out.println("That's enough for today. See you another time.");
        System.out.println(HORIZONTAL_LINE);
    }
}
