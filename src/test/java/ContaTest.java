import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    //private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    //private final PrintStream originalErr = System.err;

    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        //System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
        //System.setErr(originalErr);
    }

    private Conta obj;
    private Conta obj2;

    @BeforeAll
    public void init() {
        Cliente cl = new Cliente();
        cl.setNome("ClienteNome");
        obj = new Conta(cl){
            @Override
            public void imprimirExtrato() {}
        };

        obj2 = new Conta(new Cliente()){
            @Override
            public void imprimirExtrato() {}
        };
    }

    @Test
    void testConta(){
        Assertions.assertDoesNotThrow( () -> {
            new Conta(new Cliente()){
                @Override
                public void imprimirExtrato() {}
            };
        });
    }

    @Test
    @Order(1)
    void testAgencia(){
        Assertions.assertEquals( obj2.getAgencia(), obj.getAgencia());
    }

    @Test
    @Order(2)
    void testNumero(){
        Assertions.assertNotEquals( obj2.getNumero(), obj.getNumero());
    }

    @Test
    @Order(3)
    void testSaldo(){
        Assertions.assertEquals( 0 , obj.getSaldo());
        Assertions.assertEquals( 0, obj2.getSaldo());
    }


    @Test
    @Order(4)
    void testSacar(){
        obj.sacar(100);
        Assertions.assertEquals( -100, obj.getSaldo());
    }

    @Test
    @Order(5)
    void testDepositar(){
        obj2.depositar(150);
        Assertions.assertEquals( 150, obj2.getSaldo());
    }

    @Test
    @Order(6)
    void testTransferir(){
        obj2.transferir(125, obj);
        Assertions.assertEquals( 25, obj.getSaldo());
    }

    @Test
    @Order(7)
    void testImprimirInfosComuns(){
        obj.imprimirInfosComuns();
        Assertions.assertEquals(
                String.format("Titular: %s\r\n", obj.cliente.getNome())+
                String.format("Agencia: %d\r\n", obj.agencia)+
                String.format("Numero: %d\r\n", obj.numero)+
                String.format("Saldo: %.2f\r\n", obj.saldo)
                , outContent.toString());
        //assertEquals("hello again", errContent.toString());
    }
}
