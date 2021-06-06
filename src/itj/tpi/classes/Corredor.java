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
public class Corredor implements Comparable<Corredor> {

    private final Integer id;
    private Integer posicao;
    private final Integer habilidadeCurva;
    private final Float potenciaMotor;
    private Integer integridade;

    public Corredor(Integer idCorredor,Integer posicao,  Integer habilidadeCurvaCorredor, Float potenciaMotor) {
        this.id = idCorredor;
        this.posicao = posicao;
        this.habilidadeCurva = habilidadeCurvaCorredor;
        this.potenciaMotor = potenciaMotor;
        this.integridade = 5;
    }

    @Override
    public String toString() {
        return "\nCorredor " + "#" + id + " - Posição: " + posicao + ", Habilidade de Curva: " + habilidadeCurva + ", Potência do Motor: " + potenciaMotor + ", Integridade do Kart: " + integridade + ".";
    }

    public Integer getId() {
        return id;
    }

    public Integer getHabilidadeCurva() {
        return habilidadeCurva;
    }

    public Float getPotenciaMotor() {
        return potenciaMotor;
    }

    public Integer getIntegridade() {
        return integridade;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public Integer addPosicao() {
        this.posicao += 1;
        return this.posicao;
    }

    public Integer addPosicao(Integer posicoes) {
        this.posicao += posicoes;
        return this.posicao;
    }

    public Integer subPosicao() {
        this.posicao = this.posicao - 1;
        return this.posicao;
    }

    public Integer subPosicao(Integer posicoes) {
        this.posicao = this.posicao - posicoes;
        return this.posicao;
    }

    public Integer adicionaAvaria() {
        this.integridade = integridade - 1;
        return this.integridade;
    }

    public Integer adicionaAvaria(Integer gravidadeAvaria) {
        this.integridade = integridade - gravidadeAvaria;
        return this.integridade;
    }

    @Override
    public int compareTo(Corredor corredorComparacao) {
        return corredorComparacao.getPosicao() - this.posicao;
    }
}
