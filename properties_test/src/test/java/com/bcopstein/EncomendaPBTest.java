package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.arbitraries.IntegerArbitrary;

public class EncomendaPBTest {
    static class ChocolateBarsTestInput {
        int small;
        int big;
        int total;

        public ChocolateBarsTestInput(int small, int big, int total) {
            this.small = small;
            this.big = big;
            this.total = total;
        }

        @Override
        public String toString() {
            return "ChocolateBarsTestInput [big=" + big + ", small=" + small + ", total=" + total + "]";
        }
    }

    @Property
    void onlySmallBars(@ForAll("onlySmall") ChocolateBarsTestInput input) {
        int[] rEsp = {input.total,0};
        int[] rObs = Encomenda.qtdadeBarras(input.small, input.big, input.total);
        assertArrayEquals(rEsp,rObs);
    }

    @Property
    void onlyBigBars(@ForAll("onlyBig") ChocolateBarsTestInput input) {
        int[] rEsp = {0,input.total/5};
        int[] rObs = Encomenda.qtdadeBarras(input.small, input.big, input.total);
        assertArrayEquals(rEsp,rObs);
    }

    @Property
    void bothBars(@ForAll("both") ChocolateBarsTestInput input) {
        int barras5 = input.total / 5;
        if (barras5 > input.big){
            barras5 = input.big;
        }
        int barras1 = input.total-(barras5*5); 
        //System.out.println("Esperado: total:"+input.total+", b1:"+barras1+", b5:"+barras5);
        int[] rEsp = {barras1,barras5};
        int[] rObs = Encomenda.qtdadeBarras(input.small, input.big, input.total);
        //System.out.println("Observado:["+rObs[0]+","+rObs[1]+"]");
        assertArrayEquals(rEsp,rObs);
    }

    @Property
    void noBars(@ForAll("none") ChocolateBarsTestInput input) {
        int[] rEsp = {-1,-1};
        int[] rObs = Encomenda.qtdadeBarras(input.small, input.big, input.total);
        assertArrayEquals(rEsp,rObs);
    }
 
    @Provide
    private Arbitrary<ChocolateBarsTestInput> onlySmall() {
        IntegerArbitrary small = Arbitraries.integers().greaterOrEqual(0);
        IntegerArbitrary big = Arbitraries.integers().greaterOrEqual(0);
        IntegerArbitrary total = Arbitraries.integers().greaterOrEqual(0);

        return Combinators.combine(small, big, total).as(ChocolateBarsTestInput::new)
                .filter(k -> (k.total < 5 || k.big == 0) && k.small >= k.total);
    }

    @Provide
    private Arbitrary<ChocolateBarsTestInput> onlyBig() {
        IntegerArbitrary small = Arbitraries.integers().greaterOrEqual(0);
        IntegerArbitrary big = Arbitraries.integers().greaterOrEqual(0);
        IntegerArbitrary total = Arbitraries.integers().greaterOrEqual(0);

        return Combinators.combine(small, big, total).as(ChocolateBarsTestInput::new)
                .filter(k -> k.total <= 5 * k.big && k.total % 5 == 0);
    }

    @Provide
    private Arbitrary<ChocolateBarsTestInput> both() {
        IntegerArbitrary small = Arbitraries.integers().greaterOrEqual(1);
        IntegerArbitrary big = Arbitraries.integers().greaterOrEqual(1);
        IntegerArbitrary total =  Arbitraries.integers().between(1, 50000);

        return Combinators.combine(small, big, total).as(ChocolateBarsTestInput::new)
                .filter(k -> k.total - k.big * 5 > 0 && k.small >= k.total - k.big * 5);
    }

    @Provide
    private Arbitrary<ChocolateBarsTestInput> none() {
        IntegerArbitrary small = Arbitraries.integers().greaterOrEqual(0);
        IntegerArbitrary big = Arbitraries.integers().greaterOrEqual(0);
        IntegerArbitrary total = Arbitraries.integers().greaterOrEqual(1);

        return Combinators.combine(small, big, total).as(ChocolateBarsTestInput::new)
                .filter(k -> k.small < k.total - Math.min(k.total / 5, k.big) * 5);
    }
}