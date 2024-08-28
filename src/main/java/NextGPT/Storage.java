package NextGPT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import NextGPT.task.Deadline;
import NextGPT.task.Event;
import NextGPT.task.Task;
public class Storage {
    protected String filePath;
    public Storage(String filePath) {

        this.filePath = filePath;
    }

    public Scanner load_tasks() throws IOException{
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(this.filePath);
        return new Scanner(f);
    }

    public void add_to_memory(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        String text = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof Event) {
                text += "E,";
                text += task.isDone()? "X,":" ,";
                text += task.getDescription() + ",";
                text += ((Event) task).getStart() + ",";
                text += ((Event) task).getEnd() + "\n";
            } else if (task instanceof Deadline) {
                text += "D,";
                text += task.isDone()? "X,":" ,";
                text += task.getDescription() + ",";
                text += ((Deadline) task).getBy() + "\n";
            } else {
                text += "T,";
                text += task.isDone()? "X,":" ,";
                text += task.getDescription();
                text += "\n";

            }
        }


        fw.write(text);
        fw.close();
    }
}
