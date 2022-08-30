package Lista2Sem;

import java.util.Arrays;
import java.util.Random;

public class BatalhaNaval
{
    public static final int tamanhoTab = 5;
    public static final int tamanhoBarco = 3;
    public static final int quantidadeBarco = 3;
    public static char[][] tabuleiro;

    public static void criarTabuleiro()
    {
        tabuleiro = new char[tamanhoTab][tamanhoTab];
        for (int i = 0; i < tamanhoTab; i++)
        {
            Arrays.fill(tabuleiro[i], '°');
        }
    }
    public static void mostrarTabuleiro() {
        int i;
        for (i = 0; i < tamanhoTab; i++)
            System.out.printf("\t" + i);
        System.out.println("");
        for (i = 0; i < tamanhoTab; i++) {
            System.out.print(i + " |\t");
            for (int j = 0; j < tamanhoTab; j++) {
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println("");
        }
    }
    public static void iniciarJogo() throws InterruptedException {
        System.out.println("O jogo está começando, espere um momento enquanto os barcos são colocados!");
        //Thread.sleep(3000);
    }
    public static void verificarBarco() {
        int posX, posY;

        Random aleatorio = new Random();

        for (int i = 0; i < quantidadeBarco;) {
            posX = aleatorio.nextInt(tamanhoTab);
            posY = aleatorio.nextInt(tamanhoTab);
            if (inserirBarco(posX, posY)) {
                i++;
            }
        }
    }
    public static boolean inserirBarco(int posX, int posY) {
        if (posX + tamanhoBarco <= tamanhoTab) {
            if (!semBarcoNaColuna(posX, posY)) {
                colocarBarco(posX, posY, false);
                return true;
            }
        }
        else if (posY + tamanhoBarco <= tamanhoTab){
            if (!semBarcoNaLinha(posX, posY)) {
                colocarBarco(posY, posX, true);
                return true;
            }
        }
        return false;
    }
    public static boolean semBarcoNaLinha(int i, int j) {
        if (contarPartesBarcoNaLinha(i, j, '@') == 0)
            return false;
        else return true;
    }
    public static int contarPartesBarcoNaLinha(int i, int j, char simbolo){
        int countPartes = 0;
        for (int k = j; k < j + tamanhoBarco; k++) {
            if (tabuleiro[i][k] == simbolo)
                countPartes++;
        }
        return countPartes;
    }
    public static boolean semBarcoNaColuna(int i, int j) {
        if (contarPartesBarcoNaColuna(i, j, '@') == 0)
            return false;
        else return true;
    }
    public static int contarPartesBarcoNaColuna(int i, int j, char simbolo){
        int countPartes = 0;
        for (int k = i; k < i + tamanhoBarco; k++) {
            if (tabuleiro[k][j] == simbolo)
                countPartes++;
        }
        return countPartes;
    }
    public static void colocarBarco(int i, int j, boolean linha) {
        for (int k = i; k < i + tamanhoBarco; k++){
            if (linha)
                tabuleiro[j][k] = '@';
            else tabuleiro[k][j] = '@';
        }
    }
    public static void main(String[] args) throws InterruptedException {
        criarTabuleiro();
        iniciarJogo();
        mostrarTabuleiro();
        verificarBarco();
        mostrarTabuleiro();
    }
}