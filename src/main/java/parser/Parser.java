package parser;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import mollyexception.MollyException;
import task.*;
public class Parser {

    private Storage storage;

    private TaskList taskList;

    public Parser(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Handles the different user inputs and decides what actions to perform for each of them.
     * @param userInput
     * @throws MollyException
     */

    public String handleUserInput(String userInput) throws MollyException {
        StringBuilder mollyResponse = new StringBuilder();
        if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
            String[] markParts = userInput.split(" ");
            if (markParts.length == 2) {
                try {
                    int taskToMark = Integer.parseInt(markParts[1]) - 1;
                    if (taskToMark > taskList.getBotMemory().size() - 1 || taskToMark < 0) {
                        throw new MollyException("Please select a valid task number.");
                    } else {
                        mollyResponse.append(taskList.toggleTaskDone(taskToMark)).append("\n");
                        storage.saveTasks(taskList);
                        //return mollyResponse.toString();
                    }

                } catch (NumberFormatException e) {
                    Ui.printLine();
                    throw new MollyException("Invalid command. Please enter a valid task number to mark.");
                }
            } else {
                Ui.printLine();
                throw new MollyException("Invalid  mark/unmark command. Please use 'mark/unmark (task number)'.");
            }

        } else if (userInput.startsWith("delete")) {
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                try {
                    int taskToDelete = Integer.parseInt(parts[1]) - 1;
                    if (taskToDelete > taskList.getBotMemory().size() - 1 || taskToDelete < 0) {
                        throw new MollyException("Please select a valid task number.");
                    } else {
                        mollyResponse.append(taskList.removeTask(taskToDelete)).append("\n");
                        storage.saveTasks(taskList);
                    }
                } catch (NumberFormatException e) {
                    throw new MollyException("Invalid command. Please enter a valid task number.");
                }
            } else {
                throw new MollyException("Invalid  delete command. Please use 'delete (task number)'.");
            }

        } else if (userInput.startsWith("find")) {
            String[] queryParts = userInput.split(" ");
            if (queryParts.length == 2) {
                String searchTerm = queryParts[1];
                mollyResponse.append(taskList.findTerm(searchTerm)).append("\n");
                //return mollyResponse.toString();
            } else {
                throw new MollyException("Invalid find command. Please use 'find (search term)'.");
            }

        } else if (!userInput.toLowerCase().equals("list")) {
            if (userInput.startsWith("todo")) {
                if (userInput.equals("todo")) {
                    throw new MollyException("Yikes! Sorry, the todo description cannot be empty.");
                } else {
                    Task newToDo = new Task(userInput.substring(5));
                    mollyResponse.append(taskList.addTask(newToDo));
                    storage.saveTasks(taskList);
                }
            } else if (userInput.startsWith("deadline")) {
                if (userInput.equals("deadline")) {
                    throw new MollyException("Yikes! Sorry, the description of a deadline cannot be empty.");
                } else {
                    String[] deadlineDetails = userInput.substring(9).split( " /by ");
                    if (deadlineDetails.length == 2) {
                        String description = deadlineDetails[0].trim();
                        String by = deadlineDetails[1].trim();
                        Deadline newDeadline = new Deadline(description, by);
                        mollyResponse.append(taskList.addTask(newDeadline));
                        storage.saveTasks(taskList);
                    } else {
                        Ui.printLine();
                        throw new MollyException("Sorry, invalid format for deadline. Please follow the format: deadline (description) /by (end date). The end date can be in the format DD-MM-YYYY HHmm (24 hour format).");
                    }
                }
            } else if (userInput.startsWith("event")) {
                if (userInput.equals("event")) {
                    throw new MollyException("Yikes! Sorry, the description of an event cannot be empty.");
                } else {
                    String[] eventDetails = userInput.substring(6).split( " /from | /to ");
                    if (eventDetails.length == 3) {
                        String description = eventDetails[0].trim();
                        String from = eventDetails[1].trim();
                        String to = eventDetails[2].trim();
                        Event newEvent = new Event(description, from, to);
                        mollyResponse.append(taskList.addTask(newEvent));
                        storage.saveTasks(taskList);
                    } else {
                        throw new MollyException("Sorry, invalid format for event. Please follow the format: event (description) /from (start date or time) /to (end date or time). The start and end date/times can be in the format DD-MM-YYYY HHmm (24 hour format)");
                    }
                }
            } else if (userInput.equals("/help")) {
                return Ui.printHelpCommands();

            } else {
                throw new MollyException("I'm sorry, I do not know how to respond to that. Type '/help' for the list of allowed commands.");
            }

        } else {
            mollyResponse.append(taskList.listToString()).append("\n");
        }

        return mollyResponse.toString().trim();

    }

}
