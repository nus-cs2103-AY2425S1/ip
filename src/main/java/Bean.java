import java.util.Scanner;
import java.util.ArrayList;
public class Bean {
    public static void main(String[] args) {
        String greeting = "________________________________\n"
                + "Hello! I'm Bean\n"
                + "What can i do for you?\n"
                +"________________________________";
        String byeMsg =
                 "________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "________________________________";
        System.out.println(greeting);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        int pointer = 0;
        while (true) {
            String response = scanner.nextLine();
            String[] splited = response.split(" ");
            if (splited[0].equals("todo") || splited[0].equals("event") || splited[0].equals("deadline")) {
                Task current = null;
                try {
                    switch (splited[0]) {
                        case "todo":
                            current = new Todo(response.replace("todo ", ""));
                            break;
                        case "event":
                            current = new Event(response.replace("event ", ""));
                            break;
                        case "deadline":
                            current = new Deadline(response.replace("deadline ", ""));
                            break;
                    }
                    taskList.add(current);
                    pointer++;
                    String output = "________________________________\n" + "Got it. I've added this task:";
                    System.out.println(output);
                    System.out.println(current.getString());
                    output = "Now you have " + String.valueOf(pointer) + " tasks in the list.\n" + "________________________________";
                    System.out.println(output);
                } catch (DukeException e) {
                    System.out.println("________________________________");
                    System.out.println(e.getMessage() + "________________________________");
                }
            } else if (response.equals("list")) {
                System.out.println("________________________________");
                for (int i = 0; i < pointer; i++) {
                    String output = String.valueOf(i + 1) + ". " + taskList.get(i).getString();
                    System.out.println(output);
                }
                System.out.println("________________________________");
            } else if (splited[0].equals("mark")) {
                int index = Integer.parseInt(splited[1]) - 1;
                try {
                    if (index < 0 || index > pointer) {
                        throw new DukeException("Invalid position!");
                    }
                    Task curr = taskList.get(index);
                    if (curr.isDone) {
                        throw new DukeException("It is already marked!");
                    }
                    String msg = curr.mark();
                    System.out.println("________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(msg);
                    System.out.println("________________________________");
                } catch (DukeException e) {
                    System.out.println("________________________________");
                    System.out.println(e.getMessage() + "\n________________________________");
                }
            } else if (splited[0].equals("unmark")) {
                int index = Integer.parseInt(splited[1]) - 1;
                try {
                    if (index < 0 || index > pointer) {
                        throw new DukeException("Invalid position!");
                    }
                    Task curr = taskList.get(index);
                    if (!curr.isDone) {
                        throw new DukeException("It is already unmarked!");
                    }
                    String msg = curr.mark();
                    System.out.println("________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(msg);
                    System.out.println("________________________________");
                } catch (DukeException e) {
                    System.out.println("________________________________");
                    System.out.println(e.getMessage() + "\n________________________________");
                }
            } else if (splited[0].equals("delete")) {
                int index = Integer.parseInt(splited[1]) - 1;
                try {
                    if (index < 0 || index > pointer) {
                        throw new DukeException("Invalid position!");
                    }
                    System.out.println("________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskList.remove(index).getString());
                    pointer--;
                    System.out.println( "Now you have " + String.valueOf(pointer) + " tasks in the list.\n" + "________________________________");
                } catch (DukeException e) {
                    System.out.println("________________________________");
                    System.out.println(e.getMessage() + "\n________________________________");
                }
            } else if (splited[0].equals("bye")){
                System.out.println(byeMsg);
                break;
            } else {
                try {
                    throw new DukeException("I dont understand what you are trying to say :(");
                } catch (DukeException e) {
                    System.out.println("________________________________");
                    System.out.println(e.getMessage() + "\n________________________________");
                }
            }
        }
    }
}
