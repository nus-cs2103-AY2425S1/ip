import java.util.Scanner;  // Import the Scanner class

public class Ai {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        String greetings = "Hi, I'm your favourite idol, Ai!!! \n"
                        + "What shall we do today? Teehee o(◠u◠)o \n";

        String closing = "Don't you wanna get my autograph first? \n"
                        + "Aww okie :,( See ya!! \n";

        String line = "____________________________________________________________ \n";



        System.out.println(line + greetings + line);

        String reply = scanner.nextLine();  // Read user input
        System.out.println(line);

        while (!reply.equals("bye")) {
            System.out.println(reply + "\n" + line);
            reply = scanner.nextLine();  // Read user input
            System.out.println(line);
        }
        System.out.println(closing + line);
    }
}
