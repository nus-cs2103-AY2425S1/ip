import java.util.ArrayList;

public class Controller {
    private Ui ui;
    private Storage storage;
    private ArrayList<Task> store;

    public Controller(String chatbotName) {
        this.ui = new Ui(chatbotName);
        this.storage = new Storage();
        this.store = storage.loadTasks();
    }

    public void startProgram() {
        ui.printHelp();
        ui.greet();
    }

    public void endProgram() {
        storage.saveTasks(store);
        ui.farewell();
    }

    public void handleList() {
        ui.printListMessage(store);
    }

    public void handleHelp() {
        ui.printHelp();
    }

    public void handleDone(int index) {
        store.get(index).toggleDone();
        ui.printDoneMessage(store.get(index));
    }

    public void handleUndone(int index) {
        store.get(index).toggleDone();
        ui.printUndoneMessage(store.get(index));
    }

    public void handleDelete(int index) {
        store.remove(index);
        ui.printDeleteMessage(store.get(index), store.size());
    }

    public void handleTodo(String input) {
        String task = input.substring(5);
        if (task.equals("")) {
            ui.printEmptyTaskErrorMessage();
            return;
        }
        store.add(new TaskTodo(task));
        ui.printTodoMessage(store.getLast(), store.size());
    }

    public void handleDeadline(String input) {
        int byIndex = input.indexOf("/by ");
        if (byIndex == -1) {
            ui.printEmptyByErrorMessage();
            return;
        }
        String task = input.substring(9, byIndex - 1);
        if (task.equals("")) {
            ui.printEmptyTaskErrorMessage();
            return;
        }
        String by = input.substring(byIndex + 4);
        store.add(new TaskDeadline(task, by));
        ui.printDeadlineMessage(store.getLast(), store.size());
    }

    public void handleEvent(String input) {
        int fromIndex = input.indexOf("/from ");
        int toIndex = input.indexOf("/to ");
        if (fromIndex == -1 || toIndex == -1) {
            ui.printInvalidEventDateErrorMessage();
            return;
        }
        String task = input.substring(6, fromIndex - 1);
        if (task.equals("")) {
            ui.printEmptyTaskErrorMessage();
            return;
        }
        String from = input.substring(fromIndex + 6, toIndex - 1);
        String to = input.substring(toIndex + 4);
        store.add(new TaskEvent(task, from, to));
        ui.printEventMessage(store.getLast(), store.size());
    }
}
