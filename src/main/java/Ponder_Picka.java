import java.util.Scanner;

public class Ponder_Picka {

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
            userCommand = scan.next();

            if (!userCommand.equals("bye")) {
                System.out.println("        " + userCommand);
                System.out.println("................................................");
            }
            else {
                System.out.println("\n------------------------------------------------");
                Ponder_Picka.bidBye();
                System.out.println("\n------------------------------------------------");
                break;
            }
        }


    }

    public static void main(String[] args) {
        String logo = "Ponder_Picka";

        System.out.println("------------------------------------------------");
        System.out.println("Hello I'm " + logo);
        Ponder_Picka.greet();
        System.out.println("------------------------------------------------");

        Ponder_Picka.echo();
    }
}
