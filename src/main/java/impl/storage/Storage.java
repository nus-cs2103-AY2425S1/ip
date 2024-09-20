package impl.storage;

import impl.chatbot.Danny;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    /**
     * Stores and Loads Tasks into a text file
     * <p>
     * Tasks stored in this syntax:
     * type Description /By/From /To | Done
     */
    File file;
    String filePath;
    Danny danny;

    public Storage(String path, Danny bot) {
        assert !path.isEmpty();
        file = new File(path);
        filePath = path;
        danny = bot;
    }

    public void loadTask() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        String line;
        while (s.hasNext()) {
            line = s.nextLine();
            if (!line.isEmpty()) {
                assert line.contains(" \\| ");
                String[] split = line.split(" \\| ");
                danny.parseString(split[0]);
                if (Objects.equals(split[1], "true")) {
                    danny.setLastDone();
                }
                if (split.length == 3) {
                    danny.setLastTag(split[2]);
                }
            }
        }
    }

    public void saveTask() throws IOException {
        String write = danny.saveTasks();
        file.createNewFile();
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(String.valueOf(write));
        fw.close();
    }

}
