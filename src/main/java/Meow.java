import java.util.Scanner;

public class Meow {
    private final String name = "Meow";
    private final Task[] tasks = new Task[100];
    private int taskCount = 0;

    private void line() {
        System.out.println("____________________________________________________________");
    }

    public static class MeowException extends Exception {
        public MeowException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Meow MEOW = new Meow();


        System.out.println("_____________________________________________________________\n" +
                " Hello! I'm " + MEOW.name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while (true) {
            try {
                String input = scanner.nextLine();
                MEOW.line();

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    MEOW.line();
                    break;
                } else if (input.equals("list")) {
                    for (int i = 0; i < MEOW.taskCount; i++) {
                        System.out.println((i + 1) + "." + MEOW.tasks[i]);
                    }
                    MEOW.line();
                } else if (input.startsWith("mark")) {
                    if (input.startsWith("mark ")) {
                        int index = Integer.parseInt(input.substring(5).trim()) - 1; // chop off the first few letters to get index
                        if (index >= 0 && index < MEOW.taskCount) {
                            MEOW.tasks[index].mark();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(MEOW.tasks[index]);
                        } else {
                            throw new MeowException("GRRR! Invalid task number, you only have " + MEOW.taskCount + " tasks.");
                        }
                        MEOW.line();
                    } else { // "mark"
                        throw new MeowException("Please specify which task to mark. Example: mark 1");
                    }
                } else if (input.startsWith("unmark")) {
                    if (input.startsWith("unmark ")) {
                        int index = Integer.parseInt(input.substring(7).trim()) - 1; // chop off the first few letters to get index
                        if (index >= 0 && index < MEOW.taskCount) {
                            MEOW.tasks[index].unMark();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(MEOW.tasks[index]);
                        } else {
                            throw new MeowException("GRRR! Invalid task number, you only have " + MEOW.taskCount + " tasks.");
                        }
                        MEOW.line();
                    } else {
                        throw new MeowException("Please specify which task to unmark. Example: unmark 1");
                    }
                } else if (input.startsWith("deadline")) {
                    if (input.startsWith("deadline ")) {
                        String[] parts = input.substring(9).split(" /by "); // chop off the first few letters to get deadline
                        if (parts.length == 2) {
                            MEOW.tasks[MEOW.taskCount] = new Deadline(parts[0], parts[1]);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(MEOW.tasks[MEOW.taskCount]);
                            MEOW.taskCount++;
                            System.out.println(MEOW.taskCount <= 1 ? "Now you have " + MEOW.taskCount + " task in the list."
                                    : "Now you have " + MEOW.taskCount + " tasks in the list.");
                        } else {
                            throw new MeowException("Invalid deadline format. Example: deadline return book /by Christmas");
                        }
                    } else {
                        throw new MeowException("Invalid deadline format. Example: deadline return book /by Christmas");
                    }
                } else if (input.startsWith("event")) {
                    if (input.startsWith("event ")) {
                        String[] parts = input.substring(6).split(" /from | /to "); // chop off the first few letters to get duration
                        if (parts.length == 3) {
                            MEOW.tasks[MEOW.taskCount] = new Event(parts[0], parts[1], parts[2]);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(MEOW.tasks[MEOW.taskCount]);
                            MEOW.taskCount++;
                            System.out.println(MEOW.taskCount <= 1 ? "Now you have " + MEOW.taskCount + " task in the list."
                                    : "Now you have " + MEOW.taskCount + " tasks in the list.");
                        } else {
                            throw new MeowException("Invalid event format. Example: event gym workout /from Mon 1pm /to 2.30pm");
                        }
                    } else {
                        throw new MeowException("Invalid event format. Example: event gym workout /from Mon 1pm /to 2.30pm");
                    }
                } else if (input.startsWith("todo")) {
                    if (input.startsWith("todo ")) {
                        String description = input.substring(5).trim(); // chop off the first few letters to get task
                        MEOW.tasks[MEOW.taskCount] = new ToDo(description);
                        System.out.println("added: " + MEOW.tasks[MEOW.taskCount]);
                        MEOW.taskCount++;
                        System.out.println(MEOW.taskCount <= 1 ? "Now you have " + MEOW.taskCount + " task in the list."
                                : "Now you have " + MEOW.taskCount + " tasks in the list.");
                        MEOW.line();
                    } else {
                        throw new MeowException("Invalid todo format. Example: todo eat lunch");
                    }
                } else {
                    throw new MeowException("Whatchu sayin bruh?");
                }
            } catch (MeowException e) {
                System.out.println(e.getMessage());
                MEOW.line();
            }
        }
    }
}