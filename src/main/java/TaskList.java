import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    Task createTask(String taskType, String taskDetails) {
        Task task;
        System.out.println(taskDetails);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] taskDetailsArr = taskDetails.split(" /", 3);
        String description = taskDetailsArr[0];
        if (taskType.equals("todo")) {
            task = new Todo(description);
        } else if (taskType.equals("deadline")) {
            String byStr = taskDetailsArr[1].substring(3);
            LocalDateTime by = LocalDateTime.parse(byStr, formatter);
            task = new Deadline(description, by);
        } else {
            String fromStr = taskDetailsArr[1].substring(5);
            String toStr = taskDetailsArr[2].substring(3);
            LocalDateTime from = LocalDateTime.parse(fromStr, formatter);
            LocalDateTime to = LocalDateTime.parse(toStr, formatter);
            task = new Event(description, from, to);
        }
        return task;
    }

    void addToTaskList(Task task) {
        taskList.add(task);
    }

    Task markTaskAsDone(int taskNumber) throws FredException {
        try {
            Task task = taskList.get(taskNumber);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

    Task markTaskAsNotDone(int taskNumber) throws FredException {
        try {
            Task task = taskList.get(taskNumber);
            task.markAsNotDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

    Task deleteFromTaskList(int taskNumber) throws FredException {
        try {
            Task task = taskList.remove(taskNumber);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

    void loadTasksFromDataFile(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            addToTaskList(task);
        }
    }

    int getTaskListSize() {
        return taskList.size();
    }

    ArrayList<Task> getTaskList() {
        return taskList;
    }
}
