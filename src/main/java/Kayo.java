import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kayo {
    public static void main(String[] args) {
        List<String> taskList = new ArrayList<>();
        Greet();
        Scanner input = new Scanner(System.in);
        String inputString = "";
        while(!inputString.equals("Bye")) {
            inputString = input.nextLine();
            if(inputString.equals("Bye")) {
                break;
            } else if(inputString.equals("list")) {
                ListItems(taskList);
            } else {
                taskList.add(inputString);
                System.out.println("added: " +inputString);
            }
        }
        Exit();
    }
    public void runBot() {

    }

    private static void ListItems(List<String> taskList) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + ". "+ taskList.get(i));
        }
    }
    public static void Greet() {
        System.out.println("Hello! I'm Kayo! ");
        System.out.println("What can I do for you?");
    }
    public static void Exit(){
        System.out.println("Bye. I hope to see you again soon!");
    }
}

