import java.util.Scanner;
public class UserInteraction {


    public static void interact() {
        Scanner scannerObject = new Scanner(System.in);


        while (true) {
            String instruction = scannerObject.nextLine();
            System.out.println(instruction);
            System.out.println(Meeju.LINE_BREAK);
            if (instruction.equals("bye")) {
                break;
            } else if (instruction.equals("list")) {
                TaskCollection.printList();
            } else if (instruction.startsWith("mark")) {
                TaskCollection.markItem(instruction.substring(5));
            } else if (instruction.startsWith("unmark")) {
                TaskCollection.unmarkItem(instruction.substring(7));
            } else {
                TaskCollection.addTask(new Task(instruction));
            }
            System.out.println(Meeju.LINE_BREAK);
        }
    }
}
