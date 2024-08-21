import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lineBreak = "____________________________________________________________\n";
        String intro = lineBreak + "Hello! I'm Light\nWhat can I do for you?\n" + lineBreak;
        String exit = lineBreak + " Bye. Hope to see you again soon!\n" + lineBreak;
        System.out.println(intro);
        String command;
        Scanner scanner = new Scanner(System.in);
        command = scanner.nextLine();
        while (!command.equals("bye")) {
            System.out.println(lineBreak + command +"\n" + lineBreak);
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println(exit);
    }
}
