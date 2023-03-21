package com.lists;

public class GerenciaLugares {
    private int lugares[][] = new int[60][20];
    private int ocupados = 1;

    public int verificaLugar(String assento) {
        int fileira; 
        int nroAssento; 
        int result = -1;
        char f = assento.charAt(0), a = assento.charAt(3);
       
        if((assento.length() != 6) || (f != 'F' || a != 'A')) {
            return 0;
        } 

        fileira = assento.substring(1, 3).charAt(0) == '0' ? Integer.parseInt(assento.substring(2, 3)) - 1: Integer.parseInt(assento.substring(1, 3)) - 1;
        nroAssento = assento.substring(4, 6).charAt(0) == '0' ? Integer.parseInt(assento.substring(5, 6)) - 1: Integer.parseInt(assento.substring(4, 6)) - 1;

        if(fileira < 0 || fileira > 59 || nroAssento < 0 || nroAssento > 19) {
            result = 0;
        } else if(lugares[fileira][nroAssento] != 0) {
            result = 1;
        } else if((ocupados <= 100 && fileira <= 18 && fileira >= 0) || (ocupados <= 200 && ocupados > 100 && fileira >= 40 && fileira <= 59)){
            result = 2;
        } else if(lugares[fileira][nroAssento] == 0) {
            lugares[fileira][nroAssento] = 1;
            ocupados++;
            result = 3;
        }

        return result;
    }
}
