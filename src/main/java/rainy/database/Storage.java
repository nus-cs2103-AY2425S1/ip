package rainy.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

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
    public TaskTracker copyPreviousFiles(File newFile) throws InvalidIndexException, InvalidMarkAndUnmarkException, IOException {
        TaskTracker newTask;
        try {
            newTask = new TaskTracker();
            TaskTracker[] taskHolder = new TaskTracker[]{newTask};
            AtomicInteger trace = new AtomicInteger(0);
            Files.lines(newFile.toPath()).skip(1).forEach(x -> {
                if (x.charAt(8) == 'T') {
                    taskHolder[0].updateListToDo(x.substring(11));
                } else if (x.charAt(8) == 'D') {
                    String updatedOldData = x.substring(11, x.length() - 1);
                    String[] deadlineSplit = updatedOldData.split(" \\(");
                    taskHolder[0].updateListDeadline(deadlineSplit[0] + " ", deadlineSplit[1]);
                } else {
                    String updatedOldData = x.substring(11, x.length() - 1);
                    String[] eventSplit = updatedOldData.split(" \\(");
                    String newDate = eventSplit[1].split(" from ")[0];
                    String newTime = eventSplit[1].split(" from ")[1];
                    taskHolder[0].updateListEvent(eventSplit[0] + " ", newDate, newTime);
                }
                int newCount = trace.getAndIncrement();
                if (x.charAt(4) == 'X') {
                    try {
                        taskHolder[0].markDone(newCount);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            newTask = taskHolder[0];
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

