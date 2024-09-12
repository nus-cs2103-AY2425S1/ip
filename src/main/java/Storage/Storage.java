package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

/**
 * Deals with loading tasks from memory and saving tasks into memory.
 */
public class Storage {

    private File memory;
    private File parent;
    private TaskList taskList;

    /**
     * Constructor for Storage.
     * Takes in a string as the filePath for the memory file.
     *
     * @param s FilePath for memory file.
     */
    public Storage(String s) {
        memory = new File(s);
        parent = memory.getParentFile();
        try {
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

    /**
     * Writes taskList into memory file.
     */
    public void save() {
        try {
            FileWriter fw = new FileWriter(memory);
            fw.write(taskList.fileTaskInfo());
            fw.close();
        } catch (IOException e) {
            System.out.println("Memory file does not exist");
        }
    }

    /**
     * Loads taskList from memory file, before returning the created taskList.
     *
     * @return taskList created from memory file.
     */
    public TaskList load() {
        taskList = new TaskList();
        try {
            Scanner s = new Scanner(memory);

            while (s.hasNext()) {
                String str = s.nextLine();
                String[] fileInput = str.split(" / ", 3);
                String identifier = fileInput[0];
                String done = fileInput[1];
                String description = fileInput[2];
                Task t = null;

                if (identifier.equals("[T]")) {
                    t = createToDo(done, description);
                } else if (identifier.equals("[D]")) {
                    t = createDeadline(done, description);
                } else if (identifier.equals("[E]")) {
                    t = createEvent(done, description);
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

    private Task createToDo(String done, String description) {
        Task t = new ToDo(description);
        if (done.equals("[D]")) {
            t.done();
        }
        return t;
    }

    private Task createDeadline(String done, String description) {
        String[] split = description.split(" / ", 2);
        Task t = new Deadline(split[0], split[1]);
        if (done.equals("[D]")) {
            t.done();
        }
        return t;
    }

    private Task createEvent(String done, String description) {
        String[] split = description.split(" / ", 3);
        Task t = new Events(split[0], split[1], split[2]);
        if (done.equals("[D]")) {
            t.done();
        }
        return t;
    }

}
