package Kira;

import Exceptions.EmptyException;
import Exceptions.InvalidTaskException;
import Exceptions.UnreadableException;
import Tasks.*;

import java.util.Objects;

public class Parser {
    private Kira kira;
    public Parser(Kira kira) {
        this.kira = kira;
    }

    /**
     * Executes user's input commands and
     * Prints out the respective response by Kira
     *
     * @param userInput User's input
     * @throws EmptyException If user's input is empty
     * @throws InvalidTaskException If index of task does not exist
     * @throws UnreadableException If user's input does not correspond to any built-in commands
     */
    public void parse(String userInput) throws EmptyException, InvalidTaskException, UnreadableException {

        List list = this.kira.getList();

        if (userInput.equalsIgnoreCase("list")) {
            System.out.println(list.displayList());
            return;
        }

        String[] strings = userInput.split("\\s+", 2);
        String firstWord = strings[0];


        if (Objects.equals(firstWord, "mark")) {
            if (strings.length < 2) {
                throw new EmptyException("mark");
            }
            String restOfWords = strings[1];
            int index = Integer.parseInt(restOfWords) - 1;
            Task task = list.getTask(index);
            task.markAsDone();
            System.out.println(task.markedNotification());

        } else if (Objects.equals(firstWord, "unmark")) {
            if (strings.length < 2) {
                throw new EmptyException("unmark");
            }
            String restOfWords = strings[1];
            int index = Integer.parseInt(restOfWords) - 1;
            Task task = list.getTask(index);
            task.markAsUndone();

        } else if (Objects.equals(firstWord, "todo")) {
            if (strings.length < 2) {
                throw new EmptyException("todo");
            }
            String restOfWords = strings[1];
            Task task = new ToDo(restOfWords);
            list.addTaskToList(task);
            System.out.println(list.addedNotification(task));

        } else if (Objects.equals(firstWord, "deadline")) {
            if (strings.length < 2) {
                throw new EmptyException("deadline");
            }
            String restOfWords = strings[1];
            String deadline = restOfWords.split(" /by ")[1];
            String input = restOfWords.split(" /by ")[0];
            Task task = new Deadline(input, deadline);
            list.addTaskToList(task);
            System.out.println(list.addedNotification(task));

        } else if (Objects.equals(firstWord, "event")) {
            if (strings.length < 2) {
                throw new EmptyException("event");
            }
            String restOfWords = strings[1];
            String input = restOfWords.split(" /from ")[0];
            String period = restOfWords.split(" /from ")[1];
            String start = period.split(" /to ")[0];
            String end = period.split(" /to ")[1];
            Task task = new Event(input, start, end);
            list.addTaskToList(task);
            System.out.println(list.addedNotification(task));

        } else if (Objects.equals(firstWord, "delete")) {
            if (strings.length < 2) {
                throw new EmptyException("delete");
            }
            String restOfWords = strings[1];
            int index = Integer.parseInt(restOfWords) - 1;
            list.deleteTask(index);

        } else if (Objects.equals(firstWord, "find")) {
            if (strings.length < 2) {
                throw new EmptyException("find");
            }
            String keyWord = strings[1];
            List filteredList = list.filterByKeyword(keyWord);
            System.out.println(filteredList.displayList());

        } else {
            throw new UnreadableException();
        }
    }
}
