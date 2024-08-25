package sentinel.storage;

import sentinel.parser.Parser;
import sentinel.task.Task;
import sentinel.task.TaskList;

import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.nio.file.Path;

import java.util.Scanner;

import java.io.IOException;

/**
 * Represents a storage for storing Sentinel's tasks.
 */
public class Storage {
        /**
         * Loads the task list that was saved.
         */
        public static TaskList load() throws IOException {
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
                Task newTask = Parser.parseStringToTask(s.nextLine());

                if (newTask == null) {
                    continue;
                }

                listOfTasks.addTask(newTask);
            }

            return listOfTasks;
        }

        /**
         * Saves the current task list to a file.
         */
        public static void save(String content) throws IOException {
            FileWriter fw = new FileWriter("src/main/data/data.txt");
            fw.write(content);
            fw.close();
        }
}
