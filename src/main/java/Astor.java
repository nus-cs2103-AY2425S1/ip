import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Astor {

    private List<Task> listOfTasks;

    private String separatorLine() {
        return "--------------------------------------";
    }

    private String processer(String input) throws AstorException {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again!";
        } else if (input.equals("list")) {
            String reply = "Here are the tasks in your list:";
            int index = 1;
            for (Task t : listOfTasks) {
                reply += "\n" + index + ". " + t.toString();
                index++;
            }
            return reply;
        } else if (input.startsWith("mark")) {
            int index = -1;
            try {
                String formattedString = input.substring(4).trim();
                index = Integer.parseInt(formattedString);
            } catch (NumberFormatException e) {
                throw new MarkingTaskNotANumberException();
            }

            if (index >= 1 && index <= listOfTasks.size()) {
                Task task = listOfTasks.get(index - 1);
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
        } else if (input.startsWith("unmark")) {
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
        } else if (input.isEmpty()) {
            throw new EmptyInputException();
        } else if (input.startsWith("todo")) {
            String s = input.substring(4).trim();
            if (s.isEmpty()) {
                throw new EmptyTaskInfoException();
            } else {
                Task task = new Todo(s);
                listOfTasks.add(task);
                return "Got it. I've added this task:\n  " +
                        task + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
            }
        } else if (input.startsWith("deadline")) {
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
        } else if (input.startsWith("event")) {
            String s = input.substring(5).trim();
            if (s.isEmpty()) {
                throw new EmptyTaskInfoException();
            } else {
                String[] stringArr = s.split("/from");
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
        } else if (input.startsWith("delete")) {
            int index = -1;
            if (listOfTasks.size() == 0) {
                throw DeleteTaskOutOfRangeException.noTaskToDelete();
            }
            try {
                String formattedString = input.substring(6).trim();
                index = Integer.parseInt(formattedString);
            } catch (NumberFormatException e) {
                throw new DeleteTaskNumberException();
            }
            if (index >= 1 && index <= listOfTasks.size()) {
                Task task = listOfTasks.remove(index - 1);
                return "Noted. I've removed this task:\n  " +
                        task + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
            } else {
                throw DeleteTaskOutOfRangeException.outOfRangeTaskToDelete(listOfTasks.size());
            }
        } else {
            throw new UnspecificTaskException();
        }

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
