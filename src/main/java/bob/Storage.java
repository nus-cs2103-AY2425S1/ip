package bob;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private Path filePath;
    public Storage(String filePath) throws IOException {
        this.filePath = Paths.get(filePath);
        Files.createDirectories(this.filePath.getParent());

        if (!Files.exists(this.filePath)) {
            Files.createFile(this.filePath);
        }
    }

    public List<Task> load() throws FileNotFoundException {
        Scanner fileReader = new Scanner(this.filePath.toFile());
        List<Task> temp = new ArrayList<>();
        while (fileReader.hasNext()) {
            String record = fileReader.nextLine();
            Task task = Task.from(record);
            temp.add(task);
        }
        return temp;
    }
    /**
     * Saves tasklist in an appropriate format within storage
     *
     * @param tasklist Tasklist that is to be saved
     */
    public void save(TaskList tasklist) throws IOException {
        store(tasklist.toText());
    }

    /**
     * Stores the given string in storage, overriding any text currently in the file
     */
    public void store(String str) throws IOException {
        FileWriter fw = new FileWriter(filePath.toFile());
        fw.write(str);
        fw.close();
    }
}
