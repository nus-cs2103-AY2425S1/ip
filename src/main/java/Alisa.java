import java.util.ArrayList;
import java.util.Scanner;

public class Alisa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String divider = "------------------------------------------------------------------------------------";
        String exitCommand = "bye";
        String input = "";
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hey, Alisa here! What do you need help with?");
        System.out.println("BTW Say the word bye to get out of this conversation");
        System.out.println(divider);

        while (true) {
            input = sc.nextLine();
            System.out.println(divider);

            if (input.equals(exitCommand)) {
                System.out.println("Since you technically said bye, see ya next time!");
                System.out.println(divider);
                sc.close();
                break;
            }
            else if (input.equals("list")) {
                if (taskList.isEmpty()) {
                    System.out.println("The list is empty, sorry :(");
                } else {
                    for (int i = 1; i <= taskList.size(); i++) {
                        System.out.println(i + ". " + taskList.get(i-1));
                    }
                    System.out.println(divider);
                }
            }
            else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("added: " + newTask);
                System.out.println(divider);
            }
        }

    }
}







