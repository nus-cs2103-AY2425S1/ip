import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException, NaegaException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            file.getParentFile().mkdirs();  // Create the folder if it doesn't exist
            file.createNewFile();  // Create the file if it doesn't exist
            return tasks;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" \\| ");
            Task task = parseTask(parts);
            tasks.add(task);
        }
        reader.close();
        return tasks;
    }

    private Task parseTask(String[] parts) throws NaegaException {
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                // Todo tasks should have 3 parts
                if (parts.length != 3) {
                    throw new NaegaException("Invalid data format for Todo task.");
                }
                Task todo = new Todo(description);
                if (isDone) todo.markAsDone();
                return todo;

            case "D":
                // Deadline tasks should have 4 parts
                if (parts.length != 4) {
                    throw new NaegaException("Invalid data format for Deadline task.");
                }
                Task deadline = new Deadline(description, parts[3]);
                if (isDone) deadline.markAsDone();
                return deadline;

            case "E":
                // Event tasks should have 5 parts
                if (parts.length != 5) {
                    throw new NaegaException("Invalid data format for Event task.");
                }
                Task event = new Event(description, parts[3], parts[4]);
                if (isDone) event.markAsDone();
                return event;

            default:
                throw new NaegaException("Corrupted data in file.");
        }
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            writer.write(task.toSaveFormat() + "\n");
        }
        writer.close();
    }
}