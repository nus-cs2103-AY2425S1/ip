import java.util.Scanner;
public class Bean {
    public static void main(String[] args) {
        String greeting = "________________________________\n"
                + "Hello! I'm Bean\n"
                + "What can i do for you?\n"
                +"________________________________\n";
        String byeMsg =
                 "________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "________________________________\n";
        System.out.println(greeting);
        Scanner scanner = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int pointer = 0;
        while (true) {
            String response = scanner.nextLine();
            String[] splited = response.split(" ");
            if (splited[0].equals("todo") || splited[0].equals("event") || splited[0].equals("deadline")) {
                String output = "________________________________\n" + "Got it. I've added this task:";
                System.out.println(output);
                Task current = null;
                switch (splited[0]) {
                    case "todo":
                        current = new Todo(response.replace("todo ",""));
                        break;
                    case "event":
                        current = new Event(response.replace("event ",""));
                        break;
                    case "deadline":
                        current = new Deadline(response.replace("deadline ",""));
                        break;
                }
                taskList[pointer] = current;
                pointer++;
                System.out.println(current.getString());
                output = "Now you have " + String.valueOf(pointer) + " tasks in the list.\n" + "________________________________";
                System.out.println(output);
            } else if (response.equals("list")) {
                System.out.println("________________________________");
                for (int i = 0; i < pointer; i++) {
                    String output = String.valueOf(i + 1) + ". " + taskList[i].getString();
                    System.out.println(output);
                }
                System.out.println("________________________________");
            } else if (splited[0].equals("mark")) {
                int index = Integer.parseInt(splited[1]) - 1;
                Task curr = taskList[index];
                String msg = curr.mark();
                System.out.println("________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(msg);
                System.out.println("________________________________");
            } else if (splited[0].equals("unmark")) {
                int index = Integer.parseInt(splited[1]) - 1;
                Task curr = taskList[index];
                String msg = curr.mark();
                System.out.println("________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(msg);
                System.out.println("________________________________");
            } else if (splited[0].equals("bye")){
                System.out.println(byeMsg);
                break;
            }
        }
    }
}
