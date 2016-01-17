/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.serialize.resource;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import si.mko.test.serialize.api.ITransferObject;

/**
 *
 * @author matej
 */
public enum CDataProvider {

    INSTANCE;

    public Collection<ITransferObject> get(int n, Class<? extends ITransferObject> cls) {
        Constructor<? extends ITransferObject> constructor = null;
        try {
            constructor = cls.getConstructor(String.class, String.class, Collection.class, Long.class);
        } catch (Throwable ex) {
            Logger.getLogger("TEST").log(Level.SEVERE, null, ex);
        }
        return this.get(n, constructor);
    }

    private Collection<ITransferObject> get(int n, Constructor<? extends ITransferObject> constructor) {

        Collection<ITransferObject> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Collection<String> strings = new ArrayList<>();
            strings.add(UUID.randomUUID().toString());
            strings.add(UUID.randomUUID().toString());
            strings.add(UUID.randomUUID().toString());

            try {
                result.add(
                        constructor.newInstance(UUID.randomUUID().toString(),
                                UUID.randomUUID().toString(),
                                strings,
                                -123l
                        ));
            } catch (Throwable ex) {
                Logger.getLogger(CDataProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

}
