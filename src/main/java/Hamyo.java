import java.util.Scanner;

public class Hamyo {

    private static boolean active = true;
    private static String[] items = new String[100];
    private static int nItems = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        greet();

        while (active) {
            String str = scanner.nextLine();
            if (str.startsWith("add")) {
                if (str.length() > 4) {
                    add(str.substring(4));
                } else {
                    System.out.println("Oh no! Usage: add [task]");
                }
            } else if (str.equals("list")) {
                listItems();
            } else if (str.startsWith("bye")) {
                terminate();
            } else {
                echo(str);
            }
        }
    }

    public static void printLine() {
        System.out.println("_______________________________________________________");
    }

    private static void printLogo() {
        System.out.println(
            "$$   $$   $$$$    $$$$ $$$$   $$   $$  $$$$$$\n" +
            "$$   $$  $$  $$  $$  $$$  $$  $$   $$  $$  $$\n" +
            "$$$$$$$  $$$$$$  $$  $$$  $$  $$$$$$$  $$  $$\n" +
            "$$   $$  $$  $$  $$  $$$  $$       $$  $$  $$\n" +
            "$$   $$  $$  $$  $$  $$$  $$  $$$$$$   $$$$$$");
    }

    public static void greet() {
        printLine();
        printLogo();
        System.out.println("\nAnnyeonghaseyo! Hamyo here!\nHow may I assist you today?");
        printLine();
    }

    public static void terminate() {
        active = false;
        System.out.println("Annyeong! Till we meet again. <3");
        printLine();
    }

    public static void add(String item) {
        items[nItems++] = item;
        System.out.println("added: " + item);
        printLine();
    }

    public static void listItems() {
        for (int i = 0; i < nItems; i++) {
            System.out.printf("%d. %s\n", i + 1, items[i]);
        }
        printLine();
    }

    public static void echo(String str) {
        System.out.println(str);
        printLine();
    }
}
