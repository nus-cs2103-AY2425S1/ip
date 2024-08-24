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
            if (str.startsWith("todo")) {
                if (str.length() > 5) {
                    add("todo", str.substring(5));
                } else {
                    System.out.println("Oh no! Usage: todo [task]");
                }
            } else if (str.startsWith("deadline")) {
                if (str.length() > 9) {
                    add("deadline", str.substring(9));
                } else {
                    System.out.println("Oh no! Usage: todo [task]");
                }
            } else if (str.startsWith("event")) {
                if (str.length() > 6) {
                    add("event", str.substring(6));
                } else {
                    System.out.println("Oh no! Usage: todo [task]");
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

    public static void add(String taskType, String task) {
        if (taskType.equals("todo")) {
            tasks[nItems++] = new ToDo(new String[]{task});
        } else if (taskType.equals("deadline")) {
            tasks[nItems++] = new Deadline(task.split(" /by "));
        } else if (taskType.equals("event")) {
            tasks[nItems++] = new Event(task.split(" /from | /to "));
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[nItems - 1].toString());
        System.out.printf("There are %d tasks in the list now.\n", nItems);
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
