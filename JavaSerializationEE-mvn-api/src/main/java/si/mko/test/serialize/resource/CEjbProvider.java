/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.mko.test.serialize.resource;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import si.mko.test.serialize.api.NewSessionBeanRemote;

/**
 *
 * @author matej
 */
public enum CEjbProvider {

    INSTANCE;

    public NewSessionBeanRemote getNewEjbConnection() throws NamingException {
        Properties props = new Properties();

        props.setProperty("java.naming.factory.initial",
                "com.sun.enterprise.naming.SerialInitContextFactory");

        props.setProperty("java.naming.factory.url.pkgs",
                "com.sun.enterprise.naming");

        props.setProperty("java.naming.factory.state",
                "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

        // optional.  Defaults to localhost.  Only needed if web server is running 
        // on a different host than the appserver    
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");

        // optional.  Defaults to 3700.  Only needed if target orb port is not 3700.
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

        InitialContext ic = new InitialContext(props);

        NewSessionBeanRemote gRemote = (NewSessionBeanRemote) ic.lookup("java:global/JavaSerializationEE-mvn-ear/JavaSerializationEE-mvn-ejb-1.0-SNAPSHOT/NewSessionBean!si.mko.test.serialize.api.NewSessionBeanRemote");
        return gRemote;
    }
}
