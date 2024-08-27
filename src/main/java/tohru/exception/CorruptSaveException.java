package tohru.exception;

import tohru.task.TodoItem;

import java.util.ArrayList;

/**
 * Catches errors that are due to corrupt save entries in the save file.
 */
public class CorruptSaveException extends TohruException {

    /** Array to hold the non-corrupt to-do items. */
    private final ArrayList<TodoItem> savedEntries;

    /**
     * Raises CorruptSaveException.
     *
     * @param errorEntriesCount Number of entries that are corrupt in the save files.
     * @param savedEntries Entries that are not corrupted.
     */
    public CorruptSaveException(int errorEntriesCount, ArrayList<TodoItem> savedEntries) {
        super(String.format("%d entries found in your save file is corrupt", errorEntriesCount));
        this.savedEntries = savedEntries;
    }

    /**
     * Retrieves the remaining non-corrupt entries.
     *
     * @return An ArrayList of non-corrupt to-do items.
     */
    public ArrayList<TodoItem> getSavedEntries() {
        return savedEntries;
    }
}
