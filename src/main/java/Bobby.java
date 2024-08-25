import java.util.Scanner;
public class Bobby {
    public static String greeting = "Hello! I'm Bobby\n"
            + "What can I do for you?\n";
    public static String exit = "Bye. Hope to see you again soon!\n";
    public static Scanner scan = new Scanner(System.in);
    public static String[] list = new String[100];
    public static int counter = 1;

    /*create an add_list function that takes in a
    String input to add it to the list of items.
     */
    public static void add_list(Task input) {
        input.ranking = counter;
        list[counter - 1] = counter + ". [" + input.getStatusIcon() + " ] " + input.description;
        counter++;
    }

    /* create a print_list function that takes in a
    list to print each element in a new line
     */
    public static void print_list(String[] list) {
        for (int x = 0; list[x] != null; x++) {
            System.out.println(list[x] + "\n");
        }
    }

    /* create a checker function that takes in a
     String input to echo everytime there is an
     input from user. Chatbot will only return an
     exit signal when user reply bye.
     */
    public static void checker(Task input) {
        if (input.description.equals("bye")) {
            System.out.println(exit);
            return;
        } if (input.description.equals("list")) {
            print_list(list);
            Task t = new Task(scan.nextLine());
            checker(t);
        } else {
            System.out.println("added: " + input.description);
            add_list(input);
            Task t = new Task(scan.nextLine());
            checker(t);
        }
    }
    public static void main(String[] args) {
        System.out.println(greeting);
        Task t = new Task(scan.nextLine());
        checker(t);
    }
}
