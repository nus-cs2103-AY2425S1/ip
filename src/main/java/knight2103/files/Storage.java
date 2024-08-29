package knight2103.files;

import knight2103.tasks.*;

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

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(this.taskFile);
            while (scanner.hasNextLine()) {
                formatLineToTask(scanner.nextLine()).ifPresent(item -> taskList.add(item));
                System.out.println(taskList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return taskList;
    }

    public void save(Task taskToSave) {
        try {
            FileWriter taskListWriter = new FileWriter(this.taskFile, true);
            taskListWriter.write("\n" + taskToSave.saveToFileFormat());
            taskListWriter.close();
        } catch (IOException e) {
            System.out.println("Problems creating an instance of FileWriter");
        }
    }

    public void save(TaskList taskList) {
        try {
            FileWriter taskListWriter = new FileWriter(this.taskFile, false);
            taskListWriter.write(taskList.printToFile());
            taskListWriter.close();
        } catch (IOException e) {
            System.out.println("Problems creating an instance of FileWriter");
        }
    }

    private Task checkForMarkedFormat(String[] lineArray, Task taskToAdd) throws FileContentsInvalid {
        if (lineArray[1].equals("0")) {
            return taskToAdd;
        } else if (lineArray[1].equals("1")) {
            taskToAdd.markDone();
            return taskToAdd;
        } else {
            throw new FileContentsInvalid("the value of the 2nd column should only be 1 or 2");
        }
    }

    // to simplify
    private Optional<Task> formatLineToTask(String lineInFile) {
        String[] lineArray = lineInFile.split(" \\| ");
        try {
            Task taskToAdd;
            switch (lineArray[0]) {
            case "T":
                if (lineArray.length != 3) {
                    throw new FileContentsInvalid("Number of columns mismatch. There should be 3 for Todo");
                }
                taskToAdd = new Todo(lineArray[2]);
                return Optional.of(checkForMarkedFormat(lineArray, taskToAdd));
            case "D":
                if (lineArray.length != 4) {
                    throw new FileContentsInvalid("Number of columns mismatch. There should be 4 for Deadline");
                }
                try {
                    taskToAdd = new Deadline(lineArray[2], lineArray[3]);
                    return Optional.of(checkForMarkedFormat(lineArray, taskToAdd));
                } catch (DateTimeParseException e) {
                    System.out.println("knight2103.tasks.Deadline format is wrong in the file contents."
                            + " For deadline, it should be yyyy-MM-dd format.");
                }
                break;
            case "E":
                if (lineArray.length != 5) {
                    throw new FileContentsInvalid("Number of columns mismatch. There should be 5 for Events");
                }
                try {
                    taskToAdd = new Event(lineArray[2], lineArray[3], lineArray[4]);
                    return Optional.of(checkForMarkedFormat(lineArray, taskToAdd));
                } catch (DateTimeParseException e) {
                    System.out.println("Deadline format is wrong in the file contents."
                            + " For events, it should be yyyy-MM-ddThh:mm format");
                }
                break;
            default:
                throw new FileContentsInvalid("Only T, E, D accepted but others found");
            }
        } catch (FileContentsInvalid e) {
            System.out.println(e);
        }
        return Optional.empty();
    }
}