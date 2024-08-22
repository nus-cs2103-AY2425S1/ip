import java.util.ArrayList;
import java.util.Scanner;
public class Talky {
    public static void printSeperator(String content) {
        String seperator = "---------------------------------------";
        System.out.println(seperator);
        System.out.println(content);
        System.out.println(seperator);
    }
    public static void main(String[] args) {
        ArrayList<String> userTexts = new ArrayList<>();

        printSeperator("Hello I'm Talky\n" + "How may I help you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                String textsList = "";
                int rank = 1;
                for (String text : userTexts) {
                    textsList += rank + ". " + text + "\n";
                    rank++;
                }
                printSeperator(textsList);
            } else {
                userTexts.add(command);
                printSeperator("added: " + command);
            }
        }

        printSeperator("Bye. Do let me know if there's anything else!");
    }
}
