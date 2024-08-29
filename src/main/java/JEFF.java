import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class JEFF {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String DIR_PATH = "./data";
    private static final String FILE_PATH = DIR_PATH + "/JEFF.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private Ui ui;
    private Storage storage;

    public JEFF() {
        this.ui = new Ui();
        this.storage = new Storage(DIR_PATH, FILE_PATH);
    }

    public static void main(String[] args) {
        new JEFF().run();
    }

    public void run() {
        // Load saved files (if any)
        taskList = storage.loadData();
        ui.showWelcome();

        boolean continueChat = true;
        while (continueChat) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                // parse the command
                String[] parts = input.split(" ", 2);
                if (this.handleCommand(parts)) continueChat = false;
            } catch (JEFFException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showExit();
    }

    private boolean handleCommand(String[] parts) throws JEFFException {
        switch (parts[0].toLowerCase()) {
        case "bye":
            return true;
        case "list":
            printList();
            break;
        case "print":
            if (parts.length == 2) {
                printTasksOnDate(parts[1]);
            } else {
                throw new JEFFException("You must provide a date after the command!");
            }
            break;
        case "mark":
            if (parts.length == 2 && isNumeric(parts[1])) {
                markDone(Integer.parseInt(parts[1]));
            } else {
                throw new JEFFException("You must provide only one number after mark!");
            }
            break;
        case "unmark":
            if (parts.length == 2 && isNumeric(parts[1])) {
                markNotDone(Integer.parseInt(parts[1]));
            } else {
                throw new JEFFException("You must provide only one number after unmark!");
            }
            break;
        case "delete":
            if (parts.length == 2 && isNumeric(parts[1])) {
                deleteTask(Integer.parseInt(parts[1]));
            } else {
                throw new JEFFException("You must provide only one number after unmark!");
            }
            break;
        case "todo":
            if (parts.length != 2) {
                throw new JEFFException("You must provide a valid task to do!");
            } else {
                addTask(parts[1], "todo");
                storage.saveTask(taskList);
            }
            break;
        case "deadline":
            if (parts.length != 2) {
                throw new JEFFException("You must provide a valid deadline task!");
            } else {
                addTask(parts[1], "deadline");
                storage.saveTask(taskList);
            }
            break;
        case "event":
            if (parts.length != 2) {
                throw new JEFFException("You must provide a valid event task!");
            } else {
                addTask(parts[1], "event");
                storage.saveTask(taskList);
            }
            break;
        default:
            throw new JEFFException("That is not a valid command!");
        }
        return false;
    }

    private static void printTasksOnDate(String dateString) throws JEFFException {
        try {
            DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
            System.out.println("Tasks due on " + date.format(DATE_FORMATTER) + ":");
            boolean found = false;
            for (Task task : taskList) {
                if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    if (deadlineTask.getDueDate().toLocalDate().equals(date)) {
                        System.out.println(deadlineTask);
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("No tasks due on this date.");
            }
        } catch (DateTimeParseException e) {
            throw new JEFFException("Invalid date format! Please use 'DD/MM/YYYY'.");
        }
    }

    private static void deleteTask(int i) throws JEFFException {
        if (i <= 0 || i > taskList.size()) {
            throw new JEFFException("The number is outside the range!");
        }
        System.out.println("Ok, I will delete this task:");
        System.out.printf("%s\n", taskList.get(i - 1));
        taskList.remove(i - 1);
    }

    private void markNotDone(int i) throws JEFFException {
        if (i <= 0 || i > taskList.size()) {
            throw new JEFFException("The number is outside the range!");
        }
        taskList.get(i - 1).markNotDone();
        storage.updateSave(taskList, i - 1);
        System.out.println("Ok, I marked this as undone:");
        System.out.printf("%s\n", taskList.get(i - 1));
    }

    private void markDone(int i) throws JEFFException {
        if (i <= 0 || i > taskList.size()) {
            throw new JEFFException("The number is outside the range!");
        }
        taskList.get(i - 1).markDone();
        storage.updateSave(taskList, i - 1);
        System.out.println("Alrighty, I marked this as done:");
        System.out.printf("%s\n", taskList.get(i - 1));
    }

    // Overloaded `addTask` to have more functionality
    private static void addTask(String input, String task) throws JEFFException {

        switch (task.toLowerCase()) {
        case "todo":
            taskList.add(new ToDo(input));
            break;
        case "deadline":
            String[] deadline_parts = input.split("/by", 2);
            if (deadline_parts.length < 2) {
                throw new JEFFException("You did not follow the format for providing a deadline!" +
                        "\nIt should be: task /by date");
            }
            try {
                taskList.add(
                        new Deadline(deadline_parts[0].trim(),
                                LocalDateTime.parse(deadline_parts[1].trim(), FORMATTER)
                        )
                );
            } catch (DateTimeParseException e) {
                throw new JEFFException("You need to format your dates as follows: DD/MM/YYYY HHMM");
            }
            break;
        case "event":
            String[] event_parts = input.split("/from | /to ", 3);
            if (event_parts.length < 3) {
                throw new JEFFException("You did not follow the format for providing a deadline!" +
                        "\nIt should be: task /from date /to date");
            }
            try {
                taskList.add(
                        new Event(event_parts[0].trim(),
                                LocalDateTime.parse(event_parts[1].trim(), FORMATTER),
                                LocalDateTime.parse(event_parts[2].trim(), FORMATTER)
                        )
                );
            } catch (DateTimeParseException e) {
                throw new JEFFException("You need to format your dates as follows: DD/MM/YYYY HHMM");
            }
            break;
        default:
            throw new JEFFException("Unsupported Task!");
        }
        // Save the task
        System.out.printf("added: %s\n", taskList.get(taskList.size() - 1));
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal
    }

    private static void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }
    }

}