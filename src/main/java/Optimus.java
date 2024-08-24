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
            String text = stringScanner.nextLine();
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
            } else {
                if (count < 100) {
                    record[count] = new Task(text);
                    System.out.println("added: " + text);
                    count++;
                }
            }

        }
    }
}
