import java.util.InputMismatchException;
// Змінюючи параметри алгоритму, визначаємо кращі вхідні параметри алгоритму
//самого алгоритму не стосується але потрібний щоб отримати кращий результат при тестуванні

public class TastingWithChange {
    private final Test<Object>[] ARRAY;
    private final int countOfCycle;
    private int pos = 0;
    private int cyclNum = 0;


    public TastingWithChange(Test<Object>[] array, int number_of_cycles) {
        this.ARRAY = array;
        countOfCycle = number_of_cycles;
    }

    public boolean hasNext() {
        return countOfCycle != cyclNum;
    }
    public void setOptimal() {
        ARRAY[pos].setOptimal();
    }

    public void iterate() {
        final Test<Object> changeable = ARRAY[pos];
        if (changeable.hasNext()) {
            changeable.setTesting(true);
            changeable.change();
        } else {
            changeable.setTesting(false);
            changeable.fromStart();
            pos++;
            if (pos >= ARRAY.length) {
                pos = 0;
                cyclNum++;
            }
            iterate();
        }
    }


}