import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static String LINE = "\t" + "------------------------------------";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void ensureFileExists() {
        File file = new File(filePath);
        File directory = new File(file.getParent());

        // Create directory if it does not exist
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println(LINE);
                System.out.println("\t" + "New directory created: " + directory.getPath());
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println("\t" + "Failed to create new directory: " + directory.getPath());
                System.out.println(LINE);
            }
        }

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println(LINE);
                    System.out.println("\t" + "New file created: " + file.getPath());
                    System.out.println(LINE);
                } else {
                    System.out.println(LINE);
                    System.out.println("\t" + "Failed to create new file: " + file.getPath());
                    System.out.println(LINE);
                }
            } catch (IOException e) {
                System.out.println(LINE);
                System.out.println("\t" + "Error creating new File");
                System.out.println(LINE);
            }
        }
    }
    public ArrayList<Task> load() throws FileNotFoundException {
        ensureFileExists();
        ArrayList<Task> collatedTasks = new ArrayList<>();
        File file = new File(filePath); // create a File for the given file path
        Scanner scanner = new Scanner(file); // create a Scanner using the File as the source
        while (scanner.hasNext()) {
            String dataLine = scanner.nextLine();
            String[] inputs = dataLine.split("\\|");
            String taskType = inputs[0].trim();
            String doneStatus = inputs[1].trim();
            String taskDescription = inputs[2].trim();
            switch(taskType) {
                case "T":
                    ToDo currentTask = new ToDo(taskDescription);
                    if (doneStatus.equals("1")) {
                        currentTask.markAsDone();
                    }
                    collatedTasks.add(currentTask);
                    break;
                case "D":
                    String deadline = inputs[3].trim();
                    Deadline currentDeadline = new Deadline(taskDescription, deadline);
                    if (doneStatus.equals("1")) {
                        currentDeadline.markAsDone();
                    }
                    collatedTasks.add(currentDeadline);
                    break;
                case "E":
                    String fromToDate = inputs[3].trim();
                    String[] fromToSplit = fromToDate.split("to");
                    String from = fromToSplit[0].trim();
                    String to = fromToSplit[1].trim();
                    Event currentEvent = new Event(taskDescription, from, to);
                    if (doneStatus.equals("1")) {
                        currentEvent.markAsDone();
                    }
                    collatedTasks.add(currentEvent);
                    break;
                default:
                    System.out.println("\t" + "There is an error in the file!");
            }
        }
        return collatedTasks;
    }

    public void updateFile(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task currentTask : tasks.getTasks()) {
                fileWriter.write(currentTask.getFileString()+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(LINE);
            System.out.println("\t" + "Something went wrong: " + e.getMessage());
            System.out.println(LINE);
        }
    }

    public void printFile() {
        try {
            File file = new File(filePath); // create a File for the given file path
            Scanner scanner = new Scanner(file); // create a Scanner using the File as the source
            System.out.println(LINE);
            while (scanner.hasNext()) {
                System.out.println("\t" + scanner.nextLine());
            }
            System.out.println(LINE);
        } catch (FileNotFoundException e) {
            System.out.println(LINE);
            System.out.println("\t" + "I'm sorry, but I can't find the data for the ToDos!");
            System.out.println("\t" + "Please specify the correct file path.");
            System.out.println(LINE);
        }
    }

}
