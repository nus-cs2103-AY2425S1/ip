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
            newTask = this.processFile(newFile);
        } catch (FileNotFoundException e) {
            newTask = new TaskTracker();
        }
        assert(newTask.getCounter() >= 0);
        newTask.toggleReceivedInputs();
        return newTask;
    }

    public TaskTracker processFile(File newFile) throws IOException {
        TaskTracker newTask = new TaskTracker();
        TaskTracker[] taskHolder = new TaskTracker[]{newTask};
        AtomicInteger trace = new AtomicInteger(0);
        AtomicInteger nextCounter = new AtomicInteger(8);
        AtomicInteger markedCounter = new AtomicInteger(4);
        Files.lines(newFile.toPath()).skip(1).forEach(x -> {
            int charLabel, markedLabel = 0;
            if ((trace.get() + 1) % 10 == 0) {
                charLabel = nextCounter.incrementAndGet();
                markedLabel = markedCounter.incrementAndGet();
            } else {
                charLabel = nextCounter.get();
                markedLabel = markedCounter.get();
            }
            if (x.charAt(charLabel) == 'T') {
                taskHolder[0] = this.updateToDo(taskHolder[0], x);
            } else if (x.charAt(charLabel) == 'D') {
                taskHolder[0] = this.updateDeadline(taskHolder[0], x);
            } else {
                taskHolder[0] = this.updateEvent(taskHolder[0], x);
            }
            int taskCount = trace.getAndIncrement();
            try {
                taskHolder[0] = this.markSavedTask(taskHolder[0], x, taskCount, markedLabel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return taskHolder[0];
    }

    public TaskTracker markSavedTask(TaskTracker taskTracker, String userInput, int taskCount, int markedLabel) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (userInput.charAt(markedLabel) == 'X') {
            try {
                taskTracker.markDone(taskCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return taskTracker;
    }

    public TaskTracker updateToDo(TaskTracker taskTracker, String userInput) {
        taskTracker.addListToDo(userInput.substring(11));
        return taskTracker;
    }

    public TaskTracker updateDeadline(TaskTracker taskTracker, String userInput) {
        String updatedOldData = userInput.substring(11, userInput.length() - 1);
        String[] deadlineSplit = updatedOldData.split(" \\(");
        taskTracker.addListDeadline(deadlineSplit[0] + " ", deadlineSplit[1]);
        return taskTracker;
    }

    public TaskTracker updateEvent(TaskTracker taskTracker, String userInput) {
        String updatedOldData = userInput.substring(11, userInput.length() - 1);
        String[] eventSplit = updatedOldData.split(" \\(");
        String newDate = eventSplit[1].split(" from ")[0];
        String newTime = eventSplit[1].split(" from ")[1];
        taskTracker.addListEvent(eventSplit[0] + " ", newDate, newTime);
        return taskTracker;
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
            fw.write(tm.printList());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

