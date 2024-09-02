import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected static final String DIRECTORY_PATH = "./data";
    protected static final String DB_PATH = String.valueOf(Paths.get(Storage.DIRECTORY_PATH, "devon_tasks.txt"));
    protected static final String DB_DELIMITER = "\\|";

    protected static final DateTimeFormatter DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter DATE_TIME_FORMATTER_FOR_DB = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public Storage() {

    }

    protected ArrayList<String> loadTasksFromDatabase() {
        ArrayList<String> tasks = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(new File(Storage.DB_PATH));
            while (fileReader.hasNextLine()) {
                tasks.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            this.createTaskDatabase();
        }
        return tasks;
    }

    private void createTaskDatabase() {
        new File(Storage.DIRECTORY_PATH).mkdir();
        try {
            new File(Storage.DB_PATH).createNewFile();
        } catch (IOException e) {
            System.out.println("Error: Cannot create database!");
        }
    }

    protected void saveTasksToDatabase(TaskList tasks) throws IOException {
        FileWriter filewriter = new FileWriter(Storage.DB_PATH);
        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            bufferedWriter.write(tasks.getTask(i).dbReadableFormat());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        filewriter.close();
    }
}
