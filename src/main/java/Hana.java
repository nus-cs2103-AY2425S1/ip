import java.util.Scanner;

public class Hana {
    public static void main(String[] args) {
        String line = "    ____________________________________________________________\n";
        String greeting = "    Hello I'm Hana\n" +
                "    What can I do for you?\n";
        String closing = "    Bye. Hope to see you again soon!\n";
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        System.out.println(line + greeting + line);
        String input = scanner.nextLine();  // Read user input
        String output = "";
        while (!input.equals("bye")) {
            output = "    " + input + "\n";
            System.out.println(line + output + line);
            input = scanner.nextLine();
        }
        System.out.println(line + closing + line);
    }
}
