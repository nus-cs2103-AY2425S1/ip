package donna;

import donna.task.Deadline;
import donna.task.Event;
import donna.task.Task;
import donna.task.ToDo;

import java.util.List;
import java.util.Scanner;

public class Donna {
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    private static Storage storage;

    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/donna-tasks.txt";

    public Donna() {
        ui = new Ui();
        storage = new Storage(DIRECTORY_PATH, FILE_PATH);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DonnaException e) {
            ui.printErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void run() {
        boolean dataWasLoaded = !tasks.isEmpty();
        ui.printGreeting(dataWasLoaded);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            try {
                ParsedCommand result = parser.parse(input);
                String commandType = result.getCommandType();

                switch (commandType) {
                case "exit":
                    ui.printGoodbyeMessage();
                    storage.saveTasks(tasks);
                    sc.close();
                    return;
                case "list":
                    ui.printTaskList(tasks);
                    break;
                case "mark":
                    handleMark(result.getArgument1());
                    break;
                case "unmark":
                    handleUnmark(result.getArgument1());
                    break;
                case "delete":
                    handleDelete(result.getArgument1());
                    break;
                case "add":
                    handleAdd(result.getArgument1(), result.getArgument2());
                    break;
                case "find":
                    List<Task> foundTasks = tasks.searchTasks(result.getArgument1());
                    ui.printFindResults(foundTasks);
                }
            } catch (DonnaException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    private void handleMark(String argument) throws DonnaException {
        try {
            int taskIdx = Integer.parseInt(argument) - 1;
            Task task = tasks.markTask(taskIdx, true);
            ui.printTaskMarkedMessage(task, true);
        } catch (NumberFormatException e) {
            ui.printErrorMessage(DonnaException.invalidTaskNumber().getMessage());
        }
    }

    private void handleUnmark(String argument) throws DonnaException {
        try {
            int taskIdx = Integer.parseInt(argument) - 1;
            Task task = tasks.markTask(taskIdx, false);
            ui.printTaskMarkedMessage(task, false);
        } catch (NumberFormatException e) {
            ui.printErrorMessage(DonnaException.invalidTaskNumber().getMessage());
        }
    }

    private void handleDelete(String argument) throws DonnaException {
        try {
            int taskIndex = Integer.parseInt(argument) - 1;
            Task task = tasks.deleteTask(taskIndex);
            ui.printTaskDeletedMessage(task, tasks.getTaskCount());
        } catch (NumberFormatException e) {
            ui.printErrorMessage(DonnaException.invalidTaskNumber().getMessage());
        }
    }

    private void handleAdd(String type, String description) throws DonnaException {
        Task newTask;
        switch (type) {
            case "todo":
                newTask = new ToDo(description);
                break;
            case "deadline":
                String[] deadlineParts = description.split(" /by ", 2);
                if (deadlineParts.length != 2) {
                    throw DonnaException.emptyDescription(type);
                }
                newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;
            case "event":
                String[] eventParts = description.split(" /from ", 2);
                if (eventParts.length != 2) {
                    throw DonnaException.emptyDescription(type);
                }
                String[] eventTimes = eventParts[1].split(" /to ", 2);
                if (eventTimes.length != 2) {
                    throw DonnaException.emptyEventTime();
                }
                newTask = new Event(eventParts[0], eventTimes[0], eventTimes[1]);
                break;
            default:
                throw DonnaException.invalidTaskType(type);
        }
        tasks.addTask(newTask);
        ui.printTaskAddedMessage(newTask, tasks.getTaskCount());
    }

    public static void main(String[] args) {
        new Donna().run();
    }
}