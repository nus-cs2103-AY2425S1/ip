import java.util.Scanner;
import java.util.ArrayList;
public class MichaelScott {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) throws MichaelScottException{
        Scanner myScanner = new Scanner(System.in);
        boolean flag = true;
        ArrayList<Task> todo = new ArrayList<Task>();


        System.out.println("Running MichaelScott.exe");
        printLine();
        System.out.println("Hello! I'm Michael Scott");
        System.out.println("What can I do for you?");
        printLine();

        while(flag) {
            String command = myScanner.nextLine();
            String[] parts = command.split(" ", 2);
            String action = parts[0];
            switch (action) {
                case "list" -> {
                    printLine();
                    for (int i = 0; i < todo.size(); i++) {
                        System.out.println(i + 1 + ". " + todo.get(i).toString());
                    }
                    printLine();
                }
                case "bye" -> {
                    printLine();
                    System.out.println("Catch you on the flippity flip! ");
                    printLine();
                    flag = false;
                }
                case "mark" -> {
                    try {
                        if (parts.length < 2 || parts[1].isBlank()) {
                            throw new MichaelScottException("Please provide the number of task to be marked as completed");
                        }
                        int index = Integer.parseInt(parts[1]) - 1;
                        if (index > todo.size()) {
                            throw new MichaelScottException("Please provide a task in range");
                        }
                        Task task = todo.get(index);
                        task.completeTask();
                        printLine();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(task.toString());
                        printLine();
                    } catch (MichaelScottException e) {
                        printLine();
                        System.out.println(e.getMessage());
                        printLine();
                    }
                }
                case "unmark" -> {
                    try {
                        if (parts.length < 2 || parts[1].isBlank()) {
                            throw new MichaelScottException("Please provide the number of task to be marked as not completed");
                        }
                        int index = Integer.parseInt(parts[1]) - 1;
                        if (index > todo.size()) {
                            throw new MichaelScottException("Please provide a task in range");
                        }
                        Task task = todo.get(index);
                        task.undoTask();
                        printLine();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(task.toString());
                        printLine();
                    } catch (MichaelScottException e) {
                        printLine();
                        System.out.println(e.getMessage());
                        printLine();
                    }
                }
                case "delete" -> {
                    try {
                        if (parts.length < 2 || parts[1].isBlank()) {
                            throw new MichaelScottException("Please provide the number of task to be deleted");
                        }

                        int index = Integer.parseInt(parts[1]) - 1;
                        Task task = todo.get(index);
                        todo.remove(index);
                        printLine();
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + todo.size() + (todo.size() > 1 ? " tasks" : (" task")) + " in the list.");
                        printLine();
                    } catch (MichaelScottException e) {
                        printLine();
                        System.out.println(e.getMessage());
                        printLine();
                    }
                }
                case "todo" -> {
                    try {
                        if (parts.length < 2 || parts[1].isBlank()) {
                            throw new MichaelScottException("Please specify the task you intend to do.");
                        }

                        todo.add(new Todo(parts[1]));
                        printLine();
                        System.out.println("Got it. I've added this task:");
                        System.out.println("    " + todo.get(todo.size() - 1).toString());
                        System.out.println("Now you have " + todo.size() + (todo.size() > 1 ? " tasks" : (" task")) + " in the list.");
                        printLine();
                    } catch (MichaelScottException e) {
                        printLine();
                        System.out.println(e.getMessage());
                        printLine();
                    }
                }
                case "deadline" -> {

                    try {
                        if (parts.length < 2 || parts[1].isBlank()) {
                            throw new MichaelScottException("Please specify the task and its deadline after the command");
                        }
                        String[] deadlineParts = parts[1].split(" /by ");
                        if (deadlineParts.length < 2) {
                            throw new MichaelScottException("Please specify the deadline");
                        }

                        todo.add(new Deadline(deadlineParts[0], deadlineParts[1]));
                        printLine();
                        System.out.println("Got it. I've added this task:");
                        System.out.println("    " + todo.get(todo.size() - 1).toString());
                        System.out.println("Now you have " + todo.size() + (todo.size() > 1 ? " tasks" : (" task" )) + " in the list.");
                        printLine();
                    } catch (MichaelScottException e) {
                        printLine();
                        System.out.println(e.getMessage());
                        printLine();
                    }
                }
                case "event" -> {
                    try {
                        if (parts.length < 2 || parts[1].isBlank()) {
                            throw new MichaelScottException("Please specify the event and its to-form after the command");
                        }
                        String[] eventParts = parts[1].split(" /from | /to ");
                        if (eventParts.length != 3) {
                            throw new MichaelScottException("Please specify description, start-time and end-time");
                        }
                        System.out.println(eventParts[0]);
                        System.out.println(eventParts[1]);
                        System.out.println(eventParts[2]);
                        todo.add(new Event(eventParts[0], eventParts[1], eventParts[2]));
                        printLine();
                        System.out.println("Got it. I've added this task:");
                        System.out.println("    " + todo.get(todo.size() - 1).toString());
                        System.out.println("Now you have " + todo.size() + (todo.size() > 1 ? " tasks" : (" task" )) + " in the list.");
                        printLine();
                    } catch (MichaelScottException e) {
                        printLine();
                        System.out.println(e.getMessage());
                        printLine();
                    }
                }
                default -> {
                    try {
                        throw new MichaelScottException("I don't understand what you mean to say!");
                    } catch (MichaelScottException e){
                        printLine();
                        System.out.println(e.getMessage());
                        printLine();
                    }
                }
            }

        }
    }
}
