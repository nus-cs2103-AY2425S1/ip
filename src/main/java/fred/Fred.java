package fred;
import java.util.ArrayList;

public class Fred {
    Storage storage;
    TaskList tasks;
    Ui ui;
    Parser parser;

    public Fred() {
        storage = new Storage();
        tasks = new TaskList();
        ui = new Ui();
        parser = new Parser();
    }

    void run() {
        storage.getDataFile();
        ArrayList<Task> tasksFromDataFile = storage.getTasksFromDataFile();
        tasks.loadTasksFromDataFile(tasksFromDataFile);
        ui.greet();
        String input;
        while (true) {
            input = ui.getInput();
            try {
                String[] action = parser.parseInput(input);
                executeAction(action);
            } catch (FredException e) {
                ui.say(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Fred().run();
    }

    void exit(){
        System.exit(0);
    }

    void executeAction(String[] action) throws FredException {
        String message = null;
        int taskNumber;
        Task task;
        switch (action[0]) {
            case "sayFarewell":
                ui.sayFarewell();
                exit();
                break;
            case "printTaskList":
                ArrayList<Task> taskList = tasks.getTaskList();
                ui.printTaskList(taskList);
                break;
            case "markTaskAsDone":
                taskNumber = Integer.parseInt(action[1]);
                task = tasks.markTaskAsDone(taskNumber);
                message = String.format("Nice! I've marked this task as done:\n" +
                        "   %s", task);
                break;
            case "markTaskAsNotDone":
                taskNumber = Integer.parseInt(action[1]);
                task = tasks.markTaskAsNotDone(taskNumber);
                message = String.format("OK, I've marked this task as not done yet:\n" +
                        "   %s", task);
                break;
            case "deleteFromTaskList":
                taskNumber = Integer.parseInt(action[1]);
                task = tasks.deleteFromTaskList(taskNumber);
                storage.deleteFromDataFile(taskNumber);
                message = String.format("Noted. I've removed this task:\n" +
                        "   %s", task);
                break;
            case "addToTaskList":
                task = tasks.createTask(action[1], action[2]);
                tasks.addToTaskList(task);
                storage.appendToDataFile(task);
                message = String.format("Got it. I've added this task:\n" +
                        "   %s\n" +
                        "Now you have %d tasks in the list.", task, tasks.getTaskListSize());
                break;
        }
        if (message != null) {
            ui.say(message);
        }
    }
}
