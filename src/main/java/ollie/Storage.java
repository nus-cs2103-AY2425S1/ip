import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws OllieException {
        try {
            File f = new File(this.filePath); // create a File for the given file path
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
                switch (splitString[0]) {
                    case "D" -> {
                        // Save as deadline
                        String[] details = splitString[2].split(" \\| ", 2);
                        if (details.length < 2) {
                            throw new CorruptFileException(filePath);
                        }
                        task = new Deadline(details[0], details[1]);
                    }
                    case "E" -> {
                        // Save as event
                        String[] details = splitString[2].split(" \\| ", 3);
                        if (details.length < 3) {
                            throw new CorruptFileException(filePath);
                        }
                        task = new Event(details[0], details[1], details[2]);
                    }
                    case "T" ->
                        // Save as todo
                            task = new Todo(splitString[2]);
                    default -> throw new CorruptFileException(filePath);
                }

                // Check for mark
                if (Integer.parseInt(splitString[1]) != 0) {
                    task.markAsDone();
                }

                output.add(task);
            }
            return output;
        } catch (FileNotFoundException | CorruptFileException e) {
            throw new OllieException(e.getMessage());
        }
    }

    public void save(ArrayList<Task> tasks) throws OllieException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task t : tasks) {
                fw.write(t.getFormattedString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new OllieException(e.getMessage());
        }
    }
}
