import java.util.Scanner;
import java.util.ArrayList;

public class Bonnie {

    // Variables that are
    private static String username;
    private static ArrayList<String> tasklist = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello I'm Bonnie, what is your name?");
        Scanner scanner_obj = new Scanner(System.in);
        String my_username = scanner_obj.nextLine();
        System.out.println(String.format("Hey %s! Welcome to the Bonnie chat bot!\n", my_username));
        System.out.println("What would you like to do next?\n" +
                "1. bye : exits the chat bot\n" +
                "2. any other string : Bonnie adds that string into your task list\n");
        username = my_username;

        while (true) {
            Scanner repeated_scanner = new Scanner(System.in);
            String input = repeated_scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye " + username + "\n");
                return;
            } else if (input.equals("list")) {
                String list = "Your current tasks are\n";
                for (String task : tasklist) {
                    list += String.format("1. %s\n", task);
                }
                System.out.println(list);
            } else {
                // Want to add task to task_list
                tasklist.add(input);
                System.out.println(String.format("Hey %s, I have added \"%s\" into your task list!", username, input));
            }
        }
    }

}
