import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class KafkaTextReader {

    public static void printFileContents(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String[] fileContent = s.nextLine().trim().split(" \\| ");
                String taskType = fileContent[0];
                boolean isDone = Boolean.parseBoolean(fileContent[1]);
                String description = fileContent[2];
                switch (taskType) {
                    case "T":
                        Task todo = new Todo(description, isDone);
                        tasks.add(todo);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(fileContent[3]);
                        Task deadline = new Deadline(description, by, isDone);
                        tasks.add(deadline);
                        break;
                    case "E":
                        String from = fileContent[3];
                        String to = fileContent[4];
                        Task event = new Event(description, from, to, isDone);
                        tasks.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
                System.out.println("File not found");
        }
    }
}
