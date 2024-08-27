import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String pathString;

    public Storage(String pathString) {
        this.pathString = pathString;
    }

    public TaskList retrieveData() {
        try {
            File dataFile = new File(this.pathString);
            if (!dataFile.exists()) {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            }
            Scanner fileScanner = new Scanner(dataFile);
            boolean isCorrupted = false;
            ArrayList<Task> list = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                try {
                    String[] dataItem = fileScanner.nextLine().split("/");
                    String type = dataItem[0];
                    boolean isDone = Boolean.parseBoolean(dataItem[1]);
                    switch (type) {
                    case "T":
                        list.add(new Todo(dataItem[2], isDone));
                        break;
                    case "D":
                        list.add(new Deadline(dataItem[2], LocalDateTime.parse(dataItem[3]), isDone));
                        break;
                    case "E":
                        list.add(new Event(dataItem[2], LocalDateTime.parse(dataItem[3]), LocalDateTime.parse(dataItem[4]), isDone));
                        break;
                    default:
                        isCorrupted = true;
                    }
                } catch (Exception e) {
                    isCorrupted = true;
                }
            }
            if (isCorrupted) {
                Ui.printCorruptedDataErr();
            }
            fileScanner.close();
            return new TaskList(list);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return new TaskList();
        }
    }

    public void updateData(TaskList list) {
        try {
            File dataFile = new File("../../../data/mahesh.txt");
            if (!dataFile.exists()) {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(dataFile);
            fileWriter.write(list.toFileData());
            fileWriter.close();
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
