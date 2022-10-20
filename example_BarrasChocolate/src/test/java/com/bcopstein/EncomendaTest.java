package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class EncomendaTest {
    @Test
    public void testaQuantidadesSuficientes(){
        int[] rEsp = {5,1};
        int[] rObs = Encomenda.qtdadeBarras(5,1,10);
        //System.out.println("rEsp[0]:"+rEsp[0]+", rEsp[1]:"+rEsp[1]);
        //System.out.println("rObs[0]:"+rObs[0]+", rObs[1]:"+rObs[1]);
        assertArrayEquals(rEsp,rObs);
    }
}
