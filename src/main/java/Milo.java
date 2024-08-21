import java.util.Scanner;
import TaskObj.Task;

public class Milo {
    private static final String hLine = "____________________________________________________________\n";

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
            String[] arrOfInput = userInput.split(" ");
            switch (arrOfInput[0]) {
                // show list
                case "list":
                    printList(todoList);
                    userInput = myScanner.nextLine();
                    break;
                // mark as complete
                case "mark":
                    Task curTask = todoList[Integer.parseInt(arrOfInput[1]) - 1];
                    curTask.mark();
                    printMark(curTask);
                    userInput = myScanner.nextLine();
                    break;
                // mark as incomplete
                case "unmark":
                    Task currTask = todoList[Integer.parseInt(arrOfInput[1]) - 1];
                    currTask.unmark();
                    printUnmark(currTask);
                    userInput = myScanner.nextLine();
                    break;
                default:
                    todoList[Task.taskNumber] = new Task(userInput);
                    System.out.println(hLine + "added: " + userInput + "\n" + hLine);
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
}
