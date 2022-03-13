import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BancoTest {

    private final Banco obj = new Banco();

    @Test
    void testNome(){
        String example = "Teste";
        obj.setNome(example);
        Assertions.assertEquals( example, obj.getNome());
    }

    @Test
    void testContas(){
        List<Conta> example = new ArrayList<>();
        obj.setContas(example);
        Assertions.assertEquals( example, obj.getContas());
    }
}
