import java.util.ArrayList;
import java.util.Scanner;

public class Makima {

    public static final String LINE_SEPERATOR = "__________________";
    private boolean running = true;
    private ArrayList<String> lines = new ArrayList<String>();

    public void greeting() {
        System.out.println("Yahallo! I'm your friendly chatbot, Makima!");
        System.out.println("What can I do for you? *_*");
        System.out.println(LINE_SEPERATOR);
    }

    public void farewell() {
        System.out.println("Baibai. Hope to see you soon! ^_^");
        System.out.println(LINE_SEPERATOR);
    }

    public static void main(String[] args) {
        new Makima();
    }

    public Makima() {
        Scanner sc = new Scanner(System.in);
        greeting();

        while (running) {
            String userInput = sc.nextLine();
            System.out.println(LINE_SEPERATOR);

            switch (userInput) {
                case "bye":
                    running = false;
                    break;

                case "list":
                    for (int i = 0; i < lines.size(); i++) {
                        System.out.println(i + ": " + lines.get(i));
                    }
                    System.out.println(LINE_SEPERATOR);
                    break;

                default:
                    System.out.println("added: " + userInput);
                    lines.add(userInput);
                    System.out.println(LINE_SEPERATOR);
            }
        }

        farewell();
    }

}
