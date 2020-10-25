import java.util.ArrayList;

public abstract class GeneticAlgo<T extends Liveable> {
    protected ArrayList<T> animals;
    protected T firsParent;
    protected T secondParent;

    public void setAnimals(ArrayList<T> animals) {
        this.animals = animals;
    }

    public abstract void choosePair();

    public T getFirstParent() {
        return firsParent;
    }

    public T getSecondParent() {
        return secondParent;
    }
}
