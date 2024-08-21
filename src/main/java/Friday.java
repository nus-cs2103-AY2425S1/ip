import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        boolean endScanner = false;
        List<String> toDoList = new ArrayList<>();
        System.out.println("___________________________________________________________");
        System.out.println("Hello! I'm Friday");
        System.out.println("What can I do for you?");
        System.out.println("___________________________________________________________");
        while (!endScanner) {
            String toDo = scannerObj.nextLine();
            if (Objects.equals(toDo, "bye")) {
                endScanner = true;
            } else if(Objects.equals(toDo, "list")) {
                System.out.println("    _______________________________________________________");
                for (int i = 0; i < toDoList.size(); i++) {
                    String returnString = "    " + (i + 1) + ". " + toDoList.get(i);
                    System.out.println(returnString);
                }
                System.out.println("    _______________________________________________________");
            }
            else {
                String returnString = "added: " + toDo;
                toDoList.add(toDo);
                System.out.println("    _______________________________________________________");
                System.out.println("    " + returnString);
                System.out.println("    _______________________________________________________");

            }
        }
        System.out.println("___________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________________________________");
    }

}
