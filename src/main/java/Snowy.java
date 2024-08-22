import java.util.Scanner;
import java.util.HashMap;

public class Snowy {

    public static void main(String[] args) {
        String line ="____________________________________________________________";
        System.out.println(line + "\n Hello I'm Snowy\n What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);

        String[] chats = new String[100];
        boolean isBye = false;
        int count = 0;

        while (!isBye) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                isBye = true;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < chats.length; i++) {
                    if (chats[i]!= null) {
                        String chat = String.format("%d. %s\n", i + 1, chats[i]);
                        System.out.println(chat);
                    } else {
                        System.out.println(line);
                        break;
                    }
                }
            } else {
                System.out.println(line + "\nYou said: " + input + "\n" + line);
                chats[count] = input;
                count++;
            }
        }
        System.out.println("Bye. See you next time!");
    }
}
