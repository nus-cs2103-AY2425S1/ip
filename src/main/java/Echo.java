import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class Echo {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        String logo = "____________________________________________________________\n";
        System.out.println("Hello, I'm Echo\n" + logo);
        String input = null;
        while (!Objects.equals(input, "bye")) {
            input = myObj.nextLine();
            String[] parts = input.split(" ");
            if (Objects.equals(input, "bye")) {
                System.out.println("Bye bye...");
            } else if (Objects.equals(input, "list")) {

                int count = 1;
                for (Task task : tasks) {
                    System.out.println(count + ". " + task);
                    count++;
                }
            } else if (input.startsWith("mark ") && parts.length == 2) {
                int num = Integer.parseInt(parts[1]);
                Task doneTask =tasks.get(num - 1);
               doneTask.setDone();
                System.out.println("Nice! I've marked this task as done:\n" + doneTask);

            } else if (input.startsWith("unmark ") && parts.length == 2) {
                int num = Integer.parseInt(parts[1]);
                Task undoneTask =tasks.get(num - 1);
                undoneTask.setUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + undoneTask);

            }else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println("added: " + input);
            }
        }
    }

    public static class Task {
        boolean isDone;
        String taskDes;

        public Task(String taskDes){
            this.taskDes = taskDes;
            isDone = false;
        }
        public void setDone(){
            isDone = true;
        }

        public void setUndone(){
            isDone = false;
        }

        public String toString(){
            if(isDone){
                return "[X]" + taskDes;
            } else {
                return "[ ]" + taskDes;
            }
        }
    }
}
