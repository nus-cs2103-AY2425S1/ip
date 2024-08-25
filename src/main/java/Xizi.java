//https://nus-cs2103-ay2425s1.github.io/website/admin/standardsAndConventions.html
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;


public class Xizi {
    private static final String FILE_PATH = "./data/xizi.txt";
    private static final String DIVIDER = "____________________________________________________________";
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private Storage storage;
    private TaskList actions;
    private Ui ui;

    public Xizi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        actions = new TaskList();

        try {
            List<Task> loadedTasks = storage.load();
            actions = new TaskList(loadedTasks);
        } catch (IOException | XiziException e) {
            ui.printErrorMessage(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Xizi(FILE_PATH).run();
    }

    public void run() {
        ui.showWelcomeMessage();

        while (true) {
            String userInput = ui.readUserInput();
            CommandType commandType = CommandType.fromInput(userInput);

            try {
                switch (commandType) {
                case DELETE:
                    handleDelete(actions, storage, userInput);
                    break;
                case MARK:
                    handleMark(actions, storage, userInput);
                    break;
                case UNMARK:
                    handleUnmark(actions, storage, userInput);
                    break;
                case TODO:
                    handleTodo(actions, storage, userInput);
                    break;
                case DEADLINE:
                    handleDeadline(actions, storage, userInput);
                    break;
                case EVENT:
                    handleEvent(actions, storage, userInput);
                    break;
                case LIST:
                    handleList(actions);
                    break;
                case BYE:
                    ui.showGoodbyeMessage();
                    return;
                case LIST_ON:
                    handleListOn(actions, userInput);
                    break;
                case HELP:
                    ui.printHelp();
                    break;
                default:
                    throw new XiziException("Sorry, I didn't understand that command. Type help for all available commands and format.");
                }
            } catch (IOException | XiziException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    // Handler methods for each command
    private static void handleDelete(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.DELETE.matcher(userInput);
        if (matcher.matches()) {
            int taskNumber = Integer.parseInt(matcher.group(1)) - 1;
            validateTaskNumber(taskNumber, actions);
            Task deleted = actions.deleteTask(taskNumber);
            storage.saveTasks(actions.getItems());
            System.out.println(DIVIDER);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + deleted);
            System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
            System.out.println(DIVIDER);
        }
    }

    private static void handleMark(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.MARK.matcher(userInput);
        if (matcher.matches()) {
            int taskNumber = Integer.parseInt(matcher.group(1)) - 1;
            validateTaskNumber(taskNumber, actions);
            System.out.println(DIVIDER);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(actions.markTask(taskNumber));
            System.out.println(DIVIDER);
            storage.saveTasks(actions.getItems());
        }
    }

    private static void handleUnmark(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.UNMARK.matcher(userInput);
        if (matcher.matches()) {
            int taskNumber = Integer.parseInt(matcher.group(1)) - 1;
            validateTaskNumber(taskNumber, actions);
            System.out.println(DIVIDER);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(actions.unmarkTask(taskNumber));
            System.out.println(DIVIDER);
            storage.saveTasks(actions.getItems());
        }
    }

    private static void handleTodo(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.TODO.matcher(userInput);
        if (matcher.matches()) {
            String taskDescription = matcher.group(1).trim();
            if (taskDescription.isEmpty()) {
                throw new XiziException("The description of a todo cannot be empty. Type help to see the formats required.");
            }
            actions.addTask(new Todo(taskDescription));
            storage.appendTask(new Todo(taskDescription));
            System.out.println(DIVIDER);
            System.out.println("Got it. I've added this task:");
            System.out.println("  [T][ ] " + taskDescription);
            System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
            System.out.println(DIVIDER);
        }
    }

    private static void handleDeadline(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.DEADLINE.matcher(userInput);
        if (matcher.matches()) {
            String taskDescription = matcher.group(1).trim();
            String deadline = matcher.group(2).trim();
            if (taskDescription.isEmpty() || deadline.isEmpty()) {
                throw new XiziException("The description or time of a deadline cannot be empty.Type help to see the formats required.");
            }
            Deadline newTask = new Deadline(taskDescription, LocalDateTime.parse(deadline, INPUT_DATE_FORMAT));
            actions.addTask(newTask);
            storage.appendTask(newTask);
            System.out.println(DIVIDER);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask.toString());
            System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
            System.out.println(DIVIDER);
        }
    }

    private void handleEvent(TaskList actions, Storage storage, String userInput) throws IOException, XiziException {
        Matcher matcher = CommandType.EVENT.matcher(userInput);
        if (matcher.matches()) {
            String taskDescription = matcher.group(1).trim();
            String fromTime = matcher.group(2).trim();
            String toTime = matcher.group(3).trim();
            if (taskDescription.isEmpty() || fromTime.isEmpty() || toTime.isEmpty()) {
                throw new XiziException("The description, from or to time of an event cannot be empty.Type help to see the formats required.");
            }
            try {
                Event newTask = new Event(taskDescription, LocalDateTime.parse(fromTime, INPUT_DATE_FORMAT), LocalDateTime.parse(toTime, INPUT_DATE_FORMAT));
                actions.addTask(newTask);
                storage.appendTask(newTask);
                System.out.println(DIVIDER);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                System.out.println("Now you have " + actions.getSize() + " tasks in the list.");
                System.out.println(DIVIDER);
            } catch (DateTimeParseException e){
                this.ui.printErrorMessage("The format of the time keyed in should be of the form 'd/M/yyyy HHmm'.");
            }
        }
    }

    private static void handleList(TaskList actions) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        actions.printActions();
        System.out.println(DIVIDER);
    }

    public static void handleListOn(TaskList actions, String userInput) {
        Matcher matcher = CommandType.LIST_ON.matcher(userInput);
        boolean tasksFound = false;
        if (matcher.matches()) {
            String dateTimeStr = matcher.group(1);
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, INPUT_DATE_FORMAT);
                System.out.println(DIVIDER);
                System.out.println("Here are the tasks on the particular day in your list:");
                for (Task task : actions.getItems()) {
                    if (task instanceof Event) {
                        Event event = (Event) task;
                        if ((event.from.isBefore(dateTime) || event.from.equals(dateTime))&&
                                (event.to.isAfter(dateTime) || event.to.equals(dateTime))) {
                            System.out.println(event);
                            tasksFound = true;
                        }
                    } else if (task instanceof Deadline) {
                        Deadline deadline = (Deadline) task;
                        if (deadline.ddl.equals(dateTime)) {
                            System.out.println(deadline);
                            tasksFound = true;
                        }
                    }
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format. Please use 'd/M/yyyy HHmm'.");
            }

        }
        if (!tasksFound) {
            System.out.println("No tasks found on this date.");
        }
        System.out.println(DIVIDER);
    }

    private static void validateTaskNumber(int taskNumber, TaskList actions) throws XiziException {
        if (taskNumber < 0 || taskNumber >= actions.getSize()) {
            throw new XiziException("The task number does not exist. You have " + actions.getSize() + " tasks in total.");
        }
    }





}
