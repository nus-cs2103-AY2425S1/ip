import java.util.Scanner;

public class Schedulo {
    public static void main(String[] args) {
        String horizontalLine = "---------------";
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        System.out.println(horizontalLine);
        System.out.println("Hello I am Schedulo!");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
        while (true) {
            String command = sc.nextLine();
            System.out.println(horizontalLine);
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            }

            taskList.executeCommand(command);
            
            System.out.println(horizontalLine);
        }

        sc.close();
    }
}

// Todo
// testing
// add enums