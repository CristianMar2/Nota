package Lista2Sem;

import java.util.Arrays;

public class BatalhaNaval
{
    static int tamanhoTab = 5;
    static int tamanhoBarco = 3;
    static char[][] tabuleiro;

    public static void criarTabuleiro()
    {
        tabuleiro = new char[tamanhoTab][tamanhoTab];
        for (int i = 0; i < tamanhoTab; i++)
        {
            Arrays.fill(tabuleiro[i], '~');
        }
    }
    public static void mostrarTabuleiro()
    {
        int i;
        for (i = 0; i < tamanhoTab; i++)
            System.out.printf("\t" + i);
        System.out.println("");
        for (i = 0; i < tamanhoTab; i++)
        {
            System.out.print(i + " |\t");
            for (int j = 0; j < tamanhoTab; j++)
            {
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println("");
        }
    }
    public static void iniciarJogo() throws InterruptedException
    {
        System.out.println("O jogo está começando, espere um momento enquanto os barcos são colocados!");
        Thread.sleep(3000);
    }
    public static void verificarBarco()
    {

    }
    public static void main(String[] args) throws InterruptedException
    {
        iniciarJogo();
        criarTabuleiro();
        mostrarTabuleiro();
    }
}