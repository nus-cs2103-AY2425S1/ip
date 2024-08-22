interface Command<T> {
    void execute(T context, Ui ui);

    default boolean isExit() {
        return false;
    }
}

