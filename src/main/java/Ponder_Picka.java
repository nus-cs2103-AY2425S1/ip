public class Ponder_Picka {

    public static void greet() {
        System.out.println("\nIt is a great day to ponder! How may I help you?");
    }

    public static void bidBye() {
        System.out.println("\nBye! See you real soon!");
    }

    public static void main(String[] args) {
        String logo = "Ponder_Picka";
        System.out.println("Hello I'm " + logo);

        Ponder_Picka.greet();
        System.out.println("\n-------------------------------------------------");
        Ponder_Picka.bidBye();
    }
}
