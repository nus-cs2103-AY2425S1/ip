import java.util.Scanner;

public class Peridot {

    private static List list = new List();
    public static void botSay(String string) {
        System.out.println("Peridot: " + string);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        botSay("Hello I'm Peridot!");
        botSay("What's up?");
        String userResponse = scanner.nextLine();
        while (!userResponse.equals("bye")) {
            String command = userResponse.contains(" ")
                    ? userResponse.substring(0, userResponse.indexOf(' '))
                    : userResponse;
            switch (command) {
                case "list":
                    list.listOut();
                    break;
                case "mark":
                    try {
                        int index = Integer.parseInt(userResponse.substring(userResponse.indexOf(' ') + 1, userResponse.indexOf(' ') + 2)) - 1;
                        list.markTask(index);
                        botSay("Nice! I've marked this task as done:");
                        list.printTask(index);
                    }
                    catch (NumberFormatException e) {
                         System.out.println("failed");
                    }
                    break;
                case "unmark":
                    try {
                        int index = Integer.parseInt(userResponse.substring(userResponse.indexOf(' ') + 1, userResponse.indexOf(' ') + 2)) - 1;
                        list.unmarkTask(index);
                        botSay("OK, I've marked this task as not done yet:");
                        list.printTask(index);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("failed");
                    }
                    break;
                default:
                    list.add(userResponse);
                    botSay("added: " + userResponse);
            }
            userResponse = scanner.nextLine();
        }
        botSay("Bye!");
    }
}
