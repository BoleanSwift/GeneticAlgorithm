public class Tester {
    public static <Mutation> void main(String[] args) {
        // чередування операторів мутацій,кросоверу для отримання кращого результату////
        Test<GeneticAlgo<PathSearchingAnimal>> selectionStrategy = new ObjectVariation<GeneticAlgo<PathSearchingAnimal>>(new GeneticAlgo[]{new Selection<PathSearchingAnimal>()});
        Test<CrossingStrategy> crossing = new ObjectVariation<>(new CrossingStrategy[]{new CycleCrossing(), new OrderCrossing(), new PartiallyMapped()});
        Test<Mutation> mutable = new ObjectVariation(new Mutable[]{(Mutable) new SingleMutation(), (Mutable) new DoubleMutation()});
        Test<LocalImprovable> localImprovable = new ObjectVariation<>(new LocalImprovable[]{new OnePointLocal(1.0 / 10000000), new NullLocal()});
        Test<Integer> numberOfAnimals = new IntegerVariation(500, 30, 10);
        TastingWithChange changer = new TastingWithChange(new Test[]{ selectionStrategy, crossing, mutable, localImprovable, numberOfAnimals}, 3);/////
        double TheBest = Double.POSITIVE_INFINITY;
        final int[][] MATRIX_OF_DISTANCE = MatrixGenerator.generate(300);
        while (changer.hasNext()) {
            changer.iterate();
            double newLength = GeneticAlgorithm(new GeneticWorld(MATRIX_OF_DISTANCE,
                    selectionStrategy.get(), crossing.get(), (Mutable) mutable.get(), localImprovable.get(),
                    numberOfAnimals.get()));
            if (newLength < TheBest) {
                TheBest = newLength;
                changer.setOptimal();
                System.out.println("Найкращий результат: " + TheBest);


            }
        }
        System.out.println("Найкращий результат: " + TheBest);
        System.out.println("Найкраще схрещування: " + crossing.getOptimalNumber());
        System.out.println("Оптимальна мутація: " + mutable.getOptimalNumber());
        System.out.println("Локальні покращення: " + localImprovable.getOptimalNumber());

    }

    private static double GeneticAlgorithm(GeneticWorld geneticWorld) {
        for (int i = 0; i < 5000; i++) {
            geneticWorld.iterate();
        }
        return geneticWorld.getLength();
    }
}
