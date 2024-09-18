package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import mollyexception.MollyException;
import tasklist.TaskList;
import task.*;

public class Storage {

    public String FILE_PATH;
    public ArrayList<Task> botMemory;
    public Storage(String FILE_PATH) {
        assert FILE_PATH != null && !FILE_PATH.isEmpty() : "File path should not be null or empty";
        this.FILE_PATH = FILE_PATH;
        this.botMemory = new ArrayList<>();
    }


    /**
     * Loads tasks from the Molly.txt file, creating new tasks for each line and adding them to the botMemory ArrayList.
     * @return
     */
    public ArrayList<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String taskData = fileScanner.nextLine();
                    assert taskData != null && !taskData.isEmpty() : "Task data should not be null or empty";
                    try {
                        Task task = parseTask(taskData);
                        if (task != null) {
                            botMemory.add(task);
                        }
                    } catch (MollyException e) {
                        System.out.println(e.getMessage());
                    }
                }
                fileScanner.close();
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        } else {
            file.getParentFile().mkdirs();
        }
        return botMemory;
    }

    /**
     * Parses tasks when reading the Molly.txt file, returning the tasks so that they
     * can be added to the botMemory ArrayList.
     * @param taskData
     * @return
     */
    public Task parseTask(String taskData) throws MollyException {
        assert taskData != null : "Task data should not be null";
        if (taskData.startsWith("[T]")) {
            String description = taskData.substring(7);
            assert description != null && !description.isEmpty() : "Task description should not be null or empty";
            Task task = new Task(description);
            if (taskData.charAt(4) == 'X') {
                task.markDone();
            }
            return task;
        } else if (taskData.startsWith("[D]")) {
            int byIndex = taskData.indexOf("(by:");
            assert byIndex != -1 : "Deadline task should contain a valid '(by:' index";
            if (byIndex != -1) {
                String description = taskData.substring(7, byIndex - 1);
                String by = taskData.substring(byIndex + 5, taskData.length() - 1);
                assert description != null && !description.isEmpty() : "Deadline description should not be null or empty";
                assert by != null && !by.isEmpty() : "Deadline time should not be null or empty";
                Deadline deadline = new Deadline(description, by);
                if (taskData.charAt(4) == 'X') {
                    deadline.markDone();
                }
                return deadline;
            }
        } else if (taskData.startsWith("[E]")) {
            int fromIndex = taskData.indexOf("(from:");
            int toIndex = taskData.indexOf("to:");
            assert fromIndex != -1 && toIndex != -1 : "Event task should contain valid '(from:' and 'to:' indices";
            if (fromIndex != -1 && toIndex != -1) {
                String description = taskData.substring(7, fromIndex - 1);
                String from = taskData.substring(fromIndex + 7, toIndex - 1);
                String to = taskData.substring(toIndex + 4, taskData.length() - 1);
                Event event = new Event(description, from, to);
                if (taskData.charAt(4) == 'X') {
                    event.markDone();
                }
                return event;
            }
        }
        return null;
    }


    /**
     * Saves all the tasks from the botMemory ArrayList into the Molly.txt file
     * @param taskList
     */
    public void saveTasks(TaskList taskList) {
        assert taskList != null : "TaskList should not be null";
        this.botMemory = taskList.getBotMemory();
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.getBotMemory()) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
