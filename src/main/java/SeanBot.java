import java.util.Scanner;
public class SeanBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm SeanBot\n" + "What can I do for you?");
        Task[] tasks = new Task[100];
        int counter = 0;
        
        Scanner scanner = new Scanner(System.in);
        while(true){
            String userInput = scanner.nextLine();
            String[] part = userInput.split(" ", 2);
            String first = part[0];
            if (first.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (first.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (first.equals("mark")) {
                int second = Integer.parseInt(part[1]) - 1;
                tasks[second].Done();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[second]);
            } else if (first.equals("unmark")) {
                int second = Integer.parseInt(part[1]) - 1;
                tasks[second].Undone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[second]);
            }
            else {
                tasks[counter] = new Task(userInput);
                counter++;
                System.out.println("added: " + userInput);
            }
        }
        scanner.close();
    }
}