import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContaCorrenteTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    private ContaCorrente obj;

    @BeforeAll
    public void init() {
        Cliente cl = new Cliente();
        cl.setNome("ClienteNome");
        obj = new ContaCorrente(cl);
    }

    @Test
    void testImprimirExtrato(){
        obj.imprimirExtrato();
        Assertions.assertEquals(
                "=== Extrato Conta Corrente ===\r\n"+
                String.format("Titular: %s\r\n", obj.cliente.getNome())+
                String.format("Agencia: %d\r\n", obj.agencia)+
                String.format("Numero: %d\r\n", obj.numero)+
                String.format("Saldo: %.2f\r\n", obj.saldo)
                , outContent.toString());
    }
}
