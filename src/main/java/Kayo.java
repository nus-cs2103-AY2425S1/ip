import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kayo {
    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();
        Greet();
        Scanner input = new Scanner(System.in);
        String inputString = "";
        while(true) {
            inputString = input.nextLine();
            if(inputString.equals("bye")) {
                break;
            } else if(inputString.equals("list")) {
                ListItems(taskList);
            } else if (inputString.contains("unmark")) {
                String[] splitList = inputString.split(" ");
                int index = Integer.parseInt(splitList[1])-1;
                taskList.get(index).setDone(false);

                System.out.print("OK, I've marked this task as not done yet:\n");
                System.out.print(taskList.get(index).toString());
                System.out.print("\n");

            } else if(inputString.contains("mark")) {
                String[] splitList = inputString.split(" ");
                int index = Integer.parseInt(splitList[1]) -1;
                taskList.get(index).setDone(true);

                System.out.print("Nice! I've marked this task at done:\n");
                System.out.print(taskList.get(index).toString());
                System.out.print("\n");
            } else {
                Task t = new Task(inputString,false);
                taskList.add(t);
                System.out.println("added: " +inputString);
            }
        }
        Exit();
    }
    public void runBot() {

    }
    private static class Task{

        private String task;
        private boolean isDone;

        public Task(String task, boolean isDone){
            this.task = task;
            this.isDone = isDone;
        }
        public void setDone(boolean isDone){
            this.isDone = isDone;
        }
        public String toString(){
            String taskString = (isDone) ? "[X]": "[ ]";
            return taskString + task;
        }
    }
    private static void ListItems(List<Task> taskList) {
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

