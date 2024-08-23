import java.io.*;
import java.util.ArrayList;

public class DBHandler {

    private static final String filePath = "./data/db.txt";

    static void fileChecker() {
        File file = new File(filePath);
        File directory = new File(file.getParent());

        try{
            if(!directory.exists()){
                boolean isCreated = directory.mkdir();
                if (!isCreated){
                    System.out.println("Issue creating Directory");
                }
            }
            if(!file.exists()){
                if(!file.createNewFile()){
                    System.out.println("Issue creating File.");
                }
            }
        }catch (IOException e) {
            System.out.println("Error in file creation.");
        }
    }

    static void load(ArrayList<Task> tasks) {

        fileChecker();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0].strip();
                boolean isDone = parts[1].strip().equals("1");
                String desc = parts[2].strip();

                switch (taskType) {
                    case "T":
                        Task.ToDo todo = new Task.ToDo(desc);
                        if (isDone) todo.markDone();
                        tasks.add(todo);
                        break;

                    case "D":
                        String by = parts[3].strip();
                        Task.Deadline deadline = new Task.Deadline(desc, by);
                        if (isDone) deadline.markDone();
                        tasks.add(deadline);
                        break;

                    case "E":
                        String from = parts[3].strip();
                        String to = parts[4].strip();
                        Task.Event event = new Task.Event(desc, from, to);
                        if (isDone) event.markDone();
                        tasks.add(event);
                        break;

                    default:
                        System.out.println("Invalid task type in db.txt: " + taskType);
                        break;
                }
            }
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

        if (task instanceof Task.Deadline deadline) {
            line += " | " + deadline.by;
        } else if (task instanceof Task.Event event) {
            line += " | " + event.from + " | " + event.to;
        }
        return line;
    }

}
