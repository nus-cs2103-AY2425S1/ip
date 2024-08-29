import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private static final String FILE_PATH = "duke.txt";
    private static final String DATA_DIR = "data/";

    public static void loadFile() {
        try {
            File fileDir = new File(DATA_DIR);
            if (!fileDir.exists()) {
                boolean isDirCreated = fileDir.mkdir();
            }
            File file = new File(DATA_DIR + FILE_PATH);
            if (!file.exists()) {
                boolean isFileCreated = file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(DATA_DIR + FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                TaskList.load(Parser.convertStringToTask(line));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeToFile(Task task) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(DATA_DIR + FILE_PATH, true))) {
            String taskString = convertTaskToString(task);
            writer.write(taskString);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String convertTaskToString(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() +
                    " | " + ((Deadline) task).getBy().toString();
        } else if (task instanceof Event) {
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() +
                    " | " + ((Event) task).getStart().toString() +
                    " | " + ((Event) task).getEnd().toString();
        }
        return "";
    }

    public static void replaceLineInFile(int index) {
        File inputFile = new File(DATA_DIR + FILE_PATH);
        File tempFile = new File(DATA_DIR + "temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            int currentIdx = 0;

            while ((currentLine = reader.readLine()) != null) {
                if (currentIdx != index) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    String replacedLine = convertTaskToString(TaskList.getTask(index));
                    writer.write(replacedLine);
                    writer.newLine();
                }
                currentIdx++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean isDeleted = inputFile.delete();
        boolean isRenamed = tempFile.renameTo(inputFile);
    }

    public static void deleteLineFromFile(int index) {
        File inputFile = new File(DATA_DIR + FILE_PATH);
        File tempFile = new File(DATA_DIR + "temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            int currentIdx = 0;

            while ((currentLine = reader.readLine()) != null) {
                if (currentIdx != index) {
                    writer.write(currentLine);
                    writer.newLine();
                }
                currentIdx++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean isDeleted = inputFile.delete();
        boolean isRenamed = tempFile.renameTo(inputFile);
    }


}
