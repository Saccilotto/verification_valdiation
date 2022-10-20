package com.lists;

import java.io.*;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

public class RankingTeste {

    @BeforeEach
    public void initialize() {}

    @ParameterizedTest
    @CsvSource({
            "Andre Fetuccini,-9999",
            "Zicavirus,-12313",
            "Manso,-1823812038123",
            "Copstein,-5678",
            "Famous,-90123941203",
            "Andre Fetuccini,-9999",
            "Zicavirus, 12313",
            "Manso,-1823812038123",
            "Copstein,-5678",
            "Famous,-90123941203",
            "Andre Fetuccini,0",
            "Zicavirus,0",
            "Manso,0",
            "Copstein,0",
            "Famous,0"
    })
    public void testaValorNegativoNuloRepetido(String name, int score) {
        Record rec = new Record(name, score);
        Ranking aux = new Ranking();
        aux.add(rec);
    }

    @Test
    public void testaPrimeiroElemento() throws FileNotFoundException {
        String line = "";
        String delimiter = ",";
        Ranking aux = new Ranking();
        try {
            String filePath = "RetiradaElementos.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            while((line = reader.readLine()) != null) {
                String[] token = line.split(delimiter); 
                Record rec = new Record(token[0], Integer.parseInt(token[1]));
                aux.add(rec);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("Primeiro" , aux.bestScore().getName());
    }
    
    @Test
    public void testaUltimoElementoRetirada() throws FileNotFoundException {
        String line = "";
        String delimiter = ",";
        Ranking aux = new Ranking();
        try {
            String filePath = "RetiradaElementos.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            while ((line = reader.readLine()) != null) {
                String[] token = line.split(delimiter); 
                Record rec = new Record(token[0], Integer.parseInt(token[1]));
                aux.add(rec);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("Ultimo" , aux.worstScore().getName());
    } 

    @Test
    public void testaNumeroElementos() throws FileNotFoundException {
        String line = "";
        String delimiter = ",";
        Ranking aux = new Ranking();
        try {
            String filePath = "RetiradaElementos.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            while ((line = reader.readLine()) != null) {
                String[] token = line.split(delimiter); 
                Record rec = new Record(token[0], Integer.parseInt(token[1]));
                aux.add(rec);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Assertions.assertEquals(20 , aux.numRecords());
    } 
}
