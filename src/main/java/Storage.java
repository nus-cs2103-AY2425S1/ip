import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Storage {
    private File taskStore;
    Storage(String filePath) {
        this.taskStore = new File(filePath);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(this.taskStore);
            while (scanner.hasNextLine()) {
                formatToTask(scanner.nextLine()).ifPresent(
                        item -> taskList.add(item)
                );
                System.out.println(taskList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return taskList;
    }

    public void save(Task taskToSave) {
        try {
            FileWriter taskListWriter = new FileWriter(this.taskStore, true);
            taskListWriter.write("\n" + taskToSave.saveToFileFormat());
            taskListWriter.close();
        } catch (IOException e) {
            System.out.println("Problems creating an instance of FileWriter");
        }
    }

    public void save (TaskList taskList) {
        try {
            FileWriter taskListWriter = new FileWriter(this.taskStore, false);
            taskListWriter.write(taskList.printToFile());
            taskListWriter.close();
        } catch (IOException e) {
            System.out.println("Problems creating an instance of FileWriter");
        }
    }


// to simplify
public static Optional<Task> formatToTask(String lineInFile) {
    String[] inputArray = lineInFile.split(" \\| ");
    try {
        Task taskToAdd;
        switch (inputArray[0]) {
            case "T":
                if (inputArray.length != 3) {
                    throw new FileContentsInvalid("Number of columns mismatch. There should be 3 for Todo");
                }
                taskToAdd = new Todo(inputArray[2]);
                if (inputArray[1].equals("0")) {
                    return Optional.of(taskToAdd);
                } else if (inputArray[1].equals("1")) {
                    taskToAdd.markDone();
                    return Optional.of(taskToAdd);
                } else {
                    throw new FileContentsInvalid("the value of the 2nd column should only be 1 or 2");
                }
            case "D":
                if (inputArray.length != 4) {
                    throw new FileContentsInvalid("Number of columns mismatch. There should be 4 for Deadline");
                }
                try {
                    taskToAdd = new Deadline(inputArray[2], inputArray[3]);
                    if (inputArray[1].equals("0")) {
                        return Optional.of(taskToAdd);
                    } else if (inputArray[1].equals("1")) {
                        taskToAdd.markDone();
                        return Optional.of(taskToAdd);
                    } else {
                        throw new FileContentsInvalid("the value of the 2nd column should only be 1 or 2");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Deadline format is wrong in the file contents");
                }
                break;
            case "E":
                if (inputArray.length != 5) {
                    throw new FileContentsInvalid("Number of columns mismatch. There should be 5 for Events");
                }
                taskToAdd = new Event(inputArray[2], inputArray[3], inputArray[4]);
                if (inputArray[1].equals("0")) {
                    return Optional.of(taskToAdd);
                } else if (inputArray[1].equals("1")) {
                    taskToAdd.markDone();
                    return Optional.of(taskToAdd);
                } else {
                    throw new FileContentsInvalid("the value of the 2nd column should only be 1 or 2");
                }
                //break;
            default:
                throw new FileContentsInvalid("Only T, E, D accepted but others found");
        }
    } catch (FileContentsInvalid e) {
        System.out.println(e);
    }
    return Optional.empty(); // code will be problematic
}

}