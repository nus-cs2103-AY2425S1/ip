public class Pair<S, U> {
    private S first;
    private U second;

    public Pair(S first, U second) {
        this.first = first;
        this.second = second;
    }

    public S getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }
}
