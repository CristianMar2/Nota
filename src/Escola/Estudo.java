package Escola;

import javax.swing.*;

public class Estudo
{
    public static void main(String[] args)
    {
        float[] notas = new float[10];
        notas = iniciarNotas();
        System.out.println(notas[0]);
        maior_menorNota(notas);
        JOptionPane.showMessageDialog(null, "A média das notas é : " + mediaNota(notas));
        menorMedia(notas);
    }
    public static float[] iniciarNotas()
    {
        float[] vetor = {3.5F, 9.2F, 5.5F, 10.0F, 6.1F, 6.6F, 8.2F, 9.5F, 5.0F, 7.0F};
        return vetor;
    }
    public static void maior_menorNota(float[] nota)
    {
        float maior = nota[0], menor = nota[0];

        for (float v : nota)
        {
            if (maior < v)
                maior = v;
            if (menor > v)
                menor = v;
        }
        JOptionPane.showMessageDialog(null, "Maior nota: " + maior + "\nMenor nota: " + menor);
    }
    public static float mediaNota(float[] nota)
    {
        float media = 0;
        for (int i = 0; i < nota.length; i++)
            media += nota[i];
        media /= nota.length;
        return media;
    }
    public static void menorMedia(float[] nota)
    {
        float media = mediaNota(nota);
        int count = 0;

        for (int i = 0; i < nota.length; i++)
        {
            if (nota[i] < media)
                count++;
        }
        JOptionPane.showMessageDialog(null, "A quantidade de notas que ficaram abaixo da média foi: " + count);
    }
    public static void notaExtra(float[] nota)
    {
        for (int i = 0; i < nota.length; i++)
        {

        }
    }
}