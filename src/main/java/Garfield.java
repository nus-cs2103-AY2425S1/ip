import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Garfield {

    private static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = """
                 ██████╗  █████╗ ██████╗ ███████╗██╗███████╗██╗     ██████╗
                ██╔════╝ ██╔══██╗██╔══██╗██╔════╝██║██╔════╝██║     ██╔══██╗
                ██║  ███╗███████║██████╔╝█████╗  ██║█████╗  ██║     ██║  ██║
                ██║   ██║██╔══██║██╔══██╗██╔══╝  ██║██╔══╝  ██║     ██║  ██║
                ╚██████╔╝██║  ██║██║  ██║██║     ██║███████╗███████╗██████╔╝
                 ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝╚══════╝╚═════╝
                """;

        String initialGreeting = "Hey. I'm\n\n" + logo
                + "\nLet's get this over with. What do you want?";
        Garfield.speak(initialGreeting);

        // Loop to get user input
        Scanner inputScanner = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = inputScanner.nextLine().strip();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                if (taskList.isEmpty()) {
                    Garfield.speak("Your list is empty. Just like my lasagna pan. "
                            + "Are we done here, or are you going to add something?");
                } else {
                    StringBuilder listSummary = new StringBuilder("Ugh, here's what you've got so far:\n\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        listSummary.append((i + 1)).append(". ")
                                .append(taskList.get(i).toString()).append("\n");

                    }
                    listSummary.append("\nCan we be done now?");
                    Garfield.speak(listSummary.toString());
                }
                continue;
            }

            if (userInput.toLowerCase().startsWith("mark")) {
                String[] output = userInput.trim().split("\\s+");
                if (output.length == 2) {
                    int taskId = Integer.parseInt(output[1]);
                    if (taskId <= taskList.size()) {
                        Task task = taskList.get(taskId - 1);
                        task.markAsDone();
                        Garfield.speak("Nice. You actually did something. I’ve marked that task as done:\n\n\t"
                        + task);
                        continue;
                    }
                }
            }

            if (userInput.toLowerCase().startsWith("unmark")) {
                String[] output = userInput.trim().split("\\s+");
                if (output.length == 2) {
                    int taskId = Integer.parseInt(output[1]);
                    if (taskId <= taskList.size()) {
                        Task task = taskList.get(taskId - 1);
                        task.markAsUndone();
                        Garfield.speak("Oh, having second thoughts? OK, I’ve marked that task as not done yet:\n\n\t"
                                + task + "\n\nClearly, you're still undecided.");
                        continue;
                    }
                }
            }

            if (userInput.toLowerCase().startsWith("todo")) {
                String todoDescription = userInput.substring(5);
                if (!todoDescription.isBlank()) {
                    Todo newTodo = new Todo(todoDescription);
                    taskList.add(newTodo);
                    Garfield.speak("Fine. I'll add '" + todoDescription + "' to the list.\n\n\t"
                    + newTodo + "\n\nJust what you needed to boost your list to a grand total of "
                    + taskList.size() + " task" + ((taskList.size() == 1)? "" : "s") + ". Lucky you.");
                    continue;
                }
            }

            if (userInput.toLowerCase().startsWith("deadline")) {
                String deadlineDescription = userInput.substring(9);
                String[] deadlineArgs = deadlineDescription.split("/by");
                if (!deadlineArgs[0].isBlank() && !deadlineArgs[1].isBlank()) {
                    Deadline newDeadline= new Deadline(deadlineArgs[0].strip(), deadlineArgs[1].strip());
                    taskList.add(newDeadline);
                    Garfield.speak("Fine. I'll add '" + deadlineArgs[0].strip() + "' to the list.\n\n\t"
                            + newDeadline + "\n\nNow your list is up to " + taskList.size() + " task"
                            + ((taskList.size() == 1)? "" : "s") + ". Because who doesn't love more deadlines.");
                    continue;
                }
            }

            if (userInput.toLowerCase().startsWith("event")) {
                String eventDescription = userInput.substring(6);
                String[] eventArgs = eventDescription.split("/from");
                eventDescription = eventArgs[0];
                eventArgs = eventArgs[1].split("/to");
                if (!eventDescription.isBlank() && !eventArgs[0].isBlank() && !eventArgs[1].isBlank()) {
                    Event newEvent = new Event(eventDescription.strip(),
                            eventArgs[0].strip(), eventArgs[1].strip());
                    taskList.add(newEvent);
                    Garfield.speak("Fine. I'll add '" + eventDescription + "' to the list.\n\n\t"
                            + newEvent + "\n\nYour list is now at " + taskList.size() + " task"
                            + ((taskList.size() == 1)? "" : "s") + ". Maybe you'll get around to actually doing them.");
                    continue;
                }
            }

        }

        Garfield.speak("Finally. Try not to come back too soon.");
    }

    private static void speak(String message) {
        Garfield.line(70);
        System.out.println(message);
        Garfield.line(70);
    }

    private static void line(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }
}