import java.util.ArrayList;
import java.util.Scanner;

public class Makima {

    public static final String LINE_SEPERATOR = "__________________";
    private boolean running = true;
    private ArrayList<String> lines = new ArrayList<String>();
    private ArrayList<Boolean> isDone = new ArrayList<>();
    private String userInput = "";
    private Scanner sc;

    public void greeting() {
        System.out.println("Yahallo! I'm your friendly chatbot, Makima!");
        System.out.println("What can I do for you? *_*");
        System.out.println(LINE_SEPERATOR);
    }

    public void farewell() {
        System.out.println("Baibai. Hope to see you soon! ^_^");
        System.out.println(LINE_SEPERATOR);
    }

    public String displayIfDone(int i) {
        if (isDone.get(i)) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public void displayList() {
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(i+1 + ":" + displayIfDone(i) + " " + lines.get(i));
        }
    }

    public int getListIndex() {
        while (true) {
            getInput();
            try {
                int index = Integer.parseInt(userInput);
                if (index < 1 || index > lines.size()) {
                    System.out.println("Invalid index");
                } else {
                    return index-1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }
    }

    public void getInput() {
        userInput = sc.nextLine();
        System.out.println(LINE_SEPERATOR);
    }

    public static void main(String[] args) {
        new Makima();
    }

    public Makima() {
        sc = new Scanner(System.in);
        greeting();

        while (running) {
            getInput();

            switch (userInput) {
                case "bye":
                    running = false;
                    break;

                case "list":
                    displayList();
                    System.out.println(LINE_SEPERATOR);
                    break;

                case "mark":
                    displayList();
                    System.out.println("Which item would you like to mark?");
                    isDone.set(getListIndex(), true);
                    System.out.println("Done!");
                    System.out.println(LINE_SEPERATOR);
                    break;

                case "unmark":
                    displayList();
                    System.out.println("Which item would you like to unmark?");
                    isDone.set(getListIndex(), false);
                    System.out.println("Done!");
                    System.out.println(LINE_SEPERATOR);
                    break;


                default:
                    System.out.println("added: " + userInput);
                    lines.add(userInput);
                    isDone.add(false);
                    System.out.println(LINE_SEPERATOR);
            }
        }

        farewell();
    }

}
