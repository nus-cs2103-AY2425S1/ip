import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import TaskObj.Task;

public class Bob {
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
        String greeting = "Hello! I'm Bob\nWhat can I do for you?\n" + cat0;
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
            switch (userInput) {
                case "list":
                    printList(todoList);
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
        for (int i = 0; i < Task.taskNumber; i++) {
            System.out.println(i+1 + "." + todoList[i].toString());
        }
        System.out.println(hLine);
    }
}
