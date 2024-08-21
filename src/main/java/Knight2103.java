import java.util.Scanner;

public class Knight2103 {
    public static String printList(String[] list, int length) {
        String stringToPrint = "";
        for (int i = 0; i < length; i++) {
            int bulletPoint = i + 1;
            stringToPrint += bulletPoint + ". " + list[i] + "\n";
        }
        return stringToPrint;
    }

    public static void main(String[] args) {
        String[] taskList = new String[100];
        int totalTaskNumber = 0;
        String botName = "Knight2103";
        String horiLine = "_____________";
        String intro = horiLine + "\n"
                + "Hello! I'm " + botName + " \n"
                + "What can I do for you?\n"
                + horiLine + "\n";

        System.out.println(intro);
        Scanner scanObject = new Scanner(System.in);
        String input = scanObject.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String listContents = printList(taskList, totalTaskNumber);
                System.out.println(horiLine + "\n" + listContents + horiLine);
            } else {
                taskList[totalTaskNumber] = input;
                totalTaskNumber++;
                System.out.println(horiLine + "\n added: " + input + "\n" + horiLine);
            }
            input = scanObject.nextLine();
        }
        System.out.println(horiLine + "\n" + "Bye. Hope to see you again soon!" + "\n" + horiLine);
        scanObject.close();
    }
}
