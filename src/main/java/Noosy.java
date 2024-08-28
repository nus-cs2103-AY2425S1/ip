import java.util.ArrayList;
import java.util.Scanner;

public class Noosy {

    public static void main(String[] args) {

        // constant statements
        String greeting = "Heyo! This is Noosy! \n" +
                "What do ya need from me?";
        String goodbye = "Alright, see ya!";
        String done = "Hooray you've done this: \n";
        String undo = "Ok don't worry, just keep working on: \n";

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting);

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            String[] separated = userInput.split(" ");
            String command = separated[0];
            String taskNum = separated.length > 1 ? separated[1] : "";

            switch (command) {
                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;


                case "mark":
                    Task completed = tasks.get(Integer.valueOf(taskNum) - 1);
                    completed.isDone();
                    System.out.println(done + completed);
                    break;

                case "unmark":
                    Task uncomplete = tasks.get(Integer.valueOf(taskNum) - 1);
                    uncomplete.unDone();
                    System.out.println(undo + uncomplete);
                    break;

                default:
                    Task task = new Task(userInput);
                    tasks.add(task);
                    System.out.println("added: " + task.getDesc());
                    break;
            }
            userInput = scanner.nextLine();
        }

        // after "bye"
        System.out.println(goodbye);
        scanner.close();
    }
}
