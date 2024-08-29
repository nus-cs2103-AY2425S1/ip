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
    @Override
    public ReturnMessage execute() {
        String todoMessage = "";
        for (int i = 1; i < additionalInput.length; i++) {
            todoMessage += additionalInput[i] + " ";
        }
        todoMessage = todoMessage.trim();
        if (todoMessage.isBlank()) {
            return new ReturnMessage("  ~  Please give a name for the To Do.",
                    "The format should be \"todo (description)\"");
        } else {
            this.toDo = new ToDo(todoMessage);
            return new ReturnMessage(super.taskList.addTask(toDo));
        }
    }

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
