import java.util.Random;

// отимуємо шлях для поставленої задачі(тобто набір генів = хромосоми)
class PathSearchingAnimal implements Liveable {

    private final int[] gene;
    private final int path;

    public PathSearchingAnimal(int[] gene, int path) {
        this.gene = gene;
        this.path = path;
    }

    public int[] getGene() {
        return gene;
    }

    public int getPath() {
        return path;
    }
    // перерозподіл генів після мутації(допомідна функція для отримання зміненого нащадка(тобто із мутацією))
    public int[] mutate() {
        int[] geneOfMutateAnimal = new int[gene.length];
        System.arraycopy(gene, 0, geneOfMutateAnimal, 0, gene.length);
        Random random = new Random();
        int firstGene = random.nextInt(gene.length);
        int secondGene;
        while (firstGene == (secondGene = random.nextInt(gene.length))) {
        }
        int temp = geneOfMutateAnimal[firstGene];
        geneOfMutateAnimal[firstGene] = geneOfMutateAnimal[secondGene];
        geneOfMutateAnimal[secondGene] = temp;
        return geneOfMutateAnimal;
    }
    //визначається пристосованість особин
    @Override
    public double fitToLive() {
        return 1.0 / path;
    }
}
