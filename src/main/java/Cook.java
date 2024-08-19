import java.util.Scanner;
import java.util.ArrayList;

public class Cook {
    public static void main(String[] args) {
        // Solution below adapted from https://www.patorjk.com/software/taag/#p=author&v=0&f=Avatar&t=Cook
        String logo = """ 
                          ____  ____  ____  _  __
                         /   _\\/  _ \\/  _ \\/ |/ /
                         |  /  | / \\|| / \\||   /\s
                         |  \\__| \\_/|| \\_/||   \\\s
                         \\____/\\____/\\____/\\_|\\_\\
                                                \s
                         """;
        System.out.print(logo);
        formatting("Hello, I'm Cook!\nWhat can I do for you?");

        // Solution below inspired by https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html
        ArrayList<String> toDoList = new ArrayList<String>();

        while (true) {
            // Solution below inspired by https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
            Scanner input = new Scanner(System.in);
            String echo = input.nextLine();
            // Breaks loop
            if (echo.equalsIgnoreCase("bye")) {
                break;
            }
            // Prints out to do list
            else if (echo.equalsIgnoreCase("list")) {
                StringBuilder taskList = new StringBuilder();
                for (int i = 0; i < toDoList.size(); i++) {
                    int taskNo = i + 1;
                    taskList.append(taskNo).append(". ").append(toDoList.get(i)).append("\n");
                }
                formatting(taskList.toString().stripTrailing());
            }
            else {
                toDoList.add(echo);
                formatting("I've added " + echo + " to your to do list!");
            }
        }

        formatting("Bye. Hope to see you again soon!");
    }

    public static void formatting(String text) {
        System.out.println("____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________");
    }
}
