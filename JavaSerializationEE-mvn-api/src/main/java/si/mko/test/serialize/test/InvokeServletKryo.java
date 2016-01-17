package si.mko.test.serialize.test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.objenesis.strategy.StdInstantiatorStrategy;
import si.mko.test.serialize.api.CTransferObjectDefaultSerialization;
import si.mko.test.serialize.api.CTransferObjects;
import si.mko.test.serialize.api.ITransferObject;

/**
 * Call servlet with kryo serialization.
 * https://github.com/EsotericSoftware/kryo
 *
 * @author matej
 */
public class InvokeServletKryo implements IBenchmarkRun {

    public double invoke(int numberOfObject, int repeat) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < repeat; i++) {
            try {
                URL url = new URL("http://localhost:8080/JavaSerializationEE-mvn-web/kryo?n=" + numberOfObject);
                URLConnection connection = url.openConnection();

                Kryo k = this.kryos.get();
                ((Kryo.DefaultInstantiatorStrategy) k.getInstantiatorStrategy()).setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
                k.register(CTransferObjects.class);
                k.register(CTransferObjectDefaultSerialization.class);
                k.register(ITransferObject.class);
                k.register(Collection.class);
                k.register(ArrayList.class);

//                Input in = new Input(connection.getInputStream(), 10000);
//                in.setBuffer(new byte[10000]);
                Input in = new Input(connection.getInputStream());
                CTransferObjects objs = k.readObject(in, CTransferObjects.class);
//                for (ITransferObject obj : objs.getCol()) {
//                    System.out.println("obj: "+obj.getString1());
//                }

            } catch (Exception e) {
                Logger.getLogger(InvokeServletKryo.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        double avgTimePerCall = ((double) (System.currentTimeMillis() - time)) / repeat;
        return avgTimePerCall;
    }

    /**
     * Kryo instances are not thread safe, each thread must have it's own
     * instance. However, to avoid creating new instances, we create a pool for
     * each thread.
     *
     * https://github.com/EsotericSoftware/kryo#pooling-kryo-instances
     */
    private ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            // configure kryo instance, customize settings
            return kryo;
        }

    };
    
    public static void main(String args[]) throws NamingException {
        InvokeServletKryo main = new InvokeServletKryo();
        int n = 10000;
        double avgTimePerCall = main.invoke(n, 5);
        System.out.println(n + " objects, impl: " + CTransferObjectDefaultSerialization.class.getSimpleName() + ", time: " + avgTimePerCall + "ms");

    }

    @Override
    public double run(int numberOfObject, int repeatRuns) {
        return this.invoke(numberOfObject, repeatRuns);
    }
}
