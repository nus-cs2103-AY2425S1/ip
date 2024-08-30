package parser;

import task.TaskList;
public class Parser {

    public void markTaskParser(String message, TaskList t) throws BobException {
        if (!message.matches("^mark \\d+$")) {
            throw new BobException("Invalid format");
        }
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        t.markTask(num - 1);
    }

    public void unmarkTaskParser(String message, TaskList t) throws BobException {
        if (!message.matches("^unmark \\d+$")) {
            throw new BobException("Invalid format");
        }
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        t.unmarkTask(num - 1);
    }

    public void deleteTaskParser(String message, TaskList t) throws BobException {
        if (!message.matches("^delete \\d+$")) {
            throw new BobException("Invalid format");
        }
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        t.deleteTask(num - 1);
    }

    public void addToDoParser(String message, TaskList t) throws BobException {
        if (!message.matches("^todo.*")) {
            throw new BobException("Invalid format");
        }
        String x = message.replaceFirst("todo ", "");
        if (x.isEmpty()) {
            throw new BobException("OOPS!!! The description of a todo cannot be empty.");
        }
        t.addToDo(x);
    }

    public void addDeadlineParser(String message, TaskList t) throws BobException {
        if (!message.matches("^deadline .* \\/by \\d{4}-\\d{2}-\\d{2}$")) {
            throw new BobException("Invalid format");
        }
        String x = message.replaceFirst("deadline ", "");
        String[] parts = x.split(" /by ");
        if (parts.length != 2) {
            throw new BobException("OOPS!!! The description/start time of a deadline cannot be empty.");
        }
        t.addDeadline(parts[0].trim(), parts[1].trim());
    }

    public void addEventParser(String message, TaskList t) throws BobException {
        if (!message.matches("^event .* \\/from \\d{4}-\\d{2}-\\d{2} \\/to \\d{4}-\\d{2}-\\d{2}$")) {
            throw new BobException("Invalid format");
        }
        String x = message.replaceFirst("event ", "");
        String[] parts = x.split(" /from | /to");
        if (parts.length != 3) {
            throw new BobException("OOPS!!! The description/start time/end time of an event cannot be empty.");
        }
        t.addEvent(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    public void findParser(String message, TaskList t) throws BobException {
        String[] words = message.split(" ",2);
        if (words.length < 2) {
            throw new BobException("Text after find cannot be empty!");
        }
        String name = words[1];
        t.findTasks(name);
    }
}
