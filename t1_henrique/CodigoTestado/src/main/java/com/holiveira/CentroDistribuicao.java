package com.holiveira;
import java.security.InvalidParameterException;

public class CentroDistribuicao {
    public enum SITUACAO {
        NORMAL,
        SOBREAVISO,
        EMERGENCIA
    }

    public enum TIPOPOSTO {
        COMUM,
        ESTRATEGICO
    }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    private int tAditivo;
    private int tGasolina;
    private int tAlcool1;
    private int tAlcool2;
    private SITUACAO situacao;

    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        this.tAditivo = tAditivo;
        this.tGasolina = tGasolina;
        this.tAlcool1 = tAlcool1;
        this.tAlcool2 = tAlcool2;
        this.defineSituacao();

        //Checar se valores são negativos ou maiores que o limite e jogar exception
        if(tAditivo > MAX_ADITIVO || tGasolina > MAX_GASOLINA || tAlcool1 > MAX_ALCOOL || tAlcool2 > MAX_ALCOOL|| tAditivo < 0 || tGasolina < 0 || tAlcool1 < 0 || tAlcool2 < 0){
            throw new InvalidParameterException("Valores inválidos no abastecimento dos tanques");
        }
    }

    public void defineSituacao(){
       if( tAditivo >= MAX_ADITIVO * 0.5 && tGasolina >= MAX_GASOLINA * 0.5 && (tAlcool1 + tAlcool2) >= MAX_ALCOOL * 0.5){
           situacao = SITUACAO.NORMAL;
       } else if( tAditivo < MAX_ADITIVO * 0.25 || tGasolina < MAX_GASOLINA * 0.25 || (tAlcool1 + tAlcool2) < MAX_ALCOOL * 0.25){
           situacao = SITUACAO.EMERGENCIA;
       } else {
           situacao = SITUACAO.SOBREAVISO;
       }
    }

    public int gettAditivo() {
        return tAditivo;
    }

    public int gettGasolina() {
        return tGasolina;
    }

    public int gettAlcool1() {
        return tAlcool1;
    }

    public int gettAlcool2() {
        return tAlcool2;
    }

    public SITUACAO gettSituacao() {
        return situacao;
    }

    public int recebeAditivo(int qtdade) {
        if(qtdade < 0){
            return -1;
        }

        if(tAditivo + qtdade <= MAX_ADITIVO){
            tAditivo = tAditivo + qtdade;
            defineSituacao();
            return qtdade;
        }

        int aux = MAX_ADITIVO - tAditivo;
        tAditivo = MAX_ADITIVO;
        defineSituacao();
        return aux;
    }

    public int recebeGasolina(int qtdade) {
        if(qtdade < 0){
            return -1;
        }

        if(tGasolina + qtdade <= MAX_GASOLINA){
            tGasolina = tGasolina + qtdade;
            defineSituacao();
            return qtdade;
        }

        int aux = MAX_GASOLINA - tGasolina;
        tGasolina = MAX_GASOLINA;
        defineSituacao();
        return aux;
    }

    public int recebeAlcool(int qtdade) {
        if(qtdade < 0){
            return -1;
        }

        int totalAlcool = tAlcool1 + tAlcool2 + qtdade;
        if(totalAlcool <= MAX_ALCOOL){
            tAlcool1 = tAlcool2 = totalAlcool/2;
            defineSituacao();
            return qtdade;
        }

        int aux = MAX_ALCOOL - tAlcool1 - tAlcool2;
        tAlcool1 = tAlcool2 = MAX_ALCOOL / 2;
        defineSituacao();
        return aux;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoposto) {
        if(qtdade < 1){
            return new int[]{-7};
        }

        if(tipoposto == TIPOPOSTO.COMUM){
            if(situacao == SITUACAO.EMERGENCIA){
                return new int[]{-14};
            }
            if(situacao == SITUACAO.SOBREAVISO){
                qtdade = qtdade / 2;
                if(qtdade == 0){
                    return new int[]{-14};
                }
            }
        }

        if(tipoposto == TIPOPOSTO.ESTRATEGICO){
            if(situacao == SITUACAO.EMERGENCIA){
                qtdade = qtdade / 2;
                if(qtdade == 0){
                    return new int[]{-14};
                }
            }
        }

        boolean getCombustivel = consegueCombustivel(qtdade, tipoposto);
        int[] combustivelTanques = getCombustivel ? new int[] {tAditivo, tGasolina, tAlcool1, tAlcool2} : new int[]{-21};
        return combustivelTanques;
    }

    private boolean consegueCombustivel(int qtdade, TIPOPOSTO tipoposto){
        int quantidadeGasolinaMistura = (int)(tGasolina - qtdade * 0.7);
        int quantidadeAlcoolMistura = (int)((tAlcool2 + tAlcool1) - qtdade * 0.25);
        int quantidadeAditivoMistura = (int)(tAditivo - qtdade * 0.05);
        boolean getCombustivel = false;

        if(quantidadeGasolinaMistura >= 0 && quantidadeAlcoolMistura >= 0 && quantidadeAditivoMistura >= 0){
                retiraCombustivel(quantidadeAditivoMistura, quantidadeGasolinaMistura, quantidadeAlcoolMistura);
                getCombustivel = true;
            }
        

        return getCombustivel;
    }

    private void retiraCombustivel(int taditivo, int tgasolina, int talcool){
        tAditivo = taditivo;
        tGasolina = tgasolina;
        tAlcool2 = tAlcool1 = talcool / 2;
        defineSituacao();
    }
}
