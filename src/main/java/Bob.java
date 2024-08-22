import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        String logo = "Bob";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        Task[] list = new Task[100];
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            try {
                if(!input.startsWith("list") && !input.startsWith("bye") && !input.startsWith("unmark") && !input.startsWith("mark") && !input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event")) {
                    throw new BobException("Invalid command. Please enter a valid command. Valid commands are: list, unmark, mark, todo, deadline, event, and bye.");
                }
                if(input.startsWith("list")) {
                    if(list[0] == null) {
                        throw new BobException("You have no tasks in the list.");
                    }
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + list[i]);
                    }
                    input = scanner.nextLine();
                    continue;
                }

                if(input.contains("unmark")) {
                    String[] parts = input.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;
                    if(index < 0 || index >= count) {
                        throw new BobException("Please enter a valid task number.");
                    }
                    list[index].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + list[index]);
                    input = scanner.nextLine();
                    continue;
                }
                if(input.contains("mark") && !input.contains("unmark")) {
                    String[] parts = input.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;
                    if(index < 0 || index >= count) {
                        throw new BobException("Please enter a valid task number.");
                    }
                    list[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + list[index]);
                    input = scanner.nextLine();
                    continue;
                }
                Task t = null;

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
                System.out.println("Now you have " + (count + 1) + (count == 0 ? " task in the list." : " tasks in the list."));
                list[count] = t;
                count++;
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
