import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * FriendlyBot is a simple task management bot that can list, mark, and unmark tasks.
 */
public class FriendlyBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String taskListFilePath = "./data/task_list.txt";
    private static final String taskListFilePathWithoutFile = "./data";

    public FriendlyBot(String filePath, String filePathWithoutFile) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, filePathWithoutFile);
        this.tasks = new TaskList(this.storage.load());
    }

    /**
     * Prints a farewell message to the console.
     */
    private void exit() {
        Command cmd = new ExitCommand();
        cmd.execute(this.tasks, this.ui, this.storage);
    }

    /**
     * Lists all the tasks currently in the task list.
     */
    private void list() {
        Command cmd = new ListCommand();
        cmd.execute(this.tasks, this.ui, this.storage);
    }

    /**
     * Marks a task as done based on the input index.
     *
     * @param input The input string containing the command and the task index.
     */
    private void mark(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            Command cmd = new MarkCommand(true, index);
            cmd.execute(this.tasks, this.ui, this.storage);
        } catch (NumberFormatException e) {
            Ui.print("Please input a valid task index!");
        }
    }

    /**
     * Marks a task as not done based on the input index.
     *
     * @param input The input string containing the command and the task index.
     */
    private void unmark(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            Command cmd = new MarkCommand(false, index);
            cmd.execute(this.tasks, this.ui, this.storage);
        } catch (NumberFormatException e) {
            Ui.print("Please input a valid task index!");
        }
    }

    /**
     * Deletes a task based on the input index.
     *
     * @param input The input string containing the task index.
     */
    private void delete(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            DeleteCommand command = new DeleteCommand(index);
            command.execute(this.tasks, this.ui, this.storage);
        } catch (NumberFormatException e) {
            Ui.print("Please input a valid task index!");
        }
    }

    /**
     * Creates a new todo task based on the input response.
     *
     * @param response The input string containing the task description.
     */
    private void createTodo(String response) {
        try {
            String taskDescription = response.split("todo ", 2)[1];
            Command cmd = new AddCommand("todo", taskDescription);
            cmd.execute(this.tasks, this.ui, this.storage);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.print("Please follow this format: todo {task_description}");
        }
    }

    /**
     * Creates a new deadline task based on the input response.
     *
     * @param response The input string containing the task description and deadline.
     */
    private void createDeadline(String response) {
        try {
            String input = response.split("deadline ", 2)[1];
            String[] descriptions = input.split(" /by ");
            LocalDate date;
            try {
                date = LocalDate.parse(descriptions[1]);
            } catch (DateTimeParseException e) {
                Ui.print("Please enter a valid date! (YYYY-MM-DD)");
                return;
            }
            String taskDescription = descriptions[0];
            Command cmd = new AddCommand("deadline", taskDescription, date);
            cmd.execute(this.tasks, this.ui, this.storage);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.print("Please follow this format: deadline {task_description} /by {date}");
            Ui.print("Date must be in YYYY-MM-DD format!");
        }
    }

    /**
     * Creates a new event task based on the input response.
     *
     * @param response The input string containing the task description, start date, and end date.
     */
    private void createEvent(String response) {
        try {
            String input = response.split("event ", 2)[1];
            String[] descriptions = input.split(" /from | /to ");
            LocalDate from;
            LocalDate to;
            try {
                from = LocalDate.parse(descriptions[1]);
                to = LocalDate.parse(descriptions[2]);
            } catch (DateTimeParseException e) {
                Ui.print("Please enter a valid date! (YYYY-MM-DD)");
                return;
            }
            String taskDescription = descriptions[0];
            Command cmd = new AddCommand("event", taskDescription, from, to);
            cmd.execute(this.tasks, this.ui, this.storage);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.print("Please follow this format: event {task_description} /from {date} /to {date}");
            Ui.print("Date must be in YYYY-MM-DD format!");
        }
    }

    private void listEventsOnDate(String response) {
        LocalDate date;
        try {
            date = LocalDate.parse(response.split("date ", 2)[1]);
        } catch (DateTimeParseException e) {
            Ui.print("Please enter a valid date! (YYYY-MM-DD)");
            return;
        }
        Command cmd = new DateCommand(date);
        cmd.execute(this.tasks, this.ui, this.storage);
    }

    private void handleCommand(String command) {
        if (command.equals("bye")) {
            this.exit();
        } else if (command.equals("list")) {
            this.list();
        } else if (command.startsWith("mark")) {
            this.mark(command);
        } else if (command.startsWith("unmark")) {
            this.unmark(command);
        } else if (command.startsWith("delete")) {
            this.delete(command);
        } else if (command.startsWith("todo")) {
            this.createTodo(command);
        } else if (command.startsWith("deadline")) {
            this.createDeadline(command);
        } else if (command.startsWith("event")) {
            this.createEvent(command);
        } else if (command.startsWith("date")) {
            this.listEventsOnDate(command);
        } else {
            Ui.print("OOPS!! I'm sorry, that's not a command :-(");
        }
    }

    /**
     * The main method to run FriendlyBot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        FriendlyBot friendlyBot = new FriendlyBot(taskListFilePath, taskListFilePathWithoutFile);
        // Initialise variables
        Scanner reader = new Scanner(System.in);
        // Start of Chat Bot
        friendlyBot.ui.showWelcome();
        String response = "";
        boolean isExit = false;
        // Commands
        while (!isExit) {
            response = reader.nextLine();
            friendlyBot.ui.printHorizontalBar();
            Command cmd = Parser.parse(response);
            cmd.execute(friendlyBot.tasks, friendlyBot.ui, friendlyBot.storage);
            isExit = cmd.isExit();
            friendlyBot.ui.printHorizontalBar();
        }
        reader.close();
    }
}
