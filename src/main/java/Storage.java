import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    // write the tasks to file

    public static void saveTasks(ArrayList<Task> tasks) {
        // check if file exists
        // if file does exist, write to it
        // if file does not exist, create file and write to it
        try {
            Files.createDirectories(Paths.get("data/"));
            File myObj = new File("data/atlas.txt");
            if (myObj.createNewFile()) {
                // System.out.println("File created: " + myObj.getName());
            } else {
                // System.out.println("File already exists. Writing to file...");
            }

             // Write to the file
            FileWriter writer = new FileWriter(myObj, false); // false for overwrite mode
            for (Task task : tasks) {
                writer.write(task.toStorage() + "\n");
            }
            writer.close();

            // System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void loadTasks(ArrayList<Task> tasks) throws IOException {
        File myObj = new File("data/atlas.txt");
        if (myObj.createNewFile()) {
            // System.out.println("File created: " + myObj.getName());
        } else {
            // System.out.println("File already exists. Loading tasks...");
        }

        // Read from the file
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("data/atlas.txt")));
        for (String line : lines) {
            String[] task = line.split("\\|");
            if (task.length < 3) {
                throw new IOException("Invalid task format");
            }
            Task newTask;
            switch (task[0]) {
                case "T":
                    newTask = new ToDo(task[2]);
                    break;
                case "D":
                    newTask = new Deadline(task[2], task[3]);
                    break;
                case "E":
                    newTask = new Event(task[2], task[3], task[4]);
                    break;
                default:
                    newTask = new Task(task[2]);
                    break;
            }
            newTask.setDone(task[1].equals("X"));
            tasks.add(newTask);
        }
    }
}
