import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Optional;

public class Knight2103 {
    private String name;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

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
    public static ArrayList<Task> loadTaskList(File fileInput) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(fileInput);
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


    public Knight2103(String filePath) {
        this.name = "Knight2103";
        this.ui = new Ui(this.name);
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) { // TOO GENERIC ERRR
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
            String fullCommand = this.ui.readCommand();
            this.ui.showLine();
                Command c = Parser.parse(fullCommand).orElseThrow(() -> new MissingCommand());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MissingCommand e) {
                System.out.println(e);
            }
        }
    }
    public static void main(String[] args) {
        new Knight2103("./savedTaskList.txt").run(); // in ip folder, not java folder
    }
}