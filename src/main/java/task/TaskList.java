package task;

import data.InsufficientInfoException;
import storage.Storage;
import storage.StorageOperationException;
import utils.exceptions.IllegalValueException;
import utils.formatters.Formatter;
import utils.helpers.HelperFunctions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> list;
    private final Storage storage = new Storage();

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.list = taskList;
    }

    public void addTask(String info, TaskType type) throws InsufficientInfoException, StorageOperationException, IllegalValueException {
        if (info.isBlank()) {
            throw new InsufficientInfoException(type);
        } else {
            Task task = Task.of(info.substring(1), type);
            list.add(task);
            storage.save(task);
            System.out.printf("Got it. I've added this task:\n %s\nNow you have %s tasks in the list.\n", task, list.size());
        }
    }

    public void deleteTask(int index) throws StorageOperationException {
        Task removed = list.remove(index - 1);
        storage.rewrite(list);
        System.out.printf("Noted. I've removed this task:\n %s\nNow you have %s tasks in the list.\n", removed, list.size());
    }

    public void markTaskAsDone(int index) throws StorageOperationException {
        list.get(index - 1).markAsDone();
        storage.rewrite(this.list);
    }

    public void markTaskAsUndone(int index) throws StorageOperationException {
        list.get(index - 1).markAsUndone();
        storage.rewrite(list);
    }

    /**
     * Decodes lines of String representing tasks and returns a {@code TaskList}.
     *
     * @param taskStrings List of {@code String} where each represents a task.
     * @return {@code TaskList} containing tasks decoded from the {@code taskStrings}.
     * @throws InsufficientInfoException
     */
    public static TaskList decodeTxt(List<String> taskStrings) throws IllegalValueException {
        ArrayList<Task> taskList = new ArrayList<>();

        for (String taskString : taskStrings) {
            if (!taskString.isEmpty()) {
                String[] keys = taskString.split(" \\| ");
                taskList.add(Task.of(keys));
            }
        }

        return new TaskList(taskList);
    }

    public void showTasks(String command) throws IllegalValueException {
        if (command.isBlank()) {
            System.out.println(this);
        } else {
            String[] keys = command.substring(1).split(" ");
            switch (keys[0]) {
            case "today" -> System.out.println(tasksOnDate(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT)));
            case "on" -> System.out.println(tasksOnDate(HelperFunctions.stringToDateTime(keys[1], true)));
            }
        }
    }

    public TaskList tasksOnDate(LocalDateTime date) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task task: list) {
            if ((task instanceof Deadline && ((Deadline) task).dueOnDate(date) )
                    || (task instanceof Event && ((Event) task).ongoingOnDate(date))) {
                result.add(task);
            }
        }

        return new TaskList(result);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        if (list.isEmpty())
            string.append("Your task list is empty. Try adding tasks: \n1. todo <task.Task Title> \n2. event <task.Task Title> /from <Start Date> /to <End Date> \n3. deadline <task.Task Title> /by <Due Date>");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                string.append("\n");
            }
            string.append(Integer.toString(i + 1)).append(". ").append(list.get(i).toString());
        }

        return string.toString();
    }
}