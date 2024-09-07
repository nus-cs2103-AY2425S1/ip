package Task;

import CommandLine.Line;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Save.Save;

import static java.lang.Integer.parseInt;

public class TaskList {
    private ArrayList<Task> taskList;
    private final Line line = new Line();
    private int taskListLength;
    private int startPointer;
    private Save save;

    public TaskList() {
        this.save = new Save();
        this.taskList = new ArrayList<Task>();
        this.startPointer = 0;
        this.taskListLength = 0;
        loadTasksFromSave();
    }

    private String formatTaskForFile(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getTaskTypeAsString()).append(" | ");
        sb.append(task.getStatus().equals("X") ? "1" : "0").append(" | ");
        sb.append(task.getTaskName());
        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).getDeadline());
        } else if (task instanceof Event) {
            sb.append(" | ").append(((Event) task).getStart());
            sb.append(" | ").append(((Event) task).getEnd());
        }
        return sb.toString();
    }

    private void saveTasks() {
        File file = new File(save.getPath());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                writer.write(formatTaskForFile(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasksFromSave() {
        File file = new File(save.getPath());
        if (!file.exists()) {
            System.out.println("File does not exist");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                Task task = parseTask(line);
                taskList.add(task);
                taskListLength++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }


    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        for (String part : parts) {
            assert !part.isEmpty() : "Error";
        }
        Task.TaskType taskType = Task.TaskType.valueOf(parts[0]);
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;
        list();
        switch (taskType) {
        case T:
            task = ToDo.of(description, taskType);
            this.taskList.add(task);
            saveTasks();
            break;
        case D:
            try {
                task = Deadline.of(description + " /by " + parts[3], taskType);
                this.taskList.add(task);
                saveTasks();
            } catch (TaskCreationException e) {
                throw new IllegalArgumentException("Invalid task type: " + taskType);
            }
            break;
        case E:
            try {
                task = Event.of(description + "/from " + parts[3] + " /to " + parts[4], taskType);
                this.taskList.add(task);
                saveTasks();
            } catch (TaskCreationException e) {
                System.out.println("Error occurred while parsing task");
                throw new IllegalArgumentException("Invalid task type: " + taskType);
            }
            break;
        default:
            throw new IllegalArgumentException("Invalid task type: " + taskType);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a task with given string.
     *
     * @param s the string containing information about the task type
     * @param taskType the type of task
     * @throws TaskCreationException if error occurs while creating task
     * @return how many tasks are in the list now, and what task was added
     */
    public String add(String s, Task.TaskType taskType) {
        StringBuilder response = new StringBuilder();
        try {
            Task newTask;
            switch (taskType) {
                case T:
                    newTask = ToDo.of(s, taskType);
                    break;
                case D:
                    newTask = Deadline.of(s, taskType);
                    break;
                case E:
                    newTask = Event.of(s, taskType);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid task type");
            }
            if (isDuplicate(newTask)) {
                return "That task is a duplicate, unable to add task";
            }
            this.taskList.add(newTask);
            this.taskListLength += 1;

            response.append("Got it. I've added this task:\n")
                    .append("[")
                    .append(newTask.getTaskTypeAsString())
                    .append("][ ] ")
                    .append(newTask.getTaskName())
                    .append("\n");

            if (this.taskListLength == 1) {
                response.append("Now you have 1 task in the list.\n");
            } else {
                response.append("Now you have ")
                        .append(this.taskListLength)
                        .append(" tasks in the list.\n");
            }

        } catch (TaskCreationException e) {
            response.append(e.getMessage())
                    .append("\n");
        }

        return response.toString();
    }

    private boolean isDuplicate(Task newTask) {
        for (Task task : this.taskList) {
            if (task.getTaskName().equals(newTask.getTaskName())) {
                return true;
            }
        }
        return false;
    }



    /**
     * Deletes a task from the task list.
     *
     * @param idx index displayed on the list to be deleted
     * @return returns how many tasks are left in the task list
     */
    public String delete(int idx) {
        idx = idx - 1;
        StringBuilder response = new StringBuilder();

        if (idx < 0 || idx >= taskListLength) {
            return "Enter a valid index";
        } else {
            Task removedTask = taskList.remove(idx);
            taskListLength--;

            response.append("    Noted. I've removed this task:\n")
                    .append("      [")
                    .append(removedTask.getTaskTypeAsString())
                    .append("][")
                    .append(removedTask.getStatus())
                    .append("] ")
                    .append(removedTask.getTaskName())
                    .append("\n");

            if (taskListLength == 1) {
                response.append("Now you have 1 task in the list.\n");
            } else {
                response.append("Now you have ")
                        .append(taskListLength)
                        .append(" tasks in the list.\n");
            }
        }

        return response.toString();
    }
    /**
     * Marks task at given index as completed.
     *
     * @param s index to be marked as done
     */
    public String markAsDone(String s) {
        int idx = parseInt(s);
        idx -= 1;
        if ((idx < 0) || (idx >= taskListLength)) {
            return "Error marking as done";
        }
        Task currentTask = taskList.get(idx);
        currentTask.markAsDone();
        String response = "Nice! I've marked this task as done:" + "[" + currentTask.getStatus() + "] " + currentTask.getTaskName();
        return response;
    }

    /**
     * Marks task at given index as not completed.
     *
     * @param s index to be marked as undone
     */
    public String markAsUndone(String s) {
        int idx = parseInt(s);
        idx -= 1;
        if ((idx < 0) || (idx >= taskListLength)) {
            return "Error marking as undone";
        }
        Task currentTask = taskList.get(idx);
        currentTask.markAsUndone();
        String response = "Ok, I've marked this task as not done:" + "[" + currentTask.getStatus() + "] " + currentTask.getTaskName();
        return response;
    }


    /**
     * Prints all tasks in the task list.
     *
     * @return list of all tasks as a string
     */
    public String list() {
        if (taskListLength == 0) {
            return "No tasks in list";
        }

        StringBuilder response = new StringBuilder();
        for (int i = startPointer; i < taskListLength; i++) {
            int counter = i + 1;
            Task currentTask = this.taskList.get(i);
            response.append(counter)
                    .append(". [")
                    .append(currentTask.getTaskTypeAsString())
                    .append("] [")
                    .append(currentTask.getStatus())
                    .append("] ")
                    .append(currentTask.getTaskName())
                    .append("\n");
        }
        return response.toString();
    }

    /**
     * Prints tasks with matching string in description.
     *
     * @param s the keyword
     * @return tasks with matching keyword as a string
     */
    public String find(String s) {
        List<Task> tasks = new ArrayList<>();
        assert !s.isEmpty() : "Search term should not be empty";
        StringBuilder response = new StringBuilder();

        for (Task task : taskList) {
            if (task.getTaskName().toLowerCase().contains(s.toLowerCase())) {
                tasks.add(task);
            }
        }

        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        }

        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int counter = i + 1;
            Task currentTask = tasks.get(i);
            response.append("    ")
                    .append(counter)
                    .append(". [")
                    .append(currentTask.getTaskTypeAsString())
                    .append("] [")
                    .append(currentTask.getStatus())
                    .append("] ")
                    .append(currentTask.getTaskName())
                    .append("\n");
        }

        return response.toString();
    }

}