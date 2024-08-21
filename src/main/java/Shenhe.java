import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shenhe {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello, Ying. I'm Shenhe. The assumption that every person has somewhere to call home is \n" +
                "naive. I got used to living in the mountains alongside the birds and beasts a long time ago. \n" +
                "You, are not the only traveller, but the most interesting one.");
        System.out.println("What do you want today?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        int totalNumberOfTasks = 0;
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            try {
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.startsWith("mark")) {
                    if (userInput.trim().length() == 4) {
                        throw new EmptyTaskDescriptionException();
                    }
                    int length = userInput.length();
                    char lastChar = userInput.charAt(length - 1);
                    int lastDigit = -1;
                    if (Character.isDigit(lastChar)) {
                        lastDigit = lastChar - '0';
                    }
                    if (lastDigit >= 1 && lastDigit <= length) {
                        tasks.get(lastDigit - 1).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(lastDigit - 1).toString());
                        System.out.println("____________________________________________________________");
                    }
                } else if (userInput.startsWith("unmark")) {
                    if (userInput.trim().length() == 6) {
                        throw new EmptyTaskDescriptionException();
                    }
                    int length = userInput.length();
                    char lastChar = userInput.charAt(length - 1);
                    int lastDigit = -1;
                    if (Character.isDigit(lastChar)) {
                        lastDigit = lastChar - '0';
                    }
                    if (lastDigit >= 1 && lastDigit <= length) {
                        tasks.get(lastDigit - 1).markAsUndone();
                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(tasks.get(lastDigit - 1).toString());
                        System.out.println("____________________________________________________________");
                    }
                } else if (userInput.startsWith("todo")) {
                    if (userInput.trim().length() == 4) {
                        throw new EmptyTaskDescriptionException();
                    }
                    String task = userInput.substring(5);
                    tasks.add(new Todo(task));
                    totalNumberOfTasks++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(totalNumberOfTasks - 1).toString());
                    System.out.println("Now you have " + totalNumberOfTasks + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("deadline")) {
                    if (userInput.trim().length() == 8) {
                        throw new EmptyTaskDescriptionException();
                    }
                    if (!userInput.contains("/")) {
                        throw new InvalidDeadlineDescription();
                    }

                    int indexOfSlash = userInput.indexOf("/");
                    String task = userInput.substring(9, indexOfSlash - 1);
                    String by = userInput.substring(indexOfSlash + 4);
                    tasks.add(new Deadline(task, by));
                    totalNumberOfTasks++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(totalNumberOfTasks - 1).toString());
                    System.out.println("Now you have " + totalNumberOfTasks + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("event")) {
                    if (userInput.trim().length() == 5) {
                        throw new EmptyTaskDescriptionException();
                    }
                    int count = 0;
                    for (int i = 0; i < userInput.length(); i++) {
                        if (userInput.charAt(i) == '/') {
                            count++;
                        }
                    }
                    if (count != 2) {
                        throw new InvalidEventDescription();
                    }
                    int indexOfFirstSlash = userInput.indexOf("/");
                    int indexOfSecondSlash = userInput.indexOf("/", indexOfFirstSlash + 1);
                    String task = userInput.substring(6, indexOfFirstSlash - 1);
                    String from = userInput.substring(indexOfFirstSlash + 6, indexOfSecondSlash - 1);
                    String to = userInput.substring(indexOfSecondSlash + 4);
                    tasks.add(new Event(task, from, to));
                    totalNumberOfTasks++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(totalNumberOfTasks - 1).toString());
                    System.out.println("Now you have " + totalNumberOfTasks + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (!userInput.startsWith("list")) {
                    throw new UnknownTaskException();
                } else if (userInput.startsWith("list") && userInput.trim().length() != 4) {
                    throw new InvalidListEnquiry();
                }
                else {
                    int length = tasks.size();
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < length; i++) {
                        System.out.println(i + 1 + "." + tasks.get(i).toString());
                    }
                    System.out.println("____________________________________________________________");
                }
            } catch (EmptyTaskDescriptionException | UnknownTaskException | InvalidDeadlineDescription |
                     InvalidEventDescription | InvalidListEnquiry e) {
                System.out.println(e.toString());
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye travaller. Hope to see you soon.");
        System.out.println("____________________________________________________________");
    }
}
