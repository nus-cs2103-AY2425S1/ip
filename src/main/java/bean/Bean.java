package bean;

import bean.command.Command;
import bean.parser.Parser;
import bean.storage.Storage;
import bean.task.DeadlineTask;
import bean.task.EventTask;
import bean.task.Task;
import bean.task.TodoTask;
import bean.ui.Ui;

public class Bean {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Bean(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void startConversation() {
        ui.showGreeting();
        while (true) {
            try {
                String input = ui.getUserInput();
                Command command = parser.parseCommand(input);

                switch (command.getType()) {
                    case "list":
                        ui.showTasks(tasks.getTasks());
                        break;
                    case "mark":
                    case "unmark":
                    case "delete":
                        int index = Integer.parseInt(command.getDetails()) - 1;
                        handleTaskOperation(command.getType(), index);
                        break;
                    case "todo":
                        tasks.addTask(new TodoTask(command.getDetails()));
                        ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
                        break;
                    case "deadline":
                        String[] deadlineParts = command.getDetails().split(" /by ");
                        tasks.addTask(new DeadlineTask(deadlineParts[0], deadlineParts[1]));
                        ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
                        break;
                    case "event":
                        String[] eventParts = command.getDetails().split(" /from | /to ");
                        tasks.addTask(new EventTask(eventParts[0], eventParts[1], eventParts[2]));
                        ui.showTaskAdded(tasks.getTask(tasks.size() - 1), tasks.size());
                        break;
                    default:
                        ui.showError("Unknown command");
                        break;
                }
                storage.save(tasks.getTasks());
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private boolean isValidIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            ui.showError("bean.task.Task index is out of bounds.");
            return false;
        }
        return true;
    }

    private void handleTaskOperation(String commandType, int index) {
        if (isValidIndex(index)) {
            switch (commandType) {
                case "mark":
                    tasks.markTask(index);
                    ui.showTaskMarked(tasks.getTask(index));
                    break;
                case "unmark":
                    tasks.unmarkTask(index);
                    ui.showTaskUnmarked(tasks.getTask(index));
                    break;
                case "delete":
                    Task removedTask = tasks.deleteTask(index);
                    ui.showTaskDeleted(removedTask, tasks.size());
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Bean("data/bean.txt").startConversation();
    }
}
