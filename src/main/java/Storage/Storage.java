package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Tasks.*;

public class Storage {

    File memory;
    File parent;
    TaskList taskList;

    public Storage(String s) {
        memory = new File(s);
        parent = new File("src/main/java/Memory");
        try{
            if (!parent.exists()) {
                //create parent directory
                parent.mkdirs();
            }
            if (memory.createNewFile()) {
                //create memory txt file
            }
        } catch (IOException e) {
            System.out.println("error creating memory files");
        }

    }

    public void save() {
        try {
            FileWriter fw = new FileWriter(memory);
            fw.write(taskList.fileTaskInfo());
            fw.close();
        } catch (IOException e) {
            System.out.println("Memory file does not exist");
        }
    }

    public TaskList load() {
        taskList = new TaskList();
        try{
            Scanner s = new Scanner(memory);

            while(s.hasNext()) {
                String str = s.nextLine();
                String[] fileInput = str.split(" / ", 3);
                String identifier = fileInput[0];
                String done = fileInput[1];
                String description = fileInput[2];
                Task t = null;

                if (identifier.equals("[T]")) {
                    t = new ToDo(description);
                    if (done.equals("[D]")) {
                        t.done();
                    }
                } else if (identifier.equals("[D]")) {
                    String[] split = description.split(" / ", 2);
                    t = new Deadline(split[0], split[1]);
                    if (done.equals("[D]")) {
                        t.done();
                    }
                } else if (identifier.equals("[E]")) {
                    String[] split = description.split(" / ", 3);
                    t = new Events(split[0], split[1], split[2]);
                    if (done.equals("[D]")) {
                        t.done();
                    }
                } else {
                    break;
                }
                taskList.add(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Memory file does not exist");
        }
        return taskList;
    }

}
