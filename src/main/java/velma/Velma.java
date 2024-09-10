package velma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import velma.exception.VelmaException;
import velma.task.Deadline;
import velma.task.Event;
import velma.task.Task;
import velma.task.TaskList;
import velma.task.Todo;


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

    /**
     * Prints a line separator.
     */
    public void printLine() {
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command command = getCommand(input);
        StringBuilder response = new StringBuilder();
        try {
            switch (command) {
            case TODO:
                String todoDescription = input.replaceFirst("todo\\s*", "").trim();
                if (todoDescription.isEmpty()) {
                    throw new VelmaException("Sorry boss! Where is your todo description?");
                }
                Task newTodo = new Todo(todoDescription);
                tasks.addTask(newTodo);
                response.append("Got it. I've added this task:\n")
                        .append(newTodo)
                        .append("\nNow you have ")
                        .append(tasks.getSize())
                        .append(" tasks in the list.");
                storage.save(tasks.getTasks());
                break;

            case DEADLINE:
                String[] parts = input.replaceFirst("deadline\\s*", "").split(" /by ");
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
                response.append("Got it. I've added this task:\n")
                        .append(newDeadline)
                        .append("\nNow you have ")
                        .append(tasks.getSize())
                        .append(" tasks in the list.");
                storage.save(tasks.getTasks());
                break;

            case EVENT:
                parts = input.replaceFirst("event\\s+", "").split(" /from | /to ");
                if (parts.length < 3) {
                    throw new VelmaException("Sorry boss! An event needs a valid start time and end time! Please use /from HHmm /to HHmm");
                }
                description = parts[0];
                String startTime = parts[1];
                String endTime = parts[2];
                Task newEvent = new Event(description, startTime, endTime);
                tasks.addTask(newEvent);
                response.append("Got it. I've added this task:\n")
                        .append(newEvent)
                        .append("\nNow you have ")
                        .append(tasks.getSize())
                        .append(" tasks in the list.");
                storage.save(tasks.getTasks());
                break;

            case LIST:
                parts = input.split(" ");
                if (parts.length == 2) {
                    String dateInString = parts[1];
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date;
                    try {
                        date = LocalDate.parse(dateInString, dateFormatter);
                    } catch (DateTimeParseException e) {
                        throw new VelmaException("Sorry boss! The date format is incorrect. Please use yyyy-MM-dd.");
                    }
                    response.append("Here are the tasks on ")
                            .append(date)
                            .append(":\n")
                            .append(ui.showTasksOnDate(tasks.getTasks(), date));
                } else {
                    response.append(ui.showAllTasks(tasks.getTasks()));
                }
                break;

            case MARK:
            case UNMARK:
                parts = input.split(" ");
                if (parts.length < 2) {
                    throw new VelmaException("Sorry boss! Please specify which task.");
                }
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                Task task = tasks.getTask(taskIndex);
                task.changeIsDoneStatus();
                if (command == Command.MARK) {
                    response.append("Nice! I have marked this task as done:\n");
                } else {
                    response.append("OK! I have marked this task as not done yet:\n");
                }
                response.append(task);
                storage.save(tasks.getTasks());
                break;

            case DELETE:
                parts = input.split(" ");
                if (parts.length < 2) {
                    throw new VelmaException("Sorry boss! Please specify which task to delete.");
                }
                taskIndex = Integer.parseInt(parts[1]) - 1;
                Task taskToDelete = tasks.getTask(taskIndex);
                tasks.deleteTask(taskIndex);
                response.append("Noted. I've removed this task:\n")
                        .append(taskToDelete)
                        .append("\nNow you have ")
                        .append(tasks.getSize())
                        .append(" tasks in the list.");
                storage.save(tasks.getTasks());
                break;

            case FIND:
                String keyword = input.replaceFirst("find\\s*", "");
                ArrayList<Task> foundTasks = tasks.findTasks(keyword);
                response.append("Here are the matching tasks in your list:\n")
                        .append(ui.showFoundTasks(foundTasks));
                break;

            case BYE:
                response.append(ui.showGoodbye());
                break;

            case UNKNOWN:
            default:
                throw new VelmaException("Sorry boss! What are you talking about?");
            }
        } catch (VelmaException e) {
            response.append(e.getMessage());
        }
        return response.toString();
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
        } else if (input.startsWith("find")) {
            return Command.FIND;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else {
            return Command.UNKNOWN;
        }
    }


}
