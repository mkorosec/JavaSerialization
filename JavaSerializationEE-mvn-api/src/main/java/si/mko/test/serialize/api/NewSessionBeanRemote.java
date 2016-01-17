package si.mko.test.serialize.api;

import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author matej
 */
@Remote
public interface NewSessionBeanRemote {

    Collection<ITransferObject> getWithStandardSerialization(int n);

    Collection<ITransferObject> getWithEmptySerialization(int n);
}
