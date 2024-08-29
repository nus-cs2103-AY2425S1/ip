import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    // filepath for file to read/write into
    private Path filePath;
    //directory path for directory file read/write into is in
    private Path directoryPath;

    public Storage(String directoryPath, String filePath){
        this.directoryPath = Paths.get(directoryPath);
        this.filePath = Paths.get(filePath);
    }

    public void writeFile(ArrayList<Task> list) throws TalkerException {
        try {
            if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
                Files.createFile(filePath);
            }
            FileWriter fileWriter = new FileWriter(filePath.toString());
            for (Task task: list) {
                fileWriter.write(task.getSaveFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new TalkerException("Unable to write to file. Error occurred: " + e.getMessage());
        }
    }

    public ArrayList<Task> readFile() throws TalkerException {
        ArrayList<Task> outputList = new ArrayList<>();
        try {
            if (Files.exists(directoryPath) &&
                    Files.isDirectory(directoryPath) &&
                    Files.exists(filePath) &&
                    Files.isRegularFile(filePath)) {
                Scanner scanner = new Scanner(filePath);
                while (scanner.hasNext()) {
                    String taskString = scanner.nextLine();
                    outputList.add(readTaskFromFile(taskString));
                }
            }
        } catch (IOException e) {
            throw new TalkerException("Unable to read file. Error occurred: " + e.getMessage());
        }
        return outputList;
    }

    private Task readTaskFromFile(String taskString) throws TalkerException{
        String[] parsed = taskString.split(" \\| ");
        boolean isComplete;

        if (parsed[1].equals("X") || parsed[1].equals(" ")) {
            isComplete = parsed[1].equals("X");
        } else {
            throw new TalkerException("Invalid completion tag, corrupted file detected.");
        }
        switch (parsed[0]) {
            case "T":
                if (parsed.length != 3) {
                    throw new TalkerException("Invalid ToDo Task, corrupted file detected.");
                }
                return new ToDo(parsed[2], isComplete);
            case "D":
                if (parsed.length != 4) {
                    throw new TalkerException("Invalid Deadline Task, corrupted file detected.");
                }
                return new Deadline(parsed[2], parsed[3], isComplete);
            case "E":
                if (parsed.length != 5) {
                    throw new TalkerException("Invalid Event Task, corrupted file detected.");
                }
                return new Event(parsed[2], parsed[3], parsed[4], isComplete);
            default:
                throw new TalkerException("Invalid task type, corrupted file detected.");
        }
    }
}
