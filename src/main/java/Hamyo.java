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
            if (str.startsWith("bye")) {
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

    public static void echo(String str) {
        System.out.println(str);
        printLine();
    }
}
