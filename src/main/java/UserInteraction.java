import java.util.Scanner;
public class UserInteraction {




    public static void interact() {
        Scanner scannerObject = new Scanner(System.in);
        Storage storage = new Storage();



        while (true) {
            String instruction = scannerObject.nextLine();
            System.out.println(instruction);
            System.out.println(Meeju.LINE_BREAK);
            if (instruction.equals("bye")) {
                break;
            } else if (instruction.equals("list")) {
                storage.printList();
            } else if (instruction.startsWith("mark ")) {
                try {
                    storage.markTask(instruction.substring(5));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            } else if (instruction.startsWith("unmark ")) {
                try {
                    storage.unmarkTask(instruction.substring(7));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            } else if (instruction.startsWith("delete ")) {
                try {
                    storage.deleteTask(instruction.substring(7));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            } else if (instruction.startsWith("todo ")) {
                try {
                    storage.addTodoTask(instruction.substring(5));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            }  else if (instruction.startsWith("deadline ")) {
                try {
                    storage.addDeadlineTask(instruction.substring(9));
                } catch (MeejuException e) {
                    System.out.println(e);
                }
            }  else if (instruction.startsWith("event ")) {
                try {
                    storage.addEventTask(instruction.substring(6));
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
