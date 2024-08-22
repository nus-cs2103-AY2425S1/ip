import java.util.Scanner;
import java.util.ArrayList;

public class Winner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("""
                --------------------------------------
                Hello! I am Winner.
                What can I do for you today?
                --------------------------------------""".indent(10));
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("list")) {
                int counter = 1;
                System.out.println(" ".repeat(10) + "--------------------------------------");
                for (String i:tasks) {
                    System.out.println(" ".repeat(10) + counter + ". " + i);
                    counter++;
                }
                System.out.println(" ".repeat(10) + "--------------------------------------");
            } else if (input.equalsIgnoreCase("bye")) {
                System.out.println("""
                        --------------------------------------
                        Hope I have been of service!
                        Thank you and see you again soon :D
                        --------------------------------------""".indent(10));
                break;
            } else {
                tasks.add(input);
                System.out.println("--------------------------------------".indent(10) +
                        ("added: " + input).indent(10) +
                        "--------------------------------------".indent(10));
            }
        }
        scanner.close();
    }
}