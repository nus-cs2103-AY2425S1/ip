package cloud;

import java.util.Scanner;

import cloud.exception.CloudException;
import cloud.task.Deadline;
import cloud.task.Event;
import cloud.task.Todo;
import cloud.util.Parser;
import cloud.util.Query;
import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

public class Cloud {

    private static final String EXIT_COMMAND = "bye";
    private static final Storage storage = new Storage();
    private static TaskList tasks = new TaskList();

    private static void greet() {
        System.out.println(
                "Hello! I'm Cloud\n" +
                        "What can I do for you?"
        );
    }

    private static void printHorizLine() {
        System.out.println(
                "____________________________________________________________"
        );
    }

    private static void echo(String message) {
        System.out.println(message);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // manage the task adding logic for all task types
    private static void addTask(Query query) {
        String command = query.getCommand().strip();
        String[] details;
        switch (command) {
        case "todo":
            Todo todo = new Todo(query.getDetails());
            tasks.add(todo);
            break;
        case "deadline":
            details = query.getDetails().split("/by");
            try {
                Deadline dl = new Deadline(details[0].strip(), details[1].strip());
                tasks.add(dl);
            } catch (CloudException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "event":
            details = query.getDetails().split("/from|/to");
            try {
                Event event = new Event(
                        details[0].strip(),
                        details[1].strip(),
                        details[2].strip()
                );
                tasks.add(event);
            } catch (CloudException e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            break;
        }
        System.out.printf(
                "Added the following task:\n\t%s\nNow you have %d task%s in the list\n",
                tasks.getLatestTask(),
                tasks.getTaskCount(),
                tasks.getTaskCount() != 1 ? "s" : ""
        );
        storage.saveData(tasks);
    }

    private static void deleteTask(Query query) {
        int taskId = Integer.parseInt(query.getDetails().strip());
        String taskStatus = tasks.getTaskStatus(taskId);
        tasks.delete(taskId);
        System.out.printf(
                "Removed the following task:\n\t%s\n%d task%s remaining\n",
                taskStatus,
                tasks.getTaskCount(),
                tasks.getTaskCount() != 1 ? "s" : ""
        );
    }

    private static void findTasks(Query query) {
        String keyword = query.getDetails();
        tasks.find(keyword);

    }

    private static void loadData() {
        tasks = storage.readData();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        ui.showGreeting();
        loadData();
        while (true) {
            ui.showLine();
            String userInput = ui.readCommand();
            // exit chat if user enters exit command
            if (userInput.equals(EXIT_COMMAND)) {
                break;
            }

            Query query = null;
            String command = "";
            // parse the user input to a cloud.utils.Query object
            try {
                query = Parser.parse(userInput);
                command = query.getCommand();
            } catch (CloudException e) {
                ui.showError(e.getMessage());
            }
            int taskNum;
            switch (command) {
            case "list":
                ui.showList(tasks.toString());
                break;
            case "mark":
                taskNum = Integer.parseInt(query.getDetails());
                tasks.mark(taskNum);
                ui.showMarked(tasks.getTaskStatus(taskNum));
                storage.saveData(tasks);
                break;
            case "unmark":
                taskNum = Integer.parseInt(query.getDetails());
                tasks.unmark(taskNum);
                ui.showUnmarked(tasks.getTaskStatus(taskNum));
                storage.saveData(tasks);
                break;
            // case fallthrough for tasks
            case ("event"):
            case ("deadline"):
            case ("todo"):
                addTask(query);
                break;
            case "delete":
                deleteTask(query);
                break;
            case "find":
                ui.showMatching(tasks.find(query.getDetails()).toString());default:
                break;
            }
        }
        exit();
        printHorizLine();
    }
}
