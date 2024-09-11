package commands;

import exceptions.DownyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code NoteCommand} class represents a command that, when executed, either lists all notes or adds a new note
 * to the note list. The command also updates the storage and user interface to reflect the addition of the new note
 * or display the list of notes.
 */
public class NoteCommand implements Command {

    private final String action;
    private final String noteContent;
    private final int noteNumber;

    /**
     * Constructs a new {@code NoteCommand} with the specified action and note content.
     * If the action is "list", the content is ignored.
     *
     * @param action The action to be performed, either "list" or "entry".
     * @param noteContent The content of the note to be added (if action is "entry").
     */
    public NoteCommand(String action, String noteContent) {
        assert action != null : "Action cannot be null.";
        this.action = action;
        this.noteContent = noteContent;
        this.noteNumber = -1;
    }

    /**
     * Constructs a new {@code NoteCommand} with the specified action only.
     * This constructor is used for actions like "list" where no content is provided.
     *
     * @param action The action to be performed, such as "list".
     */
    public NoteCommand(String action) {
        assert action != null : "Action cannot be null.";
        this.action = action;
        this.noteContent = null;
        this.noteNumber = -1;
    }

    /**
     * Constructs a new {@code NoteCommand} for deleting a note by its number.
     *
     * @param action The action to be performed ("delete").
     * @param noteNumber The number of the note to be deleted.
     */
    public NoteCommand(String action, int noteNumber) {
        assert action != null : "Action cannot be null.";
        assert noteNumber > 0 : "Note number must be positive.";
        this.action = action;
        this.noteNumber = noteNumber;
        this.noteContent = null;
    }

    /**
     * Executes the Note command by either listing all notes or adding a new note to the storage, and displaying the
     * results via the user interface.
     *
     * @param storage The storage handler used for saving the new note or retrieving existing notes.
     * @param tasks   The task list (not used for this command but required by the interface).
     * @param ui      The UI handler used for interacting with the user.
     * @return A message indicating the result of the command (either notes listed or note added).
     * @throws DownyException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        switch (action) {
        case "list" -> {
            return ui.displayNotes(storage.loadNotes());
        }
        case "entry" -> {
            assert noteContent != null : "Note content cannot be null when adding a note.";
            storage.writeNoteToFile(noteContent);
            return ui.displayNoteAdded(noteContent);
        }
        case "delete" -> {
            storage.deleteNoteFromFile(noteNumber);
            return ui.displayNoteDeleted(noteNumber);
        }
        }
        throw new DownyException("Unknown action for NoteCommand: " + action); //Should never reach here
    }

    /**
     * Returns the action associated with this command.
     *
     * @return The action, either "list" or "entry".
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Returns the content of the note (if applicable).
     *
     * @return The note content or null if not applicable.
     */
    public String getNoteContent() {
        return this.noteContent;
    }
}
