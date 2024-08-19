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
            } else {
                TaskCollection.addTask(new Task(instruction));
            }
            System.out.println(Meeju.LINE_BREAK);
        }
    }
}
