import java.util.*;

public class Rainy {
    public static void main(String[] args) {
        System.out.println("Hello! I am RAINY - Responsive, Automated, Intelligence Network for You.");
        System.out.println("What can I do for you on this marvelous day?");
        System.out.println("\n");
        TaskTracker tm = new TaskTracker();
        Scanner sc = new Scanner(System.in);
        String messages = sc.nextLine();
        String[] input = messages.split(" ");
        String message = input[0];
        int count;
        if (message.equals("mark") || message.equals("unmark")) {
            try {
                count = Integer.parseInt(input[1]);
            } catch (Exception e) {
                count = -1;
            }
        } else {
            count = -1;
        }

        while(!message.equals("bye")) {
            if (message.equals("list")) {
                tm.getList();
            } else if (message.equals("mark") && count != -1) {
                tm.markDone(count - 1);
            } else if (message.equals("unmark") && count != -1) {
                tm.unmarkDone(count - 1);
            }

            else {
                tm.updateList(messages);
            }
            messages = sc.nextLine();
            input = messages.split(" ");
            message = input[0];
            if (message.equals("mark") || message.equals("unmark")) {
                try {
                    count = Integer.parseInt(input[1]);
                } catch (Exception e) {
                    count = -1;
                }
            } else {
                count = -1;
            }
        }
        System.out.println("Goodbye! Have a nice day ahead!!");
    }
}
