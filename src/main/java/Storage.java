import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import Task.*;



public class Storage {
    ArrayList<Task> tasks;

    Storage(ArrayList<Task> lst) {
        tasks = lst;
    }

    void parseTodo(String str) {
        // "|" is one of the special expressions in regex, so need \\
        String[] splitted = str.split("\\|");
        tasks.add(new Todo(splitted[2], splitted[1].equals("1")));
    }

    void parseDeadline(String str) {
        String[] splitted = str.split("\\|");
        tasks.add(new Deadline(splitted[2], splitted[3], splitted[1].equals("1")));
    }

    void parseEvent(String str) {
        String[] splitted = str.split("\\|");
        tasks.add(new Event(splitted[2], splitted[3], splitted[4], splitted[1].equals("1")));
    }

    void openFile() {
        Path path = Paths.get("../../../data/tasks");
        Path directory = path.getParent();
        try {
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }

            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {System.out.println(e.getMessage());}
        
        // start reading from file
        try {
            Scanner scanner = new Scanner(Files.newBufferedReader(path));
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                char type = str.charAt(0);
                if (type == 'T') parseTodo(str);
                else if (type == 'E') parseEvent(str);
                else if (type == 'D') parseDeadline(str);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }

    // only call this after calling openFile, because I assume ../../../data/tasks is created already
    void saveTasks() {
        Path path = Paths.get("../../../data/tasks");
        try {
            // clear existing contents
            Files.write(path, "".getBytes());
            for (Task task : tasks) {
                Files.write(path, (task.toStore() + "\n").getBytes(), StandardOpenOption.APPEND);
            }
        } catch(IOException e) {System.out.println(e.getMessage());}

    }
}
