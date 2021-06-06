/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itj.tpi.classes;

/**
 *
 * @author luizf
 */
public class Pista {

    private Float tamanhoCircuito;
    private Integer[] curvas;

    public Pista(Float tamanhoCircuito, Integer quantidadeCurvas) {
        this.tamanhoCircuito = tamanhoCircuito;
        this.curvas = new Integer[quantidadeCurvas];

        for (Integer i = 0; i < quantidadeCurvas; i++) {
            curvas[i] = (int) (Math.random() * (6 - 1 + 1) + 1);
        }
    }

    @Override
    public String toString() {
        String parsedCurvas = "";
        for (Integer i = 0; i < curvas.length; i++) {
            parsedCurvas += "\n" + i + " - Dificuldade: " + curvas[i] + ";";
        }
        parsedCurvas = parsedCurvas.substring(0, parsedCurvas.length()-1) + ".";
        return "Pista - \n" + "Tamanho do Circuito: " + tamanhoCircuito + "m;\nCurvas -" + parsedCurvas;
    }

    public Float getTamanhoCircuito() {
        return tamanhoCircuito;
    }

    public Integer[] getCurvas() {
        return curvas;
    }
    
    
    public Integer getCurva(Integer idCurva) {
        return curvas[idCurva];
    }
}
