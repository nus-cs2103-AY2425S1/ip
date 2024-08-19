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
            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(taskList);
                System.out.println("____________________________________________________________");
            } else if (userInput.contains("mark")){
                String[] parts = userInput.split(" ");
                    String word = parts[0];
                    int num = Integer.parseInt(parts[1]);
                    if (word.equals("mark")) {
                        taskList.markTaskAsDone(num);
                    } else if (word.equals("unmark")) {
                        taskList.markTaskAsNotDone(num);
                    }    
            } else {
                taskList.addTask(userInput);
            }
            userInput = scanner.nextLine();
        } 
        System.out.println("Bye! Hope to see you again soon!");
        scanner.close();
    }
}
