import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    static void markTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markDone();
            Ui.markTask(tasks, index, true);
        } else {
            Ui.noTask();
        }
    }

    static void unmarkTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markNotDone();
            Ui.markTask(tasks, index, false);
        } else {
            Ui.noTask();
        }
    }

    static void deleteTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            Ui.alterTask(tasks, removedTask, false);
        } else {
            Ui.noTask();
        }

    }

    static void addTask(ArrayList<Task> tasks, Task.TaskType type, String desc, LocalDateTime... args) {
        Task newTask = switch (type) {
            case TODO -> new Task.ToDo(desc);
            case DEADLINE -> new Task.Deadline(desc, args[0]);
            case EVENT -> new Task.Event(desc, args[0], args[1]);
        };
        tasks.add(newTask);
        Ui.alterTask(tasks, newTask, true);
    }
}
