import java.util.*;

public class Ponder_Pika {

    private final List<String> myList = new ArrayList<>();

    private void greet() {
        String logo = "Ponder_Pika";

        System.out.println("------------------------------------------------");
        System.out.println("Hello I'm " + logo);
        System.out.println("------------------------------------------------");
        System.out.println("\nIt is a great day to ponder! How may I help you?");
    }

    private static void bidBye() {
        System.out.println("\nBye! See you real soon!");
    }

    public void echo() {
        greet();
        Scanner scan = new Scanner(System.in);
        String userCommand = "";

        while(true) {
            userCommand = scan.nextLine().trim();

            if (userCommand.equals("list")) {
                for (int i = 0; i < myList.size(); i++) {
                    System.out.println(i+1 + ". " + myList.get(i));
                }
                System.out.println("................................................");
            } else if (!userCommand.equals("bye")) {
                myList.add(userCommand);
                System.out.println("        added: " + userCommand);
                System.out.println("................................................");
            } else {
                System.out.println("\n------------------------------------------------");
                Ponder_Pika.bidBye();
                System.out.println("\n------------------------------------------------");
                break;
            }
        }

    }

    public static void main(String[] args) {
        Ponder_Pika bot = new Ponder_Pika();
        bot.echo();
    }
}
