public class CentroDistribuicao {
    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA }
    public enum TIPOPOSTO { COMUM, ESTRATEGICO }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    private int aditivo;
    private int gasolina;
    private int alcool1;
    private int alcool2;
    private SITUACAO situacao;

    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        if(tAditivo > MAX_ADITIVO || tGasolina > MAX_GASOLINA || tAlcool1 + tAlcool2 > MAX_ALCOOL || tAlcool1 != tAlcool2) {
            throw new IllegalArgumentException();
        }
        this.aditivo = tAditivo;
        this.gasolina = tGasolina;
        this.alcool1 = tAlcool1;
        this.alcool2 = tAlcool2;
        defineSituacao();
    }

    public void defineSituacao() {
        if(aditivo < (25 * MAX_ADITIVO)/100 || gasolina < (25 * MAX_GASOLINA)/100 || alcool1 + alcool2 < (25 * MAX_ALCOOL)/100){
            this.situacao = SITUACAO.EMERGENCIA;
        }
        else if(aditivo < (50 * MAX_ADITIVO)/100 || gasolina < (50 * MAX_GASOLINA)/100 || alcool1 + alcool2 < (50 * MAX_ALCOOL)/100) {
            this.situacao = SITUACAO.SOBRAVISO;
        }
        else {
            this.situacao = SITUACAO.NORMAL;
        }
    }

    public SITUACAO getSituacao() {
        return situacao;
    }

    public int gettGasolina() {
        return gasolina;
    }

    public int gettAditivo() {
        return aditivo;
    }

    public int gettAlcool1() {
        return alcool1;
    }

    public int gettAlcool2() {
        return alcool2;
    }

    public int recebeAditivo(int qtdade) {
        int qtdValida = MAX_ADITIVO - this.aditivo;

        if(qtdade <= 0) {
            return -1;
        }
        else if(qtdade > qtdValida) {
            this.aditivo += qtdValida;
            return qtdValida;
        }
        this.aditivo += qtdade;
        return qtdade;
    }

    public int recebeGasolina(int qtdade) {
        int qtdValida = MAX_GASOLINA - this.gasolina;

        if(qtdade <= 0) {
            return -1;
        }
        else if(qtdade > qtdValida) {
            this.gasolina += qtdValida;
            return qtdValida;
        }
        this.gasolina += qtdade;
        return qtdade;
    }

    public int recebeAlcool(int qtdade) {
        int qtdValida = MAX_ALCOOL - (this.alcool1 + this.alcool2);

        if(qtdade <= 0) {
            return -1;
        }
        else if(qtdade > qtdValida) {
            this.alcool1 += qtdValida/2;
            this.alcool2 += qtdValida/2;
            return qtdValida;
        }
        this.alcool1 += qtdade/2;
        this.alcool2 += qtdade/2;
        return qtdade;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoposto) {
        int[] resultado = new int[4];
        int qtdAditivo = 0;
        int qtdGasolina = 0;
        int qtdAlcool = 0;

        if(qtdade <= 0) {
            resultado[0] = -7;
        }
        else if(situacao == SITUACAO.EMERGENCIA) {
            if(tipoposto == TIPOPOSTO.COMUM) {
                resultado[0] = -14;
                return resultado;
            } else {
                qtdade = qtdade/2;

                qtdAditivo = (5 * qtdade)/100;
                qtdGasolina = (70 * qtdade)/100;
                qtdAlcool = (25 * qtdade)/100;

                if(qtdAditivo > this.aditivo || qtdGasolina > this.gasolina || qtdAlcool > this.alcool1 + this.alcool2) {
                    resultado[0] = -21;
                    return resultado;
                }
                this.aditivo -= qtdAditivo;
                this.gasolina -= qtdGasolina;
                this.alcool1 -= qtdAlcool/2;
                this.alcool2 -= qtdAlcool/2;
                resultado[0] = this.aditivo;
                resultado[1] = this.gasolina;
                resultado[2] = this.alcool1;
                resultado[3] = this.alcool2;
            }
        }
        else if(situacao == SITUACAO.SOBRAVISO) {
            if(tipoposto == TIPOPOSTO.COMUM) {
                qtdade = qtdade/2;

                qtdAditivo = (5 * qtdade)/100;
                qtdGasolina = (70 * qtdade)/100;
                qtdAlcool = (25 * qtdade)/100;

                if(qtdAditivo > this.aditivo || qtdGasolina > this.gasolina || qtdAlcool > this.alcool1 + this.alcool2) {
                    resultado[0] = -21;
                    return resultado;
                }
                this.aditivo -= qtdAditivo;
                this.gasolina -= qtdGasolina;
                this.alcool1 -= qtdAlcool/2;
                this.alcool2 -= qtdAlcool/2;
                resultado[0] = this.aditivo;
                resultado[1] = this.gasolina;
                resultado[2] = this.alcool1;
                resultado[3] = this.alcool2;
            } else {
                qtdAditivo = (5 * qtdade)/100;
                qtdGasolina = (70 * qtdade)/100;
                qtdAlcool = (25 * qtdade)/100;

                if(qtdAditivo > this.aditivo || qtdGasolina > this.gasolina || qtdAlcool > this.alcool1 + this.alcool2) {
                    resultado[0] = -21;
                    return resultado;
                }
                this.aditivo -= qtdAditivo;
                this.gasolina -= qtdGasolina;
                this.alcool1 -= qtdAlcool/2;
                this.alcool2 -= qtdAlcool/2;
                resultado[0] = this.aditivo;
                resultado[1] = this.gasolina;
                resultado[2] = this.alcool1;
                resultado[3] = this.alcool2;
            }
        }
        else {
            qtdAditivo = (5 * qtdade)/100;
            qtdGasolina = (70 * qtdade)/100;
            qtdAlcool = (25 * qtdade)/100;

            if(qtdAditivo > this.aditivo || qtdGasolina > this.gasolina || qtdAlcool > this.alcool1 + this.alcool2) {
                resultado[0] = -21;
                return resultado;
            }
            this.aditivo -= qtdAditivo;
            this.gasolina -= qtdGasolina;
            this.alcool1 -= qtdAlcool/2;
            this.alcool2 -= qtdAlcool/2;
            resultado[0] = this.aditivo;
            resultado[1] = this.gasolina;
            resultado[2] = this.alcool1;
            resultado[3] = this.alcool2;
        }

        return resultado;
    }
}
