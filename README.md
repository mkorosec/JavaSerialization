Intro
================
Java serialization performance in different scenarios, focus is on EE integration scenarios (EJB, REST webservice and client in war or jar)

Modules
----------------
- JavaSerializationEE-mvn-ear - ear bundle
- JavaSerializationEE-mvn-ejb - part of ear bundle, exposes a single EJB stateless bean with @Remote interface
- JavaSerializationEE-mvn-api - includes transfer object definition, integrations for different scenarios (ejb jndi call, rest service invocation,  ..). Includes main functions, enables test if client is outside EE container.
- JavaSerializationEE-mvn-war - can be deployed as standalone or part of ear. If standalone we test the @Remote EJB interface, otherwise it's call-by-reference

Scenarios
----------------
- EJB / native java serialization
- EJB / externalizable (empty)
- servlet / kryo
- servlet / jackson smile

Todo
---------------
- servlet / protobuf
