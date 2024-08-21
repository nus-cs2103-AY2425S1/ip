import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Deez {
    static void say(String... msgs) {
        System.out.println("____________________________________________________________");
        for (String msg: msgs) {
            System.out.println("> " + msg);
        }
        System.out.println("____________________________________________________________");
    }

    static int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String CMD_bye = "bye";
        String CMD_list = "list";
        String CMD_markDone = "mark";
        String CMD_unmarkDone = "unmark";
        String CMD_todo = "todo";
        String CMD_deadline = "deadline";
        String CMD_event = "event";

        ArrayList<Task> tasks = new ArrayList<>();

        say("Hello! I'm Deez!", "What can I do you for?");
        while (1 == 1) {
            String inputStr = br.readLine();

            // Simple commands
            if (inputStr.isEmpty()) {
                // Don't do anything
                continue;
            }
            if (inputStr.equals(CMD_bye)) {
                // Exit
                break;
            }
            if (inputStr.equals(CMD_list)) {
                if (tasks.isEmpty()) {
                    say("<No items in list>");
                    continue;
                }
                // Print list
                int cnt = 1;
                System.out.println("____________________________________________________________");
                for (Task t: tasks) {
                    System.out.println(cnt + ". " + t.toString());
                    cnt+=1;
                }
                System.out.println("____________________________________________________________");
                continue;
            }

            // Commands with input
            try {
                String[] tkns = inputStr.split(" ", 2);
                if (tkns.length <= 1) {
                    throw new DeezException("Please enter a valid command.");
                }

                String cmd = tkns[0];
                String param = tkns[1];

                if (cmd.equals(CMD_todo)) {
                    if (param.isBlank()) {
                        throw new DeezException("Please provide a description for the todo.");
                    }
                    Todo t = new Todo(param.strip());
                    tasks.add(t);
                    say("Easy. I have added your task.",
                            t.toString(),
                            "You have " + tasks.size() + " tasks in the " +
                                    "list");
                    continue;
                }

                if (cmd.equals(CMD_deadline)) {
                    if (param.isEmpty() || !param.contains("/by")) {
                        throw new DeezException("Please provide a description and deadline.", "Usage:", "deadline " +
                                "return book" +
                                " /by Sunday");
                    }
                    String[] parts = param.split("/by", 2);
                    if (parts.length != 2 || parts[0].isBlank() || parts[1].isBlank()) {
                        throw new DeezException("Description and deadline must not be blank.", "Usage:", "deadline " +
                                "return book" +
                                " /by Sunday");
                    }
                    Deadline d = new Deadline(parts[0].strip(), parts[1].strip());
                    tasks.add(d);
                    say("Donezo. I have added your task.",
                            d.toString(),
                            "You have " + tasks.size() + " tasks in the " +
                                    "list");
                    continue;
                }

                if (cmd.equals(CMD_event)) {
                    if (param.isEmpty() || !param.contains("/from") || !param.contains("/to")) {
                        throw new DeezException("Please provide a description, start date, and end date.", "Usage:", "event " +
                                "project meeting /from Mon 2pm /to 4pm");
                    }
                    String[] parts = param.split("/from|/to", 3);
                    if (parts.length != 3 || parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
                        throw new DeezException("Description start date, and end date must not be blank.", "Usage:", "event " +
                                "project meeting /from Mon 2pm /to 4pm");
                    }
                    Event e = new Event(parts[0].strip(), parts[1].strip(), parts[2].strip());
                    tasks.add(e);
                    say("Event added",
                            e.toString(),
                            "You have " + tasks.size() + " tasks in the " +
                                    "list");
                    continue;
                }

                if (cmd.equals(CMD_markDone) || cmd.equals(CMD_unmarkDone)) {
                    int taskIdx = parseInt(param);
                    if (taskIdx > 0) {
                        try {
                            Task t = tasks.get(taskIdx-1);
                            if (cmd.equals(CMD_markDone) && !t.isDone()) {
                                t.toggleDone();
                            } else if (cmd.equals(CMD_unmarkDone) && t.isDone()) {
                                t.toggleDone();
                            }
                            say("Updated task:", t.toString());
                        } catch (Exception e) {
                            throw new DeezException("No task at index " + param, "Please try again.");
                        }
                    } else {
                        throw new DeezException("Invalid input!", "Please enter a valid number.");
                    }
                }
            } catch (DeezException e) {
                say(e.getErrorMessages());
            }
        }
        say("Bye. Hope to see you soon!");
    }
}
