import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Lutchat {
    ArrayList<Task> taskList = new ArrayList<>();
    String userInput;

    void greet() {
        System.out.print("______________________________________________\n");
        System.out.print("Hello! I'm Lutchat!\n");
        System.out.print("What can I do for you?\n");
        System.out.print("______________________________________________\n");
    }

    void exit() {
        System.out.print("Bye! Hope to see you again soon!\n");
        System.out.print("______________________________________________\n");
    }

    boolean conversation(String userInput) {
        String[] userInputArr = userInput.split(" ");
        String keyword = userInputArr[0];

        switch (keyword) {
            case "bye":
                return false;
            case "list":
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print( (i+1) + "." + taskList.get(i).toString() + "\n");
                }
                System.out.print("______________________________________________\n");
                return true;
            case "mark":
                int itemNum1 = Integer.valueOf(userInputArr[1]) - 1;
                taskList.get(itemNum1).markAsDone();
                System.out.print("Nice! I've marked this task as done:\n");
                System.out.print(taskList.get(itemNum1).toString() + "\n");
                System.out.print("______________________________________________\n");
                return true;
            case "unmark":
                int itemNum2 = Integer.valueOf(userInputArr[1]) - 1;
                taskList.get(itemNum2).markAsUndone();
                System.out.print("OK, I've marked this task as not done yet:\n");
                System.out.print(taskList.get(itemNum2).toString() + "\n");
                System.out.print("______________________________________________\n");
                return true;
            default:
                taskList.add(new Task(userInput));
                System.out.print("added: " + userInput + "\n");
                System.out.print("______________________________________________\n");
                return true;
        }
    }

    public static void main(String[] args) {
        Lutchat lutchat = new Lutchat();
        Scanner sc = new Scanner(System.in);

        lutchat.greet();

        lutchat.userInput = sc.nextLine();

        while (lutchat.conversation(lutchat.userInput)) {
            lutchat.userInput = sc.nextLine();
        }

        lutchat.exit();
    }
}
