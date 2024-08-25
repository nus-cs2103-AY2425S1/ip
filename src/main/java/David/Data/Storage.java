package David.Data;

import David.Exceptions.DavidCacheException;
import David.Exceptions.DavidInvalidDateTimeException;
import David.Task.*;
import David.Parser.DateParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Format of cached data
TaskType | completed | eventName | (optional) by/from | (optional) to
   0          1           2               3                 4
 */

public class Storage {
    private String path;

    /**
     * Constructor to instantiate a new Storage object
     * @param path pathname of the cache file
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the cached list of tasks if exist.
     * If it does not exist, a new cache will be created
     * @return an arraylist of tasks
     */
    public TaskList loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
        File f = new File(this.path);
        Scanner sc = new Scanner(f);
        while(sc.hasNextLine()) {
            Task t = parseTask(sc.nextLine());
            tasks.add(t);
        }
        } catch (FileNotFoundException e) {
            //file does not exist
            this.createNewCache();
        }
        TaskList taskList = new TaskList(tasks);
        return taskList;
    }

    /**
     * Parses the input line and returns the task represented by the current line
     * @return
     */
    private Task parseTask(String s) {
        String[] info = s.split("\\|");
        Task t = null;
        boolean completed = false;
        if (info[1].equals("1")) {
            completed = true;
        }

        switch (info[0]) {
            case "T":
                //"TODO" task
                t = new TodoTask(info[2], completed);
                break;
            case "D":
                //"Deadline task"
                try {
                    LocalDateTime byDate = DateParser.getDate(info[3]);
                    t = new DeadlineTask(info[2], byDate, completed);
                    break;
                } catch (DavidInvalidDateTimeException e){}
            case "E":
                //"Event" task
                try {
                    LocalDateTime fromDate = DateParser.getDate(info[3]);
                    LocalDateTime toDate = DateParser.getDate(info[4]);
                    t = new EventTask(info[2], fromDate, toDate, completed);
                    break;
                } catch (DavidInvalidDateTimeException e) {}

        }

        return t;
    }

    /**
     * Creates a new cache inside Data folder if it does not exist
     */
    private void createNewCache() {
        File newFile = new File(this.path);
        File parentPath = newFile.getParentFile();  //gets parent path
        parentPath.mkdirs();
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("File cannot be created");
        }
    }

    /**
     * Overwrites the file with the list of current tasks
     * @throws DavidCacheException if the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     */
    public void saveTask(TaskList tasks) throws DavidCacheException{
        try {
            FileWriter writer = new FileWriter(this.path, false);
            String text = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                Task t = tasks.getTask(i);
                text += t.toCacheString() + "\n";
            }
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            //Thrown when the named file is invalid/ unavailable
            throw new DavidCacheException();
        }
    }


}
