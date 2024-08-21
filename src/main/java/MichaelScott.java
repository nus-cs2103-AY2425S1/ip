import java.util.Scanner;
import java.util.ArrayList;
public class MichaelScott {
    public static void main(String[] args) throws MichaelScottException{
        Scanner myScanner = new Scanner(System.in);
        boolean flag = true;
        ArrayList<Task> todo = new ArrayList<Task>();

//        String logo = " __  __ _      _                _   ____            _   _  \n"
//                + "|  \\/  (_) ___| |__   __ _  ___| | / ___|  ___ ___ | |_| |_ \n"
//                + "| |\\/| | |/ __| '_ \\ / _` |/ _ \\ | \\___ \\ / __/ _ \\| __| __|\n"
//                + "| |  | | | (__| | | | (_| |  __/ |  ___) | (_| (_) | |_| |_ \n"
//                + "|_|  |_|_|\\___|_| |_|\\__,_|\\___|_| |____/ \\___\\___/ \\__|\\__|\n";

        String logo = ".___  ___.  __    ______  __    __       ___       _______  __          _______.  ______   ______   .___________.___________.\n"
                    + "|   \\/   | |  |  /      ||  |  |  |     /   \\     |   ____||  |        /       | /      | /  __  \\  |           |           |\n"
                    + "|  \\  /  | |  | |  ,----'|  |__|  |    /  ^  \\    |  |__   |  |       |   (----`|  ,----'|  |  |  | `---|  |----`---|  |----`\n"
                    + "|  |\\/|  | |  | |  |     |   __   |   /  /_\\  \\   |   __|  |  |        \\   \\    |  |     |  |  |  |     |  |        |  |\n"
                    + "|  |  |  | |  | |  `----.|  |  |  |  /  _____  \\  |  |____ |  `----.----)   |   |  `----.|  `--'  |     |  |        |  | \n"
                    + "|__|  |__| |__|  \\______||__|  |__| /__/     \\__\\ |_______||_______|_______/     \\______| \\______/      |__|        |__|\n";




//        System.out.println(logo);
        System.out.println("Running Micheal Scott.exe");
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Michael Scott");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while(flag) {
            String command = myScanner.nextLine();
            String[] parts = command.split(" ", 2);
            String action = parts[0];
            switch (action) {
                case "list" -> {
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < todo.size(); i++) {
                        System.out.println(i + 1 + ". " + todo.get(i).toString());
                    }
                    System.out.println("____________________________________________________________");
                }
                case "bye" -> {
                    System.out.println("____________________________________________________________");
                    System.out.println("Catch you on the flippity flip! ");
                    System.out.println("____________________________________________________________");
                    flag = false;
                }
                case "mark" -> {
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task task = todo.get(index);
                    task.completeTask();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(task.toString());
                    System.out.println("____________________________________________________________");
                }
                case "unmark" -> {
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task task = todo.get(index);
                    task.undoTask();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task.toString());
                    System.out.println("____________________________________________________________");
                }
                case "delete" -> {
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task task = todo.get(index);
                    todo.remove(index);
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + todo.size() + (todo.size() > 1 ? " tasks" : (" task")) + " in the list.");
                    System.out.println("____________________________________________________________");
                }
                case "todo" -> {
                    try {
                        if (parts.length < 2) {
                            throw new MichaelScottException("Please specify the task you intend to do.");
                        }

                        todo.add(new Todo(parts[1]));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("    " + todo.get(todo.size() - 1).toString());
                        System.out.println("Now you have " + todo.size() + (todo.size() > 1 ? " tasks" : (" task")) + " in the list.");
                        System.out.println("____________________________________________________________");

                    } catch (MichaelScottException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println(e.getMessage());
                        System.out.println("____________________________________________________________");
                    }
                }
                case "deadline" -> {

                    try {
                        String[] deadlineParts = parts[1].split(" /by ");
                        if (deadlineParts.length < 2) {
                            throw new MichaelScottException("Please specify the deadline as well.");
                        }

                        todo.add(new Deadline(deadlineParts[0], deadlineParts[1]));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("    " + todo.get(todo.size() - 1).toString());
                        System.out.println("Now you have " + todo.size() + (todo.size() > 1 ? " tasks" : (" task" )) + " in the list.");
                        System.out.println("____________________________________________________________");
                    } catch (MichaelScottException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println(e.getMessage());
                        System.out.println("____________________________________________________________");
                    }
                }
                case "event" -> {
                    try {

                        String[] eventParts = parts[1].split(" /from | /to ");
                        if (eventParts.length != 3) {
                            throw new MichaelScottException("Please specify description, from and to");
                        }
                        todo.add(new Event(eventParts[0], eventParts[1], eventParts[2]));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("    " + todo.get(todo.size() - 1).toString());
                        System.out.println("Now you have " + todo.size() + (todo.size() > 1 ? " tasks" : (" task" )) + " in the list.");
                        System.out.println("____________________________________________________________");
                    } catch (MichaelScottException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println(e.getMessage());
                        System.out.println("____________________________________________________________");
                    }
                }
                default -> {
                    try {
                        throw new MichaelScottException("I don't understand what you mean to say!");
                    } catch (MichaelScottException e){
                        System.out.println("____________________________________________________________");
                        System.out.println(e.getMessage());
                        System.out.println("____________________________________________________________");
                    }
                }
            }

        }
    }
}
