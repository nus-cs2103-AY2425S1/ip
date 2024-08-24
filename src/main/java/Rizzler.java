import java.util.Scanner;

public class Rizzler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String LINE_BREAK = "\t#############################";

        // greet user
        System.out.println(LINE_BREAK);
        System.out.println("\they. i'm the rizzler.");
        System.out.println("\twhat do you want");
        System.out.println(LINE_BREAK);

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(LINE_BREAK);
            System.out.println("\t" + userInput);
            System.out.println(LINE_BREAK);
            userInput = scanner.nextLine();
        }
        scanner.close();

        // bid farewell to user
        System.out.println(LINE_BREAK);
        System.out.println("\tsure, cya.");
        System.out.println(LINE_BREAK);


    }
}
