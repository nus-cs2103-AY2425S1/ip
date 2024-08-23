import exception.*;
import task.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class LBot {
    static PrintFormatter printFormatter = new PrintFormatter();

    public static void main(String[] args) {
        String greeting = "Hello! I'm LBot, your dedicated personal assistant ;)\nWhat can I do for you?\nFor commands, type $help.";
        // Initialise Scanner object
        Scanner scanner = new Scanner(System.in);
        PrintFormatter.print(greeting);
        String userInput = "";
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (true) {
            try {
                userInput = scanner.nextLine();
                if (userInput.isEmpty()) {
                    // TODO Update Exception Strings to Enums, need to figure out where to place the Enum
                    throw new IncorrectArgumentException("Please enter a valid command");
                }
                parseCommand(userInput, taskList);
            } catch (LBotException e) {
                printFormatter.printException(e.getMessage());
            }
        }
    }

    /**
     * Separate user input into command and arguments
     *
     * @param input
     * @throws LBotException
     */
    static void parseCommand(String input, ArrayList<Task> taskList) throws LBotException {
        // gets command
        Dictionary<String, String> dict = new Hashtable<>();
        String command = input.split(" ")[0];
        String taskDescription = "";
        switch (command) {
            case "$t":
                try {
                    taskDescription = input.substring(command.length() + 1).trim();
                    if (taskDescription.isEmpty()) {
                        throw new InvalidCommandException("Seems like you entered an incomplete command!");
                    }
                    Task todo = new Todo(taskDescription);
                    taskList.add(todo);
                    PrintFormatter.printAdded(todo);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException("Seems like you entered an incomplete command!");
                }
                break;
            case "$d":
                try {
                    taskDescription = input.substring(command.length() + 1).trim();
                    if (taskDescription.isEmpty()) {
                        throw new InvalidCommandException("Seems like you entered an incomplete command!");
                    }
                    dict = parseDate(taskDescription);
                    taskDescription = dict.get("input");
                    String dueDate = dict.get("date");
                    Task deadline = new Deadline(taskDescription, dueDate);
                    taskList.add(deadline);
                    printFormatter.printAdded(deadline);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException("Seems like you entered an incomplete command!");
                }
                break;
            case "$e":
                try {
                    taskDescription = input.substring(command.length() + 1).trim();
                    if (taskDescription.isEmpty() | taskDescription.split("\\$").length < 2) {
                        throw new InvalidCommandException("Seems like you entered an incomplete command!");
                    }
                    dict = parseDate(taskDescription);
                    taskDescription = dict.get("input");
                    String endDate = dict.get("date");
                    dict = parseDate(taskDescription);
                    taskDescription = dict.get("input");
                    String startDate = dict.get("date");
                    Task event = new Event(taskDescription, startDate, endDate);
                    taskList.add(event);
                    printFormatter.printAdded(event);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException("Seems like you entered an incomplete command!");
                }
                break;
            case "$l":
                if (taskList.isEmpty()) {
                    throw new ParseCommandException("No tasks added yet.");
                }
                printFormatter.printTaskList(taskList);
                break;
            case "$m":
                if (taskList.isEmpty()) {
                    throw new ParseCommandException("No tasks added yet.");
                }
                try {
                    int taskNo = Integer.parseInt(input.substring(command.length() + 1)) - 1;
                    taskList.get(taskNo).setComplete(true);
                    printFormatter.printCompleted(taskList.get(taskNo));
                } catch (Exception e) {
                    throw new IncorrectArgumentException("Please enter a valid task number!");
                }
                break;
            case "$del":
                if (taskList.isEmpty()) {
                    throw new ParseCommandException("No tasks added yet.");
                }
                try {
                    int taskNo = Integer.parseInt(input.substring(command.length() + 1)) - 1;
                    Task task = taskList.get(taskNo);
                    taskList.remove(task);
                    task.deleteTask();
                    printFormatter.printDeleted(task);
                } catch (Exception e) {
                    throw new IncorrectArgumentException("Please enter a valid task number!");
                }
                break;
            case "$help":
                printFormatter.print("""
                        Welcome to LBot. Here are the commands supported!
                        $t (description) - Add a todo
                            $t Create a Todo
                        $d (description) $(Date)- Add a deadline
                            $d Finish LBot $20/08/2024
                        $e (description) $(start) $(end) - Add an event
                            $e Go Shopping $3pm $5pm
                        $del (task no.) - Deletes a task
                            $del 1
                        $l - Lists all tasks
                            $l
                        $m (task no.) - Mark a task as complete
                            $m 1
                        $help - Shows the help page
                        $bye - Say bye to LBot""");
                break;
            case "$bye":
                printFormatter.print("Bye. Hope to smell you again!");
                System.exit(0);
                break;
            default:
                throw new InvalidCommandException("Command not found. Type $help for more information.");
        }
    }

    static Dictionary<String, String> parseDate(String input) throws LBotException {
        Dictionary<String, String> dict = new Hashtable<>();
        int dateIndex = input.lastIndexOf("$");
        // +1 to start after the $
        String date = input.substring(dateIndex + 1).trim();
        input = input.substring(0, dateIndex);
        dict.put("date", date);
        dict.put("input", input);
        return dict;
    }

}

