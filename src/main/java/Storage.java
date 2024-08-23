import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private final static String storageListFilePath = "src/main/text-files/listStorage.txt";
    private final static String tempFile = "src/main/text-files/tempFile.txt";

    public static void appendToListFile(Task task) {
        try {
            FileWriter fw = new FileWriter(storageListFilePath, true);
            String textToAdd = task.convertToFileFormat();
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            FormattedPrinting.taskUnableToBeStoredInFile();
        }
    }

    public static void clearListFile() {
        try {
            FileWriter fw = new FileWriter(storageListFilePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            FormattedPrinting.formatPrint("File does not exist");
        }
    }

    // the following was inspired from the following post:
    // https://stackoverflow.com/questions/5800603/delete-specific-line-from-java-text-file
    public static void deleteTaskFromFile(TaskList taskList) {
        clearListFile();
        for (int i = 0; i < taskList.getSize(); i++) {
            appendToListFile(taskList.getTask(i));
        }
    }

    // loading task list from storage.
    public static TaskList loadTaskListFromFile(TaskList taskList) {
        try {
            File f = new File(storageListFilePath);
            // Check if the file already exists and create if it is not
            if (!f.exists()) {
                if (!f.createNewFile()) {
                    FormattedPrinting.fileCorrupted();
                }
            }
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String input = s.nextLine();
                Task task = Parser.parseInputFromFile(input);
                taskList.loadTask(task);
            }
            s.close();
            return taskList;
        } catch (RuntimeException | IOException e) {
            return new TaskList();
        }
    }
}
