import java.util.ArrayList;
import java.util.Scanner;

public class Stobberi {
    private static String nameOfChatBot = "Stobberi";
    private static String helloGreeting =
            "Hello! I'm " + nameOfChatBot + ".\n"
            + "What can I do for you?";
    private static String goodByeGreeting = "Bye. Hope to see you again soon! :)\n";
    private static ArrayList<String> listOfWords= new ArrayList<String>();

    private static String displayForm(String phrase) {
        return
                "___________________________________________\n"
                + phrase
                + "\n___________________________________________\n";
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        while (!temp.equals("bye")) {
            System.out.println(displayForm(temp));
            temp = scanner.nextLine();
        }
    }

    private static void addList() {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        while (!temp.equals("bye")) {
            if (temp.equals("list")) {
                String list = "";
                for (int i = 1; i < listOfWords.size() + 1;i++) {
                    list += i + ". " + listOfWords.get(i - 1)+ "\n";
                }
                System.out.println(displayForm(list));
            } else {
                listOfWords.add(temp);
                System.out.println(displayForm("added: " + temp));
            }
            temp = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        System.out.println(displayForm(helloGreeting));
        addList();
        System.out.println(displayForm(goodByeGreeting));
    }
}
