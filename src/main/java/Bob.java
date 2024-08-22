import java.util.ArrayList;
import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String logo = "Bob";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            try {
                if(!input.startsWith("list") && !input.startsWith("bye") && !input.startsWith("unmark") && !input.startsWith("mark") && !input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event") && !input.startsWith("delete")) {
                    throw new BobException("Invalid command. Please enter a valid command. Valid commands are: list, unmark, mark, delete, todo, deadline, event, and bye.");
                }
                if(input.startsWith("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                    input = scanner.nextLine();
                    continue;
                }

                if(input.contains("unmark")) {
                    String[] parts = input.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;
                    if(index < 0 || index >= list.size()) {
                        throw new BobException("Please enter a valid task number.");
                    }
                    list.get(index).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + list.get(index));
                    input = scanner.nextLine();
                    continue;
                }
                if(input.contains("mark") && !input.contains("unmark")) {
                    String[] parts = input.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;
                    if(index < 0 || index >= list.size()) {
                        throw new BobException("Please enter a valid task number.");
                    }
                    list.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + list.get(index));
                    input = scanner.nextLine();
                    continue;
                }
                Task t = null;

                if(input.startsWith("delete")) {
                    String[] parts = input.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;
                    if(index < 0 || index >= list.size()) {
                        throw new BobException("Please enter a valid task number.");
                    }

                    System.out.println("Noted. I've removed this task:\n" + list.get(index));
                    list.remove(index);
                    System.out.println("Now you have " + list.size() + (list.isEmpty() ? " task in the list." : " tasks in the list."));
                    input = scanner.nextLine();
                    continue;
                }

                if(input.startsWith("todo")) {
                    String[] parts = input.split(" ");
                    StringBuilder description = new StringBuilder();
                    for (int i = 1; i < parts.length; i++) {
                        description.append(parts[i]).append(" ");
                    }
                    String descriptionStr = description.toString().trim();
                    if (descriptionStr.isEmpty()) {
                        throw new BobException("The description of a todo cannot be empty.");
                    }
                    t = new Todo(descriptionStr);
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.split(" ");
                    StringBuilder description = new StringBuilder();
                    int j = 0;
                    for (int i = 1; i < parts.length - 1; i++) {
                        if(parts[i].contains("/by")) {
                            break;
                        }
                        description.append(parts[i]).append(" ");
                        j = i + 2;
                    }
                    StringBuilder date = new StringBuilder();
                    for (int i = j; i < parts.length; i++) {
                        date.append(parts[i]).append(" ");
                    }
                    // Remove last space
                    String descriptionStr = description.toString().trim();
                    String dateStr = date.toString().trim();
                    if (!input.contains("/by")) {
                        throw new BobException("The date of a deadline cannot be empty. Please enter in the format: /by <date>");
                    }
                    if (descriptionStr.isEmpty()) {
                        throw new BobException("The description of a deadline cannot be empty.");
                    }

                    t = new Deadline(descriptionStr, dateStr);
                } else if (input.startsWith("event")) {
                    String[] parts = input.split(" ");
                    StringBuilder description = new StringBuilder();
                    int j = 0;
                    for (int i = 1; i < parts.length - 1; i++) {
                        if(parts[i].contains("/from")) {
                            break;
                        }
                        description.append(parts[i]).append(" ");
                        j = i + 2;
                    }
                    StringBuilder from = new StringBuilder();
                    for (int i = j; i < parts.length - 2; i++) {
                        if(parts[i].contains("/to")) {
                            break;
                        }
                        from.append(parts[i]).append(" ");
                        j = i + 2;
                    }
                    StringBuilder to = new StringBuilder();
                    for (int i = j; i < parts.length; i++) {
                        to.append(parts[i]).append(" ");
                    }
                    // Remove last space
                    String descriptionStr = description.toString().trim();
                    String fromStr = from.toString();
                    String toStr = to.toString().trim();
                    if (descriptionStr.isEmpty()) {
                        throw new BobException("The description of an event cannot be empty.");
                    }
                    if (!input.contains("/from")) {
                        throw new BobException("The start date/time of an event cannot be empty. Please enter in the format: /from <date>");
                    }
                    if (!input.contains("/to")) {
                        throw new BobException("The end date/time of an event cannot be empty. Please enter in the format: /to <date>");
                    }

                    t = new Event(descriptionStr, fromStr, toStr);
                }
                System.out.println("Got it. I've added this task:\n" + t);
                System.out.println("Now you have " + (list.size() + 1) + (list.isEmpty() ? " task in the list." : " tasks in the list."));
                list.add(t);
            } catch (BobException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
