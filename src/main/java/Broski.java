import java.util.ArrayList;
import java.util.Scanner;

public class Broski {
    private String line = "_________________________________________";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>(100);
    public void start() {
        System.out.println(line);
        System.out.println("Wassup! I'm Broski!");
        System.out.println("What can I do for you bro?");
        System.out.println(line);
    }

    public void chatbot() {
        String reply = scanner.nextLine();
        if (reply.equals("list")) {
            System.out.println(line);
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
            System.out.println(line);
            this.chatbot();
        } else if (reply.equals("bye")) {
            System.out.println(line);
            this.exit();
        } else if (reply.length() > 5 && reply.substring(0, 4).equals("mark")) {
            System.out.println(line);
            int i = Integer.valueOf(reply.split("[ ]")[1]);
            list.get(i).mark();
            System.out.println("Solid! Marked as done for you:");
            System.out.println(list.get(i));
            System.out.println(line);
            this.chatbot();
        } else if (reply.length() > 7 && reply.substring(0, 6).equals("unmark")) {
            System.out.println(line);
            int i = Integer.valueOf(reply.split("[ ]")[1]);
            list.get(i).unmark();
            System.out.println("Alright, I've marked the task as undone:");
            System.out.println(list.get(i));
            System.out.println(line);
            this.chatbot();
        } else {
            System.out.println(line);
            list.add(new Task(reply));
            System.out.println("added: " + reply);
            System.out.println(line);
            this.chatbot();
        }
    }

    public void exit() {
        System.out.println("Bye, bro. See ya around!");
        System.out.println(line);
    }

    public static void main(String[] args) {
        Broski bot = new Broski();
        bot.start();
        bot.chatbot();
    }
}
