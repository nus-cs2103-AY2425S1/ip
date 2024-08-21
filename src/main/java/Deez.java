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
        String logo = """
                    ____  _________________
                   / __ \\/ ____/ ____/__  /
                  / / / / __/ / __/    / /\s
                 / /_/ / /___/ /___   / /__
                /_____/_____/_____/  /____/
                                          \s
                """;
        System.out.println(logo);

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
            String[] tkns = inputStr.split(" ", 2);
            if (tkns.length > 1 ) {
                String cmd = tkns[0];
                String param = tkns[1];

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
                            say("No task at index " + param, "Please try again.");
                        }
                        continue;
                    } else {
                        say("Invalid input!", "Please enter a valid number.");
                        continue;
                    }
                }
            }

            // Add item to items list
            tasks.add(new Task(inputStr));
            say("Added " + inputStr);
        }
        say("Bye. Hope to see you soon!");
    }


}
