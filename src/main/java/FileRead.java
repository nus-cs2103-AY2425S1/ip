import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class FileRead {
    public static ArrayList<Task> loadFileContents(String filePath) throws FileNotFoundException, CorruptFileException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> output = new ArrayList<>();

        // Parse file
        while (s.hasNext()) {
            String input = s.nextLine();
            String[] splitString = input.split(" \\| ", 3); // Type | 1 | Content
            if (splitString.length < 3) {
                throw new CorruptFileException(filePath);
            }

            Task task;
            if (splitString[0].equals("D")){
                // Save as deadline
                String[] details = splitString[2].split(" \\| ", 2);
                if (details.length < 2) {
                    throw new CorruptFileException(filePath);
                }
                task = new Deadline(details[0], details[1]);
            } else if (splitString[0].equals("E")){
                // Save as event
                String[] details = splitString[2].split(" \\| ", 3);
                if (details.length < 3) {
                    throw new CorruptFileException(filePath);
                }
                task = new Event(details[0], details[1], details[2]);
            } else if (splitString[0].equals("T")) {
                // Save as todo
                task = new Todo(splitString[2]);
            } else {
                throw new CorruptFileException(filePath);
            }

            // Check for mark
            if (Integer.parseInt(splitString[1]) != 0) {
                task.markAsDone();
            }

            output.add(task);
        }
        return output;
    }
}
