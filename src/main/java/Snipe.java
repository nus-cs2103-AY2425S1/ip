import java.util.ArrayList;
import java.util.Scanner;
public class Snipe {
    private static final String NAME = "Snipe";
    private static final String LOGO = "  _________      .__               \n"
            + " /   _____/ ____ |__|_____   ____  \n"
            + " \\_____  \\ /    \\|  \\____ \\_/ __ \\ \n"
            + " /        \\   |  \\  |  |_> >  ___/ \n"
            + "/_______  /___|  /__|   __/ \\___  >\n"
            + "        \\/     \\/   |__|        \\/ \n";
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private static ArrayList<String[]> list = new ArrayList<String[]>();
    public void initChat() {
        greetUser();
        handleUserInput();
    }

    private void greetUser() {
        String OPENING_MESSAGE = "Hello! I'm\n" + LOGO +"\nWhat can I do for you?";
        printWithLines(OPENING_MESSAGE);
    }
    private void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                exitChat(scanner);
                break;
            }
            handleSpecialInputs(userInput, scanner);
        }
    }
    private void handleSpecialInputs(String userInput, Scanner scanner) {
        if (userInput.equalsIgnoreCase("list")) {
            returnList();
        } else if(userInput.startsWith("mark")) {
            String[] split = userInput.split(" ");
            int index = Integer.valueOf(split[1]) - 1;
            if (list.get(index)[1] == "X") {
                printWithLines("This task is already marked done!");
            } else {
                list.get(index)[1] = "X";
                printWithLines("Nice! I've marked this task as done:\n" +
                        "[X] " + list.get(index)[0]);
            }
        } else if(userInput.startsWith("unmark")) {
            String[] split = userInput.split(" ");
            int index = Integer.valueOf(split[1]) - 1;
            if (list.get(index)[1] == " ") {
                printWithLines("This task is currently not done yet!");
            } else {
                list.get(index)[1] = " ";
                printWithLines("OK, I've marked this task as not done yet:\n" +
                        "[ ] " + list.get(index)[0]);
            }
        } else {
            String[] input = new String[]{userInput, " "};
            list.add(input);
            String message = "added: " + userInput;
            printWithLines(message);
        }
    }
    private void returnList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            String item = String.format("%d.", i + 1) + "[" + list.get(i)[1] + "] " + list.get(i)[0];
            System.out.println(item);
        }
        System.out.println(HORIZONTAL_LINE);
    }
    private void printWithLines(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }
    private void exitChat(Scanner scanner) {
        String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
        printWithLines(CLOSING_MESSAGE);
        scanner.close();
    }
    public static void main(String[] args) {
        Snipe snipe = new Snipe();
        snipe.initChat();
    }
}


