
import java.util.Scanner;

public class Sentinel {

    public static int index = 0;
    public static Task[] items = new Task[100];

    public static void main(String[] args) {
        String logo
                = """
                   _____                                                                                      _____ 
                  ( ___ )                                                                                    ( ___ )
                   |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | 
                   |   |                                                                                      |   | 
                   |   |   ________  _______   ________   _________  ___  ________   _______   ___            |   | 
                   |   |  |\\   ____\\|\\  ___ \\ |\\   ___  \\|\\___   ___\\\\  \\|\\   ___  \\|\\  ___ \\ |\\  \\           |   | 
                   |   |  \\ \\  \\___|\\ \\   __/|\\ \\  \\\\ \\  \\|___ \\  \\_\\ \\  \\ \\  \\\\ \\  \\ \\   __/|\\ \\  \\          |   | 
                   |   |   \\ \\_____  \\ \\  \\_|/_\\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|/_\\ \\  \\         |   | 
                   |   |    \\|____|\\  \\ \\  \\_|\\ \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\____    |   | 
                   |   |      ____\\_\\  \\ \\_______\\ \\__\\  \\__\\   \\ \\__\\ \\ \\__\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\  |   | 
                   |   |     |\\_________\\|_______|\\|__| \\|__|    \\|__|  \\|__|\\|__| \\|__|\\|_______\\|_______|   |   | 
                   |   |     \\|_________|                                                                     |   | 
                   |   |                                                                                      |   | 
                   |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| 
                  (_____)                                                                                    (_____)""";

        System.out.println("Hello from\n" + logo);
        System.out.println("\nWhat can I do for you?\n____________________________________________________________\n");
        try (Scanner sc = new Scanner(System.in)) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________\n");
            OUTER:
            while (true) {
                switch (input) {
                    case "bye" -> {
                        break OUTER;
                    }
                    case "list" -> {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < index; i++) {
                            System.out.println("\t" + (i + 1) + ". [" + items[i].getStatusIcon() + "] " + items[i]);
                        }
                    }
                    case "mark" -> {
                        int num = sc.nextInt();
                        if (num > index) {System.out.println("No such item in the list!"); break;}
                        else if (items[num-1].isDone()) {System.out.println(items[num-1].toString() + " has already been marked as done."); break;}
                        items[num - 1].setDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("\t" + "[" + items[num - 1].getStatusIcon() + "] " + items[num - 1]);
                    }
                    case "unmark" -> {
                        int num = sc.nextInt();
                        if (num > index) {System.out.println("No such item in the list!"); break;}
                        else if (!items[num-1].isDone()) {System.out.println(items[num-1] + " has already been marked as undone."); break;}
                        items[num - 1].setUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("\t" + "[" + items[num - 1].getStatusIcon() + "] " + items[num - 1]);
                    }
                    default -> {
                        items[index++] = new Task(input);
                        System.out.println("added: " + items[index-1]);
                    }
                }
                System.out.println("____________________________________________________________\n");
                input = sc.next();
                System.out.println("____________________________________________________________\n");
            }
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
