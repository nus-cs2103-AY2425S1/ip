package victor.commands;

import java.io.IOException;
import java.nio.file.Path;

import victor.messages.ReturnMessage;
import victor.tasklist.TaskList;
import victor.tasks.ToDo;

public class ToDoCommand extends Command {
    private ToDo toDo;
    public ToDoCommand(String[] additionalInput) {
        super(additionalInput);
    }

    /**
     * Overrides the execute method from the Command class. Processes user input and handles
     * inputs with blank names by prompting the user to enter a valid range and format of dates.
     * Calls the addTask method of task list to add the task to the program-wide task list.
     * @return A return message with the event action summary (successful) or a prompt to the user (unsuccessful).
     */
    @Override
    public ReturnMessage execute() {
        String todoMessage = "";
        for (int i = 1; i < additionalInput.length; i++) {
            todoMessage += additionalInput[i] + " ";
        }
        todoMessage = todoMessage.trim();
        if (todoMessage.isBlank()) {
            return new ReturnMessage("  ~  Please give a name for the To Do.",
                    "  ~  The format should be \"todo (description)\"");
        } else {
            this.toDo = new ToDo(todoMessage);
            return new ReturnMessage(super.taskList.addTask(toDo));
        }
    }

    /**
     * Overrides the generic write method in the parent Command class. Handles the case where the event is null
     * (has not been set or incorrectly generated) by not writing anything. Otherwise, calls the writeToFile method
     * from the TaskList class with the given file path. Appends to file instead of overwriting.
     * @param filePath The file path, relative to the project root directory, where to write the changes.
     */
    @Override
    public void write(Path filePath) {
        try {
            if (this.toDo != null) {
                this.toDo.writeToFile(filePath);
            }
        } catch (IOException writeException) {
            throw new RuntimeException("Problem writing to file.");
        }
    }

    public ToDo getToDo() {
        return this.toDo;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public String[] getAdditionalInput() {
        return this.additionalInput;
    }
}
