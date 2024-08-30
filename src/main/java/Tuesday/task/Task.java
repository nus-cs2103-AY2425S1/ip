package Tuesday.task;

import Tuesday.util.Storage;

import java.util.ArrayList;
import java.io.File;

public class Task {
    // variables
    private String description;
    private boolean isMarked;
    public static ArrayList<Task> taskArrayList = new ArrayList<>();
    public static int count = 0;

    /**
     * Constructor for Task
     * Used for new task created
     *
     * @param description Description of the Command
     */
    public Task(String description) {
        if (description.isEmpty()){
            return;
        }
        this.description = description;
        this.isMarked = false;
        Task.taskArrayList.add(this);
        count++;
    }

    /**
     * Constructor for Task
     * Used for data collected from data file
     *
     * @param description Description of the command
     * @param done Marked task
     */
    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
        Task.taskArrayList.add(this);
        count++;
    }

    /**
     * Constructor for Task
     * Used for storage only
     *
     * @param storage Storage object used to read and write to the datafile
     */
    public Task(Storage storage) {
        storage.createNewDatafile();
    }

    /**
     * Used for the output message for the terminal
     *
     * @return X or " " depending on marked
     */
    public String getDoneX() {
        if (this.isMarked)
            return "X";
        else
            return " ";
    }

    /**
     * Used for the output message for the datafile
     *
     * @return 1 or 0 depending on marked
     */
    public String getDone1() {
        if (this.isMarked)
            return "1";
        else
            return "0";
    }

    /**
     * Changed the state of the done
     *
     * @param state State of the done
     */
    public void changeDone(boolean state) {
        this.isMarked = state;
    }

    /**
     * Delete the current task based on the index
     *
     * @param index Index of the task wants to delete
     */
    public static void deleteTask(int index) {
        count--;
        Task.taskArrayList.remove(index);
    }

    public String getDescription() {
        return this.description;
    }
    /**
     * Write to the date file
     *
     * @param dataFile Date file which stores the data
     * @return Description of the command
     */
    public String writeToDatafile(File dataFile){

        return this.description;
    }

    /**
     * Used for an output message
     *
     * @return Output message
     */
    @Override
    public String toString() {
        return "["+ this.getDoneX() +"] "+ this.description;
    }
}
