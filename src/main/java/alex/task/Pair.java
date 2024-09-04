package alex.task;

public class Pair<T, S> {
    private T first;

    private S second;
    public Pair(T t, S s) {
        this.first = t;
        this.second = s;
    }

    public T getFirst() {
        return this.first;
    }

    public S getSecond() {
        return this.second;
    }
}
