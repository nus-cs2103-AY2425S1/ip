import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private ArrayList<IndividualTask> tasks = new ArrayList<IndividualTask>();
    private String path;

    public Storage(String path) {
        this.path = path;
        this.loadTasksFromFile();
    }
    public void loadTasksFromFile() {
        File directory = new File("./data");
        File file = new File(path);
        if (!directory.exists()) {
            System.out.println("No existing directory found. " +
                    "Create a directory data and a file 'duke.txt' inside that directory. " +
                    "Starting with an empty task list.");
            return;
        }
        if (!file.exists()) {
            System.out.println("No existing data file found. " +
                    "Create a file 'duke.txt' in the data directory. " +
                    "Starting with an empty task list.");
            return;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String tasks = scanner.nextLine();
                String[] parts = tasks.split(" \\| ");
                String typeOfTask = parts[0];
                boolean isTaskDone = parts[1].equals("1");
                String descriptionOfTask = parts[2];
                IndividualTask task = switch (typeOfTask) {
                    case "T" -> new ToDo(descriptionOfTask);
                    case "D" -> new Deadline(descriptionOfTask, parts[3]);
                    case "E" -> new Event(descriptionOfTask, parts[3], parts[4]);
                    default -> null;
                };
                if (task != null) {
                    if (isTaskDone) {
                        task.markOrUnmark("mark");
                    }
                    this.tasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("The data file not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("There is no data in the file");
        }
    }
    public ArrayList<IndividualTask> load() {
        return this.tasks;
    }

    public void saveTasksToFile(ArrayList<IndividualTask> curList)  {
        try {
            FileWriter writer = new FileWriter(path);
            for (IndividualTask task : curList) {
                writer.write(task.saveToFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in saving task: " + e.getMessage());
        }
    }
}
