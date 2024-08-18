@FunctionalInterface
interface TaskCreator {
    Task createTask() throws DukeException;
}
