import java.util.Scanner;

public class MonoBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public MonoBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MonoBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        Scanner sc = new Scanner(System.in);
        boolean keepAlive = true;

        while (keepAlive) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parseCommand(input);

                switch (command) {
                    case LIST -> ui.printTasks(tasks);
                    case TODO, DEADLINE, EVENT -> {
                        Task newTask = Parser.parseTask(input);
                        tasks.addTask(newTask);
                        ui.printAddedTask(newTask, tasks.size());
                        saveTasksToStorage();
                    }
                    case MARK -> {
                        int index = getTaskIndex(input);
                        tasks.markTask(index);
                        ui.printMarkedTask(tasks.getTask(index));
                        saveTasksToStorage();
                    }
                    case UNMARK -> {
                        int index = getTaskIndex(input);
                        tasks.unmarkTask(index);
                        ui.printUnmarkedTask(tasks.getTask(index));
                        saveTasksToStorage();
                    }
                    case DELETE -> {
                        int index = getTaskIndex(input);
                        Task deletedTask = tasks.getTask(index);
                        tasks.deleteTask(index);
                        ui.printDeletedTask(deletedTask, tasks.size());
                        saveTasksToStorage();
                    }
                    case INVALID -> ui.printError("Invalid Command. Valid commands are: \n" +
                            "list, todo, deadline, event, mark, unmark, bye");
                    case BYE -> {
                        sc.close();
                        keepAlive = false;
                    }
                }
            } catch (MonoBotException e) {
                ui.printError(e.toString());
            } catch (IndexOutOfBoundsException e) {
                ui.printError(getIndexErrorMessage());
            } catch (NumberFormatException e) {
                ui.printError("You are expected to provide a number after the command");
            }
        }
        ui.printFarewell();
    }

    private void saveTasksToStorage() {
        try {
            storage.save(tasks.getTasks());
        } catch (MonoBotException e) {
            ui.printError("Error saving tasks: " + e.getMessage());
        }
    }

    private int getTaskIndex(String input) throws MonoBotException {
        String[] parts = input.split(" ", 2);
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new MonoBotException("Please specify which task to process");
        }
        return Integer.parseInt(parts[1].trim()) - 1;
    }

    private String getIndexErrorMessage() {
        int len = tasks.size();
        if (len > 0) {
            return "Please provide an integer between 1 and " + len;
        } else {
            return "No tasks added yet";
        }
    }

    public static void main(String[] args) {
        new MonoBot("./data/monobot.txt").run();
    }
}