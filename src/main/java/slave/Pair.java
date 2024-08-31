package slave;

public class Pair<T, U> {
    T t;
    U u;

    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T getFirst() {
        return this.t;
    }

    public U getSecond() {
        return this.u;
    }

    @Override
    public String toString() {
        return "[" + t + "," + u + "]";
    }

}
