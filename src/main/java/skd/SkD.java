package skd;

import java.util.List;
import java.util.Scanner;

import skd.command.CommandType;
import skd.command.Parser;
import skd.storage.ToStore;
import skd.ui.Ui;
import task.Task;


/**
 * Main class for the SKD application.
 *
 * The SKD application allows users to manage tasks, such as ToDos, Deadlines, and Events.
 * Users can add, delete, mark, and unmark tasks, as well as save and load tasks from a file.
 *
 * The application provides a command-line interface where users can input commands
 * to interact with their task list. The commands are parsed and appropriate actions
 * are taken, such as adding a new task or marking a task as done.
 *
 * Tasks are saved to a file and loaded from the file on startup, ensuring persistence
 * of the user's task list between sessions.
 */
public class SkD {
    private ToStore storage;
    private List<Task> tasks;
    private Ui ui;

    /**
     * Creates a new SKD instance.
     *
     * @param filePath Path to storage file.
     */
    public SkD(String filePath) {
        ui = new Ui();
        storage = new ToStore(filePath);
        tasks = storage.load();
    }

    /**
     * Runs the main execution loop of the chatbot.
     * Continuously reads user input, parses commands, and executes them.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String input = ui.readCommand();
            try {
                CommandType commandType = Parser.parseCommand(input);

                switch (commandType) {
                case BYE:
                    ui.showByeMessage();
                    isRunning = false;
                    break;
                case LIST:
                    ui.showTaskList(tasks);
                    break;
                case MARK:
                    Parser.parseMark(input, tasks);
                    storage.save(tasks);
                    break;
                case UNMARK:
                    Parser.parseUnmark(input, tasks);
                    storage.save(tasks);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task newTask = Parser.parseAddCommand(input, commandType);
                    tasks.add(newTask);
                    ui.showAddedTask(newTask, tasks.size());
                    storage.save(tasks);
                    break;
                case DELETE:
                    Parser.parseDelete(input, tasks);
                    storage.save(tasks);
                    break;
                case FIND:
                    String keyword = input.substring(5).trim();
                    ui.showFoundTasks(tasks, keyword);
                    break;
                default:
                    throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IllegalArgumentException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Gets the response based on the input provided by the user.
     *
     * @param input The user's input command.
     * @return The response as a string.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            CommandType commandType = Parser.parseCommand(input);

            switch (commandType) {
            case BYE:
                response = ui.stringShowByeMessage();
                break;
            case LIST:
                response = ui.stringShowTaskList(tasks);
                break;
            case MARK:
                Parser.parseMark(input, tasks);
                storage.save(tasks);
                int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                response = tasks.get(markIndex).stringMarkAsDone();
                break;
            case UNMARK:
                Parser.parseUnmark(input, tasks);
                storage.save(tasks);
                int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                response = tasks.get(unmarkIndex).stringUnmark();
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                Task newTask = Parser.parseAddCommand(input, commandType);
                tasks.add(newTask);
                storage.save(tasks);
                response = newTask.stringPrintTaskAddedMessage(tasks.size());
                break;
            case DELETE:
                int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                Task taskToDelete = tasks.get(deleteIndex);
                Parser.parseDelete(input, tasks);
                storage.save(tasks);
                response = taskToDelete.stringPrintTaskRemovedMessage(tasks.size());
                break;
            case FIND:
                String keyword = input.substring(5).trim();
                response = ui.stringShowFoundTasks(tasks, keyword);
                break;
            default:
                throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            response = ui.stringShowError(e.getMessage());
        }
        return response;
    }


    /**
     * The main method, start of the SKD chatbot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new SkD("data/duke.txt").run();
    }
}
