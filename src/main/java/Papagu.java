import java.util.Scanner;

public class Papagu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        System.out.println("____________________________________________________________");
        System.out.println("Hello from Papagu!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            //If input is list return TaskList
            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                System.out.println(taskList);
                System.out.println("____________________________________________________________");
            } else if (userInput.contains("mark")){
                String[] parts = userInput.split(" ");
                    String word = parts[0];
                    int num = Integer.parseInt(parts[1]) - 1;
                    if (word.equals("mark")) {
                        System.out.println("____________________________________________________________");
                        taskList.markTaskAsDone(num);
                        System.out.println("____________________________________________________________");
                    } else if (word.equals("unmark")) {
                        System.out.println("____________________________________________________________");
                        taskList.markTaskAsNotDone(num);
                        System.out.println("____________________________________________________________");
                    }    
            } else if (userInput.contains("todo")) {
                String[] input = userInput.split(" ", 2);
                String description = input[1];
                ToDos newToDo = new ToDos(description);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                taskList.addTask(newToDo);
                System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                
            } else if (userInput.contains("deadline")) {
                String[] input = userInput.split(" ", 2);
                String[] parts = input[1].split(" /by ");
                String description = parts[0];
                String time = parts[1];
                Deadlines newDeadline = new Deadlines(description, time);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                taskList.addTask(newDeadline);
                System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userInput.contains("event")) {
                String[] input = userInput.split(" ", 2);
                String[] parts = input[1].split(" /from ");
                String description = parts[0];
                String time = parts[1];
                String[] duration = time.split(" /to ");
                String start = duration[0];
                String end = duration[1];
                Events newEvent = new Events(description, start, end);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                taskList.addTask(newEvent);
                System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } 
            userInput = scanner.nextLine();
        } 
        System.out.println("Bye! Hope to see you again soon!");
        scanner.close();
    }
}
