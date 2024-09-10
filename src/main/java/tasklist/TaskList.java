package tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import exceptions.TheOrangeRatchetCatException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import ui.Ui;

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
        int index = 1;
        StringBuilder sbr = new StringBuilder("Here are the tasks in your list: \n");
        System.out.println("Here are the tasks in your list:");
        for (Task item : items) {
            System.out.println(index + "." + item);
            sbr.append(index + "." + item + "\n");
            index++;
        }
        //return scanner.nextLine();
        return sbr.toString();
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
            int taskIndex = Integer.parseInt(input) - 1;
            Task markingTask = items.get(taskIndex);
            markingTask.setDone(true);
            Ui.markingTaskPrint(markingTask);
            return "Nice! I've marked this task as done:\n"
                    + "[" + markingTask.getStatusIcon() + "] " + markingTask.getDes() + "\n";
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
            int taskIndex = Integer.parseInt(input) - 1;
            Task markingTask = items.get(taskIndex);
            markingTask.setDone(false);
            Ui.unmarkingTaskPrint(markingTask);
            return "OK, I've marked this task as not done yet:\n"
                    + "[" + markingTask.getStatusIcon() + "] " + markingTask.getDes() + "\n";
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
            Task taskToDelete = items.get(index - 1);
            items.remove(index - 1);
            Task.decrementTaskCount();
            Ui.deletingTaskPrint(taskToDelete);
            return "Noted. I've removed this task:\n"
                    + taskToDelete + "\n"
                    + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
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
        Task nextTask = new ToDo(input);
        Ui.addingToDoPrint(nextTask);
        items.add(nextTask);
        //return scanner.nextLine();
        return "Got it. I've added this task:\n"
                + nextTask + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
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
        System.out.println("Date is: " + date);
        LocalDate dateBy;

        try { // Utilises LocalDate static method to parse input
            dateBy = LocalDate.parse(date); // Could throw a DateTimeParseException
            //System.out.println(dateBy);
        } catch (DateTimeParseException e) {
            System.out.println("1231");
            System.out.println("The deadline follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!");
            //return scanner.nextLine();
            return "The deadline follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!";
        }

        if (date.isEmpty()) {
            //throw new TheOrangeRatchetCatException("You need to provide a deadline!");
            return "Adding a deadline follows a specific format : deadline <taskDescription> /by<YYYY>-<MM>-<DD>";
        }
        // If task added successfully, the program will reach here!
        Task nextTask = new Deadline(taskDescription, dateBy);
        Ui.addingDeadlinePrint(nextTask);
        items.add(nextTask);
        //return scanner.nextLine();
        return "Got it. I've added this task:\n"
                + nextTask + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
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
        // Split the input string by "/from"
        String[] parts = input.split("/from");

        // Assert that the input contains the "/from" delimiter.
        assert parts.length > 1 : "Input must contain '/from' to specify the start date.";
        // The taskDescription is the first part after removing the word "event"
        String taskDescription = parts[0].replace("event", "").trim();
        if (taskDescription.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing! "
                    + "Correct input format for adding event: event <Task> /from <input> /to <input>");
        }
        // Further split the remaining part by "/to"
        String[] dateParts = parts[1].split("/to");

        // Assert that the input contains the "/to" delimiter.
        assert dateParts.length > 1 : "Input must contain '/to' to specify the end date.";

        // The "fromDate" is the first part
        String fromDate = dateParts[0].trim();
        // The "toDate" is the second part
        String toDate = dateParts.length > 1 ? dateParts[1].trim() : "";

        // Assert that both fromDate and toDate are not empty
        assert !fromDate.isEmpty() : "From date must not be empty.";
        assert !toDate.isEmpty() : "To date must not be empty.";
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

        // Assert that fromLocalDate is not after toLocalDate
        assert !fromLocalDate.isAfter(toLocalDate) : "Start date must be before or on the end date.";

        if (toDate.isEmpty()) {
            throw new TheOrangeRatchetCatException("You need to specify an end time!");
        }
        Task nextTask = new Event(taskDescription, fromLocalDate, toLocalDate);
        Ui.addingEventPrint(nextTask);
        items.add(nextTask);
        //return scanner.nextLine();
        return "Got it. I've added this task:\n"
                + nextTask + "\n"
                + "Now you have " + Task.getTaskCount() + " tasks in the list.\n";
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
        int index = 1;
        for (Task task : items) {
            if (task instanceof Deadline) {
                LocalDate byDate = ((Deadline) task).getDeadlineDate();
                if (byDate.isAfter(date)) {
                    System.out.println(index + "." + task);
                    sbr.append(index + "." + task + "\n");
                    index++;
                }
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
            System.out.println("Looks Like there's no task with taskDescription that contains " + "'" + input + "'");
            System.out.println("Try Looking for something else!");
            return "Looks like there's no task with taskDescription that contains '" + input + "'\n"
                    + "Try looking for something else!";
        }
        System.out.println("____________________________________________________________");
        //return scanner.nextLine();
        return sbr.toString();
    }

    // Input is a string of multiple tasks split by \n
    // Create and process each task.
    // And pass into addMany(Tasks ...) {}
    public static String addMany(String input, List<Task> items, Scanner scanner) {
        return "";
    }
}
