import java.util.ArrayList;
import java.util.Scanner;

public class Broski {
    private String line = "_________________________________________";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>(100);
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
        } else {
            System.out.println(line);
            list.add(reply);
            System.out.println("added: " + reply);
            System.out.println(line);
            this.chatbot();
        }
    }

    public void exit() {
        System.out.println("Bye. See ya around!");
        System.out.println(line);
    }

    public static void main(String[] args) {
        Broski bot = new Broski();
        bot.start();
        bot.chatbot();
    }
}
