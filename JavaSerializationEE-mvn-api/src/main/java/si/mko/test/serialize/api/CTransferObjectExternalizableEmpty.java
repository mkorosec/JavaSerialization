/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.serialize.api;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;

/**
 *
 * @author matej
 */
public class CTransferObjectExternalizableEmpty extends CTransferObjectDefaultSerialization implements Externalizable {

    public CTransferObjectExternalizableEmpty() {
    }

    public CTransferObjectExternalizableEmpty(String string1, String string2, Collection<String> colStrings, Long internalId) {
        super(string1, string2, colStrings, internalId);
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
    }

}
