public class Main {
    public static void main(String[] args) {
        Pilha pilha = new Pilha(10);
        
        pilha.insere(13);
        pilha.insere(23);
        pilha.insere(33);
        pilha.insere(48);
        pilha.insere(27);
        pilha.mostrareElemenrtos();
        pilha.remove();
        pilha.remove();
        pilha.mostrareElemenrtos();
        pilha.insere(22);
        pilha.insere(34);
        pilha.insere(50);
        pilha.insere(60);
        pilha.remove();
        pilha.mostrareElemenrtos();
    }
}