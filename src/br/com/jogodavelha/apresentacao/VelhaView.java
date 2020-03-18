package br.com.jogodavelha.apresentacao;

import br.com.jogodavelha.controlador.TabuleiroController;
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
    private JLabel titulo, background, jogador1, jogador2;
    private char marcadorJ1, marcadorJ2, ganhador;
    private boolean efetuouJogada;
    private final int distY = 75;
    private String jg1, jg2;
    private int jg1Placar, jg2Placar, jogo;

    public VelhaView() {
        tc = new TabuleiroController();
        jg1 = JOptionPane.showInputDialog(null, "Entre com seu nome:", "Jogador 1", JOptionPane.QUESTION_MESSAGE);
        jg2 = JOptionPane.showInputDialog(null, "Entre com seu nome:", "Jogador 2", JOptionPane.QUESTION_MESSAGE);
        init();
        defineMarcador();
        efetuouJogada = false;
        ganhador = ' ';
    }
    
    public void reiniciar() {
        tc = new TabuleiroController(); 
        janela.dispose();
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
        
        jogador1 = new JLabel(jg1 + ": " + jg1Placar, SwingConstants.CENTER);
        jogador1.setFont(new Font("Arial", 1, 15));
        jogador1.setBounds(0, 0, 140, distY);
        
        titulo = new JLabel("Jogo da Velha", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", 1, 15));
        titulo.setBounds(0, 0, 430, distY);
        
        jogador2 = new JLabel(jg2 + ": " + jg2Placar, SwingConstants.CENTER);
        jogador2.setFont(new Font("Arial", 1, 15));
        jogador2.setBounds(0, 0, 710, distY);

        painel.add(jogador1);
        painel.add(titulo);
        painel.add(jogador2);
        
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
                botoes[i][j].setBounds(10 + i * largura + i * espacador,
                        j * altura + distY + (j * espacador),
                        altura, largura);
                botoes[i][j].setVisible(true);
                botoes[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!efetuouJogada) {
                            efetuouJogada = realizarJogada(e, marcadorJ1);
                        } else {
                            efetuouJogada = realizarJogada(e, marcadorJ2);
                        }
                        ganhador = tc.verificaGanhador();
                        if(ganhador == marcadorJ1){
                            mostrarVencedor(jg1);
                            jg1Placar += 1;                  
                            jogarNovamente();
                        }else if(ganhador == marcadorJ2){
                            mostrarVencedor(jg2);
                            jg2Placar += 1;                       
                            jogarNovamente();
                        }else if(ganhador == '#'){
                            JOptionPane.showMessageDialog(null, "Deu Velha #", 
                                    "Empate", JOptionPane.INFORMATION_MESSAGE);
                            jogarNovamente();
                        }
                    }
                });
                painel.add(botoes[i][j]);
            }
        }
    }    
    
    private void jogarNovamente() {
       int controle = JOptionPane.showConfirmDialog(null,
                                 "Deseja jogar novamente?", null,
                                 JOptionPane.YES_NO_OPTION);
       boolean resposta = (controle == JOptionPane.YES_OPTION);
       if(resposta) {
           reiniciar();
       } else {
           janela.dispose();
       }
    }
    
    private void mostrarVencedor(String s){
        JOptionPane.showMessageDialog(janela, "Parabens " + s, 
                                    "Vencedor", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean realizarJogada(ActionEvent evt, char marca) {
        for (int i = 0; i < botoes.length; i++) {
            for (int j = 0; j < botoes.length; j++) {
                if (evt.getSource() == botoes[i][j]) {
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

    }

    public static void main(String[] args) {
        VelhaView vw = new VelhaView();
        vw.jogar();
    }
}
