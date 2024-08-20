import java.util.Scanner;
import java.util.ArrayList;

public class Twilight {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I am Twilight your personal assistant\nWhat can I do for you?" );
        ArrayList<String> tasks =  new ArrayList<String>();
        String command = input.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("" + (i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(command);
                System.out.println("added: " + command);
            }
            command = input.nextLine();
        }
        System.out.println("See you");
    }
}
