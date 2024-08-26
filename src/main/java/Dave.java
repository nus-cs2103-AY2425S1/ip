import java.util.Scanner;

public class Dave {

    public static void main(String[] args) {
        String logo = " ____    _    __     ______\n"
                + "|  _ \\  / \\   \\ \\   / / ___|\n"
                + "| | | |/ _ \\   \\ \\ / /|  _|\n"
                + "| |_| / ___ \\   \\ V / | |___\n"
                + "|____/_/   \\_\\   \\_/  |_____|\n";
        String horizontal = "__________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal);
        System.out.println("Hello! I'm Dave.");
        System.out.println("What can I do for you?");
        System.out.println(horizontal);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true)
        {
            userInput = scanner.nextLine();
            if (userInput.trim().equals("bye"))
            {
                break;
            }
            else
            {
                System.out.println(horizontal);
                System.out.println(userInput);
                System.out.println(horizontal);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal);
        scanner.close();

    }
}
