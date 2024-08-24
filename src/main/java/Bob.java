import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

public class Bob {
    private static final List<Task> taskList = new ArrayList<>();
    private static int numTasks = 0;

    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello! I'm Bob the bot!\nHow can I help you?");

        while (true) {
            String userInput = br.readLine().trim();

            try {
                Command command = getCommand(userInput);
                String taskDetails = getTaskDetails(userInput);

                switch (command) {
                    case BYE:
                        System.out.println(commandBye());
                        return;

                    case LIST:
                        System.out.println(commandList());
                        break;

                    case MARK:
                        System.out.println(commandMark(taskDetails));
                        break;

                    case UNMARK:
                        System.out.println(commandUnmark(taskDetails));
                        break;

                    case TODO:
                        System.out.println(commandTodo(taskDetails));
                        break;

                    case DEADLINE:
                        System.out.println(commandDeadline(taskDetails));
                        break;

                    case EVENT:
                        System.out.println(commandEvent(taskDetails));
                        break;

                    case DELETE:
                        System.out.println(commandDelete(taskDetails));
                        break;

                    case UNKNOWN:
                    default:
                        throw new BobException("Sorry, I do not understand. Please try something else.");
                }
            } catch (BobException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    static Command getCommand(String userInput) throws BobException {
        if (userInput.isEmpty() || userInput.equals(" ")) {
            throw new BobException("You did not key in anything...");
        }
        String commandStr = userInput.split(" ", 2)[0].toUpperCase();
        try {
            return Command.valueOf(commandStr);
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    static String getTaskDetails(String userInput) throws BobException {
        String[] args = userInput.split(" ", 2);
        if (args.length < 2) {
            return "";
        }
        return args[1].trim();
    }

    static Task getTask(int taskNum) throws BobException {
        try {
            return taskList.get(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("No such task number found. Please provide a valid task number.");
        }
    }

    static void addTask(Task task) {
        taskList.add(task);
        numTasks++;
    }

    static String getTaskList() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 1; i <= numTasks; i++) {
            Task currTask = taskList.get(i - 1);
            if (i == numTasks) {
                tasks.append(i).append(". ").append(currTask); // currTask.toString() ?
                continue;
            }
            tasks.append(i).append(". ").append(currTask).append("\n");
        }
        return tasks.toString();
    }

    static String commandBye() {
        return "Bye! Hope to see you again :)";
    }

    static String commandList() {
        return "Your list of tasks:\n" + getTaskList();
    }

    static String commandMark(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = getTask(taskNum);
            currTask.markAsDone();
            return "Good Job! Marking this task as done:\n " + currTask;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    static String commandUnmark(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = getTask(taskNum);
            currTask.markAsUndone();
            return "Okay, marking this task as not done yet:\n " + currTask;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    static String commandTodo(String taskDetails) throws BobException {
        String format = "Add ToDo task in the following format:\ntodo <description>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }
        Task task = new ToDo(taskDetails);
        addTask(task);
        return "Adding ToDo task:\n " + task
                + "\nTotal number of tasks in your list: " + numTasks;
    }

    static String commandDeadline(String taskDetails) throws BobException {
        String format = "Add Deadline task in the following format:\n"
                + "deadline <description> /by <due date>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }

        try {
            String[] params = taskDetails.split(" /");
            String description = params[0];
            String by = params[1].split(" ", 2)[1];
            Task task = new Deadline(description, by);
            addTask(task);
            return "Adding Deadline task:\n " + task
                    + "\nTotal number of tasks in your list: " + numTasks;
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("You may have missing details or wrong format!\n" + format);
        }
    }

    static String commandEvent(String taskDetails) throws BobException {
        String format = "Add Event task in the following format:\n"
                + "deadline <description> /from <start date> /to <due date>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }

        try {
            String[] params = taskDetails.split(" /");
            String description = params[0];
            String from = params[1].split(" ", 2)[1];
            String to = params[2].split(" ", 2)[1];
            Task task = new Event(description, from, to);
            addTask(task);
            return "Adding Event task:\n " + task
                    + "\nTotal number of tasks in your list: " + numTasks;
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("You may have missing details or wrong format!\n" + format);
        }
    }

    static void delTask(int taskNum) {
        taskList.remove(taskNum - 1);
        numTasks--;
    }

    static String commandDelete(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = getTask(taskNum);
            delTask(taskNum);
            return "Noted, removing this task:\n " + currTask
                    + "\nTotal number of tasks in your list: " + numTasks ;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }
}