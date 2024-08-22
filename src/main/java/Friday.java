import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        boolean endScanner = false;
        List<Task> toDoList = new ArrayList<>();
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
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println("    " + (i+1) + "." + toDoList.get(i));
                }
                System.out.println("    _______________________________________________________");
            } else if(Objects.equals(toDo.split(" ")[0], "mark")) {
                System.out.println("    _______________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                int taskNumber = Integer.parseInt(toDo.split(" ")[1]);
                toDoList.get(taskNumber-1).markAsCompleted();
                System.out.println("      " + toDoList.get(taskNumber-1));
                System.out.println("    _______________________________________________________");

            } else if (Objects.equals(toDo.split(" ")[0], "unmark")) {
                System.out.println("    _______________________________________________________");
                System.out.println("    OK, I've marked this task as not done yet:");
                int taskNumber = Integer.parseInt(toDo.split(" ")[1]);
                toDoList.get(taskNumber-1).markAsUncompleted();
                System.out.println("      " + toDoList.get(taskNumber-1));
                System.out.println("    _______________________________________________________");
            }
            else {
                Task newTask = new Task(toDo);
                String returnString = "added: " + toDo;
                toDoList.add(newTask);
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
