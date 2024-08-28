import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.time.LocalDateTime;

public class Bob {
    private static final String FILE_PATH = "data" + File.separator + "Bob.txt";
    private static TaskList taskList;
    private static Storage storage;

    public Bob(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (BobException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    public enum Command {
        BYE, LIST, RELEVANT, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    public static void main(String[] args) {
        try {
            new Bob(FILE_PATH).run();
        } catch (BobException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws BobException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello! I'm Bob the bot!\nHow can I help you?");

        while (true) {
            try {
                String userInput = br.readLine().trim();
                Command command = Parser.parseCommand(userInput);
                String taskDetails = Parser.getTaskDetails(userInput);

                switch (command) {
                    case BYE:
                        System.out.println(commandBye());
                        return;

                    case LIST:
                        System.out.println(commandList());
                        break;

                    case RELEVANT:
                        System.out.println(commandRelevant(taskDetails));
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
                throw new BobException("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

//    static String getRelevantTasks(String dateStr) throws BobException {
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate date = LocalDate.parse(dateStr, formatter);
//
//            StringBuilder tasks = new StringBuilder();
//            int numRelevantTasks = 0;
//            for (Task currTask : taskList.getTasks()) {
//                if (currTask.isRelevant(date)) {
//                    numRelevantTasks++;
//                    tasks.append(numRelevantTasks).append(". ").append(currTask).append("\n");
//                }
//            }
//            DateTimeFormatter formatterWords = DateTimeFormatter.ofPattern("MMM dd yyyy");
//            tasks.append("Total number of relevant tasks for ").append(date.format(formatterWords))
//                    .append(": ").append(numRelevantTasks);
//            return tasks.toString();
//        } catch (DateTimeParseException e) {
//            throw new BobException("Invalid date format. Required format: relevant yyyy-MM-dd");
//        }
//    }

    static String commandBye() {
        return "Bye! Hope to see you again :)";
    }

    static String commandList() {
        if (taskList.isEmpty()) {
            return "There are 0 tasks in your list now. Start adding them!";
        }
        return "Your list of tasks:\n" + taskList.printTasks();
    }

    static String commandRelevant(String dateStr) throws BobException {
        return taskList.getRelevantTasks(dateStr);
    }

    static String commandMark(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = taskList.getTask(taskNum);
            currTask.markAsDone();
            storage.saveTasks(taskList);
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
            Task currTask = taskList.getTask(taskNum);
            currTask.markAsUndone();
            storage.saveTasks(taskList);
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
        ToDo task = new ToDo(taskDetails);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        return "Adding ToDo task:\n " + task
                + "\nTotal number of tasks in your list: " + taskList.getNumTasks();
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
            String byStr = params[1].split(" ", 2)[1];

            LocalDateTime by = Parser.parseDateTime(byStr);
            if (by.isBefore(LocalDateTime.now())) {
                throw new BobException("Oops! The date you provided is in the past. "
                        + "Kindly provide a future date.");
            }
            Deadline task = new Deadline(description, by);
            taskList.addTask(task);
            storage.saveTasks(taskList);
            return "Adding Deadline task:\n " + task
                    + "\nTotal number of tasks in your list: " + taskList.getNumTasks();
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
            String fromStr = params[1].split(" ", 2)[1];
            String toStr = params[2].split(" ", 2)[1];

            LocalDateTime from = Parser.parseDateTime(fromStr);
            LocalDateTime to = Parser.parseDateTime(toStr);
            if (from.isBefore(LocalDateTime.now()) || to.isBefore(LocalDateTime.now())) {
                throw new BobException("Oops! The date you provided is in the past. "
                        + "Kindly provide a future date.");
            }
            if (to.isBefore(from)) {
                throw new BobException("The end date cannot be before the start date. " +
                        "Please try again.");
            }
            Event task = new Event(description, from, to);
            taskList.addTask(task);
            storage.saveTasks(taskList);
            return "Adding Event task:\n " + task
                    + "\nTotal number of tasks in your list: " + taskList.getNumTasks();
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("You may have missing details or wrong format!\n" + format);
        }
    }

    static String commandDelete(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }

        int taskNum = Integer.parseInt(taskDetails);
        Task currTask = taskList.getTask(taskNum);
        taskList.delTask(taskNum);
        storage.saveTasks(taskList);
        return "Noted, removing this task:\n " + currTask
                + "\nTotal number of tasks in your list: " + taskList.getNumTasks() ;
    }
}