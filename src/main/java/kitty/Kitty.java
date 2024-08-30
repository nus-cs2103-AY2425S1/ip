package kitty;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Provides an interactive ChatBot named Kitty.
 */
public class Kitty {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Kitty() {
        tasks = new TaskList();
        storage = new Storage();
        ui = new Ui(tasks, storage);
    }

    /**
     * Starts the ChatBot.
     *
     * @param args Program input.
     */
    public static void main(String[] args) {
        new Kitty().run();
    }

    public void run() {
        ui.run();
    }




    /**
     * Searches for the tasks.Task with keyword in the name.
     * 
     * @param str The keyword.
     */
//    public static void find(String str) {
//        try {
//            String[] aux = str.split(" ");
//            if (aux.length != 2) {
//                throw new kittyexceptions.FindException();
//            }
//
//            System.out.println(DIVISION_LINE);
//            System.out.println("Meow~ Here you are!");
//            Tasks.stream().filter(task -> task.containsKeyword(aux[1]))
//                    .forEach(System.out::println);
//            System.out.println("\n" + DIVISION_LINE);
//
//        } catch (kittyexceptions.FindException e) {
//            System.out.println(e);
//            System.out.println("\n" + DIVISION_LINE);
//        }
//
//    }

//    /**
//     * Creates tasks from valid user input, adds to list and updates the file.
//     *
//     * @param item Potential task to add.
//     */
//    private static void add(String item) {
//        String[] aux = item.split(" ", 2);
//        String type = aux[0];
//        tasks.Task tmp;
//        try {
//            // input check
//            if (aux.length == 1
//                    || (!type.equals("todo") && !type.equals("deadline") && !type.equals("event"))) {
//                System.out.println(DIVISION_LINE);
//                System.out.println("Oooops... I don't know what you want to do though...");
//                throw new kittyexceptions.TaskException();
//            }
//
//            // create corresponding tasks according to valid input
//            String name = aux[1].trim();
//            tmp = type.equals("todo")
//                    ? new tasks.Todo(name)
//                    : type.equals("deadline")
//                    ? createDeadline(name)
//                    : createEvent(name);
//
//            // update data structure and file
//            Tasks.add(tmp);
//            String data = tmp.getTaskData();
//            try {
//                addLine(data);
//            } catch (IOException e) {
//                System.out.println("Write failed");
//            }
//
//            // output in the terminal
//            System.out.println(DIVISION_LINE);
//            System.out.println("Okie, I added it into the list:");
//            System.out.println("  " + tmp);
//            System.out.printf("Now you have %d tasks in the list.\n\n", Tasks.size());
//            System.out.println(DIVISION_LINE);
//        } catch (kittyexceptions.KittyException e) {
//            System.out.println(e);
//            System.out.println("\n" + DIVISION_LINE);
//        }
//    }


//    /**
//     * Creates tasks.Deadline object if the command is in valid form.
//     *
//     * @param str The deadline command
//     * @return A tasks.Task object.
//     * @throws kittyexceptions.DeadlineException If the format is not valid for an event.
//     * @throws kittyexceptions.DateFormatException If the time specification is not in valid form.
//     */
//    private static tasks.Task createDeadline(String str) throws kittyexceptions.DeadlineException, kittyexceptions.DateFormatException{
//        String[] parts = str.split("/by");
//        if (parts.length != 2)
//            throw new kittyexceptions.DeadlineException();
//        try {
//            LocalDateTime time = LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMAT);
//            return new tasks.Deadline(parts[0], time);
//        } catch (DateTimeParseException e) {
//            throw new kittyexceptions.DateFormatException();
//        }
//    }
//
//    /**
//     * Creates tasks.Event object if the command if the command is in valid format.
//     *
//     * @param str The event command.
//     * @return a tasks.Task object.
//     * @throws kittyexceptions.EventException If the format is not valid for an event.
//     * @throws kittyexceptions.DateFormatException If the time specification is not in valid form.
//     */
//    private static tasks.Task createEvent(String str) throws kittyexceptions.EventException, kittyexceptions.DateFormatException{
//        String[] parts = str.split("/from|/to");
//        if (parts.length != 3)
//            throw new kittyexceptions.EventException();
//        try {
//            LocalDateTime from = LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMAT);
//            LocalDateTime to = LocalDateTime.parse(parts[2].trim(), DATE_TIME_FORMAT);
//            return new tasks.Event(parts[0], from, to);
//        } catch (DateTimeParseException e) {
//            throw new kittyexceptions.DateFormatException();
//        }
//    }




    /**
     * Helps to determine the task index to mark, unmark and delete tasks in the list.
     *
     * @param input The command to investigate.
     * @return the first number of the command.
     */
//    private static int extractFirstNumber(String input) {
//        // Replace all non-digit characters with spaces
//        String cleanedInput = input.replaceAll("\\D+", " ");
//
//        // Split the cleaned string by spaces
//        String[] parts = cleanedInput.trim().split("\\s+");
//
//        // Check if there are any parts and parse the first one
//        if (parts.length > 0) {
//            try {
//                return Integer.parseInt(parts[0]);
//            } catch (NumberFormatException e) {
//                // Handle the case where parsing fails
//                return -1;
//            }
//        }
//
//        // Return null if no number is found
//        return -1;
//    }

}
