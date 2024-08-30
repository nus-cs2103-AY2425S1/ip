package greetbot;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Date;

//the design of the class is referring to https://github.com/david-eom/CS2103T-IP/blob/master/src/main/java/duke/Duke.java#L150
public class GreetBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning;

    public GreetBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isRunning = true;
        tasks = new TaskList(storage.load());
        storage.saveData(tasks);
    }

    // run and getResponse method is adapted from https://github.com/david-eom/CS2103T-IP/blob/master/src/main/java/duke/Duke.java
    public void run() {
        System.out.println(this.ui.greetUser());

        while (this.isRunning) {
            try {
                String input = this.ui.readInput().strip();
                System.out.println(this.getResponse(input));
            } catch (RandomInputException e) {
                System.out.println(e.getMessage());
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String getResponse(String input) throws RandomInputException, EmptyDescriptionException{
        String[] segment = Parser.parseCommand(input);
        String keyword = segment[0];
        if (keyword.equals("BYE")) {
            isRunning = false;
            ui.closeInput();
            storage.saveData(tasks);
            return ui.farewellUser();
        } else if (keyword.equals("LIST")) {
            return ui.showList(this.tasks);
        } else if (keyword.equals("MARK")) {
            return this.markAsDone(Parser.parseMarkUnmarkDelete(segment[1]) - 1);
        } else if (keyword.equals("UNMARK")) {
            return this.markAsNotDone(Parser.parseMarkUnmarkDelete(segment[1]) - 1);
        } else if (keyword.equals("TODO")) {
            if (segment.length == 1) {
                throw new EmptyDescriptionException("OOPS!!! The description of todo cannot be empty.");
            }
            return this.addTodo(Parser.parseTodo(segment[1]));
        } else if (keyword.equals("EVENT")) {
            if (segment.length == 1) {
                throw new EmptyDescriptionException("OOPS!!! The description of event cannot be empty.");
            }
            return this.addEvent(Parser.parseEvent(segment[1]));
        } else if (keyword.equals("DEADLINE")) {
            if (segment.length == 1) {
                throw new EmptyDescriptionException("OOPS!!! The description of deadline cannot be empty.");
            }
            return this.addDeadline(Parser.parseDeadline(segment[1]));
        } else if (keyword.equals("DELETE")) {
            if (segment.length == 1) {
                throw new EmptyDescriptionException("OOPS!!! The description of delete cannot be empty.");
            }
            return this.deleteTask(Parser.parseMarkUnmarkDelete(segment[1]));
        } else if (keyword.equals("FIND")) {
            if (segment.length == 1) {
                throw new EmptyDescriptionException("OOPS!!! The description of find cannot be empty.");
            }
            return this.findTask(Parser.parseFind(segment[1]));
        } else if (keyword.isEmpty()){
            return "";
        } else {
            throw new RandomInputException("何のことを言っているのか分かりません");
        }
    }

    private String markAsDone(int position) {
        this.tasks.get(position).mark();
        return ui.showMarked(tasks.get(position), tasks.getLength());
    }

    private String markAsNotDone(int position) {
        this.tasks.get(position).unmark();
        return ui.showUnmarked(tasks.get(position), tasks.getLength());
    }

    private String deleteTask(int position) {
        Task deletedTask = tasks.get(position - 1);
        this.tasks.delete(position);
        return ui.showDelete(deletedTask, tasks.getLength());
    }

    private String addTodo(String description) {
        Task todo = new Task.Todo(description);
        this.tasks.add(todo);
        return this.ui.showAdd(todo, this.tasks.getLength());
    }

    private String addEvent(String[] args) {
        Task event = new Task.Event(args[0], args[1], args[2]);
        this.tasks.add(event);
        return this.ui.showAdd(event, this.tasks.getLength());
    }

    private String addDeadline(String[] args) {
        try {
            Task deadline = new Task.Deadline(args[0], args[1]);
            this.tasks.add(deadline);
            return this.ui.showAdd(deadline, this.tasks.getLength());
        } catch (DateTimeParseException e) {
            return "wrong time format for deadline task!";
        }
    }

    private String findTask(String description) {
        return this.ui.showFind(description, tasks);
    }
    public static void main(String[] args) {
        new GreetBot("data/greetbot.txt").run();
    }
}
