package ponderpika;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import ponderpika.exception.PonderPikaException;
import ponderpika.parser.Parser;
import ponderpika.storage.IoHandler;
import ponderpika.task.Task;
import ponderpika.task.TaskList;
import ponderpika.task.Todo;
import ponderpika.ui.Ui;

/**
 * The {@code ponderpika} class represents a task management system.
 * It allows users to manage tasks including adding, listing, marking, unmarking, deleting tasks, and more.
 */
public class PonderPika {

    private final IoHandler io = new IoHandler();
    private final TaskList taskList;
    private final Ui ui = new Ui();
    private final Parser parser = new Parser();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");

    /**
     * Constructs a new PonderPika instance.
     * <p>
     * This constructor initializes the PonderPika object by attempting to load the task list from
     * persistent storage using loadData. If loading the data fails due to a PonderPikaException,
     * a new, empty TaskList is created and an error message is printed.
     * </p>
     */
    public PonderPika() {
        TaskList taskList1;
        try {
            taskList1 = io.loadData();
        } catch (PonderPikaException e) {
            taskList1 = new TaskList();
            System.out.println(e.toString());
        }
        this.taskList = taskList1;
    }

    /**
     * Starts the command-line interface for interacting with the task management system.
     * This method listens for user input (commands from user) and executes corresponding commands.
     */

    public void echo() {
        ui.greet();
        Scanner scan = new Scanner(System.in);
        String userCommand;

        while (scan.hasNext()) {
            userCommand = scan.nextLine().trim();

            try {
                Command command = parser.parseCommand(userCommand);
                handleCommand(command);
            } catch (PonderPikaException e) {
                System.out.println(e.toString());
                ui.printDivider();
            }

            if (userCommand.equals("bye")) {
                break;
            }
        }
        try {
            io.saveData(taskList);
        } catch (PonderPikaException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Handles a given Command by performing the appropriate action based on the command's action type.
     * <p>
     * This method processes commands for various actions such as listing tasks, marking tasks, adding
     * different types of tasks (To-do, DEADLINE, EVENT), deleting tasks, finding tasks by a keyword, and
     * handling the termination of the application.
     * </p>
     *
     * @param command the command to be processed, which includes the action type and associated data
     *
     * @throws PonderPikaException if the action type in the command is unknown or not handled
     */

    private void handleCommand(Command command) throws PonderPikaException {
        switch (command.getAction()) {
        case LIST:
            taskList.printTasks();
            ui.printDivider();
            break;

        case MARK:
            taskList.markTask((Integer) command.getData());
            System.out.println("Your task has been marked as done.");
            break;

        case UNMARK:
            taskList.unmarkTask((Integer) command.getData());
            System.out.println("Your task has been undone.");
            break;

        case TODO:
            Task todo = new Todo((String) command.getData());
            taskList.addTask(todo);
            System.out.println("        Pika! I have added your todo: " + (String) command.getData());
            System.out.println("\nPeek-A-Boo! We have " + taskList.getTasks().size() + " tasks in our list");
            ui.printDivider();
            break;

        case DEADLINE:
            Task deadline = (Task) command.getData();
            taskList.addTask(deadline);
            System.out.println("        Pika! I have added a deadline: " + deadline.getDescription());
            System.out.println("\nPeek-A-Boo! We have " + taskList.getTasks().size() + " tasks in our list");
            ui.printDivider();
            break;

        case EVENT:
            Task event = (Task) command.getData();
            taskList.addTask(event);
            System.out.println("        Pika! I have added your event: " + event.getDescription());
            System.out.println("\nPeek-A-Boo! We have " + taskList.getTasks().size() + " tasks in our list");
            ui.printDivider();
            break;

        case DELETE:
            taskList.deleteTask((Integer) command.getData());
            System.out.println("Your task has been deleted.");
            System.out.println("\nPeek-A-Boo! We have " + taskList.getTasks().size() + " tasks in our list");
            ui.printDivider();
            break;

        case FIND:
            String keyword = (String) command.getData();
            for (int i = 0; i < taskList.getTasks().size(); i++) {
                if (taskList.getTasks().get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println((i + 1) + ". " + taskList.getTasks().get(i).toString());
                }
            }
            ui.printDivider();
            break;

        case BYE:
            System.out.println("------------------------------------------------------------");
            ui.bidBye();
            System.out.println("\n----------------------------------------------------------");
            break;
        default:
            throw new PonderPikaException("Unknown command: " + command.getAction());
        }
    }
}
