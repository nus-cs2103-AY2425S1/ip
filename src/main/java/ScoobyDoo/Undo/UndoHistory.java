package ScoobyDoo.Undo;

import java.util.EmptyStackException;
import java.util.Stack;

import ScoobyDoo.UI.UI;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

public class UndoHistory {
    private Stack<Undoable> undos;
    private Stack<Undoable> redos;

    private int maxSize;

    public UndoHistory(int size) {
        this.maxSize = size;
        undos = new Stack<>();
        redos = new Stack<>();
    }

    /** Adds an undoable object to the collector. */
    public void add(Undoable command) {
        if (this.undos.size() == this.maxSize) {
            undos.removeElementAt(0);
        }
        undos.push(command);
        this.redos.clear();
    }

    /** Undoes the last undoable object. */
    public String undo(UI ui, TaskList taskList, Storage storage) {
        try {
            Undoable undoCommand = this.undos.pop();
            redos.push(undoCommand);
            String undoMsg = undoCommand.undo(ui, taskList, storage);
            return ui.response(undoMsg);

        } catch (EmptyStackException e) {
            return ui.printErrorMessage("There is no action for you to undo");
        }
    }

    /** Redoes the last undoable object. */
    public String redo(UI ui, TaskList taskList, Storage storage) {
        try {
            Undoable redoCommand = this.redos.pop();
            undos.push(redoCommand);
            String redoMsg = redoCommand.redo(ui, taskList, storage);
            return ui.response(redoMsg);
        } catch (EmptyStackException e) {
            return ui.printErrorMessage("There is no action for you to redo");
        }
    }
}
