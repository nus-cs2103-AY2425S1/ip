import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskIO {

    enum fileStatus {
        DIRECTORY_DOES_NOT_EXIST,
        FILE_DOES_NOT_EXIST
    }

    static final String TODO = "T";
    static final String EVENT = "E";
    static final String DEADLINE = "D";

    private final File taskFile;

    public TaskIO(String pathname) {
        this.taskFile = new File(pathname);
    }

    private void createSavePoint(fileStatus status, Scanner sc) throws DenimException {
        switch (status) {
        case DIRECTORY_DOES_NOT_EXIST:
            handleDirectoryNotFound(sc);
            break;
        case FILE_DOES_NOT_EXIST:
            handleFileNotFound(sc);
            break;
        default:
            throw new DenimException("An error has occurred during the creation of files. Terminating");
        }
    }

    private void handleDirectoryNotFound(Scanner sc) throws DenimException {
        System.out.println("data directory and corresponding denim.txt not found. Create both? (y / n)\n");
        String input = sc.nextLine();

        switch (input) {
        case "y":
            File directory = new File("data");
            directory.mkdir();
            File dataFile = new File(directory,"denim.txt");

            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DenimException("Unable to create denim.txt");
            }

            break;
        case "n":
        default:
            throw new DenimException("Terminating Program. Have a nice day.");
        }
    }

    private void handleFileNotFound(Scanner sc) throws DenimException{
        System.out.println("denim.txt not found in data directory. Create denim.txt? (y / n)\n");
        String input = sc.nextLine();
        switch (input) {
        case "y":
            File denimFile = new File("data", "denim.txt");

            try {
                denimFile.createNewFile();
            } catch (IOException e) {
                throw new DenimException("Unable to create denim.txt");
            }
            break;
        case "n":
        default:
            throw new DenimException("Terminating Program. Have a nice day.");
        }
    }



    public void readTaskData(TaskList taskList, Scanner sc) throws DenimException {

        // Checks for Parent Directory ./data
        File dataDirectory = this.taskFile.getParentFile();
        if (dataDirectory == null || !dataDirectory.isDirectory()) {
            createSavePoint(fileStatus.DIRECTORY_DOES_NOT_EXIST, sc);
            return;
        }

        //Checks for denim.txt file
        if (!this.taskFile.exists()) {
            createSavePoint(fileStatus.FILE_DOES_NOT_EXIST, sc);
            return;
        }

        // Both data directory and denim.txt exists. Proceed to read from denim.txt
        try {
            Scanner fileReader = new Scanner(this.taskFile);
            while (fileReader.hasNext()) {
                String taskDescription = fileReader.nextLine();
                processTask(taskList, taskDescription);
            }
        } catch (IOException e) {
            sc.close();
            throw new DenimException("An error has occurred while trying to read denim.txt\n Terminating Program.");
        }
    }

    public void writeTaskData(Task task) throws DenimException {
        try {
            FileWriter taskWriter = new FileWriter(this.taskFile, true);
            taskWriter.write(task.toSimplifiedString());
            taskWriter.close();
        } catch (IOException e) {
            throw new DenimException("Unable to write to denim.txt");
        }
    }

    public void deleteTask(TaskList taskList) throws DenimException {
        File overridingFile = new File("data","denim.txt");
        Task task;

        try {
            FileWriter fw = new FileWriter(overridingFile);
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                task = taskList.getTask(i);
                fw.write(task.toSimplifiedString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DenimException("Error has occurred while trying to overwrite denim.txt.");
        }
    }

    public void markTask(TaskList taskList) throws DenimException {
        File overridingFile = new File("data","denim.txt");
        Task task;

        try {
            FileWriter fw = new FileWriter(overridingFile);
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                task = taskList.getTask(i);
                fw.write(task.toSimplifiedString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DenimException("Error has occurred while trying to overwrite denim.txt.");
        }
    }

    public void unmarkTask(TaskList taskList) throws DenimException {
        File overridingFile = new File("data","denim.txt");
        Task task;

        try {
            FileWriter fw = new FileWriter(overridingFile);
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                task = taskList.getTask(i);
                fw.write(task.toSimplifiedString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DenimException("Error has occurred while trying to overwrite denim.txt.");
        }
    }

    private void processTask(TaskList taskList, String task) throws DenimException {
        String[] taskComponents = task.split("\\|");
        String taskType = taskComponents[0].trim();
        boolean taskStatus = taskComponents[1].trim().equals("1");
        String taskDescription = taskComponents[2].trim();
        Task incomingTask;

        switch (taskType) {
        case TODO:
            incomingTask = new Todo(taskDescription, taskStatus);
            taskList.addTask(incomingTask);
            break;
        case DEADLINE:
            String deadlineBy = taskComponents[3].trim();
            incomingTask = new Deadline(taskDescription, taskStatus, deadlineBy);
            taskList.addTask(incomingTask);
            break;
        case EVENT:
            String eventFrom = taskComponents[3];
            String eventTo = taskComponents[4];
            incomingTask = new Event(taskDescription, taskStatus, eventFrom, eventTo);
            taskList.addTask(incomingTask);
            break;
        default:
            throw new DenimException("Unknown Formatting in data/denim.txt");
        }
    }
}
