First of all see if you have any ssl cert with alias tomcat
keytool -delete -alias tomcat
password - changeit

Generate emulation of ssl with command:

keytool -genkey -alias tomcat -keyalg RSA
password - changeit


change in server.xml following section
 <Connector port="8443" protocol="org.apache.coyote.http11.Http11Protocol"
               maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS" />

to
    <Connector SSLEnabled="true" acceptCount="100" clientAuth="false"
    disableUploadTimeout="true" enableLookups="false" maxThreads="25"
    port="8443" keystoreFile="/Users/kblyumkin/.keystore" keystorePass="changeit"
    protocol="org.apache.coyote.http11.Http11NioProtocol" scheme="https"
    secure="true" sslProtocol="TLS" />