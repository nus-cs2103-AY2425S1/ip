class Task {
    protected String detail;
    public Task(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return detail;
    }
}
