package main.java.angel;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents the Angel chatbot application.
 * Handles user commands, task management, and data persistence.
 */
public class Angel {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final String FILE_PATH = "./data/Angel.txt";
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an Angel instance and initializes the task list.
     * Loads tasks from the storage file if it exists.
     */
    public Angel() {
        taskList = new TaskList(FILE_PATH);
        ui = new Ui();
    }

    /**
     * Runs the chatbot application.
     * Continuously reads user input, processes commands, and updates task list.
     */
    public void run() {
        ui.print("Hello! I'm Angel\nWhat can I do for you?");

        while (true) {
            try {
                String input = ui.readInput();
                Parser.Command command = Parser.parseCommand(input);

                switch (command.getType()) {
                case "bye":
                    ui.print("Bye. Hope to see you again soon!");
                    ui.close();
                    return;
                case "list":
                    ui.printTasks(taskList);
                    break;
                case "todo":
                    String todoDescription = (String) command.getDetails();
                    taskList.addTask(new ToDo(todoDescription));
                    ui.print("Got it. I've added this task:\n  " + todoDescription);
                    break;
                case "deadline":
                    Parser.DeadlineDetail deadlineDetail = (Parser.DeadlineDetail) command.getDetails();
                    taskList.addTask(new Deadline(deadlineDetail.getDescription(), deadlineDetail.getDeadline()));
                    ui.print("Got it. I've added this task:\n  " + deadlineDetail.getDescription()
                            + " by: " + deadlineDetail.getDeadline().format(INPUT_FORMAT));
                    break;
                case "event":
                    Parser.EventDetail eventDetail = (Parser.EventDetail) command.getDetails();
                    taskList.addTask(new Event(eventDetail.getDescription(), eventDetail.getFrom(), eventDetail.getTo()));
                    ui.print("Got it. I've added this task:\n  " + eventDetail.getDescription() + " from: "
                            + eventDetail.getFrom().format(INPUT_FORMAT) + " to: " + eventDetail.getTo().format(INPUT_FORMAT));
                    break;
                case "mark":
                    int markIndex = (int) command.getDetails();
                    taskList.markTask(markIndex);
                    ui.print("Nice! I've marked this task as done:\n  " + taskList.listTasks().get(markIndex));
                    break;
                case "unmark":
                    int unmarkIndex = (int) command.getDetails();
                    taskList.unmarkTask(unmarkIndex);
                    ui.print("OK, I've marked this task as not done yet:\n  " + taskList.listTasks().get(unmarkIndex));
                    break;
                case "delete":
                    int deleteIndex = (int) command.getDetails();
                    taskList.deleteTask(deleteIndex);
                    ui.print("Noted. I've removed this task:\n  " + taskList.listTasks().get(deleteIndex));
                    break;
                case "find":
                    String keyword = (String) command.getDetails();
                    List<Task> foundTasks = taskList.findTasks(keyword); // Correct type is List<Task>
                    if (foundTasks.isEmpty()) {
                        ui.print("No matching tasks found.");
                    } else {
                        ui.print("Here are the matching tasks in your list:");
                        ui.printFoundTasks(foundTasks); // You need a method to print the list of tasks
                    }
                    break;
                default:
                    throw new InvalidCommandException("Unknown command: " + command.getType()
                            + ". Please enter a valid command.");
                }
            } catch (InvalidCommandException e) {
                ui.printError(e.getMessage());
            } catch (TaskNotFoundException e) {
                ui.printError(e.getMessage() + " Please check the task number and try again.");
            } catch (AngelException e) {
                ui.printError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Entry point of the Angel application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Angel().run();
    }
}
