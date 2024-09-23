package lutodo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import lutodo.tasklist.TaskList;
import lutodo.parser.Parser;
import lutodo.tasks.Task;
import lutodo.tasks.EventTask;
import lutodo.tasks.DeadlineTask;
import lutodo.tasks.TodoTask;

/**
 * Represents the tool to manage storage of the task list.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with file path.
     *
     * @param filePath Represents the file path in which the task list is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the file path.
     *
     * @return An ArrayList of Tasks containing the tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> toDo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        File taskListFile = new File(filePath);
        try {
            Scanner s = new Scanner(taskListFile);
            while (s.hasNextLine()) {
                String taskMessage = s.nextLine();
                if (taskMessage.isEmpty()) {
                    continue;
                }
                assert taskMessage.charAt(0) == '[' : "the first char of a line in the text should be [";
                Task task = TaskList.EMPTY_TASK;
                switch (taskMessage.charAt(1)) {
                case 'T':
                    task = new TodoTask(Parser.splitTaskInfo(taskMessage)[1]);
                    toDo.add(task);
                    break;
                case 'D':
                    task = new DeadlineTask(Parser.splitTaskInfo(taskMessage)[1]);
                    toDo.add(task);
                    break;
                case 'E':
                    task = new EventTask(Parser.splitTaskInfo(taskMessage)[1]);
                    toDo.add(task);
                    break;
                default:
                    System.out.println("Cannot read: " + taskMessage);
                }
                if (taskMessage.charAt(4) == 'X') {
                    task.markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            try {
                taskListFile.createNewFile();
            } catch (IOException eIO) {
                System.out.println(eIO.getMessage());
            }
        }
        return toDo;
    }

    /**
     * Saves the task list to the file int the file path.
     *
     * @param toDo The TaskList to be saved.
     */
    public void save(TaskList toDo) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 1; i <= toDo.size(); i++) {
                fileWriter.write(toDo.get(i - 1) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
