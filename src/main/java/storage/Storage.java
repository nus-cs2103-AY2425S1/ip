package storage;

import task.*;
import parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {

    private File textFile;
    private File directoryFile;

    public Storage(String filePath) {
        this.directoryFile = new File("data");
        this.textFile = new File(filePath);
        try {
            if (!directoryFile.exists()) {
                //created new data path
                directoryFile.mkdirs();
            }

            if (textFile.createNewFile()) {
                //created new Tako.txt file
            }
        } catch (IOException e) {
            System.out.println("Error in finding a file");
        }
    }

    public void load() {
        try {
            Scanner scanner = new Scanner(textFile);
            while (scanner.hasNext()) {
                String task = scanner.nextLine();
                char typeOfTask = task.charAt(1);
                char isTaskDone = task.charAt(4);
                if (typeOfTask == 'T') {
                    loadTodoTask(task, isTaskDone);
                }
                if (typeOfTask == 'D') {
                    loadDeadlineTask(task, isTaskDone);
                }
                if (typeOfTask == 'E') {
                    loadEventTask(task, isTaskDone);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public static void store(String tasks) {
        try {
            FileWriter fileWriter = new FileWriter("./data/Tako.txt");
            fileWriter.write(tasks);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("File does not exists");
        }
    }

    public void loadTodoTask(String task, char isTaskDone) {
        String description = task.substring(7);
        TaskList.addTaskLoad(new ToDo(description));
        if (isTaskDone == 'X') {
            TaskList.markTaskLoad(TaskList.length() - 1);
        }
    }

    public void loadDeadlineTask(String task, char isTaskDone) {
        int byPosition = task.indexOf("(by:");
        String description = task.substring(7, byPosition);
        String by = task.substring(byPosition + 5, task.length() - 1);
        TaskList.addTaskLoad(new Deadline(description, Parser.dateToLocalDate(by)));
        if (isTaskDone == 'X') {
            TaskList.markTaskLoad(TaskList.length() - 1);
        }
    }

    public void loadEventTask(String task, char isTaskDone) {
        int fromPosition = task.indexOf("(from:");
        int toPosition = task.indexOf("to:");
        String description = task.substring(7, fromPosition);
        String from = task.substring(fromPosition + 7, toPosition - 1);
        String to = task.substring(toPosition + 4, task.length() - 1);
        TaskList.addTaskLoad(new Event(description, Parser.dateToLocalDate(from), Parser.dateToLocalDate(to)));
        if (isTaskDone == 'X') {
            TaskList.markTaskLoad(TaskList.length() - 1);
        }
    }
}
