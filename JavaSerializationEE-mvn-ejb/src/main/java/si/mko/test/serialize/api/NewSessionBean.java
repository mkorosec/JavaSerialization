/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.serialize.api;

import java.util.Collection;
import javax.ejb.Stateless;
import si.mko.test.serialize.resource.CDataProvider;

/**
 *
 * @author matej
 */
@Stateless
public class NewSessionBean implements NewSessionBeanRemote {

    @Override
    public Collection<ITransferObject> getWithStandardSerialization(int n) {
        return CDataProvider.INSTANCE.get(n, CTransferObjectDefaultSerialization.class);
    }

    @Override
    public Collection<ITransferObject> getWithEmptySerialization(int n) {
        return CDataProvider.INSTANCE.get(n, CTransferObjectExternalizableEmpty.class);
    }

}
