import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String directoryPath;
    private final String filePath;

    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    public void saveTasks(TaskList tasks) { //writes into a text file
        try {
            Files.createDirectories(Paths.get(this.directoryPath)); //ensure directory exists
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.filePath))) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error has occurred in saving the tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() throws DonnaException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path filePath = Paths.get(this.filePath);
            if (!Files.exists(filePath)) { //ensure that the directory exists- if not, it will be created
                return tasks; //empty arrayList is returned when no file exists
            }

            File file = new File(String.valueOf(filePath));
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] inWords = line.split(" \\| ");
                String taskType = inWords[0];
                boolean isDone = inWords[1].equals("1");
                Task task = null;

                switch (taskType) {
                case "T":
                    task = new ToDo(inWords[2]);
                    break;
                case "D":
                    task = new Deadline(inWords[2], inWords[3]);
                    break;
                case "E":
                    task = new Event(inWords[2], inWords[3], inWords[4]);
                    break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }

        } catch (IOException | ArrayIndexOutOfBoundsException |DonnaException e) {
            // file data is corrupted, start in a new file
//            System.out.println("No existing file found / File is corrupted. Starting in a new file.");
            return new ArrayList<Task>(); //return empty arrayList to start afresh
        }
        return tasks;
    }
}
