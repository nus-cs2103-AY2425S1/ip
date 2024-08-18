import java.util.Scanner;
import java.util.ArrayList;

public class Rizzler {
    public static void main(String[] args) {
        String separator = "_______________________________________________________\n";
        String greeting = separator
                + "Hello! I'm the Rizzler.\n"
                + "What can I do for you today?\n"
                + separator;
        String goodbye = separator
                + "Bye! Rizz you later!\n"
                + separator;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();

        System.out.println(greeting);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(goodbye);
                break;
            } else if (input.equals("list")) {
                if (taskList.isEmpty()) {
                    System.out.println(separator
                            + "No tasks here yet\n"
                            + separator);
                } else {
                    System.out.println(separator);
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(String.valueOf(i + 1) + ". " + taskList.get(i));
                    }
                    System.out.println(separator);
                }
            } else {
                taskList.add(input);
                System.out.println(separator
                        + "added: " + input + "\n"
                        + separator);
            }
        }
    }
}
