package Alpha;

import TaskTypes.Deadline;
import TaskTypes.Event;
import TaskTypes.ToDo;
import Utility.*;
import Exception.AlphaException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Alpha {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Alpha(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AlphaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public void run() {
        System.out.println("Run Executed!");
        Scanner scanner = new Scanner(System.in);
        String input = "";
    
        ui.welcomeMessage();
        
        while (true) {
            if (!scanner.hasNext()) {
                break;
            } else {
                input = scanner.nextLine();
            }
            
            String inputCommand = Parser.extractCommand(input);
            
            // Check if the user input is "bye"
            if (input.equalsIgnoreCase(Commands.BYE.getCommand())) {
                storage.synchronizeTasks(tasks.getTaskLists());
                ui.byeMessage();
                break;  // Exit the loop
            }
            
            else if (input.equalsIgnoreCase(Commands.LIST.getCommand())) {
                ui.listTask(tasks);
            }
            
            else if (inputCommand.equalsIgnoreCase(Commands.MARK.getCommand())) {
                try {
                    Integer indexInvolved = Integer.valueOf(input.split(" ")[1]);
                    String modifiedRecord = tasks.modifyOperation(indexInvolved, true);
                    String echoResponse = "____________________________________________________________\n"
                            + "Nice! I've marked this task as done:\n"
                            + modifiedRecord + "\n"
                            + "____________________________________________________________\n";
                    System.out.println(echoResponse);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Must specify which task to mark");
                }
            }
            
            else if (inputCommand.equalsIgnoreCase(Commands.UNMARK.getCommand())) {
                try {
                    Integer indexInvolved = Integer.valueOf(input.split(" ")[1]);
                    String modifiedRecord = tasks.modifyOperation(indexInvolved, false);
                    
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Must specify which task to unmark");
                }
            }
            
            else if (inputCommand.equalsIgnoreCase(Commands.DELETE.getCommand())) {
                try {
                    Integer indexInvolved = Integer.valueOf(input.split(" ")[1]);
                    String modifiedRecord = tasks.deleteOperation(indexInvolved);
                    String echoResponse = "____________________________________________________________\n"
                            + "Noted. I've removed this task:\n "
                            + modifiedRecord + "\n"
                            + tasks.getLength() + "\n"
                            + "____________________________________________________________\n";
                    System.out.println(echoResponse);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Must specify which task to delete");
                }
            }
            
            else if (inputCommand.equalsIgnoreCase(Commands.TODO.getCommand())) {
                try {
                    String[] splitArray = input.split(" ");
                    String processedInput = String.join(" ", Arrays.copyOfRange(splitArray, 1, splitArray.length));
                    ToDo NewToDo = new ToDo(processedInput);
                    tasks.storeTask(NewToDo);
                    ui.addTaskMessage(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Handle the case where the description is missing
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
            }
            
            else if (inputCommand.equalsIgnoreCase(Commands.DEADLINE.getCommand())) {
                try {
                    String[] splitArray = input.split(" ");
                    String processedInput = String.join(" ", Arrays.copyOfRange(splitArray, 1, splitArray.length));
                    
                    // Further processing to get the description before the "/"
                    String description = Parser.extractDescription(processedInput);
                    
                    //Further processing to get by date after the "/"
                    String by = Parser.extractFirstDate(input);
                    LocalDate byDate = LocalDate.parse(by);
                    
                    Deadline NewDeadline = new Deadline(description, byDate);
                    tasks.storeTask(NewDeadline);
                    ui.addTaskMessage(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //Handle invalid inputs
                    System.out.println("Deadline creation should be in this format:" +
                            " deadline <Description> /by <Deadline>");
                } catch (DateTimeParseException e) {
                    System.out.println("Date should be in format yyyy-mm-dd");
                }
            }
            
            else if (inputCommand.equalsIgnoreCase(Commands.EVENT.getCommand())) {
                try {
                    String[] splitArray = input.split(" ");
                    String processedInput = String.join(" ", Arrays.copyOfRange(splitArray, 1, splitArray.length));
                    
                    // Further processing to get the description before the "/"
                    String description = Parser.extractDescription(processedInput);
                    
                    //Further processing to get start date after the "/"
                    String start = Parser.extractFirstDate(input);
                    LocalDate startDate = LocalDate.parse(start);
                    
                    //Further processing to get start date after the "/"
                    String end = Parser.extractSecondDate(input);
                    LocalDate endDate = LocalDate.parse(end);
                    
                    Event NewEvent = new Event(description, startDate, endDate);
                    tasks.storeTask(NewEvent);
                    ui.addTaskMessage(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //Handle invalid inputs
                    System.out.println("Event creation should be in this format: event <Description> /from <Start> /to <End>");
                } catch (DateTimeParseException e) {
                    System.out.println("Command should be in format: event project meeting /from YYYY-MM-DD /to YYYY-MM-DD");
                }
            } else {
                System.out.println("Sorry User, command is not understood." +
                        " Alpha only accepts, todo, deadline, event, list, bye, mark and unmark commands");
            }
            
        }
        
        // Close the scanner after the loop ends
        scanner.close();
    }
    
    public static void main(String[] args) {
        new Alpha("./tasks_data.txt").run();
    }
}

