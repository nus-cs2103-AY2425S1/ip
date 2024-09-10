// Parser.java
package parser;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import ui.Ui;

/**
 * Parser class that encapsulates all the commands
 */
public class Parser {

    public static final Map<String, Command> commands = new HashMap<>();

    // Implement a new command "many"
    // input command looks something like =>
    /*
    many deadline <> /by <>

     */
    // Get an array of Tasks split by "\n" and then call a method named addMultipleTasks(Tasks... tasks)
    // And then process accordingly
    static {
        // Initialize command mappings
        commands.put("list", new ListCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("todo", new ToDoCommand());
        commands.put("deadline", new DeadlineCommand());
        commands.put("event", new EventCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("find", new FindCommand());
        commands.put("on", new OnCommand());
        //commands.put("many", new AddManyCommand());
    }

    // To initialise all the commands inside the map
    public static void initialiseMap() {}

    /**
     * Static method that tries to run the program overall
     * @param items
     */
    public static void ratchetCatBot(List<Task> items) {
        Ui.sayWelcome();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Reads a line of text

        while (!input.equals("bye")) {
            String commandKey = input.split(" ")[0].trim(); // Get the command keyword
            Command command = commands.get(commandKey);

            try {
                if (command != null) {
                    input = command.execute(input, items, scanner);
                } else {
                    System.out.print("Inappropriate Command. Try again with a valid command: ");
                    input = scanner.nextLine();
                }
            } catch (DateTimeParseException e) { //TheOrangeRatchetCatException |
                System.out.println(e.getMessage());
                input = scanner.nextLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Correct input format for adding event: event <Task> /from <input> /to <input>");
                input = scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid number for the command.");
                input = scanner.nextLine();
            }
        }

        Ui.goodByeCat();
        scanner.close(); // Close the scanner to avoid resource leaks
    }

    /**
     * Returns the string that consists of all task description and date information
     * @param tasks
     */
    public static String printAllTasks(List<Task> tasks) {
        int index = 1;
        StringBuilder sbr = new StringBuilder("Here are the tasks in your list: \n");
        for (Task item : tasks) {
            System.out.println(index + "." + item);
            sbr.append(index + "." + item + "\n");
            index++;
        }
        return sbr.toString();
    }

    /**
     * Returns the string that shows that the task has been marked done
     * @param index
     * @return
     */
    public static String markTaskAsDone(List<Task> tasks, int index) {
        Task markingTask = tasks.get(index);
        markingTask.setDone(true);
        Ui.markingTaskPrint(markingTask);
        return "Nice! I've marked this task as done:\n"
                + "[" + markingTask.getStatusIcon() + "] " + markingTask.getDes() + "\n";
    }

    /**
     * Returns the string that shows that the task has been marked not done
     * @param tasks
     * @param index
     * @return
     */
    public static String unmarkTaskAsDone(List<Task> tasks, int index) {
        Task markingTask = tasks.get(index);
        markingTask.setDone(false);
        Ui.unmarkingTaskPrint(markingTask);
        return "OK, I've marked this task as not done yet:\n"
                + "[" + markingTask.getStatusIcon() + "] " + markingTask.getDes() + "\n";
    }

    /**
     * Returns the string that shows that the task at specific index has been deleted
     * @param tasks
     * @param index
     * @return
     */
    public static String deleteTasks(List<Task> tasks, int index) {
        Task taskToDelete = tasks.get(index - 1);
        tasks.remove(index - 1);
        Task.decrementTaskCount();
        Ui.deletingTaskPrint(taskToDelete);
        return "Noted. I've removed this task:\n"
                + taskToDelete + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
    }

    /**
     * Returns the string that shows that new ToDo has been added to list of tasks
     * @param taskDescription
     * @param tasks
     * @return
     */
    public static String addingToDoTaskToList(String taskDescription, List<Task> tasks) {
        Task nextTask = new ToDo(taskDescription);
        Ui.addingToDoPrint(nextTask);
        tasks.add(nextTask);
        //return scanner.nextLine();
        return "Got it. I've added this task:\n"
                + nextTask + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
    }

    /**
     * Returns the string that shows that new Deadline has been added to list of tasks
     * @param taskDescription
     * @param byDate
     * @param tasks
     * @return
     */
    public static String addingDeadlineTaskToList(String taskDescription, LocalDate byDate, List<Task> tasks) {
        // If task added successfully, the program will reach here!
        Task nextTask = new Deadline(taskDescription, byDate);
        Ui.addingDeadlinePrint(nextTask);
        tasks.add(nextTask);
        //return scanner.nextLine();
        return "Got it. I've added this task:\n"
                + nextTask + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
    }

    /**
     * Returns the string that shows that new Event has been added to list of tasks
     * @param taskDescription
     * @param fromDate
     * @param toDate
     * @param tasks
     * @return
     */
    public static String addingEventToTaskList(String taskDescription, LocalDate fromDate, LocalDate toDate,
                                               List<Task> tasks) {
        Task nextTask = new Event(taskDescription, fromDate, toDate);
        Ui.addingEventPrint(nextTask);
        tasks.add(nextTask);
        //return scanner.nextLine();
        return "Got it. I've added this task:\n"
                + nextTask + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
    }
}

