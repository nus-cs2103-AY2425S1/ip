import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * FriendlyBot is a simple task management bot that can list, mark, and unmark tasks.
 */
public class FriendlyBot {
    private ArrayList<Task> tasks;
    private int numTasks;
    private static final String taskListFilePath = "./data/task_list.txt";

    public FriendlyBot() {
        this.tasks = new ArrayList<>();
        this.numTasks = 0;
    }

    /**
     * Prints a horizontal bar for visual separation in the console output.
     */
    private static void printHorizontalBar() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a farewell message to the console.
     */
    private void exit() {
        writeToFile();
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Lists all the tasks currently in the task list.
     */
    private void list() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= numTasks; i++) {
            Task task = tasks.get(i - 1);
            System.out.println("    " + i + "." + task.toString());
        }
    }

    /**
     * Marks a task as done based on the input index.
     *
     * @param input The input string containing the command and the task index.
     */
    private void mark(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            if (index > numTasks) {
                System.out.println("    There's no such task yet!");
            } else {
                Task task = tasks.get(index - 1);
                task.markAsDone();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + task.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please input a valid task index!");
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
            if (index > numTasks) {
                System.out.println("    There's no such task yet!");
            } else {
                Task task = tasks.get(index - 1);
                task.markAsUndone();
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + task.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please input a valid task index!");
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
            if (index > numTasks) {
                System.out.println("    There's no such task yet!");
            } else {
                Task task = tasks.remove(index - 1);
                numTasks--;
                System.out.println("    Noted. I've removed this task:");
                System.out.println("      " + task.toString());
                if (numTasks == 1) {
                    System.out.println("    Now you have 1 task in the list.");
                } else if (numTasks == 0) {
                    System.out.println("    You have no more tasks!");
                } else {
                    System.out.println("    Now you have " + numTasks + " tasks in the list.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please input a valid task index!");
        }
    }

    /**
     * Creates a new todo task based on the input response.
     *
     * @param response The input string containing the task description.
     */
    private void createTodo(String response) {
        try {
            String input = response.split("todo ", 2)[1];
            Task newTask = new ToDo(input);
            tasks.add(newTask);
            numTasks++;
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + newTask.toString());
            if (numTasks == 1) {
                System.out.println("    Now you have " + numTasks + " task in the list.");
            } else {
                System.out.println("    Now you have " + numTasks + " tasks in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    Please follow this format: todo {task_description}");
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
                System.out.println("    Please enter a valid date! (YYYY-MM-DD)");
                return;
            }
            Task newTask = new Deadline(descriptions[0], date);
            tasks.add(newTask);
            numTasks++;
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + newTask.toString());
            if (numTasks == 1) {
                System.out.println("    Now you have " + numTasks + " task in the list.");
            } else {
                System.out.println("    Now you have " + numTasks + " tasks in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    Please follow this format: deadline {task_description} /by {date (DDDD-MM-YY)}");
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
                System.out.println("    Please enter a valid date! (YYYY-MM-DD)");
                return;
            }
            Task newTask = new Event(descriptions[0], from, to);
            tasks.add(newTask);
            numTasks++;
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + newTask.toString());
            if (numTasks == 1) {
                System.out.println("    Now you have " + numTasks + " task in the list.");
            } else {
                System.out.println("    Now you have " + numTasks + " tasks in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    Please follow this format: event {task_description} /from {date} /to {date}");
        }
    }

    private void listEventsOnDate(String response) {
        LocalDate date;
        try {
            date = LocalDate.parse(response.split("date ", 2)[1]);
        } catch (DateTimeParseException e) {
            System.out.println("    Please enter a valid date! (YYYY-MM-DD)");
            return;
        }
        for (Task task : this.tasks) {
            if (task instanceof Deadline d) {
                if (d.by.equals(date)) {
                    System.out.println("    " + task.toString());
                }
            } else if (task instanceof Event e) {
                if (e.from.equals(date) || e.to.equals(date) || (e.from.isBefore(date) && e.to.isAfter(date))) {
                    System.out.println("    " + task.toString());
                }
            }
        }
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
            System.out.println("    OOPS!! I'm sorry, that's not a command :-(");
        }
    }

    private void writeToFile() {
        try {
            FileWriter fw = new FileWriter(taskListFilePath);
            StringBuilder sb = new StringBuilder();
            for (Task task : this.tasks) {
                if (task instanceof ToDo) {
                    sb.append("T | ");
                } else if (task instanceof Deadline) {
                    sb.append("D | ");
                } else {
                    sb.append("E | ");
                }
                if (task.isDone) {
                    sb.append("1 | ");
                } else {
                    sb.append("0 | ");
                }
                sb.append(task.description);
                if (task instanceof Deadline) {
                    sb.append(" | ").append(((Deadline) task).by);
                } else if (task instanceof Event e) {
                    sb.append(" | ").append(e.from).append(" | ").append(e.to);
                }
                sb.append("\n");
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("    Something went wrong: " + e.getMessage());
        }
    }

    private void readFromFile() {
        try {
            File f = new File(taskListFilePath);
            if (!f.createNewFile()) {
                Scanner fileReader = new Scanner(f);
                while (fileReader.hasNext()) {
                    // task is in format {type of task} | {0 if not completed, else 1} | {name of task}
                    // | {other task attributes}
                    String task = fileReader.nextLine();
                    try {
                        Task newTask = getTask(task);
                        this.tasks.add(newTask);
                        this.numTasks++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("    Task is not in the expected format: " + task);
                        System.out.println("    Removed from task list!");
                    }
                }
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("    An error occurred: " + e.getMessage());
        }
    }

    private static Task getTask(String task) {
        String[] taskItems = task.split(" \\| ");
        String taskType = taskItems[0];
        String completed = taskItems[1];
        String taskDescription = taskItems[2];
        Task newTask;
        if (taskType.equals("T")) {
            newTask = new ToDo(taskDescription);
        } else if (taskType.equals("D")) {
            newTask = new Deadline(taskDescription, LocalDate.parse(taskItems[3]));
        } else {
            newTask = new Event(taskDescription, LocalDate.parse(taskItems[3]), LocalDate.parse(taskItems[4]));
        }
        if (completed.equals("1")) {
            newTask.markAsDone();
        }
        return newTask;
    }

    /**
     * The main method to run FriendlyBot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        FriendlyBot friendlyBot = new FriendlyBot();
        // Read file
        friendlyBot.readFromFile();
        // Initialise variables
        Scanner reader = new Scanner(System.in);
        // Start of Chat Bot
        FriendlyBot.printHorizontalBar();
        System.out.println("    Hello! I'm Friendly Bot");
        System.out.println("    What can I do for you?");
        FriendlyBot.printHorizontalBar();
        String response = "";
        // Commands
        while (!response.equals("bye")) {
            response = reader.nextLine();
            FriendlyBot.printHorizontalBar();
            friendlyBot.handleCommand(response);
            FriendlyBot.printHorizontalBar();
        }
        reader.close();
    }
}
