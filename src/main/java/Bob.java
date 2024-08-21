import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm your chatbot, Bob.\n" +
                "How may I assist you?");
        System.out.println("____________________________________________________________");

        Scanner reader = new Scanner(System.in);
        String response;


        while (!(response = readUserInput(reader)).equals("bye")) {
            System.out.println("____________________________________________________________");
           System.out.println(response);
            System.out.println("____________________________________________________________");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye.");
        System.out.println("____________________________________________________________");
    }

    private static String readUserInput(Scanner s) {
        System.out.print("> ");
        return s.next();
    }
}
