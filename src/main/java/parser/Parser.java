
package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import exceptions.ErrorMessages;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import ui.Ui;

/**
 * Parser class is a class that parses the input and executes the relevant command
 */
public class Parser {

    /**
     * Map that stores all the commands
     */
    public static final Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("list", new ListCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("todo", new ToDoCommand());
        commands.put("deadline", new DeadlineCommand());
        commands.put("event", new EventCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("find", new FindCommand());
        commands.put("on", new OnCommand());
        commands.put("cp", new ChangePriorityCommand());
    }

    /**
     * Static method that initialises the map
     */
    public static void initialiseMap() {}

    /**
     * Main method that runs the program
     * @param items the list of tasks
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
                    input = command.execute(input, items);
                } else {
                    System.out.print(ErrorMessages.INVALID_COMMAND);
                    input = scanner.nextLine();
                }
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
                input = scanner.nextLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(ErrorMessages.ARRAY_OUT_OF_BOUNDS);
                input = scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println(ErrorMessages.INCORRECT_ASCII_VALUE_INPUT);
                input = scanner.nextLine();
            }
        }
        Ui.goodByeCat();
        scanner.close();
    }

    /**
     * Returns the string that shows all the tasks in the list
     * @param tasks the list of tasks
     * @return the string that shows all the tasks in the list
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
     * Returns the string that shows that the task has been marked as done
     * @param tasks the list of tasks
     * @param index the index of the task to be marked as done
     * @return the string that shows that the task has been marked as done
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
     * @param tasks the list of tasks
     * @param index the index of the task to be marked as not done
     * @return the string that shows that the task has been marked as not done
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
     * @param tasks the list of tasks
     * @param index the index of the task to be deleted
     * @return the string that shows that the task has been deleted
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
     * @param taskDescription the description of the task
     * @param tasks the list of tasks
     * @return the string that shows that new ToDo has been added to list of tasks
     */
    public static String addingToDoTaskToList(String taskDescription, List<Task> tasks) {
        Task nextTask = new ToDo(taskDescription, 1);
        Ui.addingToDoPrint(nextTask);
        tasks.add(nextTask);
        return "Got it. I've added this task:\n"
                + nextTask + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
    }

    /**
     * Returns the string that shows that new Deadline has been added to list of tasks
     * @param taskDescription the description of the task
     * @param byDate the deadline of the task
     * @param tasks the list of tasks
     * @return the string that shows that new Deadline has been added to list of tasks
     */
    public static String addingDeadlineTaskToList(String taskDescription, LocalDate byDate, List<Task> tasks) {
        Task nextTask = new Deadline(taskDescription, byDate, 1);
        Ui.addingDeadlinePrint(nextTask);
        tasks.add(nextTask);
        return "Got it. I've added this task:\n"
                + nextTask + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
    }

    /**
     * Returns the string that shows that new Event has been added to list of tasks
     * @param taskDescription the description of the task
     * @param fromDate the starting date of the event
     * @param toDate the ending date of the event
     * @param tasks the list of tasks
     * @return the string that shows that new Event has been added to list of tasks
     */
    public static String addingEventToTaskList(String taskDescription, LocalDate fromDate, LocalDate toDate,
                                               List<Task> tasks) {
        Task nextTask = new Event(taskDescription, fromDate, toDate, 1);
        Ui.addingEventPrint(nextTask);
        tasks.add(nextTask);
        return "Got it. I've added this task:\n"
                + nextTask + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
    }

    /**
     * Prints Deadline tasks if the task coincides with the relevant dates
     * @param task the task to be printed
     * @param date the date to be compared with
     * @param sbr the string builder to append the string
     * @param index the index of the task
     */
    public static void printDeadlineIfDateCorresponds(Task task, LocalDate date, StringBuilder sbr, int index) {
        LocalDate byDate = ((Deadline) task).getDeadlineDate();
        if (byDate.isAfter(date)) {
            System.out.println(index + "." + task);
            sbr.append(index + "." + task + "\n");
        }
    }

    /**
     * Prints task if task coincides with the relevant dates
     * @param tasks the list of tasks
     * @param date the date to be compared with
     * @param sbr the string builder to append the string
     * @param index the index of the task
     */
    public static int printTasksIfDateCorresponds(List<Task> tasks, LocalDate date, StringBuilder sbr, int index) {
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Parser.printDeadlineIfDateCorresponds(task, date, sbr, index);
                index++;
            } else if (task instanceof Event) {
                LocalDate fromDate = ((Event) task).getFromDur();
                LocalDate toDate = ((Event) task).getToDur();
                if (fromDate.isBefore(date) && toDate.isAfter(date)) {
                    System.out.println(index + "." + task);
                    sbr.append(index + "." + task + "\n");
                    index++;
                }
            }
        }
        return index;
    }
}
