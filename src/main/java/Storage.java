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

    public void writeFile(TaskList list) throws TalkerException {
        try {
            if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
                Files.createFile(filePath);
            }
            FileWriter fileWriter = new FileWriter(filePath.toString());
            list.writeToFile(fileWriter);
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
                    outputList.add(Parser.parseTaskFromFile(taskString));
                }
            }
        } catch (IOException e) {
            throw new TalkerException("Unable to read file. Error occurred: " + e.getMessage());
        }
        return outputList;
    }
}
