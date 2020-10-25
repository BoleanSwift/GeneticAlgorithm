import java.util.*;
import java.util.stream.IntStream;



// селекція (турнірний спосіб - вибирається найкращий предок)
class Selection<T extends Liveable> extends GeneticAlgo<T> {
    public void choosePair() {
        //починаємо з першого(розглядаємо 1 частину масиву популяції)
        firsParent = animals.get(0);
        //пошук кращого
        for (int i = 1; i < animals.size() / 2; i++) {
            T nowParent = animals.get(i);
            if (firsParent.compareTo(nowParent) < 0)
                firsParent = nowParent;
        }
        //       (розглядаємо 2 частину масиву популяції) і так само відбираємо кращого
        secondParent = animals.get(animals.size() / 2);
        for (int i = animals.size() / 2 + 1; i < animals.size(); i++) {
            T nowParent = animals.get(i);
            if (secondParent.compareTo(nowParent) < 0)
                secondParent = nowParent;
        }
    }
}
