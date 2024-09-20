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
    private static final int START_INDEX = 0;
    private static final int TASK_TYPE = 8;
    private static final int MARKED_TASK = 4;
    private static final int SKIP_FIRST = 1;
    private static final int START_TASK_DESC = 11;
    private static final char MARKED_LABEL = 'X';

    public Storage() {

    }

    /**
     * Checks if the particular directory housing saved data exists, and if not, creates it.
     * @param directory  File representing the directory.
     */
    public void checkIfDirectoryExists(File directory) {
        if (!directory.exists()) {
            directory.mkdir();
        }
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
    public TaskTracker copyPreviousFiles(File newFile) throws InvalidIndexException, InvalidMarkAndUnmarkException,
            IOException {
        TaskTracker newTask;
        try {
            newTask = this.processFile(newFile);
        } catch (FileNotFoundException e) {
            newTask = new TaskTracker();
        }
        assert(newTask.getCounter() >= START_INDEX);
        newTask.toggleReceivedInputs();
        return newTask;
    }

    /**
     * Processes each individual line of the file and updates the <code>TaskTracker</code> object
     * with the appropriate object type.
     * @param newFile        Input file containing individual tasks previously input
     * @return               Returns the updated <code>TaskTracker</code> object.
     * @throws IOException
     */
    public TaskTracker processFile(File newFile) throws IOException {
        TaskTracker newTask = new TaskTracker();
        TaskTracker[] taskHolder = new TaskTracker[]{newTask};
        AtomicInteger trace = new AtomicInteger(START_INDEX);
        AtomicInteger nextCounter = new AtomicInteger(TASK_TYPE);
        AtomicInteger markedCounter = new AtomicInteger(MARKED_TASK);
        Files.lines(newFile.toPath()).skip(SKIP_FIRST).forEach(x -> {
            int charLabel = 0;
            int markedLabel = 0;
            if ((trace.get() + 1) == 10 || (trace.get() + 1) == 100) {
                charLabel = nextCounter.incrementAndGet();
                markedLabel = markedCounter.incrementAndGet();
            } else {
                charLabel = nextCounter.get();
                markedLabel = markedCounter.get();
            }
            if (x.charAt(charLabel) == 'T') {
                taskHolder[START_INDEX] = this.updateToDo(taskHolder[START_INDEX], x);
            } else if (x.charAt(charLabel) == 'D') {
                taskHolder[START_INDEX] = this.updateDeadline(taskHolder[START_INDEX], x);
            } else {
                taskHolder[START_INDEX] = this.updateEvent(taskHolder[START_INDEX], x);
            }
            int taskCount = trace.getAndIncrement();
            try {
                taskHolder[START_INDEX] = this.markSavedTask(taskHolder[START_INDEX], x, taskCount, markedLabel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return taskHolder[START_INDEX];
    }

    /**
     * Marks each task as done based on an input integer.
     * @param taskTracker                      Input <code>TaskTracker</code> object.
     * @param userInput                        User Input to determine if the previous input has been marked.
     * @param taskCount                        Index Nnmber of <code>Task</code>.
     * @param markedLabel                      Index Number where the marked label occurs at on the input line.
     * @return                                 Returns the updated <code>TaskTracker</code> object.
     * @throws InvalidIndexException           Throws Exception when an invalid index is entered.
     * @throws InvalidMarkAndUnmarkException   Throws Exception when non-task index is entered.
     */
    public TaskTracker markSavedTask(TaskTracker taskTracker, String userInput, int taskCount, int markedLabel)
            throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (userInput.charAt(markedLabel) == MARKED_LABEL) {
            try {
                taskTracker.markDone(taskCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return taskTracker;
    }

    /**
     * Update the parameters of the ToDo task with a String entered by the user.
     * @param taskTracker  Represents the <code>TaskTracker</code> object to be updated.
     * @param userInput    Represents the input String.
     * @return             Returns the updated <code>TaskTracker</code> object.
     */
    public TaskTracker updateToDo(TaskTracker taskTracker, String userInput) {
        int actualStart = START_TASK_DESC;
        if (taskTracker.getCounter() >= 9) {
            actualStart++;
        }
        if (taskTracker.getCounter() >= 99) {
            actualStart += 2;
        }
        taskTracker.addListToDo(userInput.substring(actualStart));
        return taskTracker;
    }

    /**
     * Update the parameters of the Deadline task with a String entered by the user.
     * @param taskTracker   Represents the <code>TaskTracker</code> object to be updated.
     * @param userInput     Represents the input String.
     * @return              Returns the updated <code>TaskTracker</code> object.
     */
    public TaskTracker updateDeadline(TaskTracker taskTracker, String userInput) {
        int actualStart = START_TASK_DESC;
        if (taskTracker.getCounter() >= 9) {
            actualStart++;
        }
        if (taskTracker.getCounter() >= 99) {
            actualStart += 2;
        }
        String updatedOldData = userInput.substring(actualStart, userInput.length() - 1);
        String[] deadlineSplit = updatedOldData.split(" \\(");
        taskTracker.addListDeadline(deadlineSplit[START_INDEX] + " ", deadlineSplit[SKIP_FIRST]);
        return taskTracker;
    }

    /**
     * Update the parameters of the Event task with a String entered by the user.
     * @param taskTracker   Represents the <code>TaskTracker</code> object to be updated.
     * @param userInput     Represents the input String.
     * @return              Returns the updated <code>TaskTracker</code> object.
     */
    public TaskTracker updateEvent(TaskTracker taskTracker, String userInput) {
        int actualStart = START_TASK_DESC;
        if (taskTracker.getCounter() >= 9) {
            actualStart++;
        }
        if (taskTracker.getCounter() >= 99) {
            actualStart += 2;
        }
        String updatedOldData = userInput.substring(actualStart, userInput.length() - 1);
        String[] eventSplit = updatedOldData.split(" \\(");
        String newDate = eventSplit[SKIP_FIRST].split(" from ")[START_INDEX];
        String newTime = eventSplit[SKIP_FIRST].split(" from ")[SKIP_FIRST];
        taskTracker.addListEvent(eventSplit[START_INDEX] + " ", newDate, newTime);
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

