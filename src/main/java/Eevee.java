import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a task management program.
 */
public class Eevee {
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;
    }

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Eevee(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.parser = new Parser();

        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            ui.printMessage("File not found!");
        }
    }

    public void run() {
        ui.printGreeting();

        while (true) {
            String input = ui.getInput();

            try {
                Parser.Command command = parser.parseCommand(input);

                switch (command) {
                case BYE:
                    ui.printExit();
                    return;
                case LIST:
                    tasks.printTasks();
                    break;
                case MARK: {
                    int taskNumber = parser.parseTaskNumber(input);
                    Task t = tasks.getTask(taskNumber);
                    if (t.isDone) {
                        throw new EeveeException("Task has already been marked as done.");
                    }
                    t.markAsDone();
                    ui.printMessage("Congratulations! I've marked the following task as done:\n  " + t);
                    break;
                }
                case UNMARK: {
                    int taskNumber = parser.parseTaskNumber(input);
                    Task t = tasks.getTask(taskNumber);
                    if (!t.isDone) {
                        throw new EeveeException("Task is not marked as done. "
                                + "Needs to be marked done in order to unmark it.");
                    }
                    t.unmarkAsDone();
                    ui.printMessage("Ok! Task no longer marked as done:\n  " + t);
                    break;
                }
                case DELETE: {
                    int taskNumber = parser.parseTaskNumber(input);
                    Task t = tasks.getTask(taskNumber);
                    tasks.removeTask(taskNumber);
                    ui.printMessage("As you wish, this task has been removed:\n " + t);
                    break;
                }
                case TODO: {
                    String s = input.substring(5).trim();
                    if (s.isEmpty()) {
                        throw new EeveeException("No task found :( "
                                + "Please input the task details and description correctly");
                    }
                    Todo t = new Todo(s);
                    tasks.addTask(t);
                    storage.appendToFile("T" + "|" + t.getStatus() + "|" + s + "\n");
                    ui.printMessage("Added the following task to your list:\n" + t);
                    break;
                }
                case DEADLINE: {
                    String[] info = input.substring(9).trim().split("/by", 2);
                    if (info.length < 2) {
                        throw new EeveeException("Deadline not given for task type 'deadline'. "
                                + "Please input a deadline denoted by '/by' or use task type 'todo' instead.");
                    }

                    // Create and store task
                    Deadline d = new Deadline(info[0], info[1]);
                    tasks.addTask(d);
                    storage.appendToFile("D" + "|" + d.getStatus() + "|" + info[0] + "|" + d.getDeadline() + "\n");
                    ui.printMessage("Added the following task to your list:\n" + d);
                    break;
                }
                case EVENT: {
                    String[] info = input.substring(6).trim().split("/from|/to", 3);
                    if (info.length < 3) {
                        throw new EeveeException("Event start and/or end timings not provided."
                                + "Please input a start time denoted by '/from' "
                                + "and an end time denoted by '/to' when using task type Event");
                    }

                    // Create and store task
                    Event e = new Event(info[0], info[1], info[2]);
                    tasks.addTask(e);
                    storage.appendToFile("E" + "|" + e.getStatus() + "|"
                            + info[0] + "|" + e.getFrom() + "|" + e.getTo() + "\n");
                    ui.printMessage("Added the following task to your list:\n" + e);
                    break;
                }
                default:
                    throw new EeveeException("You seemed to have typed wrong. This is not a valid command.");
                }
                System.out.println("You now have " + tasks.getSize() + " task(s).");
            } catch (EeveeException | IOException e) {
                ui.printMessage(e.getMessage());
            }
            ui.printDivider();
        }
    }

    /** 
     * Serves as the entry point for the Eevee program.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Eevee("data/tasks.txt").run();
    }
}
