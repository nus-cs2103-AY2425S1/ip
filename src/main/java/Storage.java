import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        System.out.println("Saving to: " + new File(filePath).getAbsolutePath());  // Debugging output

    }

    

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println("Reading line: " + line); // Debugging line
            String[] parts = line.split(" \\| ");
            System.out.println("Split parts: " + Arrays.toString(parts)); // Debugging line

            Task task;

            switch (parts[0]) {
                case "T":
                    task = new Todo(parts[2]);
                    break;
                case "D":
                    task = new Deadline(parts[2], parts[3]);
                    break;
                case "E":
                    task = new Event(parts[2], parts[3], parts[4]);
                    break;
                default:
                    throw new IOException("File content is corrupted");
            }

            if (parts[1].equals("1")) {
                task.markAsDone();
            }

            tasks.add(task);
            System.out.println("Task added: " + task); // Debugging line
        }

        return tasks;
    }


    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toSaveFormat() + System.lineSeparator());
        }
        writer.close();
    }
}
