import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kita {
    private final static ArrayList<Task> commandsList = new ArrayList<>();
    private final static Pattern toDoPattern = Pattern.compile("^todo (.+)$");
    private final static Pattern deadlinePattern = Pattern.compile("^deadline (.+) /by (.+)$");
    private final static Pattern eventPattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$");

    private static void printLine() {
        System.out.println("____________________________________________________________\n");
    }

    private static void checkTaskParameters(Matcher matcherObj, int params) {
        int paramsDetected = matcherObj.groupCount();
        if (paramsDetected < params) {
            if (paramsDetected == 0) {
                throw new KitaMissingDescription();
            }
        };
    }

    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in);

        printLine();
        System.out.println(" Hello! I'm Kita");
        System.out.println(" What can I do for you?");
        printLine();
        while (true) {
            try {
                String command = getInput.nextLine();
                printLine();
                if (command.equals("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!\n");
                    break;
                } else if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= commandsList.size(); i++) {
                        System.out.println(i + ". " + commandsList.get(i - 1));
                    }
                } else if (command.startsWith("mark")) {
                    String[] splitCommand = command.split(" ");
                    if (splitCommand.length <= 1) {
                        throw new KitaMissingIndex();
                    }

                    int numberToMark = Integer.parseInt(splitCommand[1]);
                    System.out.println("Nice! I've marked this task as done:");
                    Task selectedTask = commandsList.get(numberToMark - 1);
                    selectedTask.setCompleted(true);
                    System.out.println("  " + selectedTask);
                } else if (command.startsWith("unmark")) {
                    String[] splitCommand = command.split(" ");
                    if (splitCommand.length <= 1) {
                        throw new KitaMissingIndex();
                    }

                    int numberToMark = Integer.parseInt(splitCommand[1]);
                    System.out.println("OK, I've marked this task as not done yet:");
                    Task selectedTask = commandsList.get(numberToMark - 1);
                    selectedTask.setCompleted(false);
                    System.out.println("  " + selectedTask);
                }
                else if (command.startsWith("delete")) {
                    String[] splitCommand = command.split(" ");
                    if (splitCommand.length <= 1) {
                        throw new KitaMissingIndex();
                    }

                    int numberToDelete = Integer.parseInt(splitCommand[1]);
                    if (numberToDelete > commandsList.size() || numberToDelete <= 0) {
                        throw new KitaOutofBounds();
                    }

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + commandsList.get(numberToDelete - 1));
                    commandsList.remove(numberToDelete-1);
                    System.out.println("Now you have " + commandsList.size() + " tasks in the list.");
                }
                else {
                    Matcher eventMatcher = eventPattern.matcher(command);
                    Matcher deadlineMatcher = deadlinePattern.matcher(command);
                    Matcher todoMatcher = toDoPattern.matcher(command);
                    Task newTask;

                    if (command.startsWith("event")) {
                        if (!eventMatcher.matches()) {
                            if (!command.contains("/from")) {
                                throw new KitaMissingFrom();
                            }
                            else if (!command.contains("/to")) {
                                throw new KitaMissingTo();
                            }
                            throw new KitaMissingDescription();
                        };

                        newTask = new Event(eventMatcher.group(1), eventMatcher.group(2), eventMatcher.group(3));
                    } else if (command.startsWith("deadline")) {
                        if (!deadlineMatcher.matches()) {
                            if (!command.contains("/by")) {
                                throw new KitaMissingBy();
                            }
                            throw new KitaMissingDescription();
                        };

                        newTask = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
                    } else if (command.startsWith("todo")) {
                        if (!todoMatcher.matches()) {
                            throw new KitaMissingDescription();
                        };

                        newTask = new ToDo(todoMatcher.group(1));
                    } else {
                        // No valid command found :c
                        throw new KitaNotFound();
                    }
                    commandsList.add(newTask);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + newTask);
                    System.out.println("Now you have " + commandsList.size() + " tasks in the list.");

                }

            }
            catch (Exception e) {
                System.out.println(e);
            }
            printLine();
        }
    }
}
