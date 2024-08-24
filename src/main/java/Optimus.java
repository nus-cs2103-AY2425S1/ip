import java.util.Objects;
import java.util.Scanner;
import java.util.*;
public class Optimus {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Optimus");
        System.out.println("What can I do for you?");
        Scanner stringScanner = new Scanner(System.in);
        Task[] record = new Task[100];
        int count = 0;

        while (true) {
            String text = "";
            text = stringScanner.nextLine();
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (text.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < count; j++) {
                    System.out.println((j + 1) + ". " + record[j].toString());
                }
            } else if (text.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(text.split(" ")[1]) - 1;
                if (taskNumber >= 0 && taskNumber < count) {
                    record[taskNumber].setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + record[taskNumber].toString());
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (text.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(text.split(" ")[1]) - 1;
                if (taskNumber >= 0 && taskNumber < count) {
                    record[taskNumber].setNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + record[taskNumber].toString());
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (text.startsWith("todo ")) {
                if (count < 100) {
                    String description = text.substring(5);
                    record[count] = new ToDos(description);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + record[count].toString());
                    count++;
                    if (count == 1) {
                        System.out.println("Now you have " + count + " task in the list.");
                    }
                    else if (count > 1){
                        System.out.println("Now you have " + count + " tasks in the list.");
                    }
                }
            } else if (text.startsWith("deadline ")) {
                if (count < 100) {
                    String[] parts = text.split(" /by ");
                    String description = parts[0].substring(9);
                    String by = parts.length > 1 ? parts[1] : "";
                    record[count] = new Deadlines(description, by);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + record[count].toString());
                    count++;
                    if (count == 1) {
                        System.out.println("Now you have " + count + " task in the list.");
                    }
                    else if (count > 1){
                        System.out.println("Now you have " + count + " tasks in the list.");
                    }
                }
            } else if (text.startsWith("event ")) {
                if (count < 100) {
                    String[] parts = text.split(" /from | /to ");
                    String description = parts[0].substring(6);
                    String from = parts.length > 1 ? parts[1] : "";
                    String to = parts.length > 2 ? parts[2] : "";
                    record[count] = new Events(description, from, to);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + record[count].toString());
                    count++;
                    if (count == 1) {
                        System.out.println("Now you have " + count + " task in the list.");
                    }
                    else if (count > 1){
                        System.out.println("Now you have " + count + " tasks in the list.");
                    }
                }
            }
        }
    }
}
