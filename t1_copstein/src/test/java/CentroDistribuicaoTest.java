import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CentroDistribuicaoTest {

    @BeforeEach
    public void initialize() {}

    @Test
    public void TesteEmergenciaInferior() {
        CentroDistribuicao aux = new CentroDistribuicao(124, 2499, 313, 312);

        CentroDistribuicao.SITUACAO rEsp = aux.getSituacao();
        CentroDistribuicao.SITUACAO rObs = CentroDistribuicao.SITUACAO.EMERGENCIA;

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteEmergenciaLimite() {
        CentroDistribuicao aux = new CentroDistribuicao(125, 2500, 313, 313);

        CentroDistribuicao.SITUACAO rEsp = aux.getSituacao();
        CentroDistribuicao.SITUACAO rObs = CentroDistribuicao.SITUACAO.EMERGENCIA;

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteSobravisoInferior() {
        CentroDistribuicao aux = new CentroDistribuicao(249, 4999, 1249, 1249);

        CentroDistribuicao.SITUACAO rEsp = aux.getSituacao();
        CentroDistribuicao.SITUACAO rObs = CentroDistribuicao.SITUACAO.SOBRAVISO;

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteSobravisoLimite() {
        CentroDistribuicao aux = new CentroDistribuicao(250, 5000, 1250, 1250);

        CentroDistribuicao.SITUACAO rEsp = aux.getSituacao();
        CentroDistribuicao.SITUACAO rObs = CentroDistribuicao.SITUACAO.NORMAL;

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteNormalInferior() {
        CentroDistribuicao aux = new CentroDistribuicao(249, 4999, 1249, 1249);

        CentroDistribuicao.SITUACAO rEsp = aux.getSituacao();
        CentroDistribuicao.SITUACAO rObs = CentroDistribuicao.SITUACAO.SOBRAVISO;

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteNormalLimite() {
        CentroDistribuicao aux = new CentroDistribuicao(250, 5000, 1250, 1250);

        CentroDistribuicao.SITUACAO rEsp = aux.getSituacao();
        CentroDistribuicao.SITUACAO rObs = CentroDistribuicao.SITUACAO.NORMAL;

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteRecebeAditivoInvalido() {
        CentroDistribuicao aux = new CentroDistribuicao(100, 100, 100, 100);

        int rEsp = -1;
        int rObs = aux.recebeAditivo(0);

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteRecebeAditivoMaior() {
        CentroDistribuicao aux = new CentroDistribuicao(0, 0, 0, 0);

        int rEsp = 500;
        int rObs = aux.recebeAditivo(501);

        Assertions.assertEquals(rEsp, rObs);
    }


    @Test
    public void TesteRecebeAditivoMenor() {
        CentroDistribuicao aux = new CentroDistribuicao(0, 0, 0, 0);

        int rEsp = 499;
        int rObs = aux.recebeAditivo(499);

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteRecebeAlcoolInvalido() {
        CentroDistribuicao aux = new CentroDistribuicao(0, 0, 0, 0);

        int rEsp = -1;
        int rObs = aux.recebeAlcool(0);

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteRecebeAlcoolMaior() {
        CentroDistribuicao aux = new CentroDistribuicao(0, 0, 0, 0);

        int rEsp = 2500;
        int rObs = aux.recebeAlcool(2501);

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteRecebeAlcoolMenor() {
        CentroDistribuicao aux = new CentroDistribuicao(0, 0, 0, 0);

        int rEsp = 2499;
        int rObs = aux.recebeAditivo(2499);

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteEncomendaTipoPostoInvalido() {
        CentroDistribuicao aux = new CentroDistribuicao(0, 0, 0, 0);

        int rEsp[] = { -7, 0, 0, 0 };
        int rObs[] = aux.encomendaCombustivel(0, CentroDistribuicao.TIPOPOSTO.INVALIDO);

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteEncomendaTipoPostoComumEmergencia() {
        CentroDistribuicao aux = new CentroDistribuicao(0, 0, 0, 0);

        int rEsp[] = { -14, 0, 0, 0 };
        int rObs[] = aux.encomendaCombustivel(300, CentroDistribuicao.TIPOPOSTO.COMUM);

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteEncomendaTipoPostoComumSobraviso() {
        CentroDistribuicao aux = new CentroDistribuicao(249, 4999, 1249, 1249);

        int rEsp[] = { 150, 0, 0, 0 };
        int rObs[] = aux.encomendaCombustivel(300, CentroDistribuicao.TIPOPOSTO.COMUM);

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteEncomendaTipoPostoEstrategicoEmergencia() {
        CentroDistribuicao aux = new CentroDistribuicao(124, 2499, 624, 624);

        int rEsp[] = { 150, 0, 0, 0 };
        int rObs[] = aux.encomendaCombustivel(300, CentroDistribuicao.TIPOPOSTO.ESTRATEGICO);

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteEncomendaValidoCalculaEstouro() {
        CentroDistribuicao aux = new CentroDistribuicao(0, 0, 0, 0);

        int rEsp[] = { -21, 0, 0, 0 };
        int rObs[] = aux.encomendaCombustivel(13001, CentroDistribuicao.TIPOPOSTO.COMUM);

        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void TesteEncomendaValidoCalculaValido() {
        CentroDistribuicao aux = new CentroDistribuicao(500, 10000, 1250, 1250);

        int rEsp[] = { 500, 10000, 1250, 1250 };
        int rObs[] = aux.encomendaCombustivel(12999, CentroDistribuicao.TIPOPOSTO.COMUM);

        Assertions.assertEquals(rEsp, rObs);
    }

//    @Test
//    public void TesteEncomendaValidoCalculaSituacaoEmergencia() {
//        CentroDistribuicao aux = new CentroDistribuicao(500, 10000, 1250, 1250);
//
//        Situacao rEsp[] = CentroDistribuicao.SITUACAO.EMERGENCIA;
//        Situacao rObs[] = aux.encomendaCombustivel(9751, CentroDistribuicao.TIPOPOSTO.COMUM);
//
//        Assertions.assertEquals(rEsp, rObs);
//    }
//
//    @Test
//    public void TesteEncomendaValidoCalculaSituacaoSobraviso() {
//        CentroDistribuicao aux = new CentroDistribuicao(500, 10000, 1250, 1250);
//
//        Situacao rEsp[] = CentroDistribuicao.SITUACAO.EMERGENCIA;
//        Situacao rObs[] = aux.encomendaCombustivel(6501, CentroDistribuicao.TIPOPOSTO.COMUM);
//
//        Assertions.assertEquals(rEsp, rObs);
//    }
//
//    @Test
//    public void TesteEncomendaValidoCalculaSituacaoNormal() {
//        CentroDistribuicao aux = new CentroDistribuicao(500, 10000, 1250, 1250);
//
//        Situacao rEsp[] = CentroDistribuicao.SITUACAO.NORMAL;
//        Situacao rObs[] = aux.encomendaCombustivel(6500, CentroDistribuicao.TIPOPOSTO.COMUM);
//
//        Assertions.assertEquals(rEsp, rObs);
//    }
}
