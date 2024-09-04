package task;

import data.InsufficientInfoException;
import storage.Storage;
import storage.StorageOperationException;
import utils.exceptions.IllegalValueException;
import utils.helpers.HelperFunctions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public String addTask(String info, TaskType type) throws InsufficientInfoException, StorageOperationException, IllegalValueException {
        if (info.isBlank()) {
            throw new InsufficientInfoException(type);
        } else {
            Task task = Task.of(info.substring(1), type);
            list.add(task);
            storage.save(task);
            return String.format("Got it. I've added this task:\n %s\nNow you have %s tasks in the list.", task, list.size());
        }
    }

    public void deleteTask(int index) throws StorageOperationException, IndexOutOfBoundsException {
        if (index > list.size()) throw new IndexOutOfBoundsException(String.format("There is only %s tasks in the list.", list.size()));
        Task removed = list.remove(index - 1);
        storage.rewrite(list);
        System.out.printf("Noted. I've removed this task:\n %s\nNow you have %s tasks in the list.\n", removed, list.size());
    }

    public String markTaskAsDone(int index) throws StorageOperationException {
        storage.rewrite(this.list);
        return list.get(index - 1).markAsDone();
    }

    public String markTaskAsUndone(int index) throws StorageOperationException {
        storage.rewrite(list);
        return list.get(index - 1).markAsUndone();
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
            string.append("Your task list is empty. Try adding tasks: \n1. todo <Task Title> \n2. event <Task Title> /from <Start Date> /to <End Date> \n3. deadline <Task Title> /by <Due Date>");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                string.append("\n");
            }
            string.append(Integer.toString(i + 1)).append(". ").append(list.get(i).toString());
        }

        return string.toString();
    }
}