import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SavingSystem {
    private static String LOCATION_DIR = "data";
    private static String LOCATION_FILENAME = "kita.txt";
    private static String LOCATION = LOCATION_DIR + "/" + LOCATION_FILENAME;
    private File saveFile;
    private Path saveFilePath;

    public SavingSystem() throws FileSystemException, IOException{
        this.saveFile = new File(LOCATION);
        if (!this.saveFile.exists() || !this.saveFile.isFile()) {
            System.out.println("Save file does not exist, creating one now at " + LOCATION);
            this.createSaveFile();
        }

        this.saveFilePath = Paths.get(LOCATION);
    }

    /*
    * Creates a new file at the path - regardless of whether the file exists or not
    * Directory will also be created along the path
    * @return void
    * */
    public void createSaveFile() throws IOException {
        File directory = new File(LOCATION_DIR);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                System.out.println("Failed to create directory: " + LOCATION_DIR);
                throw new FileSystemException("data/");
            }
        }

        if (!this.saveFile.createNewFile()) {
            System.out.println("Failed to create file: " + LOCATION);
            throw new FileSystemException("data/kita.txt");
        }
    }

    public ArrayList<Task> readTasksFromFile() throws IOException {
        try {
            List<String> lines = Files.readAllLines(this.saveFilePath);
            ArrayList<Task> savedTasks = new ArrayList<>();
            for (String line : lines) {
                String[] splitLine = line.split(",");
                Task taskToAdd;
                if (splitLine[0].equals("E")) {
                    taskToAdd = new Event(splitLine[2], splitLine[3], splitLine[4]);
                } else if (splitLine[0].equals("D")) {
                    taskToAdd = new Deadline(splitLine[2], splitLine[3]);
                } else {
                    taskToAdd = new ToDo(splitLine[2]);
                }
                taskToAdd.setCompleted(splitLine[1].equals("0") ? false : true);
                savedTasks.add(taskToAdd);
            }
            return savedTasks;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Format error - file might be corrupted, returning an empty task list insttead.");
            return new ArrayList<>();
        }
    }

    public void writeTasksToFile(ArrayList<Task> commandList) throws IOException {
        ArrayList<String> taskStrings = new ArrayList<>();

        for (Task task: commandList) {
            String type = task.type();
            String checked = task.getCompleted() ? "1" : "0";
            String name = task.getName();
            String additionalProperties;

            if (type.equals("E")) {
                Event eventTask = (Event) task;
                additionalProperties = "," + eventTask.getFrom() + "," + eventTask.getTo();
            }
            else if (type.equals("D")) {
                Deadline deadlineTask = (Deadline) task;
                additionalProperties = "," + deadlineTask.getByTime();
            }
            else {
                additionalProperties = "";
            }

            taskStrings.add(type + "," + checked + "," + name + additionalProperties);
        }
        Files.write(saveFilePath, taskStrings);
    }
}
