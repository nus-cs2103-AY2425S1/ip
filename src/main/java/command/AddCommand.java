package command;

import java.util.Arrays;
import java.util.List;

import exception.*;
import task.*;
import ui.Ui;
import utility.Parser;

public class AddCommand extends UserCommand {
    private Task createTask(String[] words, String command) throws LevelHundredException {
        if (command.equals("todo")) {
            String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            if (taskDescription.equals("")) {
                throw new MissingArgumentException(command, "description");
            }
            
            return new Todo(taskDescription);
        } else if (command.equals("deadline")) {
            List<String> tmp = Arrays.asList(words);
            int byIdx = tmp.indexOf("/by");
            if (byIdx == -1) {
                throw new MissingArgumentException(command, "by");
            }
            
            String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, byIdx));
            if (taskDescription.equals("")) {
                throw new MissingArgumentException(command, "description");
            }
            
            String by = String.join(" ", Arrays.copyOfRange(words, byIdx + 1, words.length));
            if (by.equals("")) {
                throw new MissingArgumentException(command, "by");
            }
            try {
                by = Parser.parseInputDate(by);
                return new Deadline(taskDescription, by);
            } catch (RuntimeException e) {
                throw new InvalidArgumentException(command, "by");
            }
            
        } else {
            List<String> tmp = Arrays.asList(words);
            int fromIdx = tmp.indexOf("/from");
            int toIdx = tmp.indexOf("/to");
            if (fromIdx == -1) {
                throw new MissingArgumentException(command, "from");
            }
            if (toIdx == -1) {
                throw new MissingArgumentException(command, "to");
            }

            // Get task description
            String taskDescription = String.join(" ", Arrays.copyOfRange(words, 1, fromIdx));
            if (taskDescription.equals("")) {
                throw new MissingArgumentException(command, "description");
            }
            
            // Get from date
            String from = String.join(" ", Arrays.copyOfRange(words, fromIdx + 1, toIdx));
            if (from.equals("")) {
                throw new MissingArgumentException(command, "from");
            }
            try {
                from = Parser.parseInputDate(from);
            } catch (RuntimeException e) {
                throw new InvalidArgumentException(command, "from");
            }
            
            // Get to date
            String to = String.join(" ", Arrays.copyOfRange(words, toIdx + 1, words.length));
            if (to.equals("")) {
                throw new MissingArgumentException(command, "to");
            }
            try {
                to = Parser.parseInputDate(to);
            } catch (RuntimeException e) {
                throw new InvalidArgumentException(command, "to");
            }

            return new Event(taskDescription, from, to);
        }
    }

    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) throws LevelHundredException {
        try {
            String[] words = userInput.split(" ");
            String command = words[0];

            Task newTask = createTask(words, command);
            taskList.addTask(newTask);
            storage.update(taskList.getTaskList());
            ui.printAddTask(newTask, taskList.size());
        } catch (LevelHundredException e) {
            ui.printException(e);
        }
    }
}