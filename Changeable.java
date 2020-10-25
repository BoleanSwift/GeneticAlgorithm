interface Changeable<T> {
    void change();

    boolean hasNext();

    void fromStart();

    T get();

    void setOptimal();

    void setTesting(boolean isTesting);

    int getOptimalNumber();
}
