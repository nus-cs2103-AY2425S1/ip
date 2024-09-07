package rainy.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import rainy.tasks.TaskTracker;

/**
 * Takes in a <code>File</code> object and either reads the file or writes over it entirely.
 */
public class Storage {

    /**
     * Constructs a new <code>Storage</code> object.
     */
    public Storage() {

    }

    /**
     * Takes in a <code>File</code> object and reads the data.
     * @param newFile                        Represents the file object to be read.
     * @return                               This method copies the previously established tasks to a newly
     *                                       created <code>TaskTracker</code>
     *                                       object and returns it to be used by the Rainy chatbot.
     * @throws InvalidIndexException         Thrown by <code>TaskManager</code> object when user provides
     *                                       a non-existent task number.
     * @throws InvalidMarkAndUnmarkException Thrown by <code>Task</code> object when user wants to mark a
     *                                       marked tasked or unmark an unmarked task.
     */
    public TaskTracker copyPreviousFiles(File newFile) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        TaskTracker newTask;
        UI ui = new UI();
        try {
            newTask = new TaskTracker();
            Scanner sc = new Scanner(newFile);
            int trace = 0;
            while (sc.hasNext()) {
                String oldData = sc.nextLine();
                if (trace > 0) {
                    if (oldData.charAt(8) == 'T') {
                        newTask.updateListToDo(oldData.substring(11));
                    } else if (oldData.charAt(8) == 'D') {
                        String updatedOldData = oldData.substring(11, oldData.length() - 1);
                        String[] deadlineSplit = updatedOldData.split(" \\(");
                        newTask.updateListDeadline(deadlineSplit[0] + " ", deadlineSplit[1]);
                    } else {
                        String updatedOldData = oldData.substring(11, oldData.length() - 1);
                        String[] eventSplit = updatedOldData.split(" \\(");
                        String newDate = eventSplit[1].split(" from ")[0];
                        String newTime = eventSplit[1].split(" from ")[1];
                        newTask.updateListEvent(eventSplit[0] + " ", newDate, newTime);
                    }
                    if (oldData.charAt(4) == 'X') {
                        newTask.markDone(trace - 1);
                    }
                }
                trace++;
            }
        } catch (FileNotFoundException e) {
            newTask = new TaskTracker();
        }
        newTask.toggleReceivedInputs();
        return newTask;
    }

    /**
     * Writes over the existing file to save the newly added tasks when program ends.
     *
     * @param filename Represents the file that this method will write over.
     * @param tm       Provides the list of task for this method to extract and write into the <code>File</code> object.
     */
    public void writeOverFile(File filename, TaskTracker tm) {
        try {
            filename.createNewFile();
            FileWriter fw = new FileWriter(filename);
            fw.write(tm.getList());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

