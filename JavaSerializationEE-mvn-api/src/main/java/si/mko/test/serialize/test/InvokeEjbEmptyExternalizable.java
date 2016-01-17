package si.mko.test.serialize.test;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import si.mko.test.serialize.api.ITransferObject;
import si.mko.test.serialize.api.NewSessionBeanRemote;
import si.mko.test.serialize.resource.CEjbProvider;

/**
 * Call Remote EJB, transfer object implements a Externalizable interface and
 * implements a dummy method for custom serialization - the method is empty.
 *
 * This case is meant as a comparison to other methods, it should have the
 * lowest latency (if it doesn't it's probably because of ejb overhead if
 * compared to basic servlet).
 *
 * @author matej
 */
public class InvokeEjbEmptyExternalizable implements IBenchmarkRun {

    public double invoke(NewSessionBeanRemote remote, int numberOfObject, int repeat) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < repeat; i++) {
            Collection<ITransferObject> get = remote.getWithEmptySerialization(numberOfObject);
        }
        double avgTimePerCall = ((double) (System.currentTimeMillis() - time)) / repeat;
        return avgTimePerCall;
    }

    @Override
    public double run(int numberOfObject, int repeatRuns) {
        try {
            return this.invoke(CEjbProvider.INSTANCE.getNewEjbConnection(), numberOfObject, repeatRuns);
        } catch (NamingException ex) {
            Logger.getLogger(InvokeEjbEmptyExternalizable.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static void main(String args[]) throws NamingException {
        InvokeEjbEmptyExternalizable main = new InvokeEjbEmptyExternalizable();
        NewSessionBeanRemote remote = CEjbProvider.INSTANCE.getNewEjbConnection();

        int n = 10000;
        double avgTimePerCall = main.invoke(remote, n, 5);
        System.out.println(n + " objects, impl: empty externalizable, time: " + avgTimePerCall + "ms");

    }

}
