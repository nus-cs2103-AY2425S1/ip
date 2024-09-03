package Nave;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class TaskStorage {
    String filePathString;
    Path filePath;

    public TaskStorage(String filePathString) {
        this.filePathString = filePathString;
        this.filePath = Path.of(filePathString);
    }

    public void onStart(TaskList list) {
        try {
            File loadFile = new File(filePathString);
            Scanner scanner = new Scanner(loadFile);
            while (scanner.hasNext()) {
                String currLine = scanner.nextLine();
                String[] split = currLine.split(",");
                switch (split.length) {
                    case 1:
                        list.addTask(new Todo(split[0]));
                        break;
                    case 2:
                        list.addTask(new Deadline(split[0], LocalDate.parse(split[1])));
                        break;
                    case 3:
                        list.addTask(new Event(split[0], split[1], split[2]));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            if (!Files.isDirectory(Path.of("./data"))) {
                System.out.println("Directory './data' does not exist. Please create it first.");
                System.exit(0);
            } else {
                System.out.println("File './data/tasks.txt' does not exist. Please create it first.");
                System.exit(0);
            }
        }
    }

    public void saveToFile(String content) {
        try {
            FileWriter writer = new FileWriter(filePathString, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFromFile(int place) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePathString));
            FileWriter writer = new FileWriter("./data/temp.txt");

            int counter = 1;
            String currLine;
            while((currLine = reader.readLine() ) != null) {
                if (counter == place) {
                    counter++;
                    continue;
                }
                writer.write(currLine + System.lineSeparator());
                counter++;
            }
            writer.close();
            Files.copy(Path.of("./data/temp.txt"), filePath, REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("There was an error with the file input.");
        }
    }
}
