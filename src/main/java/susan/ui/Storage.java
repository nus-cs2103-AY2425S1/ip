package susan.ui;

import susan.task.TaskList;

import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    final String FILE_PATH = "../SusanToDoList.txt";

    public Storage() {}

    public void load(TaskList tasks) throws IOException, SusanException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(tasks.printList());
            fw.close();
        } catch (IOException e) {
            throw new SusanException("Error saving tasks to file");
        }
    }
}
