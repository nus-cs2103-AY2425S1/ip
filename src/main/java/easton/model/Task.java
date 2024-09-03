package easton.model;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getCsvFormat() {
        return (isDone ?  "1" : "0") + "," + description;
    }

    /**
     * Checks if the keyword is in the description.
     *
     * @param keyword Keyword to search.
     * @return If the keyword is in the description.
     */
    public boolean hasKeyword(String keyword) {
        String[] splitString = description.split(" ");
        for (String word : splitString) {
            if (word.matches(keyword)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
