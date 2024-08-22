import java.util.Scanner;
public class Yap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "_    _  __     ______\n"
                    + " \\  // //\\    ||__| |\n"
                    + "  \\// //__\\   ||____/\n"
                    + "  || //____\\  ||\n"
                    + "  ||//      \\ ||\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Here are some things I can do for you: ");

        System.out.println("What would you like me to do for you? ");
        System.out.println("_____________________________________");
        System.out.println("Bye! It was really nice talking to you, see you soon :)");
//        String userInput;
//
//        while (true) {
//            userInput = scanner.nextLine();
//            if (userInput.equalsIgnoreCase("bye")) {
//                System.out.println("Bye! It was really nice talking to you, see you soon :)");
//                break;
//            }
//        }
    }
}
