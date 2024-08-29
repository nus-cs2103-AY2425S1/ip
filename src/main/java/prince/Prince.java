package prince;

import exception.IncompleteDescException;
import exception.UnknownWordException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import task.Task;
import task.EventTask;
import task.DeadlinesTask;
import task.ToDoTask;
import ui.Ui;

import java.util.Scanner;

public class Prince {

    //private static final String dirPath = "./data";
    //private static final String filePath = dirPath + File.separator + "prince.txt";
    //prince.txt is a file that will automatically be created if it doesnt exist
    //stores the tasks

    private Storage storage;
    private Ui ui;
    private Parser parser;

    private TaskList taskList;

    public enum TaskType {
        todo,
        deadline,
        event
    }

    public Prince(String dirPath, String filePath) {
        storage = new Storage(dirPath, filePath);
        ui = new Ui();
        parser = new Parser();
        taskList = new TaskList(storage.loadTasksFromFile());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line = "";

        System.out.println("Hello! I'm prince.Prince!");
        System.out.println("What would you like me to add to your TODO list today?");


        while(!line.equals("bye")) {
            line = scanner.nextLine();
            try {
                if(line.equals("bye")) {
                    ui.terminationMessage();
                    break;
                }
                String s = parser.parseConversation(line);
                System.out.println(s);
                System.out.println("How else would you like me to edit your TODO list today?");
                Storage.pushTasksToFile(TaskList.getList());
            } catch (IncompleteDescException | UnknownWordException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
        scanner.close();
    }
    public static void main(String[] args) {
        new Prince("./data","data/prince.Prince.txt").run();

        //createListFile();
        //loadTasksFromFile(filePath);
        // if any words, repeat scanning, but the moment the word is bye,
        // then exit and print bye

        /*try {
            System.out.println(conversation(line));
        } catch (exception.IncompleteDescException e) {
            System.out.println(e.getMessage());
        } catch (exception.UnknownWordException e) {
            System.out.println(e.getMessage());
        }*/
    }
}

