import java.util.Scanner;

public class Jade {
    public static void main(String[] args) {
        String indent = "     "; // 5 spaces for indentation
        String topLine = "    " + "_".repeat(60) + "\n";
        String botLine = "\n" + "    " + "_".repeat(60);
        String greet = indent + "Hello! I'm Jade!\n"
                + indent + "What can I do for you?";
        String exit = indent + "Bye. Hope to see you again soon!";
        String list = ""; // initial list
        int num = 0; // number of texts
        String message;

        System.out.println(topLine + greet + botLine);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(topLine + list + botLine);
                command = sc.nextLine();
            } else {
                num++;

                if (num == 1) {
                    list += indent + num + ". " + command;
                } else {
                    list += "\n" + indent + num + ". " + command;
                }

                message = indent + "added: " + command;
                System.out.println(topLine + message + botLine);
                command = sc.nextLine();
            }
        }

        System.out.println(topLine + exit + botLine);
    }
}
