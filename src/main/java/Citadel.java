import java.util.Scanner;

public class Citadel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String name = "Citadel";
        String intro = "Hello! I'm " + name + "\n";
        String question = "What can I do for you?\n";

        String input = "";

        String start = intro + question;
        System.out.println(start);

        while(!input.equalsIgnoreCase("bye")) {
            input = scanner.nextLine();
            System.out.println(input);
            System.out.println();
        }

        String goodbye = "Bye. Hope to see you again soon!\n";
        System.out.println(goodbye);
    }
}
