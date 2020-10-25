// доступні мутації
class DoubleMutation extends SingleMutation {
    @Override
    public int[] mutate(int[] gene) {
        return super.mutate(super.mutate(gene));
    }
}
