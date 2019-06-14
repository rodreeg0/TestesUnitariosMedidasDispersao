import calculo.Calculo;
import junit.framework.TestCase;

/**
 *
 * @author rodriguh
 */
public class TesteCalculos extends TestCase {

    Calculo b = new Calculo();
   
    public void test_Media() {
        assertEquals(19.25, b.calculaMedia());
    }
    
    public void test_Moda() {
        assertEquals(17.0, b.calculaModa());
    }
    
    public void test_Mediana() {
        assertEquals(18.5, b.calculaMediana());
    }
    public void test_Variancia(){
        assertEquals(26.0, b.calculaVariancia());
    }
    public void test_DesvioPadrao(){
        assertEquals(5.0, b.calculaDesvioPadrao());
    }
}