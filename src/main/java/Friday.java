import java.util.Objects;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        boolean endScanner = false;
        System.out.println("___________________________________________________________");
        System.out.println("Hello! I'm Friday");
        System.out.println("What can I do for you?");
        System.out.println("___________________________________________________________");
        while (!endScanner) {
            String toDo = scannerObj.nextLine();
            if (Objects.equals(toDo, "bye")) {
                endScanner = true;
            } else {
                System.out.println("    ___________________________________________________________");
                System.out.println("    " + toDo);
                System.out.println("    ___________________________________________________________");

            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________________________________");
    }

}
