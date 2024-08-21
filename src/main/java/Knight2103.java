import java.util.Scanner;

public class Knight2103 {
    public static String printList(Task[] list, int length) {
        String stringToPrint = "";
        for (int i = 0; i < length; i++) {
            int bulletPoint = i + 1;
            stringToPrint += bulletPoint + ". " + list[i] + "\n";
        }
        return stringToPrint;
    }

    public static void main(String[] args) {
        Task[] taskList = new Task[100];
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
                String[] inputArray = input.split("\\s+");
                if (inputArray[0].equals("mark")) {
                    int taskNumberIndex = Integer.parseInt(inputArray[1]) - 1; // can try
                    taskList[taskNumberIndex].markDone(); // need to check if it works
                    System.out.println(horiLine + "\nNice! I've marked this task as done:\n" + taskList[taskNumberIndex] + "\n" + horiLine);
                } else if (inputArray[0].equals("unmark")) {
                    int taskNumberIndex = Integer.parseInt(inputArray[1]) - 1; // can try
                    taskList[taskNumberIndex].unmarkDone();
                    System.out.println(horiLine + "\nOK, I've marked this task as not done yet:\n" + taskList[taskNumberIndex] + "\n" + horiLine);
                } else {
                    taskList[totalTaskNumber] = new Task(input);
                    totalTaskNumber++;
                    System.out.println(horiLine + "\n added: " + input + "\n" + horiLine);
                }
            }
            input = scanObject.nextLine();
        }
        System.out.println(horiLine + "\n" + "Bye. Hope to see you again soon!" + "\n" + horiLine);
        scanObject.close();
    }
}
