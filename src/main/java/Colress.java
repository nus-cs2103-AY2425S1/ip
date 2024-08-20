import java.util.ArrayList;
import java.util.Scanner;

public class Colress {
    private static ArrayList<String> toDoList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String input = "";
    private static String spacer = "______________________________________\n";
    private static String greetingMessage = "Hello! My name is Colress.\n"
            + "What can I do for you?";
    private static String listEmptyMessage = "I'm sorry, but it appears you have not added anything to your list.";
    private static String farewellMessage = "Well then, I hope to see you around soon!";
    private static String listCommand = "list";
    private static String exitCommand = "bye";
    public static void print(String s) {
        System.out.println(spacer + s + "\n" + spacer);
    }

    public static void makeList() {
        Colress.input = Colress.scanner.nextLine();
        while (!Colress.input.equals(exitCommand)) {
            if (input.equals(listCommand)) {
                print("Here is your list:" + printList());
            } else {
                toDoList.add(input);
                print(input);
            }
            Colress.input = Colress.scanner.nextLine();
        }
    }

    public static String printList() {
        String result = "";
        for(int i = 0; i < toDoList.size(); i++) {
            result += String.format("\n%d. " + toDoList.get(i), i + 1);
        }
        if(result.isEmpty()) {
            return listEmptyMessage;
        }
        return result;
    }
    public static void main(String[] args) {
        print(greetingMessage);
        Colress.makeList();
        print(farewellMessage);
    }
}
