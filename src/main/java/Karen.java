import java.util.Scanner;

public class Karen {
    public static void main(String[] args) {
        final String line = "_______________________\n";
        String output = line +
                        "Hi! I'm Karen\n" +
                        "What can I do for you?\n" +
                        line;

        System.out.print(output);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                output = line +
                        "Bye! Hope to see you again!\n" +
                        line;
                System.out.println(output);
                break;
            } else {
                output = line +
                         input + "\n" +
                         line;
                System.out.println(output);
            }
        }

    }
}
