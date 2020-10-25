import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class GeneticWorld implements PathFindable {
    // для збереження даних
    private final int[][] MATRIX_OF_DISTANCE;
    private final int numberOfAnimals;
    private final ArrayList<PathSearchingAnimal> pathSearchingAnimals = new ArrayList<>();
    private int[] path;
    private int length = Integer.MAX_VALUE / 2;

    private final GeneticAlgo<PathSearchingAnimal> selectionStrategy;
    private final CrossingStrategy crossingStrategy;
    private final Mutable mutable;
    private final LocalImprovable localImprovable;
    // отримуємо дані для роботи з алгоритмом
    public GeneticWorld(int[][] MATRIX_OF_DISTANCE,
                        GeneticAlgo<PathSearchingAnimal> selectionStrategy, CrossingStrategy crossingStrategy,
                        Mutable mutable, LocalImprovable localImprovable, int numberOfAnimals) {
        this.MATRIX_OF_DISTANCE = MATRIX_OF_DISTANCE;
        this.selectionStrategy = selectionStrategy;
        this.crossingStrategy = crossingStrategy;
        this.mutable = mutable;
        this.localImprovable = localImprovable;
        this.numberOfAnimals = numberOfAnimals;
        makePopulation();
        iterate();
    }
    //відбувається формування особин тобто формується шлях
    private void makePopulation() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < MATRIX_OF_DISTANCE.length; i++) {
            list.add(i);
        }
        for (int i = 0; i < numberOfAnimals; i++) {
            Collections.shuffle(list);
            int[] gene = new int[list.size()];
            for (int j = 0; j < gene.length; j++) {
                gene[j] = list.get(j);
            }
            pathSearchingAnimals.add(implementGene(gene));
        }
    }

    public void iterate() {
        //Вибір батьків для схрещення,вибираємо масив батьків і приміняємо вид селекції
        final ArrayList<PathSearchingAnimal> parents = (ArrayList<PathSearchingAnimal>) pathSearchingAnimals.clone();
        selectionStrategy.setAnimals(parents);

        Random random = new Random();
        selectionStrategy.choosePair();
        // видалення предків(для підримки чередування популяції)
        parents.remove(selectionStrategy.getFirstParent());
        parents.remove(selectionStrategy.getSecondParent());
//          безпосередньо метод схрещення(беруться гени відібраних предків)
        crossingStrategy.setFirstFather(selectionStrategy.getFirstParent().getGene());
        crossingStrategy.setSecondFather(selectionStrategy.getSecondParent().getGene());
        crossingStrategy.crossAnimal();
        //додавання хромосом спадкоємців до популяції
        pathSearchingAnimals.add(implementGene(crossingStrategy.getFirstChild()));
        pathSearchingAnimals.add(implementGene(crossingStrategy.getSecondChild()));

        //додавання хромосом спадкоємців до популяції
        pathSearchingAnimals.add(implementGene(mutable.mutate(crossingStrategy.getFirstChild())));
        pathSearchingAnimals.add(implementGene(mutable.mutate(crossingStrategy.getSecondChild())));

//        }


        killOfAnimals();
        //вибір найкращого щоб замінити найгіршого(підтрика рівноваги в популяії)

        PathSearchingAnimal best = Collections.max(pathSearchingAnimals);
        path = best.getGene();
        length = best.getPath();
    }

    //видалення з популяції
    private void killOfAnimals() {
        for (int i = pathSearchingAnimals.size() - numberOfAnimals; i > 0; i--) {
            //пошук найгіршого в циклі
            PathSearchingAnimal worst = Collections.min(pathSearchingAnimals);
            pathSearchingAnimals.remove(worst);
        }
    }
    //підрахунок довжини сформованого шляху(особини)
    public int calculatePathLength(int[] path) {
        int previousPoint = path[path.length - 1];
        int totalPath = 0;
        for (int j : path) {
            totalPath += MATRIX_OF_DISTANCE[previousPoint][j];
            previousPoint = j;
        }
        return totalPath;
    }
    //локальне покращення
    private PathSearchingAnimal implementGene(int[] gene) {
        final PathSearchingAnimal pathSearchingAnimal = new PathSearchingAnimal(gene, calculatePathLength(gene));
        localImprovable.improve(pathSearchingAnimals, pathSearchingAnimal.fitToLive());
        return pathSearchingAnimal;
    }

    @Override
    public int[] getPath() {
        int[] result = new int[path.length];
        System.arraycopy(path, 0, result, 0, path.length);
        return result;
    }

    @Override
    public double getLength() {
        return length;
    }
}


