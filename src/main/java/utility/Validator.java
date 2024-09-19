package utility;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Checking and creating necessary files and directories.
 */
public class Validator {

    /**
     * Ensures that the specified file and its directory exist.
     * If the directory or file does not exist, they will be created.
     *
     * @param filePath The path of the file to check and create if necessary.
     */
    public void checkFileExists(String filePath) {

        File file = new File(filePath);
        File directory = new File(file.getParent());

        try{
            if(!directory.exists()){
                boolean isCreated = directory.mkdir();
                if (!isCreated){
                    System.out.println("Issue creating Directory");
                }
            }

            if(!file.exists()){
                if(!file.createNewFile()){
                    System.out.println("Issue creating File.");
                }
            }
        }catch (IOException e) {
            System.out.println("Error in file creation.");
        }
    }

    /**
     * Detects if there is a duplicate task in the list.
     *
     * @param tasks The list of tasks.
     * @param newTask The new task to check for duplicates.
     * @return True if a duplicate task is found, false otherwise.
     */
    public Boolean detectDuplicates(ArrayList<Task> tasks, Task newTask) {
        for (Task task : tasks) {
            if (task.getDesc().equals(newTask.getDesc())) {
                if (task instanceof Deadline && newTask instanceof Deadline) {
                    if (((Deadline) task).getBy().equals(((Deadline) newTask).getBy())) {
                        return true;
                    }
                }
                else if (task instanceof Event existingEvent && newTask instanceof Event newEvent) {
                    if (existingEvent.getFrom().equals(newEvent.getFrom()) &&
                            existingEvent.getTo().equals(newEvent.getTo())) {
                        return true;
                    }
                }

                else if (task instanceof ToDo && newTask instanceof ToDo) {
                    return true;
                }
            }
        }
        return false;
    }
}
