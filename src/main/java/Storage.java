import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import Tasks.Task;

public class Storage {
    private Path path;

    Storage(Path path) {
        this.path = path;
    }

    @SuppressWarnings("unchecked")
    ArrayList<Task> loadFromFile() throws IOException, ClassNotFoundException {
        if (Files.exists(this.path)) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.path.toString()));
            ArrayList<Task> result = (ArrayList<Task>) ois.readObject();
            ois.close();
            return result;
        }
        return new ArrayList<Task>();
    }

    // save to file
    void saveToFile(ArrayList<Task> tasks) throws IOException {

        // Create directories if they don't exist
        Files.createDirectories(this.path.getParent());
        // Create file if it doesn't exist
        if (!Files.exists(this.path)) {
            Files.createFile(this.path);
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.path.toFile()));
        oos.writeObject(tasks);
        oos.close();
    }
}
