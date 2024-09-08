package knight2103.files;

import knight2103.Pair;
import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.tasks.Todo;
import knight2103.tasks.Deadline;
import knight2103.tasks.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Storage {
    private final File taskFile;

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }


    /**
     * Appends the tasks added to the storage file. When the bot's list of tasks
     * is changed due to the addition of tasks such as Todo, Deadline and Event,
     * these changes are reflected in the storage file.
     *
     * @param taskToSave The added task to be saved to the storage file.
     */
    public void save(Task taskToSave) throws IOException {
        FileWriter tasksWriter = new FileWriter(this.taskFile, true);
        tasksWriter.write("\n" + taskToSave.saveToFileFormat());
        tasksWriter.close();
    }

    /**
     * Updates by overwriting the entire list of tasks in the storage file.
     * When the task in bot's list of task is modified such as mark as done,
     * unmark as done or deleted, these modifications are captured in the
     * storage file as well. The newly updated list of task in the bot will be
     * saved into the storage file.
     *
     * @param tasks The newly updated tasks to be saved into the Storage File.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter tasksWriter = new FileWriter(this.taskFile, false);
        tasksWriter.write(tasks.printToFile());
        tasksWriter.close();
    }

    /**
     * Returns ArrayList<Task>. Reads the file that stores all the tasks and
     * load them into an ArrayList<Task> to be stored in the bot's list of tasks.
     *
     * @return The ArrayList of task that can be stored in the bot's list of tasks.
     */
    public Pair<ArrayList<Task>, String> load() throws FileNotFoundException { // Exception handling in Knight2103.java
        ArrayList<Task> tasks = new ArrayList<Task>();
        String errorMessage = "";
        int lineInFileCount = 0; // not item count, because .txt file can see line number easily
        Scanner scanner = new Scanner(this.taskFile);
        while (scanner.hasNextLine()) {
            lineInFileCount++;
            String lineInFocus = scanner.nextLine();
            if (lineInFocus.isEmpty()) {
                continue;
            }
            Pair<Optional<Task>, String> taskAndErrorMsgPair = formatLineToTask(lineInFocus);
            taskAndErrorMsgPair.getFirstItem().ifPresent(item -> tasks.add(item));
            if (!taskAndErrorMsgPair.getSecondItem().isEmpty()) {
                errorMessage += String.format("\nFile line %d - %s",
                        lineInFileCount, taskAndErrorMsgPair.getSecondItem());
            }
        }
        return new Pair<ArrayList<Task>, String>(tasks, errorMessage);
    }

    private Pair<Optional<Task>, String> formatLineToTask(String lineInFile) {
        String[] lineArray = lineInFile.split(" \\| ");
        String errorMessage = "";
        try {
            Task taskToAdd;
            switch (lineArray[0]) {
            case "T":
                if (lineArray.length != 3) {
                    throw new InvalidFileContentsException("Number of columns mismatch. There should be 3 for Todo");
                }
                taskToAdd = new Todo(lineArray[2]);
                return new Pair<Optional<Task>, String>(Optional.of(checkForMarkedFormat(lineArray,
                        taskToAdd)),
                        errorMessage);
            case "D":
                if (lineArray.length != 4) {
                    throw new InvalidFileContentsException("Number of columns mismatch. There should be 4 for Deadline");
                }
                try {
                    taskToAdd = new Deadline(lineArray[2], lineArray[3]);
                    return new Pair<Optional<Task>, String>(Optional.of(checkForMarkedFormat(lineArray, taskToAdd)), errorMessage);
                } catch (DateTimeParseException e) {
                    errorMessage += "\nknight2103.tasks.Deadline format is wrong in the file contents."
                            + " For deadline, it should be yyyy-MM-dd format.";
                }
                break;
            case "E":
                if (lineArray.length != 5) {
                    throw new InvalidFileContentsException("Number of columns mismatch. Expected 5 for Events");
                }
                try {
                    taskToAdd = new Event(lineArray[2], lineArray[3], lineArray[4]);
                    return new Pair<Optional<Task>, String>(Optional.of(checkForMarkedFormat(lineArray, taskToAdd)), errorMessage);
                } catch (DateTimeParseException e) {
                    errorMessage += "\nDeadline format is wrong in the file contents."
                            + " For events, it should be yyyy-MM-ddThh:mm format";
                }
                break;
            default:
                throw new InvalidFileContentsException("Only T, E, D accepted but others found");
            }
        } catch (InvalidFileContentsException e) {
            errorMessage += e.getMessage();
        }
        return new Pair<Optional<Task>, String>(Optional.empty(), "Errors found: " + errorMessage);
    }


    private Task checkForMarkedFormat(String[] lineArray, Task taskToAdd) throws InvalidFileContentsException {
        // here can assert the lineArray length > 2
        if (lineArray[1].equals("0")) {
            return taskToAdd;
        } else if (lineArray[1].equals("1")) {
            taskToAdd.markDone();
            return taskToAdd;
        } else {
            throw new InvalidFileContentsException("the value of the 2nd column should only be 0 or 1");
        }
    }
}