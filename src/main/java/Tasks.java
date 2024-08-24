import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tasks {
    private List<Task> tasks;
    private List<String> taskLines;
    public Tasks() {
        tasks = new ArrayList<>();
        taskLines = new ArrayList<>();
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
    public void addDeadline(String description, LocalDate deadline) {
        tasks.add(new Deadline(description, deadline));
    }
    public void writeToFile() {
        String lineToWrite = tasks.get(this.getNumTasks() - 1).getData() + "\n";
        FileWriter fw =  null;
        try {
            fw = new FileWriter("src/main/data/savedTasks.txt");
            fw.write(lineToWrite);
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
        taskLines.add(lineToWrite);
    }
    public void appendToFile() {
        String lineToWrite = tasks.get(this.getNumTasks() - 1).getData() + "\n";
        FileWriter fw =  null;
        try {
            fw = new FileWriter("src/main/data/savedTasks.txt",true);
            fw.write(lineToWrite);
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
        taskLines.add(lineToWrite);
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

        String lineToChange = taskLines.get(index - 1);
        String[] parts = lineToChange.split("0", 2);
        parts[0] += "1";
        lineToChange = parts[0] + parts[1];
        taskLines.set(index - 1, lineToChange);
        try {
            Files.write(Path.of("src/main/data/savedTasks.txt"), String.join("", taskLines).getBytes());
        } catch (IOException e) {
            System.out.println("Cannot write marked task to file.");
        }
    }

    public void printTask(int index) {
        tasks.get(index - 1).printTask();
        System.out.println();
    }
    public void unmarkTask(int index) {
        tasks.get(index - 1).uncompleteTask();

        String lineToChange = taskLines.get(index - 1);
        String[] parts = lineToChange.split("1", 2);
        parts[0] += "0";
        lineToChange = parts[0] + parts[1];
        taskLines.set(index - 1, lineToChange);
        try {
            Files.write(Path.of("src/main/data/savedTasks.txt"), String.join("", taskLines).getBytes());
        } catch (IOException e) {
            System.out.println("Cannot write marked task to file.");
        }
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);
        taskLines.remove(index - 1);
        try {
            Files.write(Path.of("src/main/data/savedTasks.txt"), String.join("", taskLines).getBytes());
        } catch (IOException e) {
            System.out.println("Cannot write deleted line to file.");
        }
    }

    private class Task {
        private Boolean isComplete = false;
        private String description;
        private TaskType type;
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
        public void printTask() {
            super.printTask();
            System.out.printf(" (by: %s)", this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
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
        public void printTask() {
            super.printTask();
            System.out.printf(" (from: %s to: %s)", this.start, this.end);
        }

        @Override
        public String getData() {
            return super.getData() + " | " + this.start + "->" + this.end;
        }
    }
}
