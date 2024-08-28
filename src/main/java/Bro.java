import bro.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bro {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initializes the application components and starts the main event loop to handle user input.
     * It sets up the user interface, parser, task list, and storage, then continuously listens for user commands
     * until the user decides to exit by typing "bye".
     *
     * The method handles various commands:
     * - "list" to display all tasks.
     * - "mark <task number>" to mark a task as completed.
     * - "unmark <task number>" to mark a task as not completed.
     * - "delete <task number>" to delete a task.
     * - "todo <task description>" to add a new to-do task.
     * - "deadline <task description> /by <date time>" to add a task with a deadline.
     * - "event <task description> /from <start date time> /to <end date time>" to add an event.
     *
     * The state of tasks is saved to a file after every change.
     * Handles exceptions for file loading errors, command format errors, and task index out-of-bounds.
     */
    public void run() {
        ui = new Ui();
        parser = new Parser(ui);
        tasks = new TaskList(ui, parser);
        storage = new Storage("./src/main/java/data.txt", tasks);
        try {
            storage.loadIn();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Nothing to load");
        } catch (BroException be) {
            System.out.println(be.getMessage());
        }
        ui.printWelcome();
        Scanner prompt = new Scanner(System.in);
        String word = prompt.nextLine();
        while (!word.equalsIgnoreCase("bye")) {
            if (word.equalsIgnoreCase("list")) {
                ui.printList(tasks);
            } else {
                String action = word.split(" ", 2)[0];
                String info;
                if (word.split(" ", 2).length == 1) {
                    info = "";
                } else {
                    info = word.split(" ", 2)[1];
                }
                try {
                    switch (action.toLowerCase()) {
                        case "mark":
                            tasks.markTask(Integer.parseInt(info));
                            storage.saveToFile();
                            break;
                        case "unmark":
                            tasks.unmarkTask(Integer.parseInt(info));
                            storage.saveToFile();
                            break;
                        case "delete":
                            tasks.deleteTask(Integer.parseInt(info));
                            storage.saveToFile();
                            break;
                        case "todo":
                            tasks.addTodo(info);
                            storage.saveToFile();
                            break;
                        case "deadline":
                            tasks.addDeadline(info);
                            storage.saveToFile();
                            break;
                        case "event":
                            tasks.addEvent(info);
                            storage.saveToFile();
                            break;
                        default:
                            ui.printDefault();
                    }
                } catch (BroException be) {
                    System.out.println(be.getMessage());
                } catch (NumberFormatException nfe) {
                    ui.printErrorFormat();
                } catch (IndexOutOfBoundsException e) {
                    ui.printErrorSize(tasks.size());
                }
            }
            Scanner next_prompt = new Scanner(System.in);
            word = next_prompt.nextLine();
        }
        ui.printExit();
    }

    public static void main(String[] args) {
        Bro bro = new Bro();
        bro.run();
    }
}