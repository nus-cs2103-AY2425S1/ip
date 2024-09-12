package tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import exceptions.TheOrangeRatchetCatException;
import parser.Parser;
import tasks.Task;

/**
 * Encapsulates all the commands that the user can use to interact with the bot
 */
public class TaskList {
    /**
     * Prints out all tasks currently in the list
     *
     * @param items represents the list of task
     * @param scanner the scanner object to read next line of input
     * @return returns the next input command by user
     */
    public static String checkList(List<Task> items, Scanner scanner) {
        System.out.println("Here are the tasks in your list:");
        String result = Parser.printAllTasks(items);
        //return scanner.nextLine();
        return result;
    }

    /**
     * Marks a particular task with that specific index as done
     *
     * @param input represents the index of the task to mark
     * @param items represents the list of task
     * @param scanner the scanner object to read next line of input
     * @return returns the next input command by user
     */
    public static String markingTask(String input, List<Task> items, Scanner scanner) {
        try {
            return Parser.markTaskAsDone(items, Integer.parseInt(input) - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            //return scanner.nextLine();
            return "Might want to reconsider your action. Please Try Again";
        } catch (NumberFormatException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            //return scanner.nextLine();
            return "Might want to reconsider your action. Please Try Again";
        }
    }

    /**
     * Unmarks a particular task with that specific index as done
     *
     * @param input represents the index of the task to unmark
     * @param items represents the list of task
     * @param scanner the scanner object to read next line of input
     * @return returns the next input command by user
     */
    public static String unmarkingTask(String input, List<Task> items, Scanner scanner) {
        try {
            return Parser.unmarkTaskAsDone(items, Integer.parseInt(input) - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            //return scanner.nextLine();
            return "Might want to reconsider your action. Please Try Again";
        } catch (NumberFormatException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            //return scanner.nextLine();
            return "Might want to reconsider your action. Please Try Again";
        }
    }

    /**
     * Deletes a particular task with that specific index as done
     *
     * @param index represents the index of the task to delete
     * @param items represents the list of task
     * @param scanner the scanner object to read next line of input
     * @return returns the next input command by user
     */
    public static String deleteTask(int index, List<Task> items, Scanner scanner) {
        try {
            return Parser.deleteTasks(items, index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Pick an appropriate number. Please Try Again");
            //return scanner.nextLine();
            return "Pick an appropriate number. Please Try Again";
        }
    }

    /**
     * Adds a new task of type ToDo
     *
     * @param input represents the taskDescription of task
     * @param items represents the list of task
     * @param scanner the scanner object to read next line of input
     * @return returns the next input command by user
     */

    // Deal with incorrect inputs todo <space> does not terminate
    public static String addingToDo(String input, List<Task> items,
                                    Scanner scanner) throws TheOrangeRatchetCatException {
        if (input.isEmpty()) {
            //throw new TheOrangeRatchetCatException("You can't do Nothing!");
            return "You can't Do Nothing";
        }
        return Parser.addingToDoTaskToList(input, items);
    }

    /**
     * Adds a new task of type Deadline
     *
     * @param input represents the taskDescription of Deadline
     * @param items represents the list of task
     * @param scanner the scanner object to read next line of input
     * @return returns the next input command by user
     */
    public static String addingDeadline(String input, List<Task> items,
                                        Scanner scanner) throws TheOrangeRatchetCatException {
        // Split the input string by "/by"
        String[] parts = input.split("/by");
        // The description is the first part after removing the word "deadline"
        String taskDescription = parts[0].replace("deadline", "").trim();
        if (taskDescription.isEmpty()) {
            //throw new TheOrangeRatchetCatException("You can't do Nothing!");
            return "Adding a deadline follows a specific format : deadline <taskDescription> /by<YYYY>-<MM>-<DD>";
        }
        // The "by" part is the second part, if it exists
        String date = parts.length > 1 ? parts[1].trim() : "";
        LocalDate dateBy;

        try { // Utilises LocalDate static method to parse input
            dateBy = LocalDate.parse(date);
            assert dateBy != null : "Parsed date should not be null"; // Assert that the date is correctly parsed
        } catch (DateTimeParseException e) {
            System.out.println("The deadline follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!");
            //return scanner.nextLine();
            return "The deadline follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!";
        }
        if (date.isEmpty()) {
            //throw new TheOrangeRatchetCatException("You need to provide a deadline!");
            return "Adding a deadline follows a specific format : deadline <taskDescription> /by<YYYY>-<MM>-<DD>";
        }
        assert !taskDescription.isEmpty() : "Task description should not be empty";
        return Parser.addingDeadlineTaskToList(taskDescription, dateBy, items);
    }
    /**
     * Adds a new task of type Event
     *
     * @param input represents the taskDescription of Event
     * @param items represents the list of task
     * @param scanner the scanner object to read next line of input
     * @return returns the next input command by user
     */
    public static String addingEvent(String input, List<Task> items,
                                     Scanner scanner) throws TheOrangeRatchetCatException {
        String[] parts = input.split("/from"); // Split the input string by "/from"
        String taskDescription = parts[0].replace("event", "").trim();
        if (taskDescription.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing! "
                    + "Correct input format for adding event: event <Task> /from <input> /to <input>");
        }
        String[] dateParts = parts[1].split("/to"); // Further split the remaining part by "/to"
        String fromDate = dateParts[0].trim(); // The "fromDate" is the first part
        String toDate = dateParts.length > 1 ? dateParts[1].trim() : ""; // The "toDate" is the second part
        LocalDate toLocalDate;
        LocalDate fromLocalDate;
        try { // Utilises LocalDate static method to parse input
            toLocalDate = LocalDate.parse(toDate); // Could throw a DateTimeParseException
            fromLocalDate = LocalDate.parse(fromDate);
        } catch (DateTimeParseException e) {
            System.out.println("The event follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!");
            //return scanner.nextLine();
            return "The event follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!";
        }
        if (fromLocalDate.isAfter(toLocalDate)) {
            throw new TheOrangeRatchetCatException("Your start date cannot be later than your end date");
        }
        // Assert that fromLocalDate is not after toLocalDate
        assert !fromLocalDate.isAfter(toLocalDate) : "Start date must be before or on the end date.";
        if (toDate.isEmpty()) {
            throw new TheOrangeRatchetCatException("You need to specify an end time!");
        }
        validateEventInputs(taskDescription, fromDate, toDate);
        return Parser.addingEventToTaskList(taskDescription, fromLocalDate, toLocalDate, items);
    }
    /**
     * To validate that the taskDescription, fromDate and toDate are not empty before creating a new Event instance
     * @param taskDescription
     * @param fromDate
     * @param toDate
     */
    private static void validateEventInputs(String taskDescription, String fromDate, String toDate) {
        assert !taskDescription.isEmpty() : "TaskDescription cannot be empty";
        assert !fromDate.isEmpty() : "From date must not be empty.";
        assert !toDate.isEmpty() : "To date must not be empty.";
    }
    /**
     * Prints out all activities that are still relevant in regard to the input date
     *
     * @param date represents the date of interest
     * @param items represents the list of task
     * @param scanner the scanner object to read next line of input
     * @return returns the next input command by user
     */
    // Make sure you deal with incorrect inputs, couldn't load => on 2024-08
    public static String activitiesOnThisDate(LocalDate date, List<Task> items, Scanner scanner) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks that occur at this date: " + date.toString());
        if (date.toString().isEmpty()) {
            return "You need to specify a date!";
        }
        StringBuilder sbr = new StringBuilder("Here are the tasks that occur at this date: " + date + "\n");
        Parser.printTasksIfDateCorresponds(items, date, sbr, 1);
        System.out.println("____________________________________________________________");
        //return scanner.nextLine();
        return sbr.toString();
    }

    /**
     * Prints out all tasks related to the input that is present in the list of tasks
     *
     * @param input represents the taskDescription of Deadline
     * @param items represents the list of task
     * @param scanner the scanner object to read next line of input
     * @return returns the next input command by user
     */
    public static String find(String input, List<Task> items, Scanner scanner) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        StringBuilder sbr = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        for (Task task : items) {
            if (task.getDes().contains(input)) {
                System.out.println(index + "." + task);
                sbr.append(index + "." + task + "\n");
                index++;
            }
        }
        if (index == 1) {
            printsMessageWhenNoTasksFound(input);
        }
        System.out.println("____________________________________________________________");
        //return scanner.nextLine();
        return sbr.toString();
    }

    /**
     * A special case where the keyword does not match any task description and returns this print
     * @return
     */
    private static String printsMessageWhenNoTasksFound(String input) {
        System.out.println("Looks Like there's no task with taskDescription that contains " + "'" + input + "'");
        System.out.println("Try Looking for something else!");
        return "Looks like there's no task with taskDescription that contains '" + input + "'\n"
                + "Try looking for something else!";
    }

    // Input is a string of multiple tasks split by \n
    // Create and process each task.
    // And pass into addMany(Tasks ...) {}
    public static String addMany(String input, List<Task> items, Scanner scanner) {
        return "";
    }

    /**
     * Changes the priorityLevel for various task instances
     * @param input
     * @param items
     * @param scanner
     * @return
     */
    public static String changePriorityForSpecificTask(String input, List<Task> items, Scanner scanner) {
        String[] result = input.trim().split("\\s+");
        if (result.length != 3) {
            return "Correct command format is...";
        }
        int severity = Integer.valueOf(result[2].trim());
        if (severity > 4 || severity < 1) {
            return "Choose only 1 to 4";
        }
        Task task;
        try {
            task = items.stream()
                    .skip(Integer.valueOf(result[1].trim()) - 1) //Skip to the task at the given index(1-based index)
                    .findFirst() //Get the first task if present
                    .orElseThrow(() -> new IllegalArgumentException("Task index out of bounds."));
        } catch (IllegalArgumentException e) {
            return "You seme to have reached too high. Try reaching for something else!";
        }
        return printsOutputStringWhenChangingPriorityForTasks(task, severity);
    }

    /**
     * Aids in printing the output string when the user changes priority for specific tasks
     * @param task
     * @param severity
     * @return
     */
    private static String printsOutputStringWhenChangingPriorityForTasks(Task task, int severity) {
        String previousPriority = Task.getPriority(task).toString();
        Task.changePriority(task, severity);
        String newPriority = Task.getPriority(task).toString();
        return "Got it. I've changed the priority of this task:\n"
                + task + "\n"
                + "from " + previousPriority + " to " + newPriority + "\n";
    }
}
