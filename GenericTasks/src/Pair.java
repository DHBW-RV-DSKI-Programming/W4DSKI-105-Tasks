class Pair<T, U> {

    private T first;
    private U second;

    Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    T getT() {
        return first;
    }

    U getU() {
        return second;
    }

    Pair<U, T> swap() {
        return new Pair<>(second, first);
    }

}
