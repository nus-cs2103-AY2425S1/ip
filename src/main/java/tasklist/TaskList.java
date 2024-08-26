package tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import tasks.*;
import exceptions.*;

public class TaskList {
    public static String checkList(String input, List<Task> items, Scanner scanner) {
        int index = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task item : items) {
            System.out.println(index + "." + item);
            index++;
        }
        return scanner.nextLine();
    }

    public static String markingTask(String input, List<Task> items, Scanner scanner) {
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            Task markingTask = items.get(taskIndex);
            markingTask.setDone(true);
            //markingTask.isDone = true;
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + markingTask.getStatusIcon() + "] " + markingTask.getDes());
            //System.out.println("[" + markingTask.getStatusIcon() + "] " + markingTask.description);
            System.out.println("____________________________________________________________");
            return scanner.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        }
    }

    public static String unmarkingTask(String input, List<Task> items, Scanner scanner) {
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            Task markingTask = items.get(taskIndex);
            markingTask.setDone(false);
            //markingTask.isDone = false;
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + markingTask.getStatusIcon() + "] " + markingTask.getDes());
            //System.out.println("[" + markingTask.getStatusIcon() + "] " + markingTask.description);
            System.out.println("____________________________________________________________");
            return scanner.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        }
    }

    public static String deleteTask(int index, List<Task> items, Scanner scanner) {
        try {
            Task taskToDelete = items.get(index - 1);
            items.remove(index - 1);
            Task.taskCount--;
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskToDelete);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
            return scanner.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        }
    }

    public static String addingToDo(String input, List<Task> items, Scanner scanner) throws TheOrangeRatchetCatException {
        if (input.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing!");
        }
        Task nextTask = new ToDo(input);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTask);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
        items.add(nextTask);
        return scanner.nextLine();
    }

    public static String addingDeadline(String input, List<Task> items, Scanner scanner) throws TheOrangeRatchetCatException {
        // Split the input string by "/by"
        String[] parts = input.split("/by");
        // The description is the first part after removing the word "deadline"
        String taskDescription = parts[0].replace("deadline", "").trim();
        if (taskDescription.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing!");
        }
        // The "by" part is the second part, if it exists
        String date = parts.length > 1 ? parts[1].trim() : "";
        LocalDate dateBy;

        try { // Utilises LocalDate static method to parse input
            dateBy = LocalDate.parse(date); // Could throw a DateTimeParseException
            //System.out.println(dateBy);
        } catch (DateTimeParseException e) {
            System.out.println("The deadline follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!");
            return scanner.nextLine();
        }

        if (date.isEmpty()) {
            throw new TheOrangeRatchetCatException("You need to provide a deadline!");
        }
        // If task added successfully, the program will reach here!
        Task nextTask = new Deadline(taskDescription, dateBy);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTask);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
        items.add(nextTask);
        return scanner.nextLine();
    }

    public static String addingEvent(String input, List<Task> items, Scanner scanner) throws TheOrangeRatchetCatException {
        // Split the input string by "/from"
        String[] parts = input.split("/from");
        // The taskDescription is the first part after removing the word "event"
        String taskDescription = parts[0].replace("event", "").trim();
        if (taskDescription.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing!");
        }
        // Further split the remaining part by "/to"
        String[] dateParts = parts[1].split("/to");

        // The "fromDate" is the first part
        String fromDate = dateParts[0].trim();
        // The "toDate" is the second part
        String toDate = dateParts.length > 1 ? dateParts[1].trim() : "";
        LocalDate toLocalDate;
        LocalDate fromLocalDate;
        try { // Utilises LocalDate static method to parse input
            toLocalDate = LocalDate.parse(toDate); // Could throw a DateTimeParseException
            fromLocalDate = LocalDate.parse(fromDate);
        } catch (DateTimeParseException e) {
            System.out.println("The event follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!");
            return scanner.nextLine();
        }

        if (toDate.isEmpty()) {
            throw new TheOrangeRatchetCatException("You need to specify an end time!");
        }
        Task nextTask = new Event(taskDescription, fromLocalDate, toLocalDate);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTask);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
        items.add(nextTask);
        return scanner.nextLine();
    }

    // Suppose to print out all activities associated with this date. To be implemented
    public static void activitiesOnThisDate(LocalDate date) {

    }
}
