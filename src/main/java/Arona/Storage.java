package Arona;

import java.nio.file.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Storage {
    private final String filepath;

    /**
     * Handles storing and loading data from data.txt file
     * @param  filepath  a relative filepath giving the location that data.txt should be stored in
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads lines from data.txt and directly copies each line to arraylist
     * @return An arraylist where each element is a line in data.txt
     * @exception java.io.IOException thrown when file can't be opened due to corrupt data or related
     * @exception SecurityException thrown when file can't be accessed due to system security settings
     */
    public ArrayList<String> load() throws Exception {
        // String array of data.txt
        ArrayList<String> data = new ArrayList<>();
        // Current data.txt directory
        Path dataDir = Paths.get(filepath);

        // Make data.txt file if it doesn't exist
        try {
            Files.createFile(dataDir);
        } catch (Exception e) {
            // If the exception isn't "file already exists", throw it
            if (!(e instanceof FileAlreadyExistsException)) {
                throw(e);
            }
        }

        // Read data.txt file
        try (Stream<String> lines = Files.lines(dataDir)){
            lines.forEach(data::add);
        }

        return data;
    }

    /**
     * Saves tasks from taskList as text in data.txt
     * @param  taskList  arraylist where each element is from Task class
     * @exception java.io.IOException thrown when file can't be opened due to corrupt data or related
     * @exception SecurityException thrown when file can't be accessed due to system security settings
     */
    public void save(TaskList taskList) throws Exception {
        // Current data.txt directory
        Path dataDir = Paths.get(filepath);

        // Write data to file
        Files.write(dataDir, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        for (int i = 0; i < taskList.size(); i++) {
            Files.write(dataDir, Collections.singletonList(taskList.get(i).getStatusIcon() + taskList.get(i).getCategory()
                    + " " + taskList.get(i)), StandardOpenOption.APPEND);
        }
    }

    /**
     * Used for debugging related to data.txt location
     * @return A string that shows the absolute file path of data.txt
     */
    public String getStorageLocation() {
        Path dataDir = Paths.get(filepath);
        return dataDir.toAbsolutePath().toString();
    }
}
