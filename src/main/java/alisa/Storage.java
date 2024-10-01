package alisa;

import alisa.exception.AlisaException;
import alisa.task.Task;
import alisa.task.TaskList;
import alisa.task.Deadline;
import alisa.task.Event;
import alisa.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File taskFile;
    private String filePath;

    /**
     * Constructs an instance of Storage.
     *
     * @param filePath The path of the file in storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskFile = new File(filePath);

        File parentDirectory = taskFile.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        try {
            taskFile.createNewFile();
        } catch (IOException e) {
            System.out.println("There was an error while creating the file!");
        }
    }

    /**
     * Returns the list of tasks from the storage file.
     *
     * @return ArrayList of tasks.
     * @throws AlisaException If file is not found.
     */
    public ArrayList<Task> loadFile() throws AlisaException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
//            if (!taskFile.exists()) {
//                taskFile.createNewFile();
//            }
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] inputArray = line.split(" \\| ");
                String taskType = inputArray[0];
                String isDone = inputArray[1];

                switch (taskType) {
                    case "T":
                        Todo newTodo = new Todo(inputArray[2]);
                        if (isDone.equals("1")) {
                            newTodo.markAsDone();
                        }
                        taskList.add(newTodo);
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(inputArray[2], inputArray[3]);
                        if (isDone.equals("1")) {
                            newDeadline.markAsDone();
                        }
                        taskList.add(newDeadline);
                        break;
                    case "E":
                        String fromTo = inputArray[3];
                        String[] fromToArray = fromTo.split("-");
                        Event newEvent = new Event(inputArray[2], fromToArray[0], fromToArray[1]);
                        if (isDone.equals("1")) {
                            newEvent.markAsDone();
                        }
                        taskList.add(newEvent);
                        break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new AlisaException("File can't be read!");
        }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
        return taskList;
    }

    /**
     * Updates the storage file when there are changes in list of tasks.
     *
     * @param taskList List of tasks to overwrite the file with.
     * @throws AlisaException If the file can't be updated.
     */
    public void syncFile(TaskList taskList) throws AlisaException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.convertToFileString());
            fw.close();
        } catch (IOException e) {
//            System.out.println(e.getMessage());
            throw new AlisaException("Couldn't update file!!");
        }
    }
}
