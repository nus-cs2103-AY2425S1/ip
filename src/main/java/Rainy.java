import java.util.*;

public class Rainy {
    private static String name = "Rainy!!!";
    private TaskTracker tm;

    public Rainy() {
        this.tm = new TaskTracker();
    }

    public static void main(String[] args) {
        System.out.println("Hello! I am RAINY - Responsive, Automated, Intelligence Network for You.");
        System.out.println("What can I do for you on this marvelous day?");
        System.out.println("\n");
        TaskTracker tm = new TaskTracker();
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while(!message.equals("bye")) {
            if (message.equals("list")) {
                tm.getList();
            } else {
                System.out.println("added " + message);
                tm.updateList(message);
            }
            message = sc.nextLine();
        }
        System.out.println("Goodbye! Have a nice day ahead!!");
    }
}
