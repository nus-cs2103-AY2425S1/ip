import java.util.*;

public class Winde {
    public static void main(String[] args) {
        /* String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         */
        // System.out.println("Hello from\n" + "Winde");
        greet();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!(input.equals("bye"))) {
            String[] order, command;
            if (input.equals("list")) {
                list();
            } else if (input.startsWith("delete")) {
                try {
                    handleDeleteException(input);
                } catch (EmptyDescriptionException e) {
                    throw new RuntimeException(e);
                } catch (TooManyParametersException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.startsWith("mark")) {
                try {
                    handleMarkException(input);
                } catch (EmptyDescriptionException e) {
                    throw new RuntimeException(e);
                } catch (TooManyParametersException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.startsWith("unmark")) {
                try {
                    handleUnmarkException(input);
                } catch (EmptyDescriptionException e) {
                    throw new RuntimeException(e);
                } catch (TooManyParametersException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.startsWith("todo")) {
                try {
                    handleTodoCommand(input);
                } catch (EmptyDescriptionException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.startsWith("deadline")) {
                try {
                    handleDeadlineCommand(input);
                } catch (EmptyDescriptionException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.startsWith("event")) {
                try {
                    handleEventCommand(input);
                } catch (EmptyDescriptionException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    throw new UnsupportedCommandException("TYPE /HELP FOR HELP STOOPIDD");
                } catch (UnsupportedCommandException e) {
                    throw new RuntimeException(e);
                }
            }
            input = scanner.nextLine();
        }

        exit();
    }

    private final static List<Task> reminder = new ArrayList<Task>();

    public static void handleDeleteException (String input) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            delete(Integer.parseInt(command[1]));
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M DELETING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
    }
    public static void delete(int i) {
        Task deleted = reminder.remove(i - 1);
        System.out.print("Noted. I've removed this task:\n" + "    " + deleted.toString() + "\n");
        System.out.println("Now you have " + reminder.size() + " tasks in the list.");
    }
    private static void handleEventCommand(String input) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            String[] order = command[1].split(" /from ");
            if (order.length == 2) {
                String[] fillerName = order[1].split(" /to ");
                event(order[0], fillerName[0], fillerName[1]);
            } else {
                throw new EmptyDescriptionException("WHEN EVENT DATE!");
            }
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M EVENT-ING!");
        }
    }
    private static void handleDeadlineCommand (String input) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            String[] order = command[1].split(" /by ");
            if (order.length == 2) {
                deadline(order[0], order[1]);
            } else {
                throw new EmptyDescriptionException("WHEN DEADLINE END!");
            }
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M DEADLINING!");
        }
    }
    private static void handleTodoCommand (String input) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            todo(command[1]);
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M TODO-ING!");
        }
    }
    private static void handleUnmarkException (String input) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            unmark(Integer.parseInt(command[1]));
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M MARKING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
    }
    public static void handleMarkException (String input) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            mark(Integer.parseInt(command[1]));
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M MARKING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
    }
    public static void todo(String action) {
        Todos td = new Todos(action);
        reminder.add(td);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + td.toString());
        System.out.println("Now you have " + reminder.size() + " tasks in the list.");
    }
    public static void deadline(String action, String date) {
        Deadline d = new Deadline(action, date);
        reminder.add(d);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + d.toString());
        System.out.println("Now you have " + reminder.size() + " tasks in the list.");
    }
    public static void event(String action, String start, String end) {
        Event e = new Event(action, start, end);
        reminder.add(e);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + e.toString());
        System.out.println("Now you have " + reminder.size() + " tasks in the list.");
    }
    public static void mark(int i) {
        System.out.print("Nice! I've marked this task as done:\n" + "    ");
        reminder.get(i - 1).mark();
        task(i);
        System.out.println();
    }

    public static void unmark(int i) {
        System.out.print("OK, I've marked this task as not done yet:\n" + "    ");
        reminder.get(i - 1).unmark();
        task(i);
        System.out.println();
    }

    public static void task(int i) {
        System.out.print(reminder.get(i - 1).toString());
    }
    public static void add(String action) {
        Task t = new Task(action);
        reminder.add(t);
        System.out.println("added: " + action);
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        if (reminder.size() == 0) {
            System.out.println("Hurray you got nothing to do!");
        } else {
            for (int i = 1; i <= reminder.size(); i++) {
                System.out.print(i + ".");
                task(i);
                System.out.println();
            }
        }
    }

    public static void greet() {
        System.out.println("Hello! I'm Winde\n" + "What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    static class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException(String message) {
            super(message);
        }
    }
    static class TooManyParametersException extends Exception {
        public TooManyParametersException(String message) {
            super(message);
        }
    }
    static class UnsupportedCommandException extends Exception {
        public UnsupportedCommandException(String message) {
            super(message);
        }
    }
}

class Task {
    protected String action;
    protected boolean complete;

    public Task(String action) {
        this.action = action;
        this.complete = false;
    }

    public String isCompleted() {
        return (complete ? "[X]" : "[ ]"); // mark done task with X
    }

    public void mark() {
        complete = true;
    }

    public void unmark() {
        complete = false;
    }

    @Override
    public String toString() {
        return (complete ? "[X]" : "[ ]") + " " + action;
    }
}

class Todos extends Task {

    public Todos(String action) {
        super(action);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {

    protected String date;

    public Deadline(String action, String date) {
        super(action);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}

class Event extends Task {

    protected String start;
    protected String end;

    public Event(String action, String start, String end) {
        super(action);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
