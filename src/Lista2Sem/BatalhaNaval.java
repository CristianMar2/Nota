package Lista2Sem;

import javax.swing.*;
import java.util.*;

public class BatalhaNaval
{
    public static final int tamanhoTab = 10;
    public static final int tamanhoBarco = 3;
    public static final int quantidadeBarco = 6;
    public static char[][] tabuleiro, tabuleiro2;
    public static int linha, coluna;
    public static Scanner ler = new Scanner(System.in);
    public static int turno;

    public static void criarTabuleiro() {
        tabuleiro = new char[tamanhoTab][tamanhoTab];
        tabuleiro2 = new char[tamanhoTab][tamanhoTab];
        for (int i = 0; i < tamanhoTab; i++)
        {
            Arrays.fill(tabuleiro[i], '°');
            Arrays.fill(tabuleiro2[i], '°');
        }
    }
    public static void mostrarTabuleiro() {
        int i;
        for (i = 0; i < tamanhoTab; i++)
            System.out.printf("\t" + (i + 1));
        System.out.println("");
        for (i = 0; i < tamanhoTab; i++) {
            System.out.print((char) (i + 65) + " |\t");
            for (int j = 0; j < tamanhoTab; j++) {
                if (tabuleiro[i][j] == '@')
                    System.out.print("º | ");
                else System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println("");
        }
    }
    public static void mostrarJogoComeçando() throws InterruptedException {
        System.out.println("O jogo está começando, espere um momento enquanto os barcos são colocados!");
        Thread.sleep(3000);
    }
    public static void criarBarco() {
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
    public static void colocarBarco(int i, int j, boolean existeLinha) {
        for (int k = i; k < i + tamanhoBarco; k++){
            if (existeLinha)
                tabuleiro[j][k] = '@';
            else tabuleiro[k][j] = '@';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        short novoJogo = 0;
        do {
            criarTabuleiro();
            mostrarJogoComeçando();
            criarBarco();
            mostrarTabuleiro();
            iniciarJogo();
            System.out.println("Deseja jogar novamente ? (Digite 1 para Sim ou qualquer outro número para não)");
        }while(novoJogo == 1);
        System.out.println("Obrigado por jogar!!");
    }
    public static void iniciarJogo() throws InterruptedException {
        int rodada = 0;
        int partesNavios = 0;

        do{
            lerCoordenadas();
            System.out.println("Tentativa: " + ++rodada + "\n");
            jogarBomba();
            if (tabuleiro[linha][coluna] == '*') partesNavios++;
        }while(partesNavios < tamanhoBarco * quantidadeBarco);
        System.out.println("Meus parabéns!! Você destruiu todos os barcos em " + rodada + " tentativas!");
    }
    public static void lerCoordenadas(){
        do {
            System.out.println("\nDigite as coordenadas separadas por espaço (Exemplo: C 3): ");
            linha = ler.next().toUpperCase(Locale.ROOT).charAt(0) - 65;
            coluna = ler.next().charAt(0) - 49;
        }while (coordenadasValidas());
    }
    public static boolean coordenadasValidas(){
        if (linha < 0 || linha >= tamanhoTab || coluna < 0 || coluna >= tamanhoTab){
            System.out.println("Coordenadas inválidas!");
            return true;
        }
        if (tabuleiro[linha][coluna] == '~' || tabuleiro[linha][coluna] == '*'){
            System.out.println("Esta casa já foi detonada!");
            return true;
        }
        return false;
    }
    public static void jogarBomba() throws InterruptedException {
        JOptionPane.showMessageDialog(null, "Lançando a bomba...");
        Thread.sleep(3000);
        if (tabuleiro[linha][coluna] == '°')
            tabuleiro[linha][coluna] = '~';
        else tabuleiro[linha][coluna] = '*';
        mostrarTabuleiro();
    }
}