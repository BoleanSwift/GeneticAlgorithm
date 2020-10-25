// Набір з локальних покращень(служать для змінив кращу сторону гена або популяції)

import java.util.ArrayList;

//якщо тварина по живучості відрізняється на одиницю( в кращу сторону)
class OnePointLocal implements LocalImprovable {
    private final double lambda;

    public OnePointLocal(double lambda) {
        this.lambda = lambda;
    }
    // то замінюємо гіршу кращою
    @Override
    public void improve(ArrayList<PathSearchingAnimal> animals, double liveFit) {
        animals.removeIf(liveable -> liveable.fitToLive() <= liveFit && liveFit - liveable.fitToLive() < lambda);
    }
}
