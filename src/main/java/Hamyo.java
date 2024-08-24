import java.util.Scanner;

public class Hamyo {

    private static boolean active = true;
    private static Task[] tasks = new Task[100];
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
                System.out.println("Oh no! Invalid command.");
                printLine();
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

    public static void add(String task) {
        tasks[nItems++] = new Task(task);
        System.out.println("added: " + task);
        printLine();
    }

    public static void listItems() {
        System.out.println("These are your tasks:");
        for (int i = 0; i < nItems; i++) {
            System.out.println(tasks[i].toString());
        }
        printLine();
    }

    public static void mark(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            tasks[index].mark();
        } catch (Exception e) {
            System.out.println("Oh no! Usage: mark [index]");
            printLine();
        }

    }

    public static void unmark(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            tasks[index].unmark();
        } catch (Exception e) {
            System.out.println("Oh no! Usage: unmark [index]");
            printLine();
        }
    }
}
