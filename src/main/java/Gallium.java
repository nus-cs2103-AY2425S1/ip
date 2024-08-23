import java.util.Scanner;

public class Gallium {
    public static void main(String[] args) {
        String helloMessage = "Hello! I am Gallium. \n    What can I do for you?";
        String byeMessage = "Bye. Hope to see you again soon!";
        String lines = "____________________________________________________________";
        Scanner userInput = new Scanner(System.in);
        System.out.println("    " + lines + "\n    " + helloMessage + "\n    " + lines);
        String Message = userInput.nextLine();
        String bye = "bye";
        while (!Message.equals(bye)) {
            System.out.println("    " + lines + "\n    " + Message + "\n    " + lines + "\n");
            Message = userInput.nextLine();
        }
        System.out.println("    " + lines + "\n    " + byeMessage + "\n    " + lines + "\n");
        userInput.close();
    }
}
