package botimusprime.tasks;

import botimusprime.parser.Parser;
import botimusprime.storage.Storage;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(String fileName) {
        this.storage = new Storage(fileName);
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks, String fileName) {
        this.tasks = tasks;
        this.storage = new Storage(fileName);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void showList() {
        if (tasks.isEmpty()) {
            System.out.println("congrats bro u got nth to do in the todolist, respect 100");
            return;
        }
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    public void markDone(String input) {
        String[] numFinder = input.split(" ");

        if (numFinder.length != 2 || !(numFinder[1].matches("\\d+"))) {
            System.out.println("eh bro u need to put the index of the item uw to mark");
        }

        int idx = Integer.parseInt(numFinder[1]) - 1;
        tasks.get(idx).markAsDone();
        storage.saveToDisk(this);
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n" +
                tasks.get(idx));
    }

    public void markUndone(String input) {
        String[] numFinder = input.split(" ");

        if (numFinder.length != 2 || !(numFinder[1].matches("\\d+"))) {
            System.out.println("eh bro u need to put the index of the item uw to unmark");
        }

        int idx = Integer.parseInt(numFinder[1]) - 1;
        tasks.get(idx).markAsUndone();
        storage.saveToDisk(this);
        System.out.println("____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n" +
                tasks.get(idx));
    }

    public void delete(String input) {
        String[] numFinder = input.split(" ");

        if (numFinder.length != 2 || !(numFinder[1].matches("\\d+"))) {
            System.out.println("eh bro u need to put the index of the item uw to delete");
        }

        int idx = Integer.parseInt(numFinder[1]) - 1;
        Task task = tasks.get(idx);
        tasks.remove(idx);
        storage.saveToDisk(this);
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task:\n" + task + "\nNow you have "
                        + tasks.size() + " tasks in the list.");
    }

    public void addToDo(String input) {
        if (input.length() <= 5) {
            System.out.println("eh bro udw to put ur description of ur task issit");
            return;
        }

        ToDo task = new ToDo(input.substring(5), false);

        tasks.add(task);
        storage.saveToDisk(this);

        System.out.println(
                "____________________________________________________________\n" +
                        String.format("Alright, I've added the task:\n %s\nNow you have %d tasks in the list.\n",
                                task, tasks.size())
                                        + "____________________________________________________________\n");
    }

    public void addDeadline(String input) {
        if (input.length() <= 9 || !input.contains("/by")) {
            System.out.println("brother u forgot to type all the deadline task details plz.");
        }
        String[] parser = input.split("/by ");

        if (parser.length < 2 || parser[1].trim().isEmpty()) {
            System.out.println("wheres the deadline!!");
            return;
        }
        String description = parser[0].substring(9);
        String deadline = parser[1];

        if (deadline.isEmpty()) {
            System.out.println("eh bro u got due date anot");
            return;
        } else if (description.isEmpty()) {
            System.out.println("eh bro ur task no description leh wake up ur idea");
        }

        Deadline task = new Deadline(description,
                false,
                        Parser.stringToDateTime(deadline));

        tasks.add(task);
        storage.saveToDisk(this);

        System.out.println(
                "____________________________________________________________\n" +
                        String.format("Alright, I've added the task:\n %s\nNow you have %d tasks in the list.\n",
                                task, tasks.size())
                                        + "____________________________________________________________\n");
    }

    public void addEvent(String input) {
        if (input.length() <= 6 || input.substring(6)
                .trim()
                        .isEmpty()) {
            System.out.println("brother u forgot to type all the event task details");
            return;
        } else if (!input.contains("/from") || !input.contains("/to")) {
            System.out.println("eh bro ur event no time issit");
            return;
        }

        String[] parser = input.split("/from ");

        if (parser.length < 2 || parser[1].trim()
                .isEmpty()) {
            System.out.println("hi plz actually put a time");
            return;
        }

        String description = parser[0].substring(6);
        String times = parser[1];

        String[] fromAndTo = times.split("/to ");

        if (fromAndTo.length < 2 || fromAndTo[0].trim().
                isEmpty() || fromAndTo[1].trim()
                        .isEmpty()) {
            System.out.println("hi plz actually put times in ur EVENT");
            return;
        }

        String from = fromAndTo[0].trim();
        String to = fromAndTo[1].trim();

        Event task = new Event(description,
                false,
                        Parser.stringToDateTime(from),
                                Parser.stringToDateTime(to));

        tasks.add(task);
        storage.saveToDisk(this);

        System.out.println(
                "____________________________________________________________\n" +
                        String.format("Alright, I've added the task:\n %s\nNow you have %d tasks in the list.\n",
                                task, tasks.size())
                                        + "____________________________________________________________\n");
    }
}