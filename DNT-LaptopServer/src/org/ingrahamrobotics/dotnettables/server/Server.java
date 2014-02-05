package org.ingrahamrobotics.dotnettables.server;

import java.io.IOException;
import org.ingrahamrobotics.dotnettables.DotNetTable;
import org.ingrahamrobotics.dotnettables.DotNetTables;

public class Server {

    public static void main(String[] args) {
        try {
            DotNetTables.startServer();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        DotNetTable server = DotNetTables.publish("FromServer");
        for (int i = 0; true; i++) {
            try {
                server.setValue("k" + i % 3, "v" + i);
                System.out.println("Setting k" + i % 3 + " to v" + i);

                // When running on the computer, sending updated values works with and without sending new values.
//                server.setValue("Key-" + i, "Value-" + i);
//                System.out.println("Setting " + i + " to " + i);

                Thread.sleep(1000);
                System.out.println("Sending");
                server.send();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
