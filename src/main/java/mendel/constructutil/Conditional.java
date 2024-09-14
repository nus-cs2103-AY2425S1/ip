package mendel.constructutil;

public class Conditional<T> {
    private final boolean isMet;
    private final T value;

    private Conditional(boolean isMet, T value) {
        this.isMet = isMet;
        this.value = value;
    }

    public static <T> Conditional<T> ifBlock(MethodCall<Boolean> bc, MethodCall<? extends T> m) {
        if (bc.produce()) {
            return new Conditional<>(true, m.produce());
        } else {
            return new Conditional<>(false, null);
        }
    }

    public Conditional<T> elifBlock(MethodCall<Boolean> bc, MethodCall<? extends T> m) {
        if (isMet) {
            return new Conditional<>(isMet, value);
        } else if (bc.produce()) {
            return new Conditional<>(true, m.produce());
        } else {
            return new Conditional<>(false, null);
        }
    }

    public T elseBlock(MethodCall<? extends T> m) {
        if (isMet) {
            return this.value;
        } else {
            return m.produce();
        }
    }

    public T getValue() {
        return this.value;
    }
}
