import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasksToFile(ArrayList<Task> tasks) {
        checkFileExists();
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {
        checkFileExists();
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                Task task = Parser.parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("Skipping invalid task" + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous task file found. Creating one...");
        } catch (IOException e) {
            System.out.println("Error occurred while loading tasks.");
            e.printStackTrace();
        }
        return tasks;
    }

    public static void checkFileExists() {
        File file = new File(filePath);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error occurred when creating the file.");
            e.printStackTrace();
        }
    }


}
