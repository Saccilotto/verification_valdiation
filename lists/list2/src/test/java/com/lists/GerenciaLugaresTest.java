package com.lists;

import java.io.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.CsvSource;

public class GerenciaLugaresTest {
    private GerenciaLugares gt;

    @BeforeEach
    public void initialize() {}

    @ParameterizedTest
    @CsvSource({
            "F62A01",
            "F32A21",
            "F2A21",
            "F02A1",
            "Z02A19",
            "F03X19"
    })
    public void testaAssentoInvalido(String assento) {
        gt = new GerenciaLugares();

        int rEsp = 0;
        int rObs = gt.verificaLugar(assento);
        Assertions.assertEquals(rEsp, rObs);
    }

    @ParameterizedTest
    @CsvSource({
            "F10A18,0,3",
            "F42A10,6,3",
            "F35A01,13,3",
            "F36A17,0,2",
            "F47A16,0,2",
            "F37A15,6,2"
    })
    public void testaOcupaLugar(String assento, int qtdadeFilasOcupar,int rEsp) {
        gt = new GerenciaLugares();

        int j = 0;
        int fileira = assento.substring(1, 3).charAt(0) == '0' ? Integer.parseInt(assento.substring(2, 3)) - 1: Integer.parseInt(assento.substring(1, 3)) - 1;
        int nroAssento = assento.substring(4, 6).charAt(0) == '0' ? Integer.parseInt(assento.substring(5, 6)) - 1: Integer.parseInt(assento.substring(4, 6)) - 1;
        String aux;
        gt.verificaLugar(assento);
        while(j == qtdadeFilasOcupar) {
            for(int i = nroAssento+1; i <= 20; i++) {
                aux = assento.replaceFirst(Integer.toString(nroAssento), Integer.toString(i++));
                gt.verificaLugar(aux);
                if(i == 20) {
                    i = 1;
                }
            }
            aux = assento.replaceFirst(Integer.toString(fileira), Integer.toString(j++));
            j++;
        }   
    }

    @ParameterizedTest
    @CsvSource({  
        "F01A01",  
        "F20A20"
    })
    public void testaLimitesAssentos100(String assento) throws FileNotFoundException {
        gt = new GerenciaLugares();
        
        String line = "";
        try {
            String filePath = "99.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            
            while((line = reader.readLine()) != null) {
                String token = line; 
                gt.verificaLugar(token);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int rEsp = 3;
        int rObs = gt.verificaLugar(assento);
        Assertions.assertEquals(rEsp, rObs);
    }

    @ParameterizedTest
    @CsvSource({    
        "F40A01",
        "F60A20"
    })
    public void testaLimitesAssentos101(String assento) throws FileNotFoundException {
        gt = new GerenciaLugares();
        
        String line = "";
        try {
            String filePath = "100.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            
            while((line = reader.readLine()) != null) {
                String token = line; 
                gt.verificaLugar(token);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int rEsp = 3;
        int rObs = gt.verificaLugar(assento);
        Assertions.assertEquals(rEsp, rObs);
    }

    @ParameterizedTest
    @CsvSource({    
        "F40A01",
        "F60A20"
    })
    public void testaLimitesAssentos200(String assento) throws FileNotFoundException {
        gt = new GerenciaLugares();
        
        String line = "";
        try {
            String filePath = "199.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            while((line = reader.readLine()) != null) {
                String token = line; 
                gt.verificaLugar(token);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int rEsp = 3;
        int rObs = gt.verificaLugar(assento);
        Assertions.assertEquals(rEsp, rObs);
    }

    @ParameterizedTest
    @CsvSource({   
        "F01A01", 
        "F20A20",
        "F35A20",
        "F40A01",
        "F45A15",
        "F60A20"
    })
    public void testaLimitesAssentos201(String assento) throws FileNotFoundException {
        gt = new GerenciaLugares();
        
        String line = "";
        try {
            String filePath = "200.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            while((line = reader.readLine()) != null) {
                String token = line; 
                gt.verificaLugar(token);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int rEsp = 3;
        int rObs = gt.verificaLugar(assento);
        Assertions.assertEquals(rEsp, rObs);
    }

}
