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

        String[] myList = new String[100];
        //taking in user input
        String input = myObj.nextLine();

        for (int i = 0; i < 100; i++) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                read(myList);
                i--;
                input = myObj.nextLine();
            } else {
                myList[i] = input;
                System.out.println("added: " + input);
                input = myObj.nextLine();
            }
        }
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void read(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            } else {
                System.out.println(i+ 1 + ". " + arr[i] + "\n");
            }
        }
    }
}
