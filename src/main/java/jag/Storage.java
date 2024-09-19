package jag;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage Class represents and encapsulates all the properties and methods
 * needed to read and write any list of tasks that the user has, either
 * from previous use of the chatbot and / or the current use
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> arrayListTasks = new ArrayList<>();
    private TaskList tasks;
    File f;
    public Storage(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.f = new File(this.filePath);

        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        }
    }

    /**
     * Loads any list of tasks that the user had saved previously at a given file
     * path
     *
     * @return An instance of TaskList of the saved previous list of tasks or
     *              an empty TaskList to be filled up by the user
     * @throws FileNotFoundException An instance of an exception in the event
     *              no such file path exists
     */
    public TaskList load() throws FileNotFoundException {
        File f = new File(this.filePath);
        char taskType;
        boolean isDone;
        String description;
        String by;
        String from;
        String to;

        if (f.exists()) {
            Scanner readFile = new Scanner(f);
            while (readFile.hasNextLine()) {
                String item = readFile.nextLine();
                taskType = item.charAt(1);
                isDone = item.charAt(4) == 'X';

                if (taskType == 'T') {
                    // If todos
                    int startIndex = item.indexOf("] ") + 1;
                    description = item.substring(startIndex).trim();

                    Todo todo = new Todo(description);
                    todo.setStatus(isDone);
                    this.arrayListTasks.add(todo);

                } else if (taskType == 'D') {
                    // If deadline
                    int startIndex = item.indexOf("] ") + 1;
                    int endIndex = item.indexOf("(");
                    description = item.substring(startIndex, endIndex).trim();

                    startIndex = item.indexOf(":") + 1;
                    endIndex = item.indexOf(')');
                    by = item.substring(startIndex, endIndex).trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH mm ss");
                    LocalDateTime d1 = LocalDateTime.parse(by, formatter);
                    Deadline deadline = new Deadline(description, d1);
                    deadline.setStatus(isDone);
                    this.arrayListTasks.add(deadline);

                } else {
                    // If event
                    int startIndex = item.indexOf("] ") + 1;
                    int endIndex = item.indexOf('(');
                    description = item.substring(startIndex, endIndex).trim();

                    startIndex = item.indexOf(":") + 1;
                    endIndex = item.indexOf(", to:");
                    from = item.substring(startIndex, endIndex).trim();
                    startIndex = item.indexOf("to: ") + 4;
                    endIndex = item.indexOf(')');
                    to = item.substring(startIndex, endIndex).trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH mm ss");
                    LocalDateTime d1 = LocalDateTime.parse(from, formatter);
                    LocalDateTime d2 = LocalDateTime.parse(to, formatter);

                    Event event = new Event(description, d1, d2);
                    event.setStatus(isDone);
                    this.arrayListTasks.add(event);
                }
            }
        }

        this.tasks = new TaskList(this.arrayListTasks);
        return this.tasks;
    }

    /**
     * Method to write and record down the saved list of tasks the user has
     * into a given file path
     *
     * @param tasks Instance of the most updated TaskList that the user has to be
     *              saved
     * @throws IOException Instance of an Exception in the event TaskList size is 0
     *              for testing purposes
     */
    public void write(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);

        if (tasks.size() == 0) {
            fw.close();
            throw new IOException("No tasks to save :(");
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            fw.write(task.toString() + "\n");
        }

        fw.close();
    }

}
