import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Astor {

    private List<Task> listOfTasks;

    private String separatorLine() {
        return "--------------------------------------";
    }

    private enum Action {
        BYE,
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        EMPTY,
        DEFAULT
    }

    private Action categorisation(String input) {
        if (input.equals("bye")) {
            return Action.BYE;
        } else if (input.startsWith("mark")) {
            return Action.MARK;
        } else if (input.startsWith("unmark")) {
            return Action.UNMARK;
        } else if (input.equals("list")) {
            return Action.LIST;
        } else if (input.startsWith("todo")) {
            return Action.TODO;
        } else if (input.startsWith("deadline")) {
            return Action.DEADLINE;
        } else if (input.startsWith("event")) {
            return Action.EVENT;
        } else if (input.startsWith("delete")) {
            return Action.DELETE;
        } else if (input.isEmpty()) {
            return Action.EMPTY;
        }
        return Action.DEFAULT;
    }

    private String processer(String input) throws AstorException {
        Action action = categorisation(input);
        switch (action) {
            case BYE:
                return "Bye. Hope to see you again!";
            case LIST:
                String reply = "Here are the tasks in your list:";
                int indexA = 1;
                for (Task t : listOfTasks) {
                    reply += "\n" + indexA + ". " + t.toString();
                    indexA++;
                }
                return reply;
            case MARK:
                int indexB;
                try {
                    String formattedString = input.substring(4).trim();
                    indexB = Integer.parseInt(formattedString);
                } catch (NumberFormatException e) {
                    throw new MarkingTaskNotANumberException();
                }

                if (indexB >= 1 && indexB <= listOfTasks.size()) {
                    Task task = listOfTasks.get(indexB - 1);
                    if (task.isDone()) {
                        return "This task is already done:\n" +
                                task;
                    } else {
                        task.markDone();
                        return "Nice! I've marked this task as done:\n" +
                                task;
                    }
                } else {
                    throw new MarkTaskOutOfRangeException(listOfTasks.size());
                }
            case UNMARK:
                int index = -1;
                try {
                    String formattedString = input.substring(6).trim();
                    index = Integer.parseInt(formattedString);
                } catch (NumberFormatException e) {
                    throw new MarkingTaskNotANumberException();
                }

                if (index >= 1 && index <= listOfTasks.size()) {
                    Task task = listOfTasks.get(index - 1);
                    if (task.isDone()) {
                        task.markUndone();
                        return "OK, I've marked this task as not done yet:\n" +
                                task;
                    } else {
                        return "This task is already marked as uncompleted:\n" +
                                task;
                    }
                } else {
                    throw new MarkTaskOutOfRangeException(listOfTasks.size());
                }
            case EMPTY:
                throw new EmptyInputException();
            case TODO:
                String s1 = input.substring(4).trim();
                if (s1.isEmpty()) {
                    throw new EmptyTaskInfoException();
                } else {
                    Task task = new Todo(s1);
                    listOfTasks.add(task);
                    return "Got it. I've added this task:\n  " +
                            task + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
                }
            case DEADLINE:
                String s = input.substring(8).trim();
                if (s.isEmpty()) {
                    throw new EmptyTaskInfoException();
                } else {
                    String[] stringArr = s.split("/by");
                    if (stringArr.length != 2) {
                        throw new EmptyDeadlineException();
                    }
                    Task task = new Deadline(stringArr[0].trim(), stringArr[1].trim());

                    listOfTasks.add(task);
                    return "Got it. I've added this task:\n  " +
                            task + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
                }
            case EVENT:
                String s2 = input.substring(5).trim();
                if (s2.isEmpty()) {
                    throw new EmptyTaskInfoException();
                } else {
                    String[] stringArr = s2.split("/from");
                    if (stringArr.length != 2) {
                        throw new EmptyTimeException();
                    }
                    String[] stringArr2 = stringArr[1].split("/to");
                    if (stringArr2.length != 2) {
                        throw new EmptyTimeException();
                    }
                    Task task = new Event(stringArr[0].trim(), stringArr2[0].trim(), stringArr2[1].trim());
                    listOfTasks.add(task);
                    return "Got it. I've added this task:\n  " +
                            task + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
                }
            case DELETE:
                int indexC = -1;
                if (listOfTasks.isEmpty()) {
                    throw DeleteTaskOutOfRangeException.noTaskToDelete();
                }
                try {
                    String formattedString = input.substring(6).trim();
                    indexC = Integer.parseInt(formattedString);
                } catch (NumberFormatException e) {
                    throw new DeleteTaskNumberException();
                }
                if (indexC >= 1 && indexC <= listOfTasks.size()) {
                    Task task = listOfTasks.remove(indexC - 1);
                    return "Noted. I've removed this task:\n  " +
                            task + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
                } else {
                    throw DeleteTaskOutOfRangeException.outOfRangeTaskToDelete(listOfTasks.size());
                }
            case DEFAULT:
                throw new UnspecificTaskException();
        }
        return "";
    }

    private String introduction() {
        return "Hello, I'm Astor!\n" +
                "What can I do for you?\n" +
                "--------------------------------------";
    }

    public static void main(String[] args) {
        Astor astor = new Astor();
        System.out.println(astor.introduction());

        Scanner scanner = new Scanner(System.in);
        astor.listOfTasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!\n" + astor.separatorLine());
                break;
            }
            String reply;

            try {
                reply = astor.processer(input);
            } catch (AstorException e) {
                reply = e.getMessage();
            }
            System.out.println(reply + "\n" + astor.separatorLine());

        }
        scanner.close();
    }

}
