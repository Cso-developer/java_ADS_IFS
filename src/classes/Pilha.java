public class Pilha {
   private int topo;
   private int tamanho;
   private int[] pilha;

   public Pilha(int tamanho) {
        this.tamanho = tamanho;
        this.pilha = new int[tamanho];
        this.topo = 0;
   }

   public void insere(int valor) {
        if (topo < tamanho) {
            pilha[topo] = valor;
            topo++;
        } else {
            System.out.println("Pilha cheia!");
        }
   }

   public void remove() {
        if (topo > 0) {
            topo--;
        } else {
            System.out.println("Pilha vazia!");
        }
   }
    
    public void mostrareElemenrtos() {
          System.out.println("Elementos na pilha:");
          for (int i = topo - 1; i >= 0; i--) {
                System.out.println(pilha[i]);
          }
    }   

 }

 
