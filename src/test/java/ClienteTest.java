import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    private final Cliente obj = new Cliente();

    @Test
    void testNome(){
        String example = "Teste";
        obj.setNome(example);
        Assertions.assertEquals( example, obj.getNome());
    }

}
