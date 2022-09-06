package ProjetoBatalha;

import javax.swing.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval2 {
    public static final int TAMANHO_TAB = 10; // constante sempre tudo maiúsculo
    public static final int TAMANHO_BARCO = 3;
    public static final int QUANTIDADE_BARCO = 6;
    public static char[][] tabuleiro, tabuleiro2;
    public static int linha, coluna;
    public static Scanner ler = new Scanner(System.in);
    public static int turno;

    public static void criarTabuleiro() {
        tabuleiro = new char[TAMANHO_TAB][TAMANHO_TAB];
        tabuleiro2 = new char[TAMANHO_TAB][TAMANHO_TAB];
        for (int i = 0; i < TAMANHO_TAB; i++)
        {
            Arrays.fill(tabuleiro[i], '°');
            Arrays.fill(tabuleiro2[i], '°');
        }
    }
    public static void mostrarTabuleiro(char[][] tab1, char[][]tab2) {
        int i;
        for (i = 0; i < TAMANHO_TAB; i++)
            System.out.printf("\t" + (i + 1));
        System.out.println("");
        for (i = 0; i < TAMANHO_TAB; i++) {
            System.out.print((char) (i + 65) + " |\t");
            for (int j = 0; j < TAMANHO_TAB; j++) {
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

        for (int i = 0; i < QUANTIDADE_BARCO;) {
            posX = aleatorio.nextInt(TAMANHO_TAB);
            posY = aleatorio.nextInt(TAMANHO_TAB);
            if (inserirBarco(posX, posY)) {
                i++;
            }
        }
    }
    public static boolean inserirBarco(int posX, int posY) {
        if (posX + TAMANHO_BARCO <= TAMANHO_TAB) {
            if (!semBarcoNaColuna(posX, posY)) {
                colocarBarco(posX, posY, false);
                return true;
            }
        }
        else if (posY + TAMANHO_BARCO <= TAMANHO_TAB){
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
        for (int k = j; k < j + TAMANHO_BARCO; k++) {
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
        for (int k = i; k < i + TAMANHO_BARCO; k++) {
            if (tabuleiro[k][j] == simbolo)
                countPartes++;
        }
        return countPartes;
    }
    public static void colocarBarco(int i, int j, boolean existeLinha) {
        for (int k = i; k < i + TAMANHO_BARCO; k++){
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
            mostrarTabuleiro(tabuleiro, tabuleiro2);
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
        }while(partesNavios < TAMANHO_BARCO * QUANTIDADE_BARCO);
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
        if (linha < 0 || linha >= TAMANHO_TAB || coluna < 0 || coluna >= TAMANHO_TAB){
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
        mostrarTabuleiro(tabuleiro, tabuleiro2);
    }
}