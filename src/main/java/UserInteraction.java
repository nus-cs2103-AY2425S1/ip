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
            } else if (instruction.startsWith("mark ")) {
                try {
                    TaskCollection.markTask(instruction.substring(5));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            } else if (instruction.startsWith("unmark ")) {
                try {
                    TaskCollection.unmarkTask(instruction.substring(7));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            } else if (instruction.startsWith("delete ")) {
                try {
                    TaskCollection.deleteTask(instruction.substring(7));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            } else if (instruction.startsWith("todo ")) {
                try {
                    TaskCollection.addTodoTask(instruction.substring(5));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            }  else if (instruction.startsWith("deadline ")) {
                try {
                    TaskCollection.addDeadlineTask(instruction.substring(9));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            }  else if (instruction.startsWith("event ")) {
                try {
                    TaskCollection.addEventTask(instruction.substring(6));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("I'm sorry, I did not understand that =^..^=");
            }
            System.out.println(Meeju.LINE_BREAK);
        }
    }
}
