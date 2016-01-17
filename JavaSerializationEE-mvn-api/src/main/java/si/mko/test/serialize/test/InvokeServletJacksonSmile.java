package si.mko.test.serialize.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import si.mko.test.serialize.api.CTransferObjectDefaultSerialization;
import si.mko.test.serialize.api.CTransferObjects;

/**
 * Call servlet with jackson smile serialization (binary json).
 * https://github.com/FasterXML/jackson-dataformat-smile
 *
 * @author matej
 */ 
public class InvokeServletJacksonSmile implements IBenchmarkRun {

    public double invoke(int numberOfObject, int repeat) {
        long time = System.currentTimeMillis();

        SmileFactory f = new SmileFactory();
        ObjectMapper mapper = new ObjectMapper(f);

        for (int i = 0; i < repeat; i++) {
            try {
                URL url = new URL("http://localhost:8080/JavaSerializationEE-mvn-web/smile?n=" + numberOfObject);
                URLConnection connection = url.openConnection();

                CTransferObjects objs = mapper.readValue(connection.getInputStream(), CTransferObjects.class);

//                System.out.println("objs.getClass(): " + objs.getClass());
//                System.out.println("objs.getCol().size(): " + objs.getCol().size());
//                System.out.println("objs.getCol().iterator().next().getString1(): " + objs.getCol().iterator().next().getString1());
            } catch (Throwable e) {
                Logger.getLogger(InvokeServletJacksonSmile.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        double avgTimePerCall = ((double) (System.currentTimeMillis() - time)) / repeat;
        return avgTimePerCall;
    }

    public static void main(String args[]) throws NamingException {
        InvokeServletJacksonSmile main = new InvokeServletJacksonSmile();
        int n = 10000;
        double avgTimePerCall = main.invoke(n, 15);
        System.out.println(n + " objects, impl: " + CTransferObjectDefaultSerialization.class.getSimpleName() + ", time: " + avgTimePerCall + "ms");

    }

    @Override
    public double run(int numberOfObject, int repeatRuns) {
        return this.invoke(numberOfObject, repeatRuns);
    }
}
