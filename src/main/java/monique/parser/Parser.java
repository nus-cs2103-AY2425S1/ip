package monique.parser;

import monique.command.*;
import monique.exception.IllegalDateFormatException;
import monique.exception.ParseException;
import monique.task.Deadline;
import monique.task.Event;
import monique.task.Task;
import monique.task.ToDo;

import java.util.Arrays;
import java.util.Set;

public class Parser {

    private static final Set<String> commands = Set.of("list", "mark", "unmark", "bye", "/commands", "delete");
    private static final Set<String> taskTypes = Set.of("todo", "deadline", "event");

    public static Command parse(String fullCommand){
        String firstWord = fullCommand.split(" ")[0];
        boolean hasSecondWord = fullCommand.split(" ").length >1;
        Command command = null;

        if (commands.contains(firstWord)) {
            switch(firstWord) {
                case "bye": {
                    command = new ByeCommand();
                    break;
                }
                case "list": {
                    command = new ListCommand();
                    break;
                }
                case "mark": {
                    //minus one bc 0-based indexing
                    try {
                        if (!hasSecondWord) {
                            throw new ParseException();
                        }
                        int taskNum = Integer.parseInt(fullCommand.split("mark ")[1]) -1;
                        command = new MarkCommand(taskNum);
                        break;
                    } catch (ParseException pe) {
                        pe.advice();
                    } catch (NumberFormatException nfe) {
                        System.out.println("you have tried to use an invalid number");
                    }
                }
                case "unmark": {
                    try {
                        if (!hasSecondWord) {
                            throw new ParseException();
                        }
                        int taskNum = Integer.parseInt(fullCommand.split("unmark ")[1]) -1;
                        command = new UnmarkCommand(taskNum);
                        break;
                    } catch (ParseException pe) {
                        pe.advice();
                    } catch (NumberFormatException nfe) {
                        System.out.println("you have tried to use an invalid number");
                    }
                }
                case "/commands": {
                    command = new GuideCommand();
                    break;
                }
                case "delete" : {
                    try {
                        if (!hasSecondWord) {
                            throw new ParseException();
                        }
                        int taskNum = Integer.parseInt(fullCommand.split("delete ")[1]) -1;
                        command = new DeleteCommand(taskNum);
                        break;
                    } catch (ParseException pe){
                        pe.advice();
                    } catch (NumberFormatException nfe) {
                        System.out.println("you have tried to use an invalid number");
                    }
                }
            }
        } else if (taskTypes.contains(firstWord)) {
            //add to taskList
            switch(firstWord){
                case "todo": {
                    try {
                        String[] words = fullCommand.split(" ");
                        if (words.length <=1){
                            throw new ParseException();
                        }
                        String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                        Task taskToAdd = new ToDo(description);
                        command = new AddCommand(taskToAdd);
                        break;
                    } catch (ParseException pe) {
                        pe.advice();
                    }
                }
                case "deadline": {
                    try {
                        String[] parts = fullCommand.split("/by");
                        if (parts.length <= 1) {
                            throw new ParseException();
                        }
                        String by = parts[1].trim();
                        String[] commandAndDescription = parts[0].trim().split(" ", 2);
                        String description = commandAndDescription.length > 1 ? commandAndDescription[1] : "";
                        Task taskToAdd = new Deadline(description, false, by);
                        command = new AddCommand(taskToAdd);
                        break;
                    } catch (ParseException pe) {
                        pe.advice();
                    } catch (IllegalDateFormatException idee) {
                        idee.advice();
                    }
                }
                case "event": {
                    try {
                        String[] fromSplit = fullCommand.split("/from");
                        if (fromSplit.length!= 2){
                            throw new ParseException();
                        }
                        String[] toSplit = fromSplit[1].split("/to");
                        if (toSplit.length != 2) {
                            throw new ParseException();
                        }
                        String[] commandAndDescription = fromSplit[0].trim().split(" ", 2);
                        String description = commandAndDescription.length > 1 ? commandAndDescription[1] : "";
                        String fromDate = toSplit[0].trim();
                        String toDate = toSplit[1].trim();
                        Task taskToAdd = new Event(description, false, fromDate, toDate);
                        command = new AddCommand(taskToAdd);
                        break;
                    } catch (ParseException pe) {
                        pe.advice();
                    } catch (IllegalDateFormatException idee) {
                        idee.advice();
                    }
                }
            }
        }
        return command!=null?command:new UnknownCommand();
    }
}

