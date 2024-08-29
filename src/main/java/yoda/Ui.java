package yoda;

import yoda.commands.Command;
import yoda.exceptions.EmptyInputException;
import yoda.exceptions.InvalidInputException;
import yoda.exceptions.NonsenseInputException;

import java.util.Scanner;

public class Ui {
    protected TaskList tasks = null;
    public void run() {
        String welcomeMessage = "Hello! For you, what can I do?";

        tasks = new TaskList(Storage.loadTasks());
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();

        printLine();
        System.out.println(welcomeMessage);
        printLine();

        while (true) {
            String input = scanner.nextLine().trim();
            try {
                Command command = parser.handle(input, tasks);
                printLine();
                command.run();
                printLine();
                Storage.saveTasks(tasks.getTasks());
            } catch (InvalidInputException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }
    }

    public void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }
}
