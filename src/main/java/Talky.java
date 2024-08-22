import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;
public class Talky {
    public static void printSeperator(String content) {
        String seperator = "---------------------------------------";
        System.out.println(seperator);
        System.out.println(content);
        System.out.println(seperator);
    }
    public static void main(String[] args) {
        class Task {
            protected String name;
            protected boolean marked;

            public Task(String name) {
                this.name = name;
                this.marked = false;
            }

            public String getName() {
                return this.name;
            }

            public boolean getMarked() {
                return this.marked;
            }
            public void setMark(boolean marked) {
                this.marked = marked;
            }
        }
        ArrayList<Task> userTasks = new ArrayList<Task>();

        printSeperator("Hello I'm Talky\n" + "How may I help you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                String textsList = "";
                int rank = 1;
                for (Task task : userTasks) {
                    String Marked = task.getMarked() ? "[X]" : "[ ]";
                    textsList += rank + "." + Marked + task.getName() + "\n";
                    rank++;
                }
                printSeperator(textsList);
            } else if (command.split(" ")[0].equals("mark")){
                int indexToChange = Integer.parseInt(command.split(" ")[1]) - 1;
                Task changedTask = userTasks.get(indexToChange);
                changedTask.setMark(true);
                printSeperator("I've marked this task as done: " + changedTask.getName());
            }  else if (command.split(" ")[0].equals("unmark")){
                int indexToChange = Integer.parseInt(command.split(" ")[1]) - 1;
                Task changedTask = userTasks.get(indexToChange);
                changedTask.setMark(false);
                printSeperator("I've marked this task as not done: " + changedTask.getName());
            } else {
                userTasks.add(new Task(command));
                printSeperator("added: " + command);
            }
        }

        printSeperator("Bye. Do let me know if there's anything else!");
    }
}
