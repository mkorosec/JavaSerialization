package si.mko.test.serialize.test;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import si.mko.test.serialize.api.ITransferObject;
import si.mko.test.serialize.api.NewSessionBeanRemote;
import si.mko.test.serialize.resource.CEjbProvider;

/**
 * Call Remote EJB, transfer object implements a standard Serializable interface
 * without specific overrides (like readObject/writeObject).
 *
 * This is the most common EJB integration scenario in case of different
 * classloarder/jvm (ie when making a remote call to a stateless EJB).
 *
 * @author matej
 */
public class InvokeEjbStandardSerialization implements IBenchmarkRun {

    public double invoke(NewSessionBeanRemote remote, int numberOfObject, int repeat) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < repeat; i++) {
            Collection<ITransferObject> get = remote.getWithStandardSerialization(numberOfObject);
        }
        double avgTimePerCall = ((double) (System.currentTimeMillis() - time)) / repeat;
        return avgTimePerCall;
    }

    @Override
    public double run(int numberOfObject, int repeatRuns) {
        try {
            return this.invoke(CEjbProvider.INSTANCE.getNewEjbConnection(), numberOfObject, repeatRuns);
        } catch (NamingException ex) {
            Logger.getLogger(InvokeEjbStandardSerialization.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static void main(String args[]) throws NamingException {
        InvokeEjbEmptyExternalizable main = new InvokeEjbEmptyExternalizable();
        NewSessionBeanRemote remote = CEjbProvider.INSTANCE.getNewEjbConnection();

        int n = 10000;
        double avgTimePerCall = main.invoke(remote, n, 5);
        System.out.println(n + " objects, impl: standard serialization, time: " + avgTimePerCall + "ms");

    }
}
