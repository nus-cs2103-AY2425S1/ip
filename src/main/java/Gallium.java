import java.util.Scanner;

public class Gallium {
    public static void main(String[] args) {
        String helloMessage = "Hello! I am Gallium. \n    What can I do for you?";
        String byeMessage = "Bye. Hope to see you again soon!";
        String lines = "____________________________________________________________";
        String[] taskList = new String[100];
        Scanner userInput = new Scanner(System.in);
        System.out.println("    " + lines + "\n    " + helloMessage + "\n    " + lines);
        String Message = userInput.nextLine();
        String bye = "bye";
        String list = "list";
        int x = 0;
        while (!Message.equals(bye)) {
            if (Message.equals(list)) {
                System.out.println("    " + lines);
                for (int i = 1; i <= x; i++) {
                    System.out.println("\n    " + i + ". " + taskList[i - 1]);
                }
                System.out.println("    " + lines + "\n    ");
                Message = userInput.nextLine();
            } else {
                System.out.println("    " + lines + "\n    " + "added: " + Message + "\n    " + lines + "\n");
                taskList[x] = Message;
                x++;
                Message = userInput.nextLine();
            }
        }
        System.out.println("    " + lines + "\n    " + byeMessage + "\n    " + lines + "\n");
        userInput.close();
    }
}
