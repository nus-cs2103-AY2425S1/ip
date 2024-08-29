import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Velma {
    private static final String FILE_PATH = "./data/velma.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes a new instance of the Velma class.
     * Sets up the UI, storage, and task list.
     * If loading tasks from storage fails, an empty task list is created.
     *
     * @param filePath The file path to the storage file.
     */
    public Velma(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public enum Command {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        UNKNOWN
    }

    /**
     * Checks user input to determine which command to carry out.
     *
     * @param input The command specified to the chatbot.
     */
    public static Command getCommand(String input) {
        if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("list")) {
            return Command.LIST;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else {
            return Command.UNKNOWN;
        }
    }

    /**
     * The main entry point for the Velma application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Velma velma = new Velma(FILE_PATH);
        velma.run();
    }

    /**
     * Runs the main loop of the Velma application.
     * Displays the welcome message and processes user commands until the "bye" command is received.
     * Handles various commands such as adding tasks, marking tasks as done, listing tasks, and deleting tasks.
     * Saves the task list to storage after each modification.
    */
    public void run() {
        ui.showWelcome();
        Scanner req = new Scanner(System.in);
        boolean end = false;

        while (!end) {
            String request = req.nextLine();
            Command command = getCommand(request);

            try {
                switch (command) {
                    case TODO:
                        String todoDescription = request.replaceFirst("todo\\s*", "").trim();
                        if (todoDescription.isEmpty()) {
                            throw new VelmaException("Sorry boss! Where is your todo description?");
                        }
                        Task newTodo = new Todo(todoDescription);
                        tasks.addTask(newTodo);
                        ui.showTaskAdded(newTodo, tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;

                    case DEADLINE:
                        String[] parts = request.replaceFirst("deadline\\s*", "").split(" /by ");
                        if (parts.length < 2) {
                            throw new VelmaException("Sorry boss! Your deadline task needs a deadline!");
                        }
                        String description = parts[0];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime deadline;
                        try {
                            deadline = LocalDateTime.parse(parts[1], formatter);
                        } catch (DateTimeParseException e) {
                            throw new VelmaException("Sorry boss! The date format is incorrect. Please use d/M/yyyy HHmm.");
                        }

                        Task newDeadline = new Deadline(description, deadline);
                        tasks.addTask(newDeadline);
                        ui.showTaskAdded(new Deadline(description, deadline), tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;

                    case EVENT:
                        parts = request.replaceFirst("event\\s+", "").split(" /from | /to ");
                        if (parts.length < 3) {
                            throw new VelmaException("Sorry boss! An event needs a valid start time and end time!");
                        }
                        description = parts[0];
                        String startTime = parts[1];
                        String endTime = parts[2];
                        Task newEvent = new Event(description, startTime, endTime);
                        tasks.addTask(newEvent);
                        ui.showTaskAdded(new Event(description, startTime, endTime), tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;

                    case LIST:
                        parts = request.split(" ");
                        if (parts.length == 2) {
                            String dateString = parts[1];
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate date;
                            try {
                                date = LocalDate.parse(dateString, dateFormatter);
                            } catch (DateTimeParseException e) {
                                throw new VelmaException("Sorry boss! The date format is incorrect. Please use yyyy-MM-dd.");
                            }
                            ui.showTasksOnDate(tasks.getTasks(), date);
                        } else {
                            ui.showAllTasks(tasks.getTasks());
                        }
                        break;

                    case MARK:
                    case UNMARK:
                        parts = request.split(" ");
                        if (parts.length < 2) {
                            throw new VelmaException("Sorry boss! Please specify which task.");
                        }
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.getTask(taskNumber);
                        task.changeIsDone();
                        if (command == Command.MARK) {
                            System.out.println("Nice! I have marked this task as done:");
                        } else {
                            System.out.println("OK! I have marked this task as not done yet:");
                        }
                        ui.showMarkUnmarkTask(task, task.isDone);
                        storage.save(tasks.getTasks());
                        break;

                    case DELETE:
                        parts = request.split(" ");
                        if (parts.length < 2) {
                            throw new VelmaException("Sorry boss! Please specify which task to delete.");
                        }
                        taskNumber = Integer.parseInt(parts[1]) - 1;
                        Task deletedTask = tasks.getTask(taskNumber);
                        tasks.deleteTask(taskNumber);
                        ui.showTaskDeleted(deletedTask, tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;

                    case BYE:
                        ui.showGoodbye();
                        break;

                    case UNKNOWN:
                    default:
                        throw new VelmaException("Sorry boss! What are you talking about?");
                }
            } catch (VelmaException e) {
               ui.showError(e.getMessage());
            }


        }
    }
}
