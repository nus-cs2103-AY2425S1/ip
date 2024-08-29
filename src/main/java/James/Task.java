package james;

public abstract class Task {
    private String description;
    private Boolean marked;

    public Task(String desc, Boolean mark) throws MissingDescriptionException{
        if (desc.isEmpty()) {
            throw new MissingDescriptionException("Looks like you left out description of the task, please try again.");
        }
        this.description = desc;
        this.marked = mark;
    }

    public void mark() {
        this.marked = true;
    }
    public void unMark() {
        this.marked = false;
    }
    public String printTask() {
        String mark = marked ? "X" : " ";
        return String.format("[%s] %s", mark, description);
    }

    public String toFileFormat() {
        return String.format("%d | %s", marked ? 1 : 0, description);
    }

    /**
     * Checks if the specified keyword is present in the task description.
     *
     * This method splits the task description into words and checks if any of them
     * match the provided keyword, ignoring case differences.
     *
     * @param keyWord The keyword to search for in the task description.
     * @return true if the keyword is found in the description; false otherwise.
     */
    public boolean matchKeyWord(String keyWord) {
        String[] words = this.description.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(keyWord)) {
                return true;
            }
        }
        return false;
    }

}
