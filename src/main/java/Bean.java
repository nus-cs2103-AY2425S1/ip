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
        String[] taskList = new String[100];
        int pointer = 0;
        while (true) {
            String response = scanner.nextLine();
            if (!response.equals("bye") && !response.equals("list")) {
                String output = "________________________________\n" + "added: " + response + "\n" + "________________________________\n";
                System.out.println(output);
                taskList[pointer] = response;
                pointer++;
            } else if (response.equals("list")) {
                System.out.println("________________________________\n");
                for (int i = 0; i < pointer; i++) {
                    String output = String.valueOf(i + 1) + ". " + taskList[i];
                    System.out.println(output);
                }
                System.out.println("________________________________\n");
            }
            else {
                System.out.println(byeMsg);
                break;
            }
        }
    }
}
