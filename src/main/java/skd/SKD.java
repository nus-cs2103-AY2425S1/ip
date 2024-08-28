package skd;

import skd.task.Deadline;
import skd.task.Event;
import skd.task.Task;
import skd.task.ToDo;
import skd.task.TaskType;

import java.util.List;
import java.util.Scanner;

public class SKD {
    private ToStore storage;
    private List<Task> tasks;
    private Ui ui;

    /**
     * Creates a new SKD instance.
     *
     * @param filePath Path to storage file.
     */
    public SKD(String filePath) {
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
     * The main method, start of the SKD chatbot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new SKD("data/duke.txt").run();
    }
}