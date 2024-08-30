package fred;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File dataFile;

    void getDataFile() {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists() || !dataDirectory.isDirectory()) {
            dataDirectory.mkdir();
        }
        dataFile = new File("./data/fred.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    ArrayList<Task> getTasksFromDataFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] lineParts = line.split(" \\| ");
            Task task = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            switch (lineParts[0]) {
            case "T":
                task = new Todo(lineParts[2]);
                break;
            case "D":
                task = new Deadline(lineParts[2], LocalDateTime.parse(lineParts[3], formatter));
                break;
            case "E":
                String[] fromTo = lineParts[3].split(" - ");
                task = new Event(lineParts[2], LocalDateTime.parse(fromTo[0], formatter), LocalDateTime.parse(fromTo[1], formatter));
                break;
            }
            if (lineParts[1].equals("1")) {
                task.markAsDone();
            } else if (lineParts[1].equals("0")) {
                task.markAsNotDone();
            }
            tasks.add(task);
        }
        return tasks;
    }

    void deleteFromDataFile(int taskNumber) {
        File tempFile = new File("./data/tmp.txt");
        try {
            FileWriter writer = new FileWriter(tempFile);
            Scanner scanner = new Scanner(dataFile);
            int i = 0;
            String currentLine;
            while (scanner.hasNext()) {
                currentLine = scanner.nextLine();
                if (i == taskNumber) {
                    continue;
                }
                writer.write(currentLine + System.lineSeparator());
                i++;
                System.out.println(i);
            }
            writer.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(dataFile);
    }

    void appendToDataFile(Task task) {
        try {
            FileWriter writer = new FileWriter(dataFile, true);
            writer.write(task.getDataFormat() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
