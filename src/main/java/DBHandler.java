import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBHandler {

    private static final String filePath = "./data/db.txt";

    static void load(ArrayList<Task> tasks) {

        Utils.fileChecker(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            try{
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String taskType = parts[0].trim();
                    boolean isDone = parts[1].trim().equals("1");
                    String desc = parts[2].trim();

                    switch (taskType) {
                        case "T":
                            Task.ToDo todo = new Task.ToDo(desc);
                            if (isDone) todo.markDone();
                            tasks.add(todo);
                            break;

                        case "D":
                            Task.Deadline deadline = new Task.Deadline(desc, Utils.parseDateTime(parts[3].trim()));
                            if (isDone) deadline.markDone();
                            tasks.add(deadline);
                            break;

                        case "E":
                            Task.Event event = new Task.Event(desc, Utils.parseDateTime(parts[3].trim()), Utils.parseDateTime(parts[4].trim()));
                            if (isDone) event.markDone();
                            tasks.add(event);
                            break;

                        default:
                            System.out.println("Invalid task type in db.txt: " + taskType);
                            break;
                    }
                }
            }catch (ArrayIndexOutOfBoundsException ignored) {}
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    static void save(ArrayList<Task> tasks){
        try (FileWriter writer = new FileWriter(filePath, false)) {  // 'false' to overwrite the file
            for (Task task : tasks) {
                String line = getString(task);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    private static String getString(Task task) {
        String taskType = task instanceof Task.ToDo ? "T" : task instanceof Task.Deadline ? "D" : "E";
        String status = task.isDone ? "1" : "0";
        String line = taskType + " | " + status + " | " + task.desc;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        if (task instanceof Task.Deadline deadline) {
            line += " | " + deadline.by.format(formatter);
        } else if (task instanceof Task.Event event) {
            line += " | " + event.from.format(formatter) + " | " + event.to.format(formatter);
        }
        return line;
    }

}
