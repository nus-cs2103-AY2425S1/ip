import task.TaskList;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private final Scanner scanner;
    private final TaskList taskList;

    public Parser(TaskList taskList, Scanner scanner) {
        this.scanner = scanner;
        this.taskList = taskList;
    }

    public void handleInput(String input) {
        while(!input.equalsIgnoreCase("bye")) {
            // display task list
            input = input.toLowerCase();
            if(input.equals("list")) {
                taskList.printTasks();
                input = scanner.nextLine();
                continue;
            }

            // mark tasks as done
            if (input.startsWith("mark ")) {
                char[] charArray = input.toCharArray();
                int taskNumber = Character.getNumericValue(charArray[charArray.length - 1]) - 1;
                taskList.markTask(taskNumber);
                input = scanner.nextLine();
                continue;
            }

            //marks tasks as undone
            if (input.toLowerCase().startsWith("unmark ")) {
                char[] charArray = input.toCharArray();
                int taskNumber = Character.getNumericValue(charArray[charArray.length - 1]) - 1;
                taskList.unmarkTask(taskNumber);
                input = scanner.nextLine();
                continue;
            }

            // delete tasks
            if(input.startsWith("delete ")) {
                String descrip = input.substring(7).trim();
                int tempInt;
                try {
                    tempInt = Integer.parseInt(descrip);
                } catch (Exception NumberFormatException) {
                    System.out.println("You can only delete a task number! No one calls amendments by their names!!");
                    input = scanner.nextLine();
                    continue;
                }

                System.out.println(tempInt);
                taskList.deleteTask(tempInt - 1);
                input = scanner.nextLine();
                continue;

            }

            // add tasks to tasklist
            if(input.startsWith("todo ") || input.startsWith("event ") || input.startsWith("deadline ")) {
                try {
                    taskList.addTask(input);
                    input = scanner.nextLine();
                    continue;
                } catch (DateTimeParseException e) {
                    System.out.println("Error while parsing date - format in yyyy-MM-dd");
                    continue;
                }
            }

            // should never reach here unless command is invalid
            System.out.println("HEY! SLEEPY JOE and CROOKED KAMALA " +
                    "might be demented but you're not! Specify a command!");
            input = scanner.nextLine();
        }
    }
}
