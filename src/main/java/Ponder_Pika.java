import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class Ponder_Pika {

    static Queue<String> myList = new LinkedList<>();

    public static void greet() {
        System.out.println("\nIt is a great day to ponder! How may I help you?");
    }

    public static void bidBye() {
        System.out.println("\nBye! See you real soon!");
    }

    public static void echo() {

        Scanner scan = new Scanner(System.in);
        String userCommand = "";

        while(true) {
            userCommand = scan.nextLine();

            if (userCommand.equals("list")) {
                String[] listArray = myList.toArray(new String[myList.size()]);
                for (int i = 0; i < listArray.length; i++) {
                    System.out.println(i+1 + ". " + listArray[i]);
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
        String logo = "Ponder_Pika";

        System.out.println("------------------------------------------------");
        System.out.println("Hello I'm " + logo);
        Ponder_Pika.greet();
        System.out.println("------------------------------------------------");

        Ponder_Pika.echo();
    }
}
