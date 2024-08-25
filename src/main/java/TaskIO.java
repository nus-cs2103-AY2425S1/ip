import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskIO {

    static final String TODO = "T";
    static final String EVENT = "E";
    static final String DEADLINE = "D";
    static final String horizontalLine = "____________________________________________________________";

    private final File taskFile;

    public TaskIO(String pathname) {
        taskFile = new File(pathname);
    }

    public void readTaskData(TaskList taskList) throws IOException, DenimException {
        Scanner sc = new Scanner(this.taskFile);
        while (sc.hasNext()) {
            String task = sc.nextLine();
            processTask(taskList, task);
        }
    }

    public void writeTaskData(String task, String filePath) throws IOException {
        FileWriter taskWriter = new FileWriter(filePath, true);
        taskWriter.write(task);
        taskWriter.close();
    }

    private static void processTask(TaskList taskList, String task) throws DenimException {
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
            String[] duration = taskComponents[3].trim().split("-");
            String eventFrom = duration[0];
            String eventTo = duration[1];
            incomingTask = new Event(taskDescription, taskStatus, eventFrom, eventTo);
            taskList.addTask(incomingTask);
            break;
        default:
            throw new DenimException("Unknown Formatting in data/denim.txt");
        }
    }
}
