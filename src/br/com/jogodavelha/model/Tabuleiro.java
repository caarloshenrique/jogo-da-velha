package br.com.jogodavelha.model;

public class Tabuleiro {
    private char [][] tabuleiro;
    
    public Tabuleiro() {
        tabuleiro = new char[3][3];
    }
    
    public void setJogada(int x, int y, char valor) {
        tabuleiro[x][y] = valor;
    }
    
    public char getJogada(int x, int y) {
        return tabuleiro[x][y];
    }

    public char[][] getTabuleiro() {
        return tabuleiro;
    }

    public char analisaTabuleiro() {
        if(tabuleiro[0][0] == tabuleiro[0][1] && tabuleiro[0][1] == tabuleiro[0][2]) {
            return tabuleiro[0][0];
        }
        if(tabuleiro[1][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[1][2]) {
            return tabuleiro[1][0];
        }
        if(tabuleiro[2][0] == tabuleiro[2][1] && tabuleiro[2][1] == tabuleiro[2][2]) {
            return tabuleiro[2][0];
        }
        if(tabuleiro[0][0] == tabuleiro[1][0] && tabuleiro[1][0] == tabuleiro[2][0]) {
            return tabuleiro[0][0];
        }
        if(tabuleiro[0][1] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][1]) {
            return tabuleiro[0][1];
        }
        if(tabuleiro[0][2] == tabuleiro[1][2] && tabuleiro[1][2] == tabuleiro[2][2]) {
            return tabuleiro[0][2];
        }
        if(tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0];
        } else {
            return ' ';
        }
    }
    
}
