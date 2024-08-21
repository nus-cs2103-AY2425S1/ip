import java.util.ArrayList;
import java.util.Scanner;
public class Zenith {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        int listCount = 1;

        System.out.println(Message.getGreeting());
        User user = new User();

        while (true) {

            String input = scanner.nextLine();
            switch (input) {
                case "list":
                    list = user.getList();
                    for (String item: list) {
                        System.out.println(listCount + ". " + item);
                        listCount++;
                    }
                    System.out.println();
                    break;
                case "bye":
                    System.out.println(Message.getExit());
                    break;
                default:
                    System.out.println("added: " + input + "\n");
                    user.addList(input);
                    break;
            }

            if (input.equals("bye")) {
                break;
            }

        }
    }
}
