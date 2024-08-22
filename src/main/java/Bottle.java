import java.util.Scanner;
public class Bottle {
    final static String lineBreak = "\n____________________________________________________________\n";

    public static void printwithBreak(String str) {
        System.out.println(lineBreak + str + lineBreak);
    }
    public static void main(String[] args) {

        String welcomeMsg =
                " Hello! I'm Bottle\n" +
                " What can I do for you?";

        String byeMsg =" Bye. Hope to see you again soon!";
        printwithBreak(welcomeMsg);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                printwithBreak(byeMsg);
                break;
            }
            printwithBreak(input);
        }
    }
}
