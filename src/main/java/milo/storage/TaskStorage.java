package milo.storage;

import milo.lists.TaskList;
import milo.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskStorage extends Storage<Task> {
    private final String taskFilePath;
    private final ArrayList<Task> todoList = new ArrayList<>();

    public TaskStorage(String taskFilePath) {
        this.taskFilePath = taskFilePath;
    }

    /**
     * transform string into task object, taking into account
     * the different types of tasks
     *
     * @param task string containing details of the task
     */
    private Task formatterToTask(String task) {
        String[] taskDesc = task.split("\\|");
        assert taskDesc.length >= 3;
        Boolean curIsCompleted = Integer.parseInt(taskDesc[1].strip()) == 1;
        switch (taskDesc[0].strip()) {
            // A to-do task
            case "T":
                return new Todo(taskDesc[2].strip(), curIsCompleted);
            // A deadline task
            case "D":
                LocalDate curDate = LocalDate.parse(taskDesc[3].strip());
                return new Deadline(taskDesc[2].strip(), curDate, curIsCompleted);
            // An event task
            case "E":
                LocalDate fromDate = LocalDate.parse(taskDesc[3].strip());
                LocalDate toDate = LocalDate.parse(taskDesc[4].strip());
                return new Event(taskDesc[2].strip(), fromDate, toDate, curIsCompleted);
            default:
                return null;
        }
    }

    /**
     * transform task into string (formatted for Storage)
     *
     * @param task object that is to be converted to a String representation
     */
    private String formatterToText(Task task) {
        assert task != null;
        return task.toTextString();
    }

    /**
     * Reads data of the storage file, i.e. miloData.txt, and iterate
     * through the String representation of task, transforming them into
     * respective task objects and then adding them to the array list
     */
    @Override
    public ArrayList<Task> readData() {
        try {
            File f = new File(this.taskFilePath);
            assert f.exists();
            Scanner s = new Scanner(f);
            // Iterates through lines in a file
            while (s.hasNext()) {
                // Format each text line into a task
                Task curTask = formatterToTask(s.nextLine());
                if (curTask != null) {
                    // Adds each task to the array list
                    this.todoList.add(curTask);
                }
            }
            return this.todoList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Method iterating through task array transforming them each task
     * into String representation (for Storage) and then adding them to
     * the data file, miloData.txt
     */
    public void saveTaskData(TaskList todoList) {
        try {
            FileWriter fw = new FileWriter(this.taskFilePath);
            assert todoList != null;
            for (int i = 0; i < todoList.getNumberOfTasks(); i++) {
                fw.write(formatterToText(todoList.get(i)) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
