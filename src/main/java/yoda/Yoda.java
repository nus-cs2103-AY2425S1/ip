package yoda;

import yoda.exceptions.EmptyInputException;
import yoda.exceptions.InvalidInputException;
import yoda.exceptions.NonsenseInputException;
import yoda.tasks.Deadline;
import yoda.tasks.Event;
import yoda.tasks.Task;
import yoda.tasks.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Yoda {
    static String line = "____________________________________________________________";
    static String welcomeMessage = "Hello! For you, what can I do?";
    static String exitMessage = "Bye. See you again soon, I hope to.";

    static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        tasks = Storage.loadTasks();
        Scanner scanner = new Scanner(System.in);

        printLine();
        System.out.println(welcomeMessage);
        printLine();

        while (true) {
            String input = scanner.nextLine().trim();
            try {
                handle(input);
            } catch (InvalidInputException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }
    }

    public static void handle(String input) throws InvalidInputException {
        String[] splitInput = input.split(" ", 2);
        if (Objects.equals(input, "")){
            throw new EmptyInputException();
        } else if (Objects.equals(splitInput[0], "bye")) {
            System.out.println(exitMessage);
            printLine();
        } else if (Objects.equals(splitInput[0], "list")) {
            printLine();
            System.out.println("Do or do not, there is no try.");
            Task[] taskArray = tasks.toArray(new Task[0]);
            for (int i = 0; i < taskArray.length; i++) {
                System.out.printf("%d. %s\n", i + 1, taskArray[i]);
            }
            printLine();
        } else if (Objects.equals(splitInput[0], "mark")) {
            if (!checkForInt(input)) {
                throw new InvalidInputException("Mark... which one?");
            }
            int index = Integer.parseInt(splitInput[1]);
            Task currentTask = tasks.get(index - 1);
            currentTask.markDone();
            printLine();
            System.out.println("Good job! Marked this as done, I have");
            System.out.printf("%s\n", tasks.get(index - 1));
            printLine();

        } else if (Objects.equals(splitInput[0], "unmark")) {
            if (!checkForInt(input)) {
                throw new InvalidInputException("Unmark... which one?");
            }
            int index = Integer.parseInt(splitInput[1]);
            Task currentTask = tasks.get(index - 1);
            currentTask.markNotDone();
            printLine();
            System.out.println("Marked this as not done, I have");
            System.out.printf("%s\n", tasks.get(index - 1));
            printLine();
        } else if (Objects.equals(splitInput[0], "delete")) {
            if (!checkForInt(input)) {
                throw new InvalidInputException("Delete... which one?");
            }
            int index = Integer.parseInt(splitInput[1]);
            Task currentTask = tasks.get(index - 1);
            tasks.remove(index - 1);

            printLine();
            System.out.println("Deleted this, I have.");
            System.out.printf("%s\n", currentTask);
            System.out.println(String.format("Now you have %d tasks in the list\n", tasks.size()));
            printLine();
        } else if (Objects.equals(splitInput[0], "todo")) {
            if (!checkValidToDo(input)) {
                throw new InvalidInputException("A todo must have a description, no...?");
            }
            String task = splitInput[1];
            ToDo newTask = new ToDo(task);
            tasks.add(newTask);
            printLine();
            System.out.println("Added task:\n" + newTask + "\n"
                    + String.format("Now you have %d tasks in the list", tasks.size()));
            printLine();

        } else if (Objects.equals(splitInput[0], "deadline")) {
            if (!checkValidDeadline(input)) {
                throw new InvalidInputException("A deadline must have a description and due by time, no...?");
            }
            String task = splitInput[1];
            String[] splitTask = task.split("/by ", 2);
            Deadline newTask = null;
            try {
                LocalDate by = LocalDate.parse(splitTask[1]);
                newTask = new Deadline(splitTask[0], by);
                tasks.add(newTask);
                printLine();
                System.out.println("Added task:\n" + newTask + "\n"
                        + String.format("Now you have %d tasks in the list", tasks.size()));
                printLine();
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Deadline must be in format yyyy-mm-dd");
            }
        } else if (Objects.equals(splitInput[0], "event")) {
            if (!checkValidEvent(input)) {
                throw new InvalidInputException("An event must have a description, start time and end time, no...?");
            }
            String task = splitInput[1];
            String[] splitTask = task.split(" /from ", 2);
            String[] times = splitTask[1].split(" /to ", 2);

            Event newTask = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            try {
                LocalDateTime from = LocalDateTime.parse(times[0], formatter);
                LocalDateTime to = LocalDateTime.parse(times[1], formatter);
                newTask = new Event(splitTask[0], from, to);
                tasks.add(newTask);
                printLine();
                System.out.println("Added task:\n" + newTask + "\n"
                        + String.format("Now you have %d tasks in the list", tasks.size()));
                printLine();
            } catch (DateTimeParseException e) {
                System.out.println("Dates and times must be in format: yyyy-mm-dd HHmm");
            }

        } else {
            throw new NonsenseInputException();
        }
        Storage.saveTasks(tasks);
    }

    public static boolean checkValidToDo(String input) {
        String[] splitInput = input.split(" ", 2);
        return splitInput.length == 2;
    }
    public static boolean checkValidDeadline(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            String[] splitTask = splitInput[1].split("/by ", 2);
            return splitTask.length == 2;
        } else {
            return false;
        }
    }
    public static boolean checkValidEvent(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            String[] splitTask = splitInput[1].split("/from ", 2);
            if (splitTask.length == 2) {
                String[] times = splitTask[1].split("/to ", 2);
                return times.length == 2;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public static boolean checkForInt(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            return splitInput[1].matches("\\d+");
        } else {
            return false;
        }
    }


    public static void printLine() {
        System.out.println(line);
    }
}
