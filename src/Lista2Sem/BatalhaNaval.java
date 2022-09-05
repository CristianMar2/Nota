package Lista2Sem;

import javax.swing.*;
import java.util.*;

public class BatalhaNaval
{
    public static final int tamanhoTab = 5;
    public static final int tamanhoBarco = 3;
    public static final int quantidadeBarco = 3;
    public static char[][] tabuleiro;
    public static int linha, coluna;
    public static Scanner ler = new Scanner(System.in);

    public static void criarTabuleiro() {
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
                if (tabuleiro[i][j] == '@')
                    System.out.print("º | ");
                else System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println("");
        }
    }
    public static void mostarJogoComeçando() throws InterruptedException {
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
        criarTabuleiro();
        mostarJogoComeçando();
        criarBarco();
        mostrarTabuleiro();
        iniciarJogo();
    }
    public static void iniciarJogo() throws InterruptedException {
        int rodada = 0;
        int partesNavios = 0;

        do{
            lerCoordenadas();
            System.out.println("Tentativa: \n" + ++rodada);
            jogarBomba();
            if (tabuleiro[linha][coluna] == '*') partesNavios++;
        }while(partesNavios < tamanhoBarco * quantidadeBarco);
        JOptionPane.showMessageDialog(null, "Meus parabéns!! Você destruiu todos od barcos em " + rodada + " tentativas!");
    }
    public static void lerCoordenadas(){
        do {
            System.out.println("Digite as coordenadas separadas por espaço (Exemplo: 2 3): ");
            linha = ler.next().toUpperCase(Locale.ROOT).charAt(0) - 48;
            coluna = ler.next().charAt(0) - 48;
        }while (coordenadasValidas());
    }
    public static boolean coordenadasValidas(){
        if (linha < 0 || linha >= tamanhoTab || coluna < 0 || coluna >= tamanhoTab){
            JOptionPane.showMessageDialog(null, "Coordenadas inválidas!");
            return true;
        }
        if (tabuleiro[linha][coluna] == '~' || tabuleiro[linha][coluna] == '*'){
            JOptionPane.showMessageDialog(null, "Esta casa já foi detonada!");
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