package eevee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a task management program.
 */
public class Eevee {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    /**
     * Constructs an instance of Eevee with a specified storage file path.
     *
     * @param filePath The storage file path.
     */
    public Eevee(String filePath) {
        assert filePath != null;
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

    /**
     * Constructs an instance of Eevee without an argument.
     */
    public Eevee() {
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_FILE_PATH);
        this.tasks = new TaskList();
        this.parser = new Parser();

        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            ui.printMessage("File not found!");
        }
    }

    /**
     * Handles command that the user inputs.
     *
     * @param input The String input that should be in the form of a command.
     * @return The String response to the given command.
     */
    public String getResponse(String input) {
        assert !input.isEmpty();
        try {
            Parser.Command command = parser.parseCommand(input);
            assert command != null;
            switch (command) {
            case BYE:
                return ui.getExit();
            case LIST:
                return tasks.listTasks();
            case MARK: {
                int taskNumber = parser.parseTaskNumber(input);
                assert taskNumber > 0 && taskNumber <= tasks.getSize();
                Task t = tasks.getTask(taskNumber);
                if (t.isDone) {
                    throw new EeveeException("Task has already been marked as done.");
                }
                t.markAsDone();
                storage.saveTasks(tasks);
                return "Congratulations! I've marked the following task as done:\n  " + t;
            }
            case UNMARK: {
                int taskNumber = parser.parseTaskNumber(input);
                assert taskNumber > 0 && taskNumber <= tasks.getSize();
                Task t = tasks.getTask(taskNumber);
                if (!t.isDone) {
                    throw new EeveeException("Task is not marked as done. "
                            + "Needs to be marked done in order to unmark it.");
                }
                t.unmarkAsDone();
                storage.saveTasks(tasks);
                return "Ok! Task no longer marked as done:\n  " + t;
            }
            case DELETE: {
                int taskNumber = parser.parseTaskNumber(input);
                assert taskNumber > 0 && taskNumber <= tasks.getSize();
                Task t = tasks.getTask(taskNumber);
                tasks.removeTask(taskNumber);
                storage.saveTasks(tasks);
                return "As you wish, this task has been removed:\n " + t;
            }
            case TODO: {
                String s = input.substring(5).trim();
                if (s.isEmpty()) {
                    throw new EeveeException("No task found :( "
                            + "Please input the task details and description correctly");
                }
                Todo t = new Todo(s);
                assert !t.isDone;
                tasks.addTask(t);
                storage.saveTasks(tasks);
                return "Added the following task to your list:\n" + t;
            }
            case DEADLINE: {
                String[] info = input.substring(9).trim().split("/by", 2);
                if (info.length < 2 || info[0].isEmpty() || info[1].isEmpty()) {
                    throw new EeveeException("Description or deadline not given for task type 'deadline'. "
                            + "Make sure the deadline is denoted by '/by'.");
                }

                // Create and store task
                Deadline d = new Deadline(info[0], info[1]);
                assert !d.isDone;
                tasks.addTask(d);
                storage.saveTasks(tasks);
                return "Added the following task to your list:\n" + d;
            }
            case EVENT: {
                String[] info = input.substring(6).trim().split("/from|/to", 3);
                if (info.length < 3 || info[0].isEmpty() || info[1].isEmpty() || info[2].isEmpty()) {
                    throw new EeveeException("Event description, start and/or end timings not provided. "
                            + "Please input a start time denoted by '/from' "
                            + "and an end time denoted by '/to' when using task type eevee.Event");
                }

                // Create and store task
                Event e = new Event(info[0], info[1], info[2]);
                assert !e.isDone;
                tasks.addTask(e);
                storage.saveTasks(tasks);
                return "Added the following task to your list:\n" + e;
            }
            case FIND: {
                String keyword = input.substring(5).trim();
                if (keyword.isEmpty()) {
                    throw new EeveeException("No keyword found :( "
                            + "Please input the keyword to find matching tasks");
                }
                ArrayList<Task> results = tasks.findTasks(keyword);
                if (results.isEmpty()) {
                    return "No tasks found matching keyword: " + keyword;
                } else {
                    StringBuilder sb = new StringBuilder("Here are the tasks containing keyword " + keyword + " :\n");
                    for (Task t : results) {
                        sb.append(t.toString()).append("\n");
                    }
                    return sb.toString();
                }
            }
            default:
                throw new EeveeException("You seemed to have typed wrong. This is not a valid command.");
            }
        } catch (EeveeException | IOException e) {
            ui.printMessage(e.getMessage());
        }
        return input;
    }

    /** 
     * Serves as the entry point for the Eevee program.
     *
     * @param args Command-line arguments are not used in this program.
     */
    public static void main(String[] args) {
        new Eevee("data/tasks.txt");
    }
}
