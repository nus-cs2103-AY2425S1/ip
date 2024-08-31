package bob;

import bob.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class Storage {

    public static void saveTasks(List<Task> tasks) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "bob.txt");
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
        }
    }
}
