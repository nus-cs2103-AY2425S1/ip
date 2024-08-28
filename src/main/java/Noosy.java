import java.util.ArrayList;
import java.util.Scanner;

public class Noosy {

    public static void main(String[] args) {

        // Constant statements
        String greeting = "Heyo! This is Noosy! \n" +
                "Need me to keep track of anything?";
        String goodbye = "Alright, see ya!";
        String done = "Hooray you've done this: \n";
        String undo = "Ok don't worry, you can continue working on this: \n";
        String delete = "Oo, removing the irrelevant task: \n";

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting);
        int totalTasks = -1;

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            String[] separated = userInput.split(" ", 2);
            String command = separated[0];
            String desc = separated.length > 1 ? separated[1] : "";

            switch (command) {
                case "list":
                    System.out.println("Heyo here are the task(s) we have: ");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;

                case "mark":
                    int taskNum = Integer.parseInt(desc);
                    Task completed = tasks.get(taskNum - 1);
                    completed.isDone();
                    System.out.println(done + completed);
                    break;

                case "unmark":
                    taskNum = Integer.parseInt(desc);
                    Task uncomplete = tasks.get(taskNum - 1);
                    uncomplete.unDone();
                    System.out.println(undo + uncomplete);
                    break;

                case "delete":
                    taskNum = Integer.parseInt(desc);
                    Task toDelete = tasks.get(taskNum - 1);
                    tasks.remove(toDelete);
                    System.out.println(delete + toDelete);
                    totalTasks = tasks.size();
                    System.out.println("We're left with " + totalTasks + " tasks in the bucket!");
                    break;

                case "todo":
                    if (desc.equals("")) {
                        System.out.println("I think you forgot the description?");
                        break;
                    }

                    Todo todo = new Todo(desc);
                    tasks.add(todo);
                    System.out.println("I added it to the list! \n" + todo);
                    totalTasks = tasks.size();
                    System.out.println("We've now got " + totalTasks + " tasks in the bucket!");
                    break;

                case "deadline":
                    String[] withDue = desc.split(" /by ");
                    if (withDue.length < 2) {
                        // error msg
                        System.out.println("I think you forgot the description / deadline?");
                        break;
                    }

                    Deadline deadline = new Deadline(withDue[0], withDue[1]);
                    tasks.add(deadline);
                    System.out.println("I added it to the list! \n" + deadline);
                    totalTasks = tasks.size();
                    System.out.println("We've now got " + totalTasks + " tasks in the bucket!");
                    break;

                case "event":
                    String[] withDuration = desc.split(" /from | /to ");
                    if (withDuration.length < 3) {
                        // error msg
                        System.out.println("I think you forgot the description / duration?");
                        break;
                    }

                    Event event = new Event(withDuration[0], withDuration[1], withDuration[2]);
                    tasks.add(event);
                    System.out.println("I added it to the list! \n" + event);
                    totalTasks = tasks.size();
                    System.out.println("We've now got " + totalTasks + " tasks in the bucket!");
                    break;

                default:
                    System.out.println("Huh? idgi...");
                    break;
            }
            userInput = scanner.nextLine();
        }

        System.out.println(goodbye);
        scanner.close();
    }
}