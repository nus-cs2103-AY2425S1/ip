package yapbot.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File file;
    private String filepath;

    public Storage(String filepath) throws IOException {
        this.filepath = filepath;
        File targetFile = new File(filepath);

        if (targetFile.exists()) {
            this.file = targetFile;
        } else {
            targetFile.getParentFile().mkdirs();
            targetFile.createNewFile();
            this.file = targetFile;
        }
    }

    public File load() {
        return this.file;
    }

    public boolean save(String saveableTasks) {
        try {
            FileWriter fileWriter = new FileWriter(filepath);

            fileWriter.write(saveableTasks);

            fileWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
