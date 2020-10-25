import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

// схрещення(кроссовер)
// порядковий кросовер(знаходимо найкращі гени і розподіляємо їх спадкоємцю
class OrderCrossing extends CrossingStrategy {

    private static int[] bornChild(int firstSplitPoint, int secondSplitPoint, int[] firstFather, int[] secondFather) {
        int maxValue = firstFather.length;
        int[] firstChild = new int[maxValue];
        Set<Integer> insertNode = new LinkedHashSet<>();
        for (int k : secondFather) {
            insertNode.add(k);
        }
        for (int i = firstSplitPoint; i < secondSplitPoint; i++) {
            firstChild[i] = firstFather[i];
            insertNode.remove(firstFather[i]);
        }
        Iterator<Integer> numbers = insertNode.iterator();

        for (int i = 0; i < firstSplitPoint; i++) {
            firstChild[i] = numbers.next();
        }

        for (int i = secondSplitPoint; i < firstChild.length; i++) {
            firstChild[i] = numbers.next();
        }

        return firstChild;
    }

    @Override
    // довільне об'єднання генів предків для отримання спадкоємців
    public void crossAnimal() {
        Random random = new Random();
        int maxValue = firstFather.length;
        final int middleOfGene = maxValue / 2;
        int firstSplitPoint = random.nextInt(middleOfGene);
        int secondSplitPoint = random.nextInt(middleOfGene) + middleOfGene;
        // отримання спадкоємців шляхом кросоверу
        firstChild = bornChild(firstSplitPoint, secondSplitPoint, firstFather, secondFather);
        secondChild = bornChild(firstSplitPoint, secondSplitPoint, secondFather, firstFather);

    }

}