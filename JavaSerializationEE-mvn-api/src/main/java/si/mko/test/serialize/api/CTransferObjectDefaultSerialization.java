/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.serialize.api;

import java.util.Collection;

/**
 *
 * @author matej
 */
public class CTransferObjectDefaultSerialization implements ITransferObject {

    private String string1;
    private String string2;
    private Collection<String> colStrings;
    private Long internalId;

    public CTransferObjectDefaultSerialization() {
    }

    public CTransferObjectDefaultSerialization(String string1, String string2, Collection<String> colStrings, Long internalId) {
        this.string1 = string1;
        this.string2 = string2;
        this.colStrings = colStrings;
        this.internalId = internalId;
    }

    @Override
    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    @Override
    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    @Override
    public Collection<String> getColStrings() {
        return colStrings;
    }

    public void setColStrings(Collection<String> colStrings) {
        this.colStrings = colStrings;
    }

    @Override
    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

}
