package br.com.jogodavelha.view;

import br.com.jogodavelha.controller.TabuleiroController;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VelhaView {
    private TabuleiroController tc;
    private JFrame janela;
    private JButton[][] botoes;
    private JPanel painel;
    private JLabel titulo;
    private char marcadorJ1, marcadorJ2, ganhador;
    private boolean efetuouJogada;

    private final int distY = 75;

    public VelhaView() {
        tc = new TabuleiroController();
        init();
        defineMarcador();
        efetuouJogada = false;
        ganhador = ' ';
    }

    public void init() {
        janela = new JFrame("Jogo da Velha - LP2");
        janela.setBounds(0, 0, 435, 430 + distY + 20);
        janela.setResizable(false);

        painel = new JPanel(null);
        painel.setBounds(0, 0, 430, 430);

        titulo = new JLabel("Jogo da Velha", SwingConstants.CENTER);
        titulo.setFont(new Font("Comic Sans", 1, 15));
        titulo.setBounds(0, 0, 430, distY);
        painel.add(titulo);

        criarBotoes();

        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void criarBotoes() {
        botoes = new JButton[3][3];
        int espacador = 10;
        int largura = 130, altura = 130;
        for (int i = 0; i < botoes.length; i++) {
            for (int j = 0; j < botoes.length; j++) {
                botoes[i][j] = new JButton();
                botoes[i][j].setText("");
                botoes[i][j].setBounds(10 + i * altura + i * espacador, j * largura + distY + (j * espacador), altura, largura);
                botoes[i][j].setVisible(true);
                botoes[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!efetuouJogada) {
                            efetuouJogada = realizarJogada(e, marcadorJ1);
                        } else {
                            efetuouJogada = realizarJogada(e, marcadorJ2);
                        }
                        ganhador = tc.verificaGanhador();
                        if(ganhador == marcadorJ1) {
                            JOptionPane.showMessageDialog(null, "Parabéns Jogador 1", "Vencedor", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Parabéns Jogador 2", "Vencedor", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                painel.add(botoes[i][j]);
            }
        }
    }

    public boolean realizarJogada(ActionEvent event, char marca) {
        for (int i = 0; i < botoes.length; i++) {
            for (int j = 0; j < botoes.length; j++) {
                if (event.getSource() == botoes[i][j]) {
                    botoes[i][j].setText(String.valueOf(marca));
                    botoes[i][j].setEnabled(false);
                    tc.marcarJogada(i, j, marca);
                }
            }
        }
        if (marca == marcadorJ1) {
            return true;
        } else {
            return false;
        }
    }

    private void defineMarcador() {
        Random r = new Random();
        int aleatorio = (int) (Math.random() * 2);
        if (aleatorio == 0) {
            marcadorJ1 = 'X';
            marcadorJ2 = 'O';
        } else {
            marcadorJ1 = 'O';
            marcadorJ2 = 'X';
        }

    }

    public void jogar() {
        System.out.println("Rodando");
    }

    public static void main(String[] args) {
        VelhaView vw = new VelhaView();
        vw.jogar();
    }
}
