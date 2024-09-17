package bing.task;

/**
 * Represents the status of a task (done or not done).
 */
public enum TaskStatus {
    DONE("X"),
    UNDONE(" ");

    private String StatusSymbol;


    /**
     * Constructs a TaskStatus with the corresponding status symbol.
     *
     * @param StatusSymbol the symbol representing the task's status
     */
    TaskStatus(String StatusSymbol) {
        this.StatusSymbol = StatusSymbol;
    }


    /**
     * Returns the symbol representing the task's status.
     *
     * @return the status symbol
     */
    public String getStatusSymbol() {
        return this.StatusSymbol;
    }


}
