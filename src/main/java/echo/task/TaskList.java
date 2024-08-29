package echo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }
    public void addTask(String description, TaskType type, String info) {
        switch (type) {
        case TODO:
            tasks.add(new Todo(description));
            break;
        case EVENT:
            String[] parts = info.split("->");
            tasks.add(new Event(description, parts[0], parts[1]));
            break;
        }
    }
    public String getFoundTasks(String stringToFind) {
        String foundTasks = "";
        int count = 1;
        for (Task task: tasks) {
            String taskString = task.getTaskString();
            if (taskString.contains(stringToFind)) {
                foundTasks += count + ". " + taskString + "\n";
                count++;
            }
        }
        return foundTasks;
    }
    public String getTasksToSave() {
        String s = "";
        for (Task t : tasks) {
            s += t.getData() + "\n";
        }
        return s;
    }
    public void addTask(Task t) {
        tasks.add(t);
    }
    public void addDeadline(String description, LocalDate deadline) {
        tasks.add(new Deadline(description, deadline));
    }
    public String getTasksString() {
        String tasksString = "";
        int count = 1;
        for (Task task : tasks) {
            tasksString += count + ". " + task.getTaskString() + "\n";
            count++;
        }
        return tasksString;
    }
    public void markTask(int index) {
        tasks.get(index - 1).completeTask();
    }
    public String getTaskString(int index) {
        return tasks.get(index - 1).getTaskString() + "\n";
    }
    public void unmarkTask(int index) {
        tasks.get(index - 1).uncompleteTask();
    }
    public int getNumTasks() {
        return tasks.size();
    }
    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    private class Task {
        private Boolean isComplete = false;
        private String description;
        private TaskType type;
        private Task(String description, TaskType type) {
            this.description = description;
            this.type = type;
        }

        public String getTaskString() {
            String msg = "[" + this.type.getTypeSymbol() + "] [";
            if (isComplete) {
                msg += "X] ";
            } else {
                msg += " ] ";
            }
            msg += this.description;
            return msg;
        }
        public void printTask() {
            System.out.print(this.getTaskString());
        }
        private void completeTask() {
            this.isComplete = true;
        }
        private void uncompleteTask() {
            this.isComplete = false;
        }
        public String getData() {
            return type.getTypeSymbol() + " | " + (isComplete ? 1 : 0) + " | " + description;
        }
    }
    private class Deadline extends Task {
        LocalDate deadline;
        Deadline(String description, LocalDate deadline) {
            super(description, TaskType.DEADLINE);
            this.deadline = deadline;
        }

        @Override
        public String getTaskString() {
            return super.getTaskString() +
                String.format(
                    " (by: %s)",
                    this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                );
        }
        @Override
        public void printTask() {
            System.out.println(this.getTaskString());
        }

        @Override
        public String getData() {
             return super.getData() + " | " + this.deadline;
        }
    }

    private class Todo extends Task {
        Todo(String description) {
            super(description, TaskType.TODO);
        }

        @Override
        public String getData() {
            return super.getData();
        }
    }

    private class Event extends Task {
        private String start;
        private String end;
        Event(String description, String start, String end) {
            super(description, TaskType.EVENT);
            this.start = start;
            this.end = end;
        }
        @Override
        public String getTaskString() {
            return super.getTaskString() +
                    String.format(
                            " (from: %s to: %s)",
                            this.start,
                            this.end
                    );
        }
        @Override
        public void printTask() {
            System.out.println(this.getTaskString());
        }
        @Override
        public String getData() {
            return super.getData() + " | " + this.start + "->" + this.end;
        }
    }
}
