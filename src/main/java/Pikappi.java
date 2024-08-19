import java.util.Scanner;

public class Pikappi {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Pika! I'm Pikappi!\nWhat can I do for you?\n");
        System.out.println("____________________________________________________________");
        Scanner reader = new Scanner(System.in);
        String command;
        while (true) {
            command = reader.nextLine();
            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Pi-kapi! See you again~\n");
                System.out.println("____________________________________________________________");
                return;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(command);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
