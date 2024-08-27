import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //Load tasks from the file
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = reader.readLine()) != null) {
            Task task = parseTask(line);
            tasks.add(task);
        }
        reader.close();
        return tasks;
    }

    //Method to parse a line into a Task object
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            System.out.print("File corrupted");
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch(type) {
            case "T":
                return new ToDoTask(description, isDone);
            case "D":
                if (parts.length < 4) {
                    System.out.print("File corrupted");
                    return null;
                }
                String deadline = parts[3];
                return new DeadlineTask(description, isDone, deadline);
            case "E":
                if (parts.length < 5) {
                    System.out.print("File corrupted");
                    return null;
                }
                String start = parts[3];
                String end = parts[4];
                return new EventTask(description, isDone, start, end);
            default:
                return null;
        }
    }

    public void saveTasksToFile(List<Task> tasks) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task : tasks) {
                String line = taskToString(task);
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.print("File cannot be found");
        }
    }

    private String taskToString(Task task) {

        if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return eventTask.toString();
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return deadlineTask.toString();
        } else {
            ToDoTask toDoTask = (ToDoTask) task;
            return toDoTask.toString();
        }
    }
}
