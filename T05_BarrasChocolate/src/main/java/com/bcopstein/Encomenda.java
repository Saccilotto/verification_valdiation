package com.bcopstein;

public class Encomenda {
  public static int[] qtdadeBarras(int barras1,int barras5,int peso) {
    int[] resp = new int[2];
    int qtdade5 = peso / 5;
    if (qtdade5 > barras5){
      qtdade5 = barras5;
    }
    //System.out.println("qtdade5:"+qtdade5+", barras 5:"+barras5);
    int faltam = peso - (qtdade5*5);
    //System.out.println("faltam:"+faltam);
    int qtdade1 = faltam;
    //System.out.println("qtdade1:"+qtdade1+", barras1:"+barras1);
    if (qtdade1 > barras1){
      resp[0] = -1;
      resp[1] = -1;
      return resp;
    }
    resp[0] = qtdade1;
    resp[1] = qtdade5;
    return resp;
  }
}