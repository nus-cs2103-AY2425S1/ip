import java.util.Scanner;

public class Jade {
    public static void main(String[] args) {
        String indent = " ";
        String topLine = indent.repeat(4) + "_".repeat(60) + "\n";
        String botLine = "\n" + indent.repeat(4) + "_".repeat(60);
        String greet = indent.repeat(5) + "Hello! I'm Jade!\n"
                + indent.repeat(5) + "What can I do for you?";
        String exit = indent.repeat(5) + "Bye. Hope to see you again soon!";

        System.out.println(topLine + greet + botLine);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println(topLine + indent.repeat(5) + command + botLine);
            command = sc.nextLine();
        }

        System.out.println(topLine + exit + botLine);
    }
}
