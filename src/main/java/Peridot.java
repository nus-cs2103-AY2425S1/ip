import java.util.Scanner;

public class Peridot {

    private static List list = new List();
    public static void botSay(String string) {
        System.out.println("Peridot: " + string);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        botSay("Hello I'm Peridot!!");
        botSay("What's up?");
        String userResponse = scanner.nextLine();
        while (!userResponse.equals("bye")) {
            try {
                list.answer(userResponse);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                userResponse = scanner.nextLine();
            }
        }
        botSay("Bye!");
    }
}
