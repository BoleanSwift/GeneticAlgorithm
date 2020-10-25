import java.util.Random;

// одиноточкова мутація(випадково відібраний ген змінює значення(вноситься зміна у шлях))
class SingleMutation implements Mutable {

    @Override
    public int[] mutate(int[] gene) {
        int[] mutated = new int[gene.length];//набір генів(міст відвіданих)
        System.arraycopy(gene, 0, mutated, 0, gene.length);
        Random random = new Random();
        // відбор на мутацію точок з масиву міст які можна відвідати
        int firstPoint = random.nextInt(gene.length);
        // допоміжна точка щоб не втратити значення міста  із головного масиву
        int secondPoint;
        while (firstPoint == (secondPoint = random.nextInt(gene.length))) {
        }
        mutated[firstPoint] = gene[secondPoint];
        mutated[secondPoint] = gene[firstPoint];
        return mutated;
    }
}
