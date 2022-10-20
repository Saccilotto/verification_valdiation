package com.bcopstein;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaMagicaTest {
    private ContaMagica conta;

    @BeforeEach
    public void initialize() {}

    @Test
    public void testaDepositoGold() {
        conta = new ContaMagica("100445-14","Zeca Pagodinho");
        conta.deposito(50000.0);

        Categoria rEsp = Categoria.GOLD;
        Categoria rObs = conta.getCategoria();

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void testaDepositoPlatinum() {
        conta = new ContaMagica("331113-12","Lula Molusco");
        conta.deposito(100000.0);
        conta.deposito(100000.0);

        Categoria rEsp = Categoria.PLATINUM;
        Categoria rObs = conta.getCategoria();

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void testaDepositoGoldBonus() {
        conta = new ContaMagica("100445-14","Zeca Pagodinho");
        conta.deposito(50000.0);
        conta.deposito(100000.0);

        double rEsp = 50000.0 + 100000.0*1.01;
        double rObs = conta.getSaldo();

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void testaDepositoPlatinumBonus() {
        conta = new ContaMagica("100445-14","Zeca Pagodinho");
        conta.deposito(100000.0);
        conta.deposito(100000.0);
        conta.deposito(100000.0);

        double rEsp = 200000.0 + 100000.0*1.025;
        double rObs = conta.getSaldo();

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void testaRetiradaGoldValor() {
        conta = new ContaMagica("100445-14","Zeca Pagodinho");
        conta.deposito(50000.0);
        conta.retirada(25000.0);

        double rEsp = 50000.0 - 25000.0;
        double rObs = conta.getSaldo();

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void testaRetiradaPlatinumValor() {
        conta = new ContaMagica("100445-14","Zeca Pagodinho");
        conta.deposito(100000.0);
        conta.deposito(100000.0);
        conta.retirada(50000.0);

        double rEsp = 200000.0 - 50000.0;
        double rObs = conta.getSaldo();

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void testaRetiradaSilver() {
        conta = new ContaMagica("121212-09","Bob Esponja");
        conta.deposito(50000.000);
        conta.retirada(25000.001);

        Categoria rEsp = Categoria.SILVER;
        Categoria rObs = conta.getCategoria();

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void testaRetiradaGold() {
        conta = new ContaMagica("111111-06","Patrick Estrela");
        conta.deposito(100000.000);
        conta.deposito(100000.000);
        conta.retirada(100000.001);

        Categoria rEsp = Categoria.GOLD;
        Categoria rObs = conta.getCategoria();

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void testaRetiradaSuperiorSaldo() {
        conta = new ContaMagica("121212-09","Bob Esponja");
        conta.deposito(25000.000);
        conta.retirada(25000.001);

        double rObs = conta.getSaldo();

        System.out.println(conta.getSaldo());

        Assertions.assertTrue(rObs > 0);
    }
}
