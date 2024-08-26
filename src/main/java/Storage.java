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
    ArrayList<Task> loadFromFile() {
        if (Files.exists(this.path)) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.path.toString()))) {
                return (ArrayList<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        }
        return new ArrayList<Task>();
    }

    // save to file
    void saveToFile(ArrayList<Task> tasks) {
        try {
            // Create directories if they don't exist
            Files.createDirectories(this.path.getParent());

            // Create file if it doesn't exist
            if (!Files.exists(this.path)) {
                Files.createFile(this.path);
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.path.toFile()))) {
                oos.writeObject(tasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
