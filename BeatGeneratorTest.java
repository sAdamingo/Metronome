import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeatGeneratorTest {

    BeatGenerator b;
    @Before
    public void setUp() throws Exception {
        b = new BeatGenerator();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGenerateBuf() throws Exception {
     //   double beat;
//        double[] x;
        byte[] buf;
        double[] x=new double[(int)(4)];
        for(int i=0;i<x.length;i++){
            x[i]=(double)(i+1)/4;
        }
        double beat =1.0;
        buf = new byte[x.length];

                b.generateBuf(beat,x,buf);

        assertEquals(buf[0],10.0);

        System.out.println(b);
    }
}
