package Escola;

import javax.swing.*;
import java.util.Arrays;

public class Exercicio2
{
    public static void main(String[] args)
    {
        int[] numero = {1,2};
        numero = trocaVetor(numero);

        JOptionPane.showMessageDialog(null, "Os números trocados são: " + Arrays.toString(numero));
    }
    public static int[] trocaVetor(int[] vetor)
    {
        int[] troca = {vetor[1], vetor[0]};
        return troca;
    }
}