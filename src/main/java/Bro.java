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
     * - "find <word> to find any tasks that contain that particular keyword.
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
                        case "find":
                            tasks.findTasks(info);
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

    /**
     * Processes a user command and returns a response based on the action specified.
     * Commands include listing tasks, marking/unmarking tasks as done, adding new tasks,
     * deleting tasks, finding tasks, and handling other task-related operations.
     * It also handles saving the current state of tasks to storage when necessary.
     *
     * @param word    The user input command. The first word indicates the action to perform,
     *                and the rest provides additional information if needed.
     * @param ui      An instance of Ui used for generating user interface messages.
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage An instance of Storage used for saving tasks to a file.
     * @return        A string response generated based on the input command. This could be a list of tasks,
     *                a confirmation message, or an error message depending on the command.
     * @throws BroException            If there is a specific error related to task operations.
     * @throws NumberFormatException   If the input for task indexing is not a valid number.
     * @throws IndexOutOfBoundsException If the task index specified is out of range.
     */
    public String getResponse(String word, Ui ui, TaskList tasks, Storage storage) {
        if (word.equalsIgnoreCase("list")) {
            return ui.printList(tasks);
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
                        String mark = tasks.markTask(Integer.parseInt(info));
                        storage.saveToFile();
                        return mark;
                        //break;
                    case "unmark":
                        String unmark = tasks.unmarkTask(Integer.parseInt(info));
                        storage.saveToFile();
                        return unmark;
                        //break;
                    case "delete":
                        String delete = tasks.deleteTask(Integer.parseInt(info));
                        storage.saveToFile();
                        return delete;
                        //break;
                    case "find":
                        return tasks.findTasks(info);
                        //break;
                    case "todo":
                        String todo = tasks.addTodo(info);
                        storage.saveToFile();
                        return todo;
                        //break;
                    case "deadline":
                        String deadline = tasks.addDeadline(info);
                        storage.saveToFile();
                        return deadline;
                        //break;
                    case "event":
                        String event = tasks.addEvent(info);
                        storage.saveToFile();
                        return event;
                        //break;
                    default:
                        return ui.printDefault();
                }
            } catch (BroException be) {
                return be.getMessage();
            } catch (NumberFormatException nfe) {
                return ui.printErrorFormat();
            } catch (IndexOutOfBoundsException e) {
                return ui.printErrorSize(tasks.size());
            }
        }
    }

    public static void main(String[] args) {
        //Bro bro = new Bro();
        //bro.run();
    }
}