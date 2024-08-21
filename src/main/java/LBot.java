import task.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class LBot {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        String greeting = "Hello! I'm LBot, your dedicated personal assistant ;)\nWhat can I do for you?\nFor commands, type $help.";
        String exitMsg = "Bye. Hope to smell you again!";
        // Initialise Scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting);
        String userInput = "";
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (true) {
            userInput = scanner.nextLine();
            // current assumption: each input has a command
            /*
                Commands:
                1. Add task
                    1. To do $t
                    2. Event $e
                    3. Deadline $d
                    4. Delete $del
                2. List tasks $l
                3. Mark tasks as complete $m
             */
            String command = userInput.split("\\s+")[0]; // split by space
            switch (command) {
                case "$bye":
                    System.out.println(exitMsg);
                    scanner.close();
                    System.exit(0);
                    break;
                case "$t":
                    try {
                        // get details of task, remove command
                        String taskDescription = userInput.substring(command.length() + 1);
                        Task todo = new Todo(taskDescription);
                        taskList.add(todo);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please specify your task.");
                    }
                    break;
                case "$d":
                    try {
                        // remove command
                        String taskDescription = userInput.substring(command.length() + 1);
                        // split description and date
                        int dateIndex = taskDescription.lastIndexOf('$');
                        String task = taskDescription.substring(0, dateIndex - 1);
                        String dueDate = taskDescription.substring(dateIndex + 1);
                        Task deadline = new Deadline(task, dueDate);
                        taskList.add(deadline);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please check that your command is in the correct format. Type $help for information :)");
                    }
                    break;
                case "$e":
                    try {
                        // remove command
                        String taskDescription = userInput.substring(command.length() + 1);
                        // split description and dates
                        int endEventIndex = taskDescription.lastIndexOf('$');
                        String endEvent = taskDescription.substring(endEventIndex + 1).trim();
                        taskDescription = taskDescription.substring(0, endEventIndex - 1).trim();
                        int startEventIndex = taskDescription.lastIndexOf('$');
                        String startEvent = taskDescription.substring(startEventIndex + 1).trim();
                        taskDescription = taskDescription.substring(0, startEventIndex - 1).trim();
                        Task event = new Event(taskDescription, startEvent, endEvent);
                        taskList.add(event);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please check that your command is in the correct format. Type $help for information :)");
                    }
                    break;
                case "$l":
                    if (taskList.isEmpty()) {
                        System.out.println("No tasks found");
                        break;
                    }
                    System.out.println("Task List:");
                    for (int i = 1; i < taskList.size() + 1; i++) {
                        System.out.println("\t" + i + ": " + taskList.get(i - 1));
                    }
                    break;
                case "$m":
                    try {
                        int taskNo = Integer.parseInt(userInput.substring(command.length() + 1)) - 1;
                        taskList.get(taskNo).setComplete(true);
                        System.out.println("Successfully marked task: " + taskList.get(taskNo));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Task does not exist");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Specify a task to mark.");
                    }
                    break;
                case "$del":
                    try {
                        int taskNo = Integer.parseInt(userInput.substring(command.length() + 1)) - 1;
                        Task task = taskList.get(taskNo);
                        taskList.remove(taskNo);
                        task.deleteTask();
                        System.out.println("Successfully marked task: " + task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Task does not exist!");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Specify a task to delete.");
                    }
                    break;
                case "$help":
                    System.out.println("""
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
                            $bye - Say bye to LBot
                            """);
                    break;
                default:
                    System.out.println("Unknown command. Please check your input.");
            }
        }
    }

}
