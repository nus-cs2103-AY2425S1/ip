package tohru.exception;

import java.util.ArrayList;

import tohru.task.TodoItem;

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

        assert errorEntriesCount > 0 : "CorruptSaveException should not be used when there are no error entries";

        this.savedEntries = savedEntries == null ? new ArrayList<>() : savedEntries;
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
