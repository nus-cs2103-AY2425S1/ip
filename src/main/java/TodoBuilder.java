public class TodoBuilder extends TaskBuilder {


    @Override
    public Task build(String input) {
        return new Todo(input);
    }
}
