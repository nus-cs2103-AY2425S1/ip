import java.util.Scanner;
import java.util.ArrayList;

public class Bob {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("------------------------------------------");
        System.out.println("Hello! I'm Bob!\nHow can I help you today?");
        System.out.println("------------------------------------------");

        while (!input.equals("bye")) {
            input = scanner.nextLine().trim(); // trim to remove any whitespace before input
            String[] inputParts = input.split(" ", 2); // split input and store into array
            String command = inputParts[0].toLowerCase(); // lowercase for comparison
            String taskDescription = (inputParts.length <= 1) ? "" : inputParts[1];

            try {
                switch (command) {
                    case "list":
                        // print tasks
                        System.out.println("------------------------------------------");
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i).toString());
                        }
                        System.out.println("------------------------------------------");
                        break;

                    case "mark":
                        int taskIndexMark = Integer.parseInt(taskDescription) - 1;
                        if (taskIndexMark < tasks.size() && taskIndexMark >= 0) {
                            tasks.get(taskIndexMark).mark();
                            System.out.println("------------------------------------------");
                            System.out.println("Yay! I've marked this task as done:");
                            System.out.println("[" + tasks.get(taskIndexMark).getStatusIcon() + "] " + tasks.get(taskIndexMark).getDescription());
                            System.out.println("------------------------------------------");
                        } else {
                            System.out.println("------------------------------------------");
                            System.out.println("Invalid index :(");
                            System.out.println("------------------------------------------");
                        }
                        break;

                    case "unmark":
                        int taskIndexUnmark = Integer.parseInt(taskDescription) - 1;
                        if (taskIndexUnmark < tasks.size() && taskIndexUnmark >= 0) {
                            tasks.get(taskIndexUnmark).unmark();
                            System.out.println("------------------------------------------");
                            System.out.println("Alright, I've marked this task as not done yet:");
                            System.out.println("[" + tasks.get(taskIndexUnmark).getStatusIcon() + "] " + tasks.get(taskIndexUnmark).getDescription());
                            System.out.println("------------------------------------------");
                        } else {
                            System.out.println("------------------------------------------");
                            System.out.println("Invalid index :(");
                            System.out.println("------------------------------------------");
                        }
                        break;

                    case "todo":
                        if (taskDescription.isEmpty()) {
                            throw new BobException("Description of the todo cannot be empty :(");
                        }
                        tasks.add(new Todo(taskDescription));
                        System.out.println("------------------------------------------");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1).toString());
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                        System.out.println("------------------------------------------");
                        break;

                    case "deadline":
                        if (taskDescription.isEmpty()) {
                            throw new BobException("Missing deadline description :(");
                        }
                        String[] dlParts = taskDescription.split(" /by ");
                        if (dlParts.length < 2) {
                            throw new BobException("Missing details :(\nPlease use this format: 'deadline [description] /by [deadline]' :(");
                        }
                        tasks.add(new Deadline(dlParts[0], dlParts[1]));
                        System.out.println("------------------------------------------");
                        System.out.println("Ok! I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1).toString());
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                        System.out.println("------------------------------------------");
                        break;

                    case "event":
                        if (taskDescription.isEmpty()) {
                            throw new BobException("Description of the event is missing :(");
                        } else if (!taskDescription.contains(" /from ") || !taskDescription.contains(" /to ")) {
                            throw new BobException("Missing details :(\nPlease use this format: event [description] /from [start] /to [end]");
                        }
                        String[] eventParts = taskDescription.split(" /from | /to ");
                        tasks.add(new Event(eventParts[0], eventParts[1], eventParts[2]));
                        System.out.println("------------------------------------------");
                        System.out.println("Ok! I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1).toString());
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                        System.out.println("------------------------------------------");
                        break;

                    case "delete":
                        int taskIndexDelete = Integer.parseInt(taskDescription) - 1;
                        if (taskIndexDelete < tasks.size() && taskIndexDelete >= 0) {
                            Task removedTask = tasks.remove(taskIndexDelete);
                            System.out.println("------------------------------------------");
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + removedTask.toString());
                            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                            System.out.println("------------------------------------------");
                        } else {
                            System.out.println("------------------------------------------");
                            System.out.println("Invalid index :(");
                            System.out.println("------------------------------------------");
                        }
                        break;


                    case "bye":
                        // exit
                        System.out.println("------------------------------------------");
                        System.out.println("Bye, see you again :)");
                        System.out.println("------------------------------------------");
                        scanner.close();
                        return;

                    default:
                        throw new BobException("Bob does not understand that command, sorry :(");
                }
            } catch (BobException e) {
                System.out.println("------------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("------------------------------------------");
            }
        }
    }
}


