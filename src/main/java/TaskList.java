import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(Storage storage) {
        try {
            this.tasks = storage.readTasksFromFile();
        } catch (BadDataFormatException | FileNotFoundException exception) {
            this.tasks = new ArrayList<Task>();
        }
    }

    public void markTask(int taskIndex, Storage storage) {
        if (tasks.get(taskIndex) == null) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(taskIndex).markAsDone();
        saveCurrentTaskState(storage);
        System.out.println("I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
    }

    public void unmarkTask(int taskIndex, Storage storage) {
        if (tasks.get(taskIndex) == null) {
            throw new IndexOutOfBoundsException();
        }
        tasks.get(taskIndex).markAsUndone();
        saveCurrentTaskState(storage);
        System.out.println("I've marked this task as not done:");
        System.out.println(tasks.get(taskIndex));
    }

    public void deleteTask(int taskIndex, Storage storage) {
        Task tempTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        saveCurrentTaskState(storage);
        System.out.println("I've removed this task:");
        System.out.println(tempTask);
    }

    public void addTask(Task task, Storage storage) {
        tasks.add(task);
        saveCurrentTaskState(storage);
        System.out.println("Added: " + tasks.get(tasks.size() - 1));
        System.out.printf("You now have %d tasks in the list%n", tasks.size());
    }

    public void listTasks() {
        for (int input = 0; input < tasks.size(); ++input) {
            System.out.println((input + 1) + ". " + tasks.get(input).toString());
        }
    }

    private void saveCurrentTaskState(Storage storage) {
        try {
            storage.writeTasksToFile(tasks);
        } catch (IOException exception) {
            System.out.println("Error saving tasks to hard disk!");
        }
    }
}
