import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Friday {
    private final ArrayList<Task> items = new ArrayList<>();
    private static final String FILE_PATH = "./data/friday.txt";

    private void loadTasks() {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String[] taskData = sc.nextLine().split(" \\| ");
                    if ((taskData.length < 3) || !taskData[1].chars().allMatch(Character::isDigit)) continue;
                    Task task;
                    switch (taskData[0]) {
                        case "T":
                            task = new Todo(taskData[2]);
                            break;
                        case "D":
                            task = new Deadline(taskData[2], taskData[3]);
                            break;
                        case "E":
                            task = new Event(taskData[2], taskData[3], taskData[4]);
                            break;
                        default:
                            System.out.println("\tCorrupted data found: "
                                    + String.join(" | ", taskData));
                            continue; // Skip corrupted data
                    }
                    if (taskData[1].equals("1")) {
                        task.markAsDone();
                    }
                    items.add(task);
                }
                sc.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    private void saveTasks() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : items) {
                String taskData = task.toFileFormat();
                writer.write(taskData + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    private void horizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    private void welcomeMessage() {
        horizontalLine();
        System.out.println("\tWelcome Back! I'm Friday.");
        System.out.println("\tWhat can I do for you today?");
        System.out.println("\tTo view the list of commands that I support, type 'help' for more information.");
        horizontalLine();
    }

    private void help() {
        System.out.println("\tHere are the list of commands that I support");
        System.out.println("\thelp - List of commands supported by me, Friday.");
        System.out.println("\tlist - View all entries to the current list of things" +
                " you have asked me to take note of.");
        System.out.println("\tmark <integer> - Mark an entry in the list as a completed task.");
        System.out.println("\tunmark <integer> - Unmark an entry in the list as a completed task.");
        System.out.println("\ttodo <string> - Remember a TODO Task for you to revisit again.");
        System.out.println("\tdeadline <string> /by <yyyy-mm-dd hhmm> - Remember a Deadline Task for" +
                " you to complete by the deadline.");
        System.out.println("\tevent <string> /from <yyyy-mm-dd hhmm> /to <yyyy-mm-dd hhmm> - Remember an Event Task" +
                " from when it begins to when it ends.");
        System.out.println("\tdelete <integer> - Delete an entry from your current list.");
        System.out.println("\tbye - Exits this app and says Good Bye to Friday :)");
    }

    private Command parseCommand(String input) {
        String[] inputs = input.split(" ");
        try {
            return Command.valueOf(inputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.INVALID;
        }
    }

    private void readInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        Task currTask;
        while (true) {
            input = sc.nextLine();
            horizontalLine();
            String[] inputs = input.split(" ");
            Command command = parseCommand(input);

            try {
                switch (command) {
                    case BYE:
                        System.out.println("\tGood Bye. Hope to see you again soon!");
                        return;
                    case HELP:
                        if (inputs.length > 1) {
                            throw new FridayException("Invalid input. 'help' command does not take any arguments.");
                        }
                        help();
                        break;
                    case LIST:
                        if (inputs.length > 1) {
                            throw new FridayException("Invalid input. 'list' command does not take any arguments.");
                        }
                        System.out.println("\tHere are the tasks in your list:");
                        for (int i = 0; i < items.size(); i++) {
                            System.out.println("\t" + (i + 1) + "." + items.get(i).toString());
                        }
                        break;
                    case MARK:
                    case UNMARK:
                        if (inputs.length != 2) {
                            throw new FridayException("Invalid input. 'mark' and 'unmark' commands require exactly" +
                                    " one argument.\n\tusage: mark <integer> || unmark <integer>");
                        }
                        markUnmark(inputs);
                        break;
                    case TODO:
                        if (inputs.length < 2) {
                            throw new FridayException("Invalid input. 'todo' command requires a description." +
                                    "\n\tusage: todo <string>");
                        }
                        currTask = new Todo(input.substring(5));
                        addTask(currTask);
                        break;
                    case DEADLINE:
                        if (inputs.length == 1) {
                            throw new FridayException("Invalid input. 'deadline' command requires a" +
                                    " description and a deadline.\n\tusage: deadline <string> /by <yyyy-MM-dd HHmm>");
                        }
                        String[] deadlineInputs = input.substring(9).split(" /by ");
                        if (deadlineInputs.length != 2) {
                            throw new FridayException("Invalid input. 'deadline' command requires a" +
                                    " description and deadline\n\tusage: deadline <string> /by <yyyy-MM-dd HHmm>.");
                        }
                        try {
                            currTask = new Deadline(deadlineInputs[0], deadlineInputs[1]);
                            addTask(currTask);
                        } catch (DateTimeParseException e) {
                            System.out.println("\tInvalid date format. Please use the format: yyyy-MM-dd HHmm.");
                        }
                        break;
                    case EVENT:
                        if (inputs.length == 1) {
                            throw new FridayException("Invalid input. 'event' command requires a description," +
                                    " start, and end time.\n\tusage: event <string> /from <yyyy-MM-dd HHmm>" +
                                    " /to <yyyy-MM-dd HHmm>");
                        }
                        String[] eventInputs = input.substring(6).split(" /from | /to ");
                        if (eventInputs.length != 3) {
                            throw new FridayException("Invalid input. 'event' command requires a description," +
                                    " start, and end time.\n\tusage: event <string> /from <yyyy-MM-dd HHmm>" +
                                    " /to <yyyy-MM-dd HHmm>.");
                        }
                        try {
                            currTask = new Event(eventInputs[0], eventInputs[1], eventInputs[2]);
                            addTask(currTask);
                        } catch (DateTimeParseException e) {
                            System.out.println("\tInvalid date format. Please use the format: yyyy-MM-dd HHmm.");
                        }
                        break;
                    case DELETE:
                        if (inputs.length != 2) {
                            throw new FridayException("Invalid input. 'delete' command requires exactly one argument." +
                                    "\n\tusage: delete <integer>");
                        }
                        deleteTask(inputs);
                        break;
                    default:
                        throw new FridayException("Invalid input. Please ensure that this command is supported by me" +
                                " and you have utilized the right syntax.\n\tCheck 'help' for more information.");
                }
            } catch (FridayException e) {
                System.out.println("\t" + e.getMessage());
            } finally {
                saveTasks();
                horizontalLine();
            }
        }
    }

    private void addTask(Task task) {
        items.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + items.size() + " tasks in the list.");
    }

    private void deleteTask(String[] inputs) throws FridayException {
        if (!inputs[1].chars().allMatch(Character::isDigit))
            throw new FridayException("Invalid input. usage: delete <index>\n\tWhat would you like to delete?");
        int target = Integer.parseInt(inputs[1]);
        if (target > items.size() || target <= 0)
            throw new FridayException("Invalid input. It appears you are attempting to" +
                    " access something that does not exist yet.");
        Task task = items.get(target - 1);
        items.remove(task);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + items.size() + " tasks in the list.");
    }

    private void markUnmark(String[] inputs) throws FridayException {
        if (!inputs[1].chars().allMatch(Character::isDigit))
            throw new FridayException("Invalid input. Where would you like to " + inputs[0] + "?");
        int target = Integer.parseInt(inputs[1]);
        if (target > items.size() || target <= 0)
            throw new FridayException("Invalid input. It appears you are attempting to" +
                    " access something that does not exist yet.");
        Task task = items.get(target - 1);
        if (inputs[0].equals("mark")) {
            task.markAsDone();
            System.out.println("\tNice! I've marked this task as done:\n\t  " + task);
        } else {
            task.unmarkAsDone();
            System.out.println("\tOK, I've marked this task as not done yet:\n\t  " + task);
        }
    }

    private void initialize() {
        loadTasks();
        welcomeMessage();
        readInput();
    }

    public static void main(String[] args) {
        Friday bot = new Friday();
        bot.initialize();
    }
}
