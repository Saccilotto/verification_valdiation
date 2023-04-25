package com.holiveira;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;

import com.holiveira.CentroDistribuicao.TIPOPOSTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CentroDistribuicaoTest {

    static CentroDistribuicao cd;

    //Testes de Inicialização de Centros de Distribuição

    @ParameterizedTest
    @MethodSource("casosNormalICD")
    public void inicializaCDNormal(int[] casosNormal) {
        cd = new CentroDistribuicao(casosNormal[0], casosNormal[1], casosNormal[2], casosNormal[3]);
        assertEquals("NORMAL", cd.gettSituacao().toString());
    }

    @ParameterizedTest
    @MethodSource("casosSobreavisoICD")
    public void inicializaCDSobreaviso(int[] casosSobreaviso) {
        cd = new CentroDistribuicao(casosSobreaviso[0], casosSobreaviso[1], casosSobreaviso[2], casosSobreaviso[3]);
        assertEquals("SOBREAVISO", cd.gettSituacao().toString());
    }

    @ParameterizedTest
    @MethodSource("casosEmergenciaICD")
    public void inicializaCDEmergencia(int[] casosEmergencia) {
        cd = new CentroDistribuicao(casosEmergencia[0], casosEmergencia[1], casosEmergencia[2], casosEmergencia[3]);
        assertEquals("EMERGENCIA", cd.gettSituacao().toString());
    }

    @ParameterizedTest
    @MethodSource("casosInvalidosICD")
    public void inicializaCDInvalida(int[] casosInvalidos) {
        assertThrows(InvalidParameterException.class, () -> new CentroDistribuicao(casosInvalidos[0], casosInvalidos[1], casosInvalidos[2], casosInvalidos[3]));
    }

    public static int[][] casosNormalICD() {
        return new int[][]{{500, 10000, 1250, 1250}, {250, 5000, 625, 625}};
    }

    public static int[][] casosSobreavisoICD() {
        return new int[][]{{249, 5000, 625, 625}, {250, 4999, 625, 625}, {250, 5000, 624, 625}, {250, 5000, 625, 624}, {125, 2500, 313, 313}};
    }

    public static int[][] casosEmergenciaICD() {
        return new int[][]{{124, 2500, 313, 313}, {125, 2499, 313, 313}, {125, 2500, 311, 313}, {125, 2500, 313, 311}, {0, 0, 0, 0}};
    }

    public static int[][] casosInvalidosICD() {
        return new int[][]
                {
                        {-1, 2500, 313, 313},
                        {-1, -1, 313, 313},
                        {-1, -1, -1, 313},
                        {-1, -1, -1, 313},
                        {-1, -1, -1, -1},

                        {125, -1, 313, 313},
                        {125, -1, -1, 313},
                        {125, -1, -1, -1},

                        {125, 2500, -1, 313},
                        {125, 2500, -1, -1},

                        {125, 2500, 313, -1},

                        {50000, 2500, 313, 313},
                        {50000, 50000, 313, 313},
                        {50000, 50000, 50000, 313},
                        {50000, 50000, 50000, 50000},

                        {125, 50000, 313, 313},
                        {125, 50000, 50000, 313},
                        {125, 50000, 50000, 50000},

                        {125, 2500, 50000, 313},
                        {125, 2500, 50000, 50000},

                        {125, 2500, 313, 50000}

                };
    }

    //Teste de Abastecimento do CD

    @ParameterizedTest
    @ValueSource(ints = {0, 125, 250})
    public void recebeAditivoSemExcesso(int aditivo) {

        cd = new CentroDistribuicao(250, 0, 0, 0);
        assertEquals(aditivo, cd.recebeAditivo(aditivo));

    }

    @ParameterizedTest
    @ValueSource(ints = {251, 500})
    public void recebeAditivoComExcesso(int aditivo) {

        cd = new CentroDistribuicao(250, 0, 0, 0);
        int aditivoArmazenado = cd.gettAditivo();
        assertEquals(CentroDistribuicao.MAX_ADITIVO - aditivoArmazenado, cd.recebeAditivo(aditivo));

    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -500})
    public void recebeAditivoInvalido(int aditivo) {
        cd = new CentroDistribuicao(0, 0, 0, 0);
        assertEquals(-1, cd.recebeAditivo(aditivo));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2500, 5000})
    public void recebeGasolinaSemExcesso(int gasolina) {

        cd = new CentroDistribuicao(0, 5000, 0, 0);
        assertEquals(gasolina, cd.recebeGasolina(gasolina));

    }

    @ParameterizedTest
    @ValueSource(ints = {5001, 10000})
    public void recebeGasolinaComExcesso(int gasolina) {

        cd = new CentroDistribuicao(0, 5000, 0, 0);
        int gasolinaArmazenada = cd.gettGasolina();
        assertEquals(CentroDistribuicao.MAX_GASOLINA - gasolinaArmazenada, cd.recebeGasolina(gasolina));

    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5000})
    public void recebeGasolinaInvalido(int gasolina) {
        cd = new CentroDistribuicao(0, 0, 0, 0);
        assertEquals(-1, cd.recebeGasolina(gasolina));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 625, 1250})
    public void recebeAlcoolSemExcesso(int alcool) {

        cd = new CentroDistribuicao(0, 0, 625, 625);
        assertEquals(alcool, cd.recebeAlcool(alcool));

    }

    @ParameterizedTest
    @ValueSource(ints = {1251, 2500})
    public void recebeAlcoolComExcesso(int alcool) {

        cd = new CentroDistribuicao(0, 0, 625, 625);
        int alcoolArmazenado = cd.gettAlcool1() * 2;
        assertEquals(CentroDistribuicao.MAX_ALCOOL - alcoolArmazenado, cd.recebeAlcool(alcool));

    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2500})
    public void recebeAlcoolInvalido(int alcool) {
        cd = new CentroDistribuicao(0, 0, 0, 0);
        assertEquals(-1, cd.recebeAlcool(alcool));
    }

    //Testes de Encomendas de Postos

    @ParameterizedTest
    @ValueSource(ints = {500, 10000})
    public void encomendaAceitado(int combustivel) {
        cd = new CentroDistribuicao(500, 10000, 1250, 1250);
        int[] tanquesAtual = cd.encomendaCombustivel(combustivel, TIPOPOSTO.COMUM);
        assertArrayEquals(new int[]{cd.gettAditivo(), cd.gettGasolina(), cd.gettAlcool1(), cd.gettAlcool2()}, tanquesAtual);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -1000})
    public void encomendaRecusadoParametro(int combustivel) {
        cd = new CentroDistribuicao(0, 0, 0, 0);
        assertArrayEquals(new int[]{-7}, cd.encomendaCombustivel(combustivel, TIPOPOSTO.COMUM));
        assertArrayEquals(new int[]{-7}, cd.encomendaCombustivel(combustivel, TIPOPOSTO.ESTRATEGICO));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    public void encomendaRecusadoSituacao(int combustivel) {
        cd = new CentroDistribuicao(120, 2400, 300, 300);
        assertArrayEquals(new int[]{-14}, cd.encomendaCombustivel(combustivel, TIPOPOSTO.COMUM));
    }

    @ParameterizedTest
    @ValueSource(ints = {600, 1000})
    public void encomendaRecusadoCombustivel(int combustivel) {
        cd = new CentroDistribuicao(500, 200, 1250, 1250);
        assertArrayEquals(new int[]{-21}, cd.encomendaCombustivel(combustivel, TIPOPOSTO.ESTRATEGICO));
    }

    //Code-Coverage

    @Test
    public void encomendaRecusadoSituacaoSobreavisoQuantidadeMenor() {
        cd = new CentroDistribuicao(249, 10000, 1250, 1250);
        assertArrayEquals(new int[]{-14}, cd.encomendaCombustivel(1, TIPOPOSTO.COMUM));
    }

    @Test
    public void encomendaRecusadoSituacaoEmergenciaQuantidadeMenor() {
        cd = new CentroDistribuicao(10, 10000, 1250, 1250);
        assertArrayEquals(new int[]{-14}, cd.encomendaCombustivel(1, TIPOPOSTO.ESTRATEGICO));
    }

    @Test
    public void encomendaAceitadoSituacaoSobreavisoQuantidadeMaiorPostoComum() {
        cd = new CentroDistribuicao(249, 10000, 1250, 1250);
        assertArrayEquals(new int[]{246, 9965, 1243, 1243}, cd.encomendaCombustivel(100, TIPOPOSTO.COMUM));
    }

    @Test
    public void encomendaAceitadoSituacaoEmergenciaQuantidadeMaiorPostoComum() {
        cd = new CentroDistribuicao(249, 10000, 1250, 1250);
        assertArrayEquals(new int[]{244, 9930, 1237, 1237}, cd.encomendaCombustivel(100, TIPOPOSTO.ESTRATEGICO));
    }

    @ParameterizedTest
    @MethodSource("casosTanquesVazios")
    public void entregaRecusadaTanquesVazios(int[] dados) {
        cd = new CentroDistribuicao(dados[0], dados[1], dados[2], dados[3]);
        assertArrayEquals(new int[]{-21}, cd.encomendaCombustivel(1000, TIPOPOSTO.ESTRATEGICO));
    }

    public static int[][] casosTanquesVazios() {
        return new int[][]{{0, 10000, 1250, 1250}, {500, 0, 1250, 1250}, {500, 10000, 0, 100}, {500, 10000, 100, 0}};
    }


}
