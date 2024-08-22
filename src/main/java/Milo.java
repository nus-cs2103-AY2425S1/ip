import java.util.Scanner;

import TaskObj.Deadlines;
import TaskObj.Events;
import TaskObj.Task;
import TaskObj.Todos;

public class Milo {
    private static final String hLine = "____________________________________________________________\n";

    private static enum taskType {
        TODO,
        EVENT,
        DEADLINE,
        INVALID
    }

    public static void main(String[] args) {
        String cat0 = """
                  ╱|、
                (˚ˎ 。7 \s
                 |、˜〵         \s
                 じしˍ,)ノ
                """;
        String cat1 = """
                 ∧,,,∧
                ( ̳• · •̳)
                /    づ♡
                """;
        String greeting = "Hello! I'm Milo\nWhat can I do for you?\n" + cat0;
        String bye = "Bye. Hope to see you again soon!\n" + cat1;
        Scanner myScanner = new Scanner(System.in);
        String greetingMessage = hLine + greeting + hLine;
        String byeMessage = hLine + bye + hLine;
        // Database to store text
        // Assumption no more than 100 tasks
        Task[] todoList = new Task[100];

        // Greets user
        System.out.println(greetingMessage);
        // Await user input
        String userInput = myScanner.nextLine();
        // Loop for user input
        while (!userInput.toLowerCase().strip().equals("bye")) {
            // Split input on space
            String[] arrOfInput = userInput.split(" ", 2);
            String action = arrOfInput[0];
            switch (action) {
                // Show list
                case "list":
                    printList(todoList);
                    userInput = myScanner.nextLine();
                    break;
                // Mark as complete
                case "mark":
                    Task curTask = todoList[Integer.parseInt(arrOfInput[1]) - 1];
                    curTask.mark();
                    printMark(curTask);
                    userInput = myScanner.nextLine();
                    break;
                // Mark as incomplete
                case "unmark":
                    Task currTask = todoList[Integer.parseInt(arrOfInput[1]) - 1];
                    currTask.unmark();
                    printUnmark(currTask);
                    userInput = myScanner.nextLine();
                    break;
                // Adding tasks
                // Todos
                case "todo":
                    // Check case where todos empty
                    if (arrOfInput.length == 1) {
                        printError(taskType.TODO, "The description of a todo cannot be empty");
                    } else {
                        String desc = arrOfInput[1];
                        Task curTodo = new Todos(desc);
                        todoList[Task.taskNumber - 1] = curTodo;
                        printTask(curTodo);
                    }
                    userInput = myScanner.nextLine();
                    break;
                // Deadline
                case "deadline":
                    // Check case where deadline empty
                    if (arrOfInput.length == 1) {
                        printError(taskType.DEADLINE, "The description of a deadline cannot be empty");
                    } else {
                        // Check case where deadline command is not properly formatted
                        String[] deadlineDesc = arrOfInput[1].split("/by", 2);
                        if (deadlineDesc.length != 2) {
                            printError(taskType.DEADLINE, "Invalid deadline command\n Proper formatting: deadline <task description> + /by + <date description>");
                        } else {
                            Task curDeadline = new Deadlines(deadlineDesc[0], deadlineDesc[1]);
                            todoList[Task.taskNumber - 1] = curDeadline;
                            printTask(curDeadline);
                        }
                    }
                    userInput = myScanner.nextLine();
                    break;
                // Event
                case "event":
                    // Check case where event empty
                    if (arrOfInput.length == 1) {
                        printError(taskType.EVENT, "The description of an event cannot be empty");
                    } else {
                        String[] eventDesc = arrOfInput[1].split("/from | /to", 3);
                        if (eventDesc.length != 3) {
                            printError(taskType.DEADLINE, "Invalid event command\n Proper formatting: deadline <task description> + /from + <starting date description> + /to + <ending date description");
                        } else {
                            Task curEvent = new Events(eventDesc[0], eventDesc[1], eventDesc[2]);
                            todoList[Task.taskNumber - 1] = curEvent;
                            printTask(curEvent);
                        }
                    }
                    userInput = myScanner.nextLine();
                    break;
                default:
                    printError(taskType.INVALID, "");
                    userInput = myScanner.nextLine();
            }
        }

        // User input == "bye"
        System.out.print(byeMessage);
    }

    // Method to print todolist
    private static void printList(Task[] todoList) {
        System.out.print(hLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.taskNumber; i++) {
            System.out.println(i+1 + "." + todoList[i].toString());
        }
        System.out.println(hLine);
    }

    private static void printMark(Task curTask) {
        System.out.print(hLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + curTask.toString());
        System.out.print(hLine);
    }

    private static void printUnmark(Task curTask) {
        System.out.print(hLine);
        System.out.println("Ok, I've marked this as not done yet:");
        System.out.println("  " + curTask.toString());
        System.out.print(hLine);
    }

    private static void printTask(Task curTask) {
        System.out.print(hLine);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + curTask.toString());
        int curTaskNumber = Task.taskNumber;
        if (curTaskNumber == 1) {
            System.out.println("Now you have " + curTaskNumber + " task in the list.");
        } else {
            System.out.println("Now you have " + curTaskNumber + " tasks in the list.");
        }
        System.out.print(hLine);
    }

    private static void printError(taskType tasktype, String desc) {
        String oops = "OOPS!!! ";
        switch (tasktype) {
            case TODO:
                System.out.println(hLine + oops + desc + "\n" + hLine);
                break;
            case DEADLINE:
                System.out.println(hLine + oops + desc + "\n" + hLine);
                break;
            case EVENT:
                System.out.println(hLine + oops + desc + "\n" + hLine);
                break;
            case INVALID:
                String invalidMessage = oops + "I'm sorry, but I don't know what that means ;-;\n";
                System.out.println(hLine + invalidMessage + hLine);
                break;
            default:
                String defaultMessage = oops + "I'm sorry, but I don't know what that means ;-;\n";
                System.out.println(hLine + defaultMessage + hLine);
        }
    }
}
