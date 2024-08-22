import java.util.Scanner;

public class Winner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                --------------------------------------
                Hello! I am Winner.
                What can I do for you today?
                --------------------------------------""".indent(10));
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("""
                        --------------------------------------
                        Hope I have been of service!
                        Thank you and see you again soon :D
                        --------------------------------------""".indent(10));
                break;
            }
            System.out.println("-------------------------------------- \n".indent(10) +
                    input.indent(10) +
                    "--------------------------------------".indent(10));
        }

    }
}
