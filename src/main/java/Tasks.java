import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> tasks;
    public Tasks() {
        tasks = new ArrayList<>();
    }
    public void addTask(String description, TaskType type, String info) {
        switch (type) {
            case TODO:
                tasks.add(new Todo(description));
                break;
            case DEADLINE:
                String deadline = info.substring(info.indexOf("/") + 4);
                tasks.add(new Deadline(description, deadline));
                break;
            case EVENT:
                String[] parts = info.split("/to");
                String start = parts[0].replace("/from", "").trim();
                String end = parts[1].replace("/to", "").trim();
                tasks.add(new Event(description, start, end));
                break;
        }
    }
    public void printTasks() {
        int count = 1;
        for (Task task : tasks) {
            System.out.print(count + ". ");
            task.printTask();
            System.out.println();
            count++;
        }
    }
    public void markTask(int index) {
        tasks.get(index - 1).completeTask();
    }

    public void printTask(int index) {
        tasks.get(index - 1).printTask();
        System.out.println();
    }
    public void unmarkTask(int index) {
        tasks.get(index - 1).uncompleteTask();
    }

    public int getNumTasks() {
        return tasks.size();
    }

    private class Task {
        private Boolean isComplete = false;
        private String description;
        protected TaskType type;
        private Task(String description, TaskType type) {
            this.description = description;
            this.type = type;
        }

        public void printTask() {
            String msg = "[" + this.type.getTypeSymbol() + "] [";
            if (isComplete) {
                msg += "X] ";
            } else {
                msg += " ] ";
            }
            msg += this.description;
            System.out.print(msg);
        }

        private void completeTask() {
            this.isComplete = true;
        }
        private void uncompleteTask() {
            this.isComplete = false;
        }
    }
    private class Deadline extends Task {
        String deadline;
        Deadline(String description, String deadline) {
            super(description, TaskType.DEADLINE);
            this.deadline = deadline;
        }

        @Override
        public void printTask() {
            super.printTask();
            System.out.printf("(by: %s)", this.deadline);
        }
    }

    private class Todo extends Task {
        Todo(String description) {
            super(description, TaskType.TODO);
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
        public void printTask() {
            super.printTask();
            System.out.printf("(from: %s to: %s)", this.start, this.end);
        }
    }
}
