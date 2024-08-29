import commands.*;
import models.Task;
import models.TasksFileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IpMan {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final List<Task> tasks = new ArrayList<>();
    private static final TasksFileManager fileManager = new TasksFileManager("./data/ipman.txt");

    public static void main(String[] args) {
        // Hello
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Ip Man.");
        System.out.println("What can I do for you?");

        // Load tasks
        fileManager.load(tasks);

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
                    command = new ListCommand(tasks);
                } else if (message.startsWith("mark")) {
                    command = new MarkCommand(tasks, message);
                } else if (message.startsWith("unmark")) {
                    command = new UnmarkCommand(tasks, message);
                } else if (message.startsWith("todo")) {
                    command = new CreateToDoCommand(tasks, message);
                } else if (message.startsWith("deadline")) {
                    command = new CreateDeadlineCommand(tasks, message);
                } else if (message.startsWith("event")) {
                    command = new CreateEventCommand(tasks, message);
                } else if (message.startsWith("delete")) {
                    command = new DeleteCommand(tasks, message);
                } else if (message.startsWith("find")) {
                    command = new FindCommand(tasks, message);
                } else {
                    System.out.println("Sorry, I don't recognise that keyword. Try again!");
                    continue;
                }

                command.execute();
                fileManager.save(tasks);
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
