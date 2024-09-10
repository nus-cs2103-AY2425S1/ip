package nextgpt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import nextgpt.task.Deadline;
import nextgpt.task.Event;
import nextgpt.task.Task;

/**
 * Storage class to load and add local saved task list.
 */
public class Storage {
    protected String filePath;
    public Storage(String filePath) {

        this.filePath = filePath;
    }

    /**
     * Loads locally saved task list located at the file path.
     *
     * @return Locally saved task list.
     * @throws IOException If file cannot be accessed.
     */
    public Scanner load_tasks() throws IOException {
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(this.filePath);
        return new Scanner(f);
    }

    /**
     * Adds the given tasklist to the local file path.
     *
     * @param taskList Task list to be saved.
     * @throws IOException If error occurs while saving.
     */
    public void add_to_memory(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        String text = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof Event) {
                text += "E,";
                text += task.isDone() ? "X, ":" ,";
                text += task.getDescription() + ",";
                text += ((Event) task).getStart() + ",";
                text += ((Event) task).getEnd() + "\n";
            } else if (task instanceof Deadline) {
                text += "D,";
                text += task.isDone() ? "X, ":" ,";
                text += task.getDescription() + ",";
                text += ((Deadline) task).getBy() + "\n";
            } else {
                text += "T,";
                text += task.isDone() ? "X, ":" ,";
                text += task.getDescription();
                text += "\n";

            }
        }


        fw.write(text);
        fw.close();
    }
}
