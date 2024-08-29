package TrackBot;

import TrackBot.task.Task;
import TrackBot.ui.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrackBotStorage {
    String filePath;
    File file;

    public TrackBotStorage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        createNewFile();
    }

    public void createNewFile() throws IOException {
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public List<Task> loadContents() throws FileNotFoundException {
        List<Task> list = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("Data file does not exist. A new file will be created.");
            return list;
        }

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            Task task = Parser.parseTask(line);
            if (task != null) {
                list.add(task);
            }
        }
        s.close();
        return list;
    }

    public void saveContents(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task : list) {
            fw.write(task.toStorageFormat() + System.lineSeparator());
        }
        fw.close();
    }

}
