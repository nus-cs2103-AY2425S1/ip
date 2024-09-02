package mryapper.task;

public abstract class Task {
    private final String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public int getStatus() {
        return isDone ? 1 : 0;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Checks whether the task description has all keywords in the search input.
     *
     * @param searchInput The search input from the user.
     * @return Whether all keywords are contained in the task description.
     */
    public boolean hasKeywords(String searchInput) {
        String[] keywords = searchInput.trim().split("\\s+");
        int i = 0;
        String[] descriptionKeywords = this.description.trim().split("\\s+");
        boolean hasMissingKeyword = false;

        while (!hasMissingKeyword && i < keywords.length) {
            String keyword = keywords[i].toLowerCase();
            boolean hasKeyword = false;
            for (String word : descriptionKeywords) {
                if (keyword.equals(word.toLowerCase())) {
                    hasKeyword = true;
                    break;
                }
            }

            if (!hasKeyword) {
                return false;
            }
            i += 1;
        }
        return !hasMissingKeyword;
    }

    // gets the string data to be written in the data file
    public abstract String getDataString();
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
