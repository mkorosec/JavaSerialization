/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.serialize.api;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author matej
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public interface ITransferObject extends Serializable {

    Collection<String> getColStrings();

    Long getInternalId();

    String getString1();

    String getString2();

}
