import java.io.File;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Storage {
    protected final Path filePath;
    protected File file;

    public Storage() {

        String home = System.getProperty("user.home");
        this.filePath = Paths.get(home, "Downloads", "CS2103T_AY2425", "iP", "data", "phenex.txt");

        if (!Files.exists(this.filePath)) {
            // create file if it doesn't exist
            try {
                Files.createFile(this.filePath);
                System.out.println("Memory initialised.");
            } catch (IOException e) {
                System.out.println("Error, unable to initialise memory: " + e.getMessage());
            }
        }

        this.file = new File(this.filePath.toString());
    }

    public void storeTasksToMemory(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath.toString());
            for (Task task : taskList.tasks) {
                String line = task.parseTaskInfo();
                fileWriter.write(line);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
