import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private Storage storage;
    private Ui ui;
    private static List<Task> listOfTasks = new ArrayList<>();
    private final String STORAGEFILEPATH = "Meerkat.txt";

    public TaskList() {
        this.storage = new Storage();
        this.ui = new Ui();
    }

    public String taskListToString(List<Task> taskList) {
        StringBuilder s = new StringBuilder();
        for (Task task : taskList) {
            s.append(task.toParseableString()).append("\n");
        }
        return s.toString();
    }
    public void createTodoTask(String name) throws IOException {
        Task thisTask = new Todo(name);
        listOfTasks.add(thisTask);
        storage.writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
        ui.printTaskCreationMessage(thisTask, this);
    }

    public void createEventTask(String name, String start, String end) throws IOException {
        Task thisTask = new Event(name, start, end);
        listOfTasks.add(thisTask);
        storage.writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
        ui.printTaskCreationMessage(thisTask, this);
    }

    public void createDeadlineTask(String name, String duedate) throws IOException {
        Task thisTask = new Deadline(name, duedate);
        listOfTasks.add(thisTask);
        storage.writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
        ui.printTaskCreationMessage(thisTask, this);
    }

    public void markTaskAsDone(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            Task thisTask = listOfTasks.get(taskNum - 1);
            thisTask.markAsCompleted();
            ui.printTaskMarkedMessage(thisTask);
            // task number is not within range
        } else {
            ui.printTaskNonMarkableMessage();
        }
        storage.writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
    }

    public void markTaskAsUndone(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            Task thisTask = listOfTasks.get(taskNum - 1);
            thisTask.markAsIncomplete();
            ui.printTaskUnmarkedMessage(thisTask);
            // task number is not within range
        } else {
            ui.printTaskNonUnmarkableMessage();
        }
        storage.writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
    }

    public void deleteTask(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            Task thisTask = listOfTasks.get(taskNum - 1);
            listOfTasks.remove(taskNum - 1);
            ui.printDeleteMessage(thisTask);
            // task number is not within range
        } else {
            ui.printUndeletableMessage();
        }
        storage.writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
    }

    public int getSize() {
        return listOfTasks.size();
    }

    public List<Task> getTaskList() {
        return listOfTasks;
    }

    public void setMostRecentTaskCompletionStatus(boolean bool) {
        int size = getSize();
        if (bool) {
            listOfTasks.get(size - 1).markAsCompleted();
        } else {
            listOfTasks.get(size - 1).markAsIncomplete();
        }
    }
}
