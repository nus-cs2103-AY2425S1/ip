import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Citadel {

    public static List<String> items = new ArrayList<>();
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
            if (input.equalsIgnoreCase("list")) {
                for(int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }
            } else {
                items.add(input);
                System.out.println("added: " + input);
                System.out.println();
            }
            }

        String goodbye = "Bye. Hope to see you again soon!\n";
        System.out.println(goodbye);
    }
}
