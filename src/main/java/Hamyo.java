import java.util.Scanner;

public class Hamyo {

    private static boolean active = true;
    private static String[] items = new String[100];
    private static boolean[] mark = new boolean[100];
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
            } else if (str.startsWith("mark")) {
                if (str.length() > 5) {
                    mark(str.substring(5));
                } else {
                    System.out.println("Oh no! Usage: mark [task]");
                }
            } else if (str.startsWith("unmark")) {
                if (str.length() > 7) {
                    unmark(str.substring(7));
                } else {
                    System.out.println("Oh no! Usage: mark [task]");
                }
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
        System.out.printf("These are your tasks:\n");
        for (int i = 0; i < nItems; i++) {
            System.out.printf("%d.[%s] %s\n", i + 1, mark[i] ? "X" : " ", items[i]);
        }
        printLine();
    }

    public static void mark(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            if (!mark[index]) {
                mark[index] = true;
                System.out.println("Yay! This task has been marked as completed.");
                System.out.printf("[X] %s\n", items[index]);
                printLine();
            } else {
                System.out.println("Oh no! This task was already marked as completed!");
                printLine();
            }
        } catch (Exception e) {
            System.out.println("Oh no! Usage: mark [index]");
            printLine();
        }

    }

    public static void unmark(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            if (mark[index]) {
                mark[index] = false;
                System.out.println("Oki! This task has been marked as incomplete.");
                System.out.printf("[ ] %s\n", items[index]);
                printLine();
            } else {
                System.out.println("Oh no! This task was already marked as incomplete!");
                printLine();
            }
        } catch (Exception e) {
            System.out.println("Oh no! Usage: unmark [index]");
            printLine();
        }
    }

    public static void echo(String str) {
        System.out.println(str);
        printLine();
    }
}
