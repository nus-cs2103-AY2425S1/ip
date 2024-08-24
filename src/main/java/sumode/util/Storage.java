package sumode.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import sumode.task.Task;


public class Storage {

    private final String filePath;
    private final File f;
    private final Ui ui;

    public Storage(String filePath, Ui ui) throws IOException {
        this.filePath = filePath;
        this.ui = ui;
        this.f = new File(filePath);

        // Ensure parent directories exist
        File parentDir = f.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.out.println("ZZZ");// Attempt to create the directory
                throw new IOException("Failed to create required directories for path: " + filePath);
            }
        }

        // Ensure the file itself exists
        if (!f.exists()) {
            if (!f.createNewFile()) {
                throw new IOException();
            }
        }
    }

    public void save(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath, false);
            for (Task task : tasks) {
                fw.write(task.savedString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            this.ui.latestSaveError();
        }
    }

    public String[] load() throws FileNotFoundException {
        Scanner s = new Scanner(f);
        s.useDelimiter("\\A");
        return s.hasNext()
                ? s.next().split("\n")
                : new String[0];
    }
}
