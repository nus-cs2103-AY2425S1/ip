package genji;

import genji.task.Task;
import genji.task.TaskList;
import genji.task.ToDo;
import genji.task.Deadline;
import genji.task.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * A class to store tasks and load when start
 */
public class Storage {
   private String filePath;

    /**
     * Constructor of Storage class
     * @param filePath place to store and load the list
     */
   public Storage(String filePath) {
       this.filePath = filePath;
   }

    /**
     * Loads todo task
     * @param list Task list where stores tasks
     * @param input Todo task
     */
   public static void loadTodo(TaskList list, String input) {
       list.add(new ToDo(input.substring(8)));
       if (input.substring(4).startsWith("1")) {
           list.get(list.size()- 1).mark();
       }
   }

    /**
     * Loads deadline task
     * @param list Task list where stores tasks
     * @param input Deadline task
     */
    public static void loadDeadline(TaskList list, String input) {
        String name = input.substring(8, input.lastIndexOf(" |"));
        LocalDateTime from = LocalDateTime.parse(input.substring
                        (input.lastIndexOf(" |") + 3),
                        DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        list.add(new Deadline(name, from));
        if (input.substring(4).startsWith("1")) {
            list.get(list.size() - 1).mark();
        }
    }

    /**
     * Loads event task
     * @param list Task list where stores tasks
     * @param input Event task
     */
    public static void loadEvent(TaskList list, String input) {
        String name = input.substring(8, input.lastIndexOf(" |"));
        LocalDateTime from = LocalDateTime.parse(input.substring
                        (input.lastIndexOf(" |") + 3, input.lastIndexOf(" to")),
                        DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        LocalDateTime to = LocalDateTime.parse(input.substring
                        (input.lastIndexOf(" to") + 4),
                        DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        list.add(new Event(name, from, to));
        if (input.substring(4).startsWith("1")) {
            list.get(list.size()- 1).mark();
        }
    }

    /**
     * Loads the list in the .txt file
     * Creates the file if it is not existed
     * @return The task list stored in the .txt file
     */
    public TaskList loadList() {
       TaskList list = new TaskList();
       try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            if (!s.hasNextLine()) {
                System.out.println("No task in list");
            }
            while (s.hasNext()) {
                String input = s.nextLine();
                if (input.startsWith("T")) {
                    loadTodo(list, input);
                } else if (input.startsWith("D")) {
                    loadDeadline(list, input);
                } else {
                    loadEvent(list, input);
                }
                System.out.println(input);
            }
        } catch (FileNotFoundException e) {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File f = new File(filePath);
            System.out.println("No task in list");
        }
    return list;
   }


    /**
     * Saves task list to the file
     * @param list list to be saved
     */
    public void saveList(TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : list.getList()) {
                fw.write(t.toListString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
