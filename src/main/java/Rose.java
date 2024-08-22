import java.util.Scanner;

public class Rose {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String horizontal = "____________________________________________________________";
        String name = "Rose";

        String opening = horizontal + "\nHi gorgeous! I'm " + name + "~~\n"
                + "How can I help you today?\n" + horizontal;
        String closing = horizontal + "\nSee you~~ Don't forget to drink some water ^_^\n" + horizontal;

        System.out.println(opening.indent(4));

        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println((horizontal + '\n' + command +
                    '\n' + horizontal).indent(4));
            command = scanner.nextLine();
        }

        System.out.println(closing.indent(4));
    }
}
