package bmo;
import java.util.Scanner;

import bmo.task.Task;

public class BMO {

    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private static String FILEPATH = "ip/data/BMO.txt";

    public BMO(String filePath) throws Exception {
        try {
            this.storage = new Storage(filePath);
            this.ui = new Ui();
            this.parser = new Parser();
            this.taskList = new TaskList();
            FILEPATH = filePath;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Logic loop to run chatbot
    public void run() throws Exception {
        storage.readStorageFile(taskList, FILEPATH);
        ui.printWelcome();
        Scanner sc = new Scanner(System.in);

        loop:
        while (true) {
            String userInput = sc.nextLine();
            String[] input = parser.parse(userInput);
        
        //Switch statement to handle different commands
        switch (input[0]) {
        
            //Lists all the tasks in the list
            case "list":
                ui.printList(taskList.getTasks());
                break;

            //Marks a task as completed
            case "mark":
                //Parses the second word as the index of the task to mark
                int index = Integer.parseInt(input[1]) - 1;

                if (index >= taskList.getSize() || index < 0) {
                    throw new BMOException("\nOh no! The task number you specified does not exist!");
                }
                taskList.markTask(index);
                storage.updateStorageFile(taskList);
                ui.printTaskMarked(taskList.getTask(index));
                break;

            //Unmarks a completed task as incomplete
            case "unmark":
                //Parses the second word as the index of the task to unmark
                int index2 = Integer.parseInt(input[1]) - 1;

                if (index2 >= taskList.getSize() || index2 < 0) {
                    throw new BMOException("\nOh no! The task number you specified does not exist!");
                }
                taskList.unmarkTask(index2);
                storage.updateStorageFile(taskList);
                ui.printTaskUnmarked(taskList.getTask(index2));
                break;

            //Adds a todo task (no deadline) to the list 
            case "todo":
                taskList.addTodo(input[1]);
                storage.saveTask(taskList.getTask(taskList.getSize() - 1));
                ui.printTaskAdded(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
                break;

            //Adds a deadline task (with deadline) to the list
            case "deadline":
                taskList.addDeadline(input[1], input[2]);
                storage.saveTask(taskList.getTask(taskList.getSize() - 1));
                ui.printTaskAdded(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
                break;

            //Adds an event task (with start and end timings) to the list
            case "event":
                //Splits the description into task description and event timings
                taskList.addEvent(input[1], input[2], input[3]);
                storage.saveTask(taskList.getTask(taskList.getSize() - 1));
                ui.printTaskAdded(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
                break;

            //Deletes a task from the list
            case "delete":
                //Parses the second word as the index of the task to delete
                int index3 = Integer.parseInt(input[1]) - 1;

                if (index3 >= taskList.getSize() || index3 < 0) {
                    throw new BMOException("\nOh no! The task number you specified does not exist!");
                }
                Task taskToDelete = taskList.getTask(index3);
                taskList.deleteTask(index3);
                storage.updateStorageFile(taskList);
                ui.printTaskRemoved(taskToDelete, taskList.getSize());
                break;

            case "bye":
                ui.printGoodbye();
                sc.close();
                storage.closeWriter();
                break loop;
            //Catches unknown commands
            default:
                throw new BMOException("\nOh no! I do not recognise that command, please try again!");
            }
        }
    }

    public static void main(String[] args) { 
        try {  
            new BMO(FILEPATH).run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
