package Arona;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.InvalidPathException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Storage {
    private final String FILEPATH;

    /**
     * Handles storing and loading data from data.txt file
     * @param  filePath  a relative filepath giving the location that data.txt should be stored in
     */
    public Storage(String filePath) {
        assert filePath != null : "filePath should be specified";
        assert !filePath.isBlank() : "filePath cannot be an blank string";
        this.FILEPATH = filePath;
    }

    /**
     * Loads lines from data.txt and directly copies each line to arraylist
     * @return An arraylist where each element is a line in data.txt
     * @exception InvalidPathException thrown when filepath isn't in the correct format
     * @exception java.io.IOException thrown when file can't be opened or parent directory doesn't exit
     * @exception SecurityException thrown when file can't be accessed due to system security settings
     */
    public ArrayList<String> load() throws Exception {
        // String array of data.txt
        ArrayList<String> data = new ArrayList<>();

        // Current data.txt directory
        Path dataDir = Paths.get(FILEPATH);

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
        // for each line in file, add to arraylist data
        try (Stream<String> lines = Files.lines(dataDir)){
            lines.forEach(data::add);
        }

        return data;
    }


    /**
     * Saves tasks from taskList as text in data.txt
     * @param  taskList  arraylist where each element is from Task class
     * @param  name  varargs array, if empty, use data.txt, else, use name given in index 0 (from archive command)
     * @exception InvalidPathException thrown when filepath isn't in the correct format
     * @exception java.io.IOException thrown when file can't be opened or parent directory doesn't exit
     * @exception SecurityException thrown when file can't be accessed due to system security settings
     */
    public void save(TaskList taskList, String... name) throws Exception {
        // Set directory
        Path dataPath;
        if (name.length == 0) {
            dataPath = Paths.get(FILEPATH);
        } else {
            dataPath = Paths.get(".\\" + name[0] + ".txt");
        }

        // Create string with all tasks
        String content = taskList.toStream()
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));

        //Write to file, create file if needed
        Files.writeString(dataPath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Used for debugging related to data.txt location
     * @return A string that shows the absolute file path of data.txt
     */
    public String getStorageLocation() {
        Path dataDir = Paths.get(FILEPATH);
        return dataDir.toAbsolutePath().toString();
    }
}
