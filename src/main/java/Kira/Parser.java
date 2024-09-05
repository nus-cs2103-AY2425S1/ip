package Kira;

import Exceptions.EmptyException;
import Exceptions.InvalidTaskException;
import Exceptions.UnreadableException;
import Kira.Kira;
import Tasks.*;

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
     * @throws EmptyException if user's input is empty
     * @throws InvalidTaskException if index of task does not exist
     * @throws UnreadableException if user's input does not correspond to any built-in commands
     */
    public void parse(String userInput) throws EmptyException, InvalidTaskException, UnreadableException {

        List list = this.kira.getList();

        if (userInput.equalsIgnoreCase("list")) {
            System.out.println(list.displayList());
            return;
        }

        String[] strings = userInput.split("\\s+", 2);
        String firstWord = strings[0];


        switch (firstWord) {
            case "mark" -> {
                if (strings.length < 2) {
                    throw new EmptyException("mark");
                }
                String restOfWords = strings[1];
                int index = Integer.parseInt(restOfWords) - 1;
                Task task = list.getTask(index);
                task.markAsDone();
                System.out.println(task.markedNotification());
            }
            case "unmark" -> {
                if (strings.length < 2) {
                    throw new EmptyException("unmark");
                }
                String restOfWords = strings[1];
                int index = Integer.parseInt(restOfWords) - 1;
                Task task = list.getTask(index);
                task.markAsUndone();
            }
            case "todo" -> {
                if (strings.length < 2) {
                    throw new EmptyException("todo");
                }
                String restOfWords = strings[1];
                Task task = new ToDo(restOfWords);
                list.addTaskToList(task);
                System.out.println(list.addedNotification(task));
            }
            case "deadline" -> {
                if (strings.length < 2) {
                    throw new EmptyException("deadline");
                }
                String restOfWords = strings[1];
                String deadline = restOfWords.split(" /by ")[1];
                String input = restOfWords.split(" /by ")[0];
                Task task = new Deadline(input, deadline);
                list.addTaskToList(task);
                System.out.println(list.addedNotification(task));
            }
            case "event" -> {
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
            }
            case "delete" -> {
                if (strings.length < 2) {
                    throw new EmptyException("delete");
                }
                String restOfWords = strings[1];
                int index = Integer.parseInt(restOfWords) - 1;
                list.deleteTask(index);
            }
            case "find" -> {
                if (strings.length < 2) {
                    throw new EmptyException("find");
                }
                String keyWord = strings[1];
                List filteredList = list.filterByKeyword(keyWord);
                System.out.println(filteredList.displayList());
            }
            default -> {
                throw new UnreadableException();
            }
        }
    }
}
