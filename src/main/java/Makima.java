import java.util.ArrayList;
import java.util.Scanner;

public class Makima {

    public static final String LINE_SEPERATOR = "__________________";
    private boolean running = true;
    private ArrayList<Task> tasks = new ArrayList<>();
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

    public void displayList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ":" + tasks.get(i));
        }
    }

    public int getListIndex(String prompt) {
        System.out.println(prompt);
        System.out.println(LINE_SEPERATOR);
        return getListIndex();
    }

    public int getListIndex() {
        while (true) {
            String userInput = getInput();
            try {
                int index = Integer.parseInt(userInput);
                if (index < 1 || index > tasks.size()) {
                    System.out.println("Invalid index");
                } else {
                    return index-1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }
    }

    public String getInput(String prompt) {
        System.out.println(prompt);
        System.out.println(LINE_SEPERATOR);
        return getInput();
    }

    public String getInput() {
        String userInput = sc.nextLine();
        System.out.println(LINE_SEPERATOR);
        return userInput;
    }

    public void done() {
        System.out.println("Done!");
        System.out.println(LINE_SEPERATOR);
    }

    public static void main(String[] args) {
        new Makima();
    }

    public Makima() {
        sc = new Scanner(System.in);
        greeting();

        while (running) {

            switch (getInput()) {
                case "bye":
                    running = false;
                    break;

                case "list":
                    displayList();
                    System.out.println(LINE_SEPERATOR);
                    break;

                case "mark":
                    displayList();
                    tasks.get(getListIndex("Which item would you like to mark?")).mark();
                    done();
                    break;

                case "unmark":
                    displayList();
                    tasks.get(getListIndex("Which item would you like to mark?")).unmark();
                    done();
                    break;

                case "todo":
                    tasks.add(new ToDos(getInput("What is the task name?")));
                    done();
                    break;

                case "deadline":
                    tasks.add(new Deadline(
                            getInput("What is the task name?"),
                            getInput("When is it due?")
                    ));
                    done();
                    break;

                case "event":
                    tasks.add(new Event(
                            getInput("What is the event name?"),
                            getInput("When does it start?"),
                            getInput("When does it end?")
                    ));
                    done();
                    break;


                default:
                    System.out.println("Unknown command!");
                    System.out.println(LINE_SEPERATOR);
            }
        }

        farewell();
    }

}
