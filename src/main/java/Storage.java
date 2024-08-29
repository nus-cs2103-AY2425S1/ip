import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    public static void readData(TaskList taskList, String filePath) {
        try (Scanner scanner =  new Scanner(new File(filePath), "UTF-8")){
            while (scanner.hasNextLine()){
                String in = scanner.nextLine();
                try {
                    Task newTask;
                    switch (in.split(" ")[0]) {
                        case "deadline":
                            newTask = Parser.newDeadline(in.split(" ", 3)[2]);
                            break;
                        case "event":
                            newTask = Parser.newEvent(in.split(" ", 3)[2]);
                            break;
                        case "todo":
                            newTask = Parser.newToDo(in.split(" ", 3)[2]);
                            break;
                        default:
                            System.out.println("Corrupted data found");
                            continue;
                    }
                    if (Objects.equals(in.split(" ")[1], "true")) {
                        newTask.mark();
                    }
                    taskList.addTask(newTask);
                } catch (MissingArgumentException | EmptyArgumentException | DateTimeParseException e) {
                    System.out.println("Corrupted data found");
                }
            }
            if (taskList.size() == 0) {
                System.out.println("No data was loaded");
            } else {
                System.out.printf("Successfully loaded %d entries from last save\n", taskList.size());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found");
        }
    }

    public static void writeData(TaskList taskList, String filePath) {
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.println(taskList.export());
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("Failed to save data");
        }

    }
}
