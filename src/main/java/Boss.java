import java.util.Scanner;

public class Boss {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int numTasks = 0;

        System.out.println("Hello! I'm the boss.");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);

        String task = myObj.nextLine();
        do {
            if (task.equals("list")) {
                int i = 1;
                while (i < numTasks + 1) {
                    System.out.println(i + ". " + tasks[i-1]);
                    i++;
                }
            }
            else if (task.contains("unmark") && isDigit(task)) {
                // replace all characters with nothing, in order to extract number!
                String taskNum = task.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(taskNum);
                Task item = tasks[num - 1];
                item.markAsUnDone();
                System.out.println("Ok! I have marked this task as not done yet!");
                System.out.println(item);
            }
            // need to ensure that the string contains a number!!!!
            else if (task.contains("mark") && isDigit(task)) {
                // replace all characters with nothing, in order to extract number!
                String taskNum = task.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(taskNum);
                Task item = tasks[num-1];
                item.markAsDone();
                System.out.println("Nice! I have marked this task as done!");
                System.out.println(item);
            }
            // any other way to deal with this than just if-else clauses?!
            else if (task.contains("todo")) {
                tasks[numTasks] = new Todo(task);

                numTasks = incrementAndPrint(numTasks, tasks);

            } else if (task.contains("deadline")) {
                String[] string = task.split("/by");
                tasks[numTasks] = new Deadline(string[0], string[1]);

                numTasks = incrementAndPrint(numTasks, tasks);

            } else if (task.contains("event")) {
                String[] string = task.split("/");
                String from = string[1].split("from")[1];
                String to = string[2].split("to")[1];
                tasks[numTasks] = new Event(string[0], from, to);

                numTasks = incrementAndPrint(numTasks, tasks);

            }
            else {
                System.out.println("added: " + task);
                tasks[numTasks] = new Task(task);
                numTasks++;
            }
            task = myObj.nextLine();
        } while(!task.equals("bye") && numTasks < 100);

        System.out.println("Bye. Hope to see you again soon!");
    }

    //for abstraction mainly
    public static int incrementAndPrint(int count, Task[] tasks) {
        System.out.println("Got it! I've added this task now");
        System.out.println(tasks[count]);
        int i = count + 1;
        System.out.println("Now you have " + i + " tasks in the list.");
        return i;
    }

    public static boolean isDigit(String s) {
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

}
