import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import storage.*;
import ui.*;
import parser.Parser;
import tasklist.TaskList;
import ui.Ui;
import tasks.*;
import exceptions.*;


enum CommandType {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE;
}

public class TheOrangeRatchetCat {

    public static void main(String[] args) {
        List<Task> items = new ArrayList<>();
        Storage.loadTasks(items); // Load tasks from file at startup
        ratchetCatBot(items); // Pass the list of tasks to the chatbot
        Storage.saveTasks(items); // Save tasks to file before exiting
    }

    private static void ratchetCatBot(List<Task> items) {
        Ui.sayWelcome();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Reads a line of text
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                input = TaskList.checkList(items, scanner);
                continue;
            }
            if (input.startsWith("mark")) {
                String numberPart = input.substring(4).trim();
                input = TaskList.markingTask(numberPart, items, scanner);
                continue;
            }
            if (input.startsWith("unmark")) {
                String numberPart = input.substring(6).trim();
                input = TaskList.unmarkingTask(numberPart, items, scanner);
                continue;
            }
            if (input.startsWith("todo")) {
                try {
                    String taskDescription = input.substring(4).trim();
                    input = TaskList.addingToDo(taskDescription, items, scanner);
                    continue;
                } catch (TheOrangeRatchetCatException e) {
                    System.out.println(e.getMessage());
                    input = scanner.nextLine();
                    continue;
                }
            }
            if (input.startsWith("deadline")) {
                try {
                    input = TaskList.addingDeadline(input, items, scanner);
                    continue;
                } catch (TheOrangeRatchetCatException e) {
                    System.out.println(e.getMessage());
                    input = scanner.nextLine();
                    continue;
                }
            }
            if (input.startsWith("event")) {
                try {
                    input = TaskList.addingEvent(input, items, scanner);
                    continue;
                } catch (TheOrangeRatchetCatException e) {
                    System.out.println(e.getMessage());
                    input = scanner.nextLine();
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Correct input format for adding event: event <Task> /from <input> /to <input>");
                    input = scanner.nextLine();
                    continue;
                }
            }
            if (input.startsWith("delete")) {
                int indexToDelete = Integer.parseInt(input.substring(6).trim());
                input = TaskList.deleteTask(indexToDelete, items, scanner);
                continue;
            }
            if (input.startsWith("find")) {
                String findWord = input.substring(4).trim();
                input = TaskList.find(findWord, items, scanner);
                continue;
            }
            if (input.startsWith("on")) {
                try {
                    LocalDate localDate = LocalDate.parse(input.substring(2).trim());
                    input = TaskList.activitiesOnThisDate(localDate, items, scanner);
                    continue;
                } catch (DateTimeParseException e) {
                    System.out.println("The date follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!");
                    input = scanner.nextLine();
                    continue;
                }
            }
            System.out.print("Inappropriate Command try again with adding either a Deadline/Todo/Event: ");
            input = scanner.nextLine(); // Reads the next line of input text again
        }
        Ui.goodByeCat();
        scanner.close(); // Close the scanner to avoid resource leaks
    }
}
