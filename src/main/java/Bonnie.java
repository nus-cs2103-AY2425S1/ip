import java.util.Scanner;
public class Bonnie {

    private static String username;
    public static void main(String[] args) {
        System.out.println("Hello I'm Bonnie, what is your name?");
        Scanner scanner_obj = new Scanner(System.in);
        String my_username = scanner_obj.nextLine();
        System.out.println(String.format("Hey %s! Welcome to the Bonnie chat bot!\n", my_username));
        System.out.println("What would you like to do next?\n" +
                "1. bye : exits the chat bot\n" +
                "2. any other string : Bonnie repeats that string\n");
        username = my_username;

        while (true) {
            Scanner repeated_scanner = new Scanner(System.in);
            String input = repeated_scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye " + username);
                return;
            } else {
                System.out.println(String.format("Hey %s, I'm not sure what you meant by %s", username, input));
            }
        }
    }

}
