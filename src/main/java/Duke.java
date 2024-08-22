import java.util.Scanner;
public class Duke {

    private static String username;
    public static void main(String[] args) {
        System.out.println("Hello I'm Bonnie, what is your name?");
        Scanner scanner_obj = new Scanner(System.in);
        String my_username = scanner_obj.nextLine();
        System.out.println(String.format("Hey %s! Welcome to the Bonnie chat bot!", my_username));
        username = my_username;

        while (true) {
            Scanner repeated_scanner = new Scanner(System.in);
            String input = repeated_scanner.nextLine();
            if (input.equals("bye")) {
                return;
            } else {
                System.out.println(String.format("Hey %s, I'm not sure what you meant by %s", username, input));
            }
        }
    }

}
