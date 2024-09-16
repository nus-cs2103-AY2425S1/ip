package bing.task;

public enum TaskStatus {
    DONE("X"),
    UNDONE(" ");

    private String StatusSymbol;

    TaskStatus(String StatusSymbol) {
        this.StatusSymbol = StatusSymbol;
    }

    public String getStatusSymbol() {
        return this.StatusSymbol;
    }


}
