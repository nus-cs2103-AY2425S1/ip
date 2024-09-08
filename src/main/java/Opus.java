import java.util.Scanner;

public class Opus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Hello! I'm Opus");
        System.out.println(" What can I do for you?");

        Task[] tasks = new Task[101];
        int taskCount = 0;

        while(true) {
            String s = scanner.nextLine();
            String[] words = s.split(" ");

            try{
                if (s.equals("bye")) {
                    break;
                } else if (s.equals("list")) {
                    for (int i = 1; i <= taskCount; i++) {
                        System.out.println(i + ". " + tasks[i]);
                    }
                } 
                else if (words[0].equals("mark")) {
                    int i = Integer.parseInt(words[1]);
                    tasks[i].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[i]);
                } 
                else if (words[0].equals("delete")) {
                    int i = Integer.parseInt(words[1]);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("   " + tasks[i]);
                    for (int j = i; j < taskCount; j++) {
                        tasks[j] = tasks[j + 1]; 
                    }
                    taskCount--; 
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                } 
                else {
                    if (words[0].equals("todo")) {
                        if (words.length <= 1) {
                            throw new OpusEmptyDescriptionException("The description of a todo cannot be empty.");
                        }
                        tasks[++taskCount] = new ToDo(s.substring(5));
                    } else if (words[0].equals("deadline")) {
                        String[] parts = s.substring(9).split(" /by ");
                        tasks[++taskCount] = new Deadline(parts[0], parts[1]);
                    } else if (words[0].equals("event")) {
                        String[] parts = s.substring(6).split(" /from ");
                        String[] parts2 = parts[1].split(" /to ");
                        tasks[++taskCount] = new Event(parts[0], parts2[1], parts2[0]);
                    }
                    else{
                        throw new OpusUnknownCommandException("I'm sorry, but I don't know what that means.");
                    }
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                }
            }
            catch (OpusException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(" Bye. Hope to see you again soon!");
        scanner.close();
    }
}
