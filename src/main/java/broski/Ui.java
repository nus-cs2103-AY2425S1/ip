package broski;

import java.util.ArrayList;

public class Ui {
    private static final String LINE = "_________________________________________";

    private static void printLine() {
        System.out.println(LINE);
    }

    public void greeting() {
        printLine();
        System.out.println("Wassup! I'm Broski!");
        System.out.println("What can I do for you bro?");
        printLine();
    }

    public void exit() {
        printLine();
        System.out.println("Bye, bro. See ya around!");
        printLine();
    }

    public void list(TaskList taskList) {
        printLine();
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i - 1));
        }
        printLine();
    }

    public void mark(TaskList taskList, Parser parser, String reply) {
        printLine();
        int i = parser.parseIndex(reply);
        taskList.get(i).mark();
        System.out.println("Solid! Marked as done for you:");
        System.out.println(taskList.get(i));
        printLine();
    }

    public void unmark(TaskList taskList, Parser parser, String reply) {
        printLine();
        int i = parser.parseIndex(reply);
        taskList.get(i).unmark();
        System.out.println("Alright, I've marked the task as undone:");
        System.out.println(taskList.get(i));
        printLine();
    }

    public void delete(TaskList taskList, Parser parser, String reply) {
        printLine();
        int i = parser.parseIndex(reply);
        String temp = taskList.get(i).toString();
        taskList.remove(i);
        System.out.println("Gotcha, I've removed this task:");
        System.out.println(temp);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }

    public void find(TaskList taskList, String reply) {
        printLine();
        String lookingFor = reply.replaceFirst("find ", "");
        ArrayList<Task> tempList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(lookingFor)) {
                tempList.add(taskList.get(i));
            }
        }
        System.out.println("Here are the matching tasks in your list bro:");
        for (int i = 1; i <= tempList.size(); i++) {
            System.out.println(i + ". " + tempList.get(i - 1));
        }
        printLine();
    }

    public void mainResponse(
            TaskList taskList, Parser parser, String reply,
            DateTimeParser dateTimeParser) throws TodoException, DeadlineException,
            EventException, WrongInputException {
        printLine();
        if (reply.length() == 4 && reply.startsWith("todo")) {
            throw new TodoException();
        }
        if ((reply.length() == 8 && reply.startsWith("deadline"))
                || (reply.startsWith("deadline") && parser.parseLength(reply) != 2)) {
            throw new DeadlineException();
        }
        if ((reply.length() == 5 && reply.startsWith("event"))
                || (reply.startsWith("event") && parser.parseLength(reply) != 3)) {
            throw new EventException();
        }
        if (!(reply.startsWith("todo") || reply.startsWith("deadline")
                || reply.startsWith("event"))) {
            throw new WrongInputException();
        }
        if (reply.length() > 5 && reply.startsWith("todo")) {
            Todo todo = new Todo(reply.replaceFirst("todo ", ""));
            taskList.add(todo);
            System.out.println("Gotcha! I've added this task:");
            System.out.println("  " + todo);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else if (reply.length() > 9 && reply.startsWith("deadline")) {
            Deadline deadline = new Deadline(
                    reply.replaceFirst("deadline ", "").split(" /")[0],
                    dateTimeParser.parseDateTime(reply.split(" /")[1]));
            taskList.add(deadline);
            System.out.println("Gotcha! I've added this task:");
            System.out.println("  " + deadline);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
            String[] splitter = reply.split(" /");
            Event event = new Event(
                    splitter[0].replaceFirst("event ", ""),
                    dateTimeParser.parseDateTime(splitter[1]),
                    dateTimeParser.parseDateTime(splitter[2]));
            taskList.add(event);
            System.out.println("Gotcha! I've added this task:");
            System.out.println("  " + event);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
        printLine();
    }

    public void todoException() {
        System.out.println("Hey, your task description is empty bro.");
        printLine();
    }

    public void deadlineException() {
        System.out.println("Hey, your task description"
                + " is either empty or your deadline is missing/wonky bro.");
        printLine();
    }

    public void eventException() {
        System.out.println("Hey, your task description"
                + " is either empty or your duration is missing/wonky bro.");
        printLine();
    }

    public void wrongInputException() {
        System.out.println("I'm sorry but I can't understand you bro."
                + " Use todo, deadline or event please!");
        printLine();
    }

    public void invalidDateTimeException() {
        System.out.println("Invalid date/time format. Please use dd/MM/yyyy HHmm format.");
        printLine();
    }
}
