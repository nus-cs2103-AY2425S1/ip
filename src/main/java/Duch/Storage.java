package duch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import duch.task.Deadline;
import duch.task.Event;
import duch.task.Task;
import duch.task.Todo;


/**
 * Storage handles reading the stored text from ./data/tasks and processes them into Task objects.
 * Also handles opening the ./data/text file and saving Task when closing Duch
 */
public class Storage {
   TaskList tasks;
    String pathString = "./data/tasks";

    public Storage(TaskList lst) {
        tasks = lst;
    }
    
    /**
     * Parse a stored todo string
     * 
     * @param str The stored todo.
     */
    public void parseTodo(String str) {
        // "|" is one of the special expressions in regex, so need \\
        String[] splitted = str.split("\\|");
        tasks.add(new Todo(splitted[2], splitted[1].equals("1")));
    }

    /**
     * Parse a stored deadline string
     * 
     * @param str The stored deadline string.
     * 
     */
    public void parseDeadline(String str) {
        String[] splitted = str.split("\\|");
        tasks.add(new Deadline(splitted[2], splitted[3], splitted[1].equals("1")));
    }

    /**
     * Parse a stored event string
     * 
     * @param str The stored event string.
     */
    public void parseEvent(String str) {
        String[] splitted = str.split("\\|");
        tasks.add(new Event(splitted[2], splitted[3], splitted[4], splitted[1].equals("1")));
    }

    /**
     * Open the ./data/text file, where the tasks will be stored.
     */
    public void openFile() {
        Path path = Paths.get(pathString);
        Path directory = path.getParent();
        try {
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }

            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {System.out.println(e.getMessage());}
        
        // make sure file exists
        assert Files.exists(path);
        
        // start reading from file
        try {
            Scanner scanner = new Scanner(Files.newBufferedReader(path));
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.length() == 0) {
                    break;
                }
                
                char type = str.charAt(0);
                if (type == 'T') {
                    parseTodo(str);
                } else if (type == 'E') {
                    parseEvent(str);
                } else if (type == 'D') {
                    parseDeadline(str);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }

    /**
     * Serialises the tasks into strings and stores it in the ./data/tasks file.
     */
    public void saveTasks() {
        Path path = Paths.get(pathString);
        try {
            // clear existing contents
            Files.write(path, "".getBytes());
            for (Task task : tasks) {
                Files.write(path, (task.toStore() + "\n").getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {System.out.println(e.getMessage());}

    }
}
