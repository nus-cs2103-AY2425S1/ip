import java.util.Scanner;

public class Knight2103 {
    public static void main(String[] args) {
        String botName = "Knight2103";
        String horiLine = "_____________";
        String intro = horiLine + "\n"
                + "Hello! I'm " + botName + " \n"
                + "What can I do for you?\n"
                + horiLine + "\n";
        // put scanner object here
        System.out.println(intro);
        Scanner scanObject = new Scanner(System.in);
        String input = scanObject.nextLine();
        while (!input.equals("bye")) {
            System.out.println(horiLine + "\n" + input + "\n" + horiLine);
            input = scanObject.nextLine();
        }
        System.out.println(horiLine + "\n" + "Bye. Hope to see you again soon!" + "\n" + horiLine);
        scanObject.close();
    }
}
