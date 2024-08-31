package seedu.maxine;

import seedu.maxine.task.Task;

class TaskStub extends Task {
    private final String data;

    TaskStub(String data) {
        this.data = data;
    }

    @Override
    public String writeToFile() {
        return data;
    }
}