import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class FileManager {

        public static TaskList loadFileContents() throws IOException {
            File f = null;

            // Check if directory exists
            Path path = Path.of("src/main/data");

            if (!Files.exists(path)) {
                boolean h = new File("src/main/data").mkdirs();
                if (h) {
                    File newFile = new File("src/main/data/data.txt");
                    newFile.createNewFile();
                    
                    f = newFile;
                }
            } else {
                f = new File("src/main/data/data.txt");
            }

            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            TaskList listOfTasks = new TaskList();

            while (s.hasNextLine()) {
                Task newTask = Task.parseStringToTask(s.nextLine());

                if (newTask == null) {
                    continue;
                }

                listOfTasks.addTask(newTask);
            }

            return listOfTasks;
        }

        public static void writeToFile(String content) throws IOException {
            FileWriter fw = new FileWriter("src/main/data/data.txt");
            fw.write(content);
            fw.close();
        }


}
