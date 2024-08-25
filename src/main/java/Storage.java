import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String dirPath;
    private String fileName;
    public Storage(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> existingTasks = new ArrayList<>();
        try {
            Scanner fileScanner = this.getFile();
            while (fileScanner.hasNextLine()) {
                // I don't really like this nested try block
                try {
                    Task newTask = Parser.parseFileLine(fileScanner.nextLine());
                    existingTasks.add(newTask);
                } catch (IOException e) {
                    System.out.println("File format corrupted");
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return existingTasks;
    }

    private Scanner getFile() throws FileNotFoundException {
        File directory = new File(this.dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File taskListFile = new File(this.dirPath + this.fileName);
        try {
            taskListFile.createNewFile();
        } catch (IOException e) {
            System.out.println("IO Error in creating new file: " + e.getMessage());
        }
        return new Scanner(taskListFile);
    }

    public void writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.dirPath + this.fileName);
            for (Task task : taskList.getList()) {
                fw.write(task.encode() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }
}
