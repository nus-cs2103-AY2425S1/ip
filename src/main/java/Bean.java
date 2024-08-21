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
            if (!response.equals("bye") && !response.equals("list") && !splited[0].equals("mark") && !splited[0].equals("unmark")) {
                String output = "________________________________\n" + "added: " + response + "\n" + "________________________________\n";
                System.out.println(output);
                taskList[pointer] = new Task(response);
                pointer++;
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
