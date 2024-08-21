import java.util.Scanner;
public class Chobo {
    private static final Task[] tasks = new Task[100];
    private static String line = "----------------------------------------";
    private static int totalTask = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Chobo\nWhat can I do for you?");
        System.out.println(line);
        while (true) {
            String original = scanner.nextLine();
            String[] input = original.split(" ",2);
            if (input[0].equals("bye")) {
                break;
            } else if (input[0].equals("list")) {
                listTask();
            } else if (input[0].equals("mark")){
                markTask(tasks[Integer.parseInt(input[1])-1]);
            } else if (input[0].equals("unmark")) {
                unmarkTask(tasks[Integer.parseInt(input[1])-1]);
            } else if (input[0].equals("todo")){
                addTask(new ToDo(input[1], false));
            } else if (input[0].equals("deadline")) {
                String[] part = input[1].split("/by" ,2);
                addTask(new Deadline(part[0],false, part[1]));
            } else if (input[0].equals("event")){
                String[] part = input[1].split("/from", 2);
                String[] dates = part[1].split("/to", 2);
                addTask(new Event(part[0],false, dates[0], dates[1]));

            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

    }

    private static void markTask(Task task) {
        System.out.println(line);
        task.mark();
        System.out.println("Nice! I have marked this task as done:\n" + task);
        System.out.println(line);
    }

    private static void unmarkTask(Task task) {
        System.out.println(line);
        task.unmark();
        System.out.println("OK, I have marked this task as not done yet\n" + task);
        System.out.println(line);
    }
    private static void listTask() {
        System.out.println(line);
        for (int i = 0; i < totalTask; i++) {
            System.out.println(i+1 + "." + tasks[i]);
        }
        System.out.println(line);
    }

    private static void addTask(Task newTask) {
        //Task newTask =  newToDo(newTaskName,false);
        tasks[totalTask] = newTask;
        System.out.println(line);
        System.out.println("added: " + newTask  );
        totalTask++;
        System.out.println(totalTask + " task(s) in the list");
        System.out.println(line);
    }
}





