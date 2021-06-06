/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itj.tpi.classes;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTextArea;

/**
 *
 * @author luizf
 */
public class Corrida {

    private JTextArea loggerCorredores, loggerCorrida, loggerCircuito;
    private Integer quantidadeVoltas;
    private Integer quantidadeCarros;
    private Pista circuitoCorrida;
    private Float categoriaCorrida;
    private ArrayList<Corredor> corredores = new ArrayList();

    private Float getRandom(Float min, Float max) {
        return (float) Math.random() * (max - min + 1) + min;
    }

    private void showMessage(String message, JTextArea targetTextArea) {
        if (targetTextArea == null) {
            System.out.println(message);
        } else {
            targetTextArea.append(message);
        }
    }

    private void setMessage(String message, JTextArea targetTextArea) {
        if (targetTextArea == null) {
            System.out.println(message);
        } else {
            targetTextArea.setText(message);
        }
    }

    public Corrida(Integer quantidadeVoltas) {
        this.quantidadeVoltas = quantidadeVoltas;
        this.quantidadeCarros = quantidadeCarros;
        this.categoriaCorrida = 150.f;
        this.loggerCorredores = null;
        this.loggerCorrida = null;
        this.loggerCircuito = null;
    }

    public Corrida(Integer quantidadeVoltas, JTextArea loggerCorredores, JTextArea loggerCorrida, JTextArea loggerCircuito) {
        this.quantidadeVoltas = quantidadeVoltas;
        this.quantidadeCarros = quantidadeCarros;
        this.categoriaCorrida = 150.f;
        this.loggerCorredores = loggerCorredores;
        this.loggerCorrida = loggerCorrida;
        this.loggerCircuito = loggerCircuito;
    }

    public void adicionaCorredor(Integer habilildadeCurva, Float potenciaMotor) {
        Corredor auxCorredor = new Corredor(this.corredores.size(), -this.corredores.size(), habilildadeCurva, potenciaMotor);
        this.corredores.add(auxCorredor);
        showMessage("Adicionando ---------------------------------" + "\n", this.loggerCorredores);
        showMessage(auxCorredor.toString() + "\n", this.loggerCorredores);
    }

    public Integer getQuantidadeVoltas() {
        return quantidadeVoltas;
    }

    public Integer getQuantidadeCarros() {
        return quantidadeCarros;
    }

    public void setQuantidadeVoltas(Integer quantidadeVoltas) {
        this.quantidadeVoltas = quantidadeVoltas;
    }

    public void resetCorredores() {
        this.corredores.clear();
        this.loggerCorredores.setText("");
    }

    public void resetCircuito() {
        this.circuitoCorrida = null;
        this.loggerCircuito.setText("");
    }

    public void iniciarCorrida() {
        showMessage("NOVA SIMULAÇÃO \n", this.loggerCorrida);
        showMessage("---------------------------------" + "\n", this.loggerCorrida);

        if (this.circuitoCorrida == null) {
            showMessage("Gerando nova pista... \n", this.loggerCorrida);
            this.circuitoCorrida = new Pista(getRandom(1500.0f, 9500.0f), Math.round(getRandom(2.0f, 12.0f)));
        }

        if (this.corredores.isEmpty()) {
            showMessage("Nenhum corredor cadastrado" + "\n", this.loggerCorrida);
            showMessage("Gerando Corredores..." + "\n", this.loggerCorrida);

            for (Integer i = 0; i < Math.round(getRandom(1.0f, 4.0f)); i++) {
                showMessage("-> Gerando Corredor [" + i + "]", null);
                adicionaCorredor(Math.round(getRandom(1.0f, 6.0f)), getRandom(100.0f, 200.0f));
            }

            showMessage("Corredores gerados: " + this.corredores.size() + "\n", this.loggerCorrida);

        } else {
            showMessage("Corredores inscritos: " + this.corredores.size() + "\n", this.loggerCorrida);

            showMessage("\n-----------------------------------" + "\n", this.loggerCorredores);
            showMessage("           Nova corrida            " + "\n", this.loggerCorredores);
            showMessage("-----------------------------------" + "\n\n", this.loggerCorredores);
            for (Integer i = 0; i < this.corredores.size(); i++) {
                showMessage(this.corredores.get(i) + "\n", this.loggerCorredores);
            }

        }

        showMessage("---------------------------------" + "\n", this.loggerCorrida);
        showMessage("Informações do Circuito: \nVoltas: " + this.quantidadeVoltas + "\n" + this.circuitoCorrida.toString() + "\n", this.loggerCircuito);

        showMessage("\nIniciando corrida -" + "\n", this.loggerCorrida);

        for (Integer i = 0; i < this.quantidadeVoltas; i++) {
            showMessage("\n------- Volta " + (i + 1) + " -------" + "\n", this.loggerCorrida);

            //Randomizar tiragem de eventos
            Collections.shuffle(this.corredores);

            showMessage("\n  -- Simulando para " + this.corredores + "", null);

            for (Integer j = 0; j < this.corredores.size(); j++) {
                //Simular eventos

                if (this.corredores.get(j).getIntegridade() > 0) {

                    //Simular eventos
                    Integer auxEvento = Math.round(getRandom(1.0f, 4.0f));
                    showMessage("-- Simulando para pos:" + j + "/id: " + this.corredores.get(j).getId() + " | Evento: " + auxEvento + "\n", null);

                    switch (auxEvento) {
                        case 1: {
                            //Simulação de curvas
                            for (Integer k = 0; k < this.circuitoCorrida.getCurvas().length; k++) {
                                if ((this.circuitoCorrida.getCurva(k) > this.corredores.get(j).getHabilidadeCurva()) && (this.circuitoCorrida.getCurva(k) - this.corredores.get(j).getHabilidadeCurva()) > 2) {

                                    showMessage("O corredor " + this.corredores.get(j).getId() + " derrapou na curva " + k + " e teve perda na integridade do seu Kart!" + "\n", this.loggerCorrida);

                                    if (this.corredores.get(j).getIntegridade() < 0) {
                                        showMessage("O corredor " + this.corredores.get(j).getId() + " teve a integridade do Kart comprometida e está fora da corrida!" + "\n", this.loggerCorrida);
                                    }

                                    this.corredores.get(j).adicionaAvaria(1);
                                    this.corredores.get(j).subPosicao(2);

                                    break;
                                } else if (this.corredores.get(j).getHabilidadeCurva() < this.circuitoCorrida.getCurva(k)) {

                                    showMessage("O corredor " + this.corredores.get(j).getId() + " errou a curva " + k + "  e perdeu uma posição." + "\n", this.loggerCorrida);

                                    this.corredores.get(j).subPosicao();

                                    break;
                                } else if (k == this.circuitoCorrida.getCurvas().length - 1) {

                                    showMessage("O corredor " + this.corredores.get(j).getId() + " completou a volta normalmente." + "\n", this.loggerCorrida);

                                }
                            }
                            break;
                        }
                        case 2: {
                            //Simulação de rendimento
                            if (this.corredores.get(j).getPotenciaMotor() >= 150.0f && this.circuitoCorrida.getTamanhoCircuito() > 5.500f) {

                                showMessage("O corredor " + this.corredores.get(j).getId() + " teve de reabastecer e perdeu uma posição." + "\n", this.loggerCorrida);

                                this.corredores.get(j).subPosicao();

                            } else if (i > this.quantidadeVoltas / 2 && Math.round(getRandom(0.0f, 100.0f)) <= 15.0f) {

                                showMessage("O corredor " + this.corredores.get(j).getId() + " teve de reabastecer e perdeu uma posição." + "\n", this.loggerCorrida);

                                this.corredores.get(j).subPosicao();

                            } else {

                                showMessage("O corredor " + this.corredores.get(j).getId() + " completou a volta normalmente." + "\n", this.loggerCorrida);

                            }

                            break;
                        }

                        case 3: {
                            //Simulação de ultrapassagem
                            ArrayList<Corredor> auxCorredores = new ArrayList(this.corredores);
                            Collections.sort(auxCorredores);
                            if (this.corredores.get(j) != auxCorredores.get(0)) {
                                Corredor auxCorredor = auxCorredores.get(auxCorredores.indexOf(this.corredores.get(j)) - 1);
                                if (this.corredores.get(j).getPotenciaMotor() > auxCorredor.getPotenciaMotor()) {

                                    showMessage("O corredor " + this.corredores.get(j).getId() + " ultrapassou o corredor " + auxCorredor.getId() + "." + "\n", this.loggerCorrida);

                                    this.corredores.get(this.corredores.indexOf(auxCorredor)).subPosicao();
                                    this.corredores.get(j).addPosicao();

                                } else if (getRandom(0.0f, 100.0f) > 80.0f) {

                                    showMessage("O corredor " + this.corredores.get(j).getId() + " ultrapassou o corredor " + auxCorredor.getId() + "." + "\n", this.loggerCorrida);

                                    this.corredores.get(this.corredores.indexOf(auxCorredor)).subPosicao();
                                    this.corredores.get(j).addPosicao();

                                } else if (getRandom(0.0f, 100.0f) < 15.0f) {

                                    showMessage("O corredor " + this.corredores.get(j).getId() + " tentou realizar uma ultrapassagem e perdeu uma posição." + "\n", this.loggerCorrida);

                                    this.corredores.get(j).subPosicao();

                                } else if (getRandom(0.0f, 100.0f) < 10.0f) {

                                    showMessage("O corredor " + this.corredores.get(j).getId() + " tentou realizar uma ultrapassagem, perdeu uma posição e teve um avaria!" + "\n", this.loggerCorrida);

                                    this.corredores.get(j).subPosicao();
                                    this.corredores.get(j).adicionaAvaria();

                                    if (this.corredores.get(j).getIntegridade() < 0) {

                                        showMessage("O corredor " + this.corredores.get(j).getId() + " teve a integridade do Kart comprometida e está fora da corrida!" + "\n", this.loggerCorrida);

                                    }
                                } else {

                                    showMessage("O corredor " + this.corredores.get(j).getId() + " completou a volta normalmente." + "\n", this.loggerCorrida);

                                }
                            } else {

                                showMessage("O corredor " + this.corredores.get(j).getId() + " completou a volta normalmente." + "\n", this.loggerCorrida);

                            }

                            break;
                        }
                        case 4: {
                            //Sem simulação

                            showMessage("O corredor " + this.corredores.get(j).getId() + " completou a volta normalmente." + "\n", this.loggerCorrida);

                            break;
                        }
                        default: {
                            //Sem simulação

                            showMessage("O corredor " + this.corredores.get(j).getId() + " completou a volta normalmente." + "\n", this.loggerCorrida);

                            break;
                        }
                    }
                }
            }
        }

        Collections.sort(this.corredores);

        showMessage("\n---------------------------------" + "\n", this.loggerCorrida);
        showMessage("          Fim da corrida         " + "\n", this.loggerCorrida);
        showMessage("---------------------------------" + "\n", this.loggerCorrida);
        showMessage("\n---------------------------------" + "\n", this.loggerCorredores);
        showMessage("          Fim da corrida         " + "\n", this.loggerCorredores);
        showMessage("---------------------------------" + "\n\n", this.loggerCorredores);
        showMessage(this.corredores + "\n", this.loggerCorredores);
        showMessage("\nPrimeiro lugar: " + this.corredores.get(0).toString() + "\n", this.loggerCorrida);
        if (this.corredores.size() > 1) {
            showMessage("\nSegundo lugar: " + this.corredores.get(1).toString() + "\n", this.loggerCorrida);
        }
        if (this.corredores.size() > 2) {
            showMessage("\nTerceiro lugar: " + this.corredores.get(2).toString() + "\n", this.loggerCorrida);
        }
        showMessage("\n---------------------------------" + "\n", this.loggerCorrida);
    }
}
