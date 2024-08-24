import java.util.Scanner;

public class Diomon {
    public static void greeting() {
        String greetingMessage = """
                           Hello! I'm Diomon
                           What do you need recorded?
                           """;
        System.out.print(greetingMessage);
    }
    public static void bye() {
        String byeMessage = """
                            ________________________________________________________________
                            Bye. Hope to see you again soon!
                            ________________________________________________________________
                            """;
        System.out.println(byeMessage);
    }
    public static void echo() {
        // Initialise instance
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        greeting();
        while(true) {
            String input = scanner.nextLine();
            String inputcheck =input.toLowerCase();
            if (inputcheck.equals("bye")) {
                bye();
                break;
            }
            System.out.println("________________________________________________________________");
            if (inputcheck.contains("mark")){
                String[] temp = inputcheck.split(" ");
                if (temp.length == 2 && temp[0].equals("mark")) {
                    Integer i = Integer.parseInt(temp[1]);
                    System.out.printf("Task %d: [%s] has been marked", i, taskList.get(i - 1));
                    taskList.mark( i- 1);
                } else if (temp.length == 2 && temp[0].equals("unmark")) {
                    Integer i = Integer.parseInt(temp[1]);
                    System.out.printf("Task %d: [%s] has been unmarked", i, taskList.get(i - 1));
                    taskList.unmark( i- 1);
                } else {
                    taskList.add(new Task(input));
                    System.out.printf("New Task: [%s] has been added.\n", input);
                    System.out.print(taskList);
                }
            } else {
                taskList.add(new Task(input));
                System.out.printf("New Task: [%s] has been added.\n", input);
                System.out.print(taskList);
            }
            System.out.println("________________________________________________________________");
        }
    }

    public static void main(String[] args) {
        echo();
    }
}
