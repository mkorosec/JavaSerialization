/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.serialize.api;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author matej
 */
public class CTransferObjects implements Serializable {

    private Collection<ITransferObject> col;

    public CTransferObjects() {
    }

    public CTransferObjects(Collection<ITransferObject> col) {
        this.col = col;
    }

    public Collection<ITransferObject> getCol() {
        return col;
    }

    public void setCol(Collection<ITransferObject> col) {
        this.col = col;
    }

}
