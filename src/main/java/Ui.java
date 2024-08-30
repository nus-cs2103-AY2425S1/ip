import task.Task;

import java.util.ArrayList;

public class Ui {
    
    public Ui() {
        // nothing
    }
    
    public void greet() {
        System.out.println("Hi! Nice to meet you :) I am Maxine");
    }
    
    public void goodbye() {
        System.out.println("\nBye! I have been maxed out and am going to sleep. " +
                "Hope to see you again soon!");
    }
    
    public void delete(Task task) {
        System.out.println("Deleting this task: " + task);
    }
    
    public void mark(Task task, Boolean isDone) {
        if (isDone) {
            System.out.println("Yay! You finally did something today");
            System.out.println(task);
        } else {
            System.out.println("Skill issue...");
            System.out.println(task);
        }
    }
    
    public void showList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }
    
    public void showLoadingError() {
        System.out.println("I...can't...load...X_X");
    }
    
    public void showError(String e) {
        System.out.print(e);
    }
}
