import java.util.Scanner;

public class Alice {
    public static void main(String[] args) {
        System.out.println("Hello! I am Alice! \nWhat can I do for you?");
        System.out.println("------------------------------------------");

        // get commands from the user while response is not "bye"
        String response = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            response = scanner.nextLine();
            if (response.equals("bye")) {
                break;
            }
            System.out.println("------------------------------------------");
            System.out.println(response);
            System.out.println("------------------------------------------");
        }
        System.out.println("------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
    }
}
