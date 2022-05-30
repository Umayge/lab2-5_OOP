
import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
public class UtilTest {

    @Test(expected = FileNotFoundException.class)
    public void testexception() throws FileNotFoundException {
        throw new FileNotFoundException();
    }
    @Test(expected = RuntimeException.class) // Проверяем на появление исключения
    public void testException() {
        throw new RuntimeException("Ошибка");
    }

    @Test(expected = Exception.class)
    public void test_show() throws Exception {
        Assert.assertNotNull(XmlOpen.getDocument());
    }
}
