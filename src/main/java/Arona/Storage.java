package Arona;

import java.util.Collections;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.nio.file.StandardOpenOption;

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<String> load() throws Exception {
        // String array of data.txt
        ArrayList<String> data = new ArrayList<>();
        // Current data.txt directory
        Path dataDir = Paths.get(filepath);

        // Make data.txt file if it doesn't exist
        try {
            Files.createFile(dataDir);
        } catch (Exception e) {
            // If the exception isnt "file already exists", throw it
            if (!e.getMessage().equals(".\\data.txt")) {
                throw(e);
            }
        }

        // Read data.txt file
        try (Stream<String> lines = Files.lines(dataDir)){
            lines.forEach(data::add);
        }

        return data;
    }

    public void save(TaskList taskList) throws Exception {
        // Current data.txt directory
        Path dataDir = Paths.get(filepath);

        // Write data to file
        Files.write(dataDir, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        for (int i = 0; i < taskList.size(); i++) {
            Files.write(dataDir, Collections.singletonList(taskList.get(i).getStatusIcon() + taskList.get(i).getCategory() + " " + taskList.get(i)), StandardOpenOption.APPEND);
        }
    }
}
