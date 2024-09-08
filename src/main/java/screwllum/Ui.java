package screwllum;

import screwllum.tasks.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    
    public String getInput() {
        return sc.nextLine();
    }
    
    public void showWelcome() {
        print("Pleased to meet you");
    }
    
    public void showLoadingError() {
        print("Sorry, you are trying to load a file that doesn't exist or is invalid");
    }
    
    public void showMessage(List<String> tokens, List<Task> taskList) {
        switch (tokens.get(0)) {
        case "bye":
            print("Good bye");
            System.exit(0);
        case "list":
            for (Task task : taskList) {
                print(task.toString());
            }
            print("You have " + taskList.size() + " tasks" );
            break;
        case "toggle":
            print("I have toggled the status of this task:");
            print(taskList.get(Integer.parseInt(tokens.get(1))).toString());
            break;
        case "delete":
            print("I have deleted this task: " + tokens.get(1));
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            print("I have added the following task:");
            print(taskList.get(taskList.size() - 1).toString());
            break;
        default:
            print("I'm afraid I do not recognise that command");
        }
    }
    
    private void print(String message) {
        System.out.println(message);
    } 
}
