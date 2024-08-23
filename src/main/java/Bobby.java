import java.util.Scanner;
public class Bobby {
    public static String greeting = "Hello! I'm Bobby\n"
            + "What can I do for you?\n";
    public static String exit = "Bye. Hope to see you again soon!\n";

    public static Scanner scan = new Scanner(System.in);

    /* create a checker function that takes in a
     String input to echo everytime there is an
     input from user. Chatbot will only return an
     exit signal when user reply bye.
     */
    public static void checker(String input) {
        if (input.equals("bye")) {
            System.out.println(exit);
        } else {
            System.out.println(input);
            checker(scan.next());
        }
    }
    public static void main(String[] args) {
        System.out.println(greeting);
        checker(scan.next());
    }
}
