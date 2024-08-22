import java.util.Objects;
import java.util.Scanner;

public class BuddyBot {
    public static void main(String[] args) {
        //Scanner object
        Scanner myObj = new Scanner(System.in);
        //Opening statement
        System.out.println("__________________________");
        System.out.println(" Hello! I'm BuddyBot");
        System.out.println(" What can I do for you?");

        Task[] myList = new Task[100];
        //taking in user input
        String input = myObj.nextLine();

        for (int i = 0; i < 100; i++) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) { //LIST command
                System.out.println("Here are the tasks in your list:");
                read(myList);
                i--;
                input = myObj.nextLine();
            } else if (input.startsWith("mark")) { //MARK command
                String last = input.substring(input.replaceAll("[0-9]+$","").length());
                try {
                    int num = Integer.parseInt(last);
                    myList[num - 1].isDone = true;
                    System.out.println("Nice! I've marked this task as done:");
                    read(myList);
                } catch (NumberFormatException e) {
                    System.out.println("This is an invalid input!");
                } finally {
                    i--;
                    input = myObj.nextLine();
                }
            } else {
                myList[i] = new Task(input);
                System.out.println("added: " + input);
                input = myObj.nextLine();
            }
        }
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void read(Task[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            } else {
                System.out.println(i+ 1 + ".[" + arr[i].getStatusIcon() + "] "  + arr[i].description + "\n");
            }
        }
    }
}
