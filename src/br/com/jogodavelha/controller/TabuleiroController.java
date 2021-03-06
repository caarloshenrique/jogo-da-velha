package br.com.jogodavelha.controller;

import br.com.jogodavelha.model.Tabuleiro;

public class TabuleiroController {
    private Tabuleiro tabuleiro;
    
    public TabuleiroController(){
        tabuleiro = new Tabuleiro();
    }
    
    public void marcarJogada(int i, int j, char marca) {
        tabuleiro.setJogada(i, j, marca);
    }
    
    public char verificaGanhador() {
        return tabuleiro.analisaTabuleiro();
    }
}
