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
            } else if (first.equals("todo")) {
                Task todo = new Todo(part[1]);
                tasks[counter] = todo;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + counter + " tasks in the list.");
            } else if (first.equals("deadline")) {
                String[] specifications = part[1].split(" /by ");
                Task deadline = new Deadline(specifications[0], specifications[1]);
                tasks[counter] = deadline;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + counter + " tasks in the list.");
            } else if (first.equals("event")) {
                String[] firstpart = part[1].split(" /from ", 2);
                String[] secondpart = firstpart[1].split(" /to", 2);
                Task event = new Event(firstpart[0].trim(), secondpart[0].trim(), secondpart[1].trim());
                tasks[counter] = event;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + counter + " tasks in the list.");
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