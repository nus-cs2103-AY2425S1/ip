import java.io.IOException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.time.DateTimeException;
import java.util.*;
import java.util.ArrayList;
import java.io.FileWriter;
import static java.lang.System.exit;
import java.util.regex.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Momo {

    private static final String FILE_PATH = "data/momo.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Momo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {

        ui.showGreeting();
        String input = ui.getUserInput().trim();

        while (true) {
            try {
                CommandType command = Parser.parseInput(input);
                if (command == CommandType.BYE) {
                    ui.showFarewell();
                }
                else if (command == CommandType.LIST) {
                    ui.printList(tasks.getTaskList());
                }
                else if (command == CommandType.MARK) {
                    changeCompletionCommand(Integer.parseInt(input.split(" ")[1]) - 1,  CommandType.MARK);
                }
                else if (command == CommandType.UNMARK) {
                    changeCompletionCommand(Integer.parseInt(input.split(" ")[1]) - 1, CommandType.UNMARK);
                }
                else if (command == CommandType.DELETE) {
                    deleteTaskCommand(Integer.parseInt(input.split(" ")[1]) - 1);
                }
                else {
                    if (command == CommandType.TODO) {
                        addToDoCommand(input);
                    }
                    else if (command == CommandType.DEADLINE) {
                        addDeadlineCommand(input);
                    }

                    else if (command == CommandType.EVENT) {
                        addEventCommand(input);
                    }

                    ui.printTaskAdded(tasks.getTaskList());

                }

            }
            catch (MomoException e){
                System.out.println(e.getMessage());
            }
            input = ui.getUserInput();
        }
    }

    public void changeCompletionCommand(int index, CommandType type) {
        if (index >= tasks.getCount() || index < 0) {
            System.out.println("You can only mark/unmark a number your task list contains");
            return;
        }

        tasks.changeCompletion(index, type);

        try {
            storage.RewriteTasksToFile(FILE_PATH, tasks.getTaskList());
        } catch (IOException e) {
            System.out.println("Tasks not rewritten successfully into file successfully:" + e.getMessage());
        }
    }

    public void deleteTaskCommand(int index) {
        if (index >= tasks.getCount() || index < 0) {
            System.out.println("You can only delete a number your task list contains");
            return;
        }

        ui.printDeletedTask(tasks.getTaskList().get(index).toString(), tasks.getCount() - 1);

        tasks.deleteTask(index);

        try {
            storage.RewriteTasksToFile(FILE_PATH, tasks.getTaskList());
        } catch (IOException e) {
            System.out.println("Tasks not rewritten successfully into file successfully:" + e.getMessage());
        }
    }

    public void addToDoCommand(String input) {
        String desc = input.split(" ",2)[1].trim();
        tasks.addTodo(desc);

        try {
            storage.addTaskToFile(FILE_PATH, new Todo(desc, false).toFileString());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void addDeadlineCommand(String input) {
        String desc = input.split(" ",2)[1];
        String task =  desc.split("/",2)[0].trim();
        String by = desc.split("/by",2)[1].trim();

        try {
            LocalDate dateBy = LocalDate.parse(by);
            tasks.addDeadline(task, dateBy);
            storage.addTaskToFile(FILE_PATH, new Deadline(task, dateBy, false).toFileString());
        } catch (DateTimeException de) {
            System.out.println("Please format your dates in yyyy-mm-dd with valid numbers: " + de.getMessage());
            return;
        } catch (IOException ioe) {
            System.out.println("Something went wrong: " + ioe.getMessage());
        }
    }

    public void addEventCommand(String input) {
        String desc = input.split(" ",2)[1];
        String task =  desc.split("/",2)[0].trim();
        String from = desc.split("/from")[1].split("/to")[0].trim();
        String to = desc.split("/to")[1].trim();

        try {
            LocalDate dateFrom = LocalDate.parse(from);
            LocalDate dateTo = LocalDate.parse(to);
            tasks.addEvent(task, dateFrom, dateTo);
            storage.addTaskToFile(FILE_PATH, new Event(task, dateFrom, dateTo, false).toFileString());
        } catch (DateTimeException de) {
            System.out.println("Please format your dates in yyyy-mm-dd with valid numbers: " + de.getMessage());
            return;
        } catch (IOException ioe) {
            System.out.println("Something went wrong: " + ioe.getMessage());
        }

    }

    public static void main(String[] args) throws MomoException {
        new Momo(FILE_PATH).run();
    }


}










