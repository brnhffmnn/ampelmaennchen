# Running the server


Make sure to create a configuration file that fits your Raspberry's pin configuration.
The current implementation requires two pins named `red` and `green`.
See an example configuration in [application.conf](application.conf).

To start the server, supply the configuration file first with `-Dconfig.file=` followed by the path to the `server.jar`: 

```
java -Dconfig.file=application.conf -jar server-raspberry-1.0-SNAPSHOT.jar 
```