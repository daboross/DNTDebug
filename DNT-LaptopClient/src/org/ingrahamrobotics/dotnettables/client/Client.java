package org.ingrahamrobotics.dotnettables.client;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ingrahamrobotics.dotnettables.DotNetTable;
import org.ingrahamrobotics.dotnettables.DotNetTable.DotNetTableEvents;
import org.ingrahamrobotics.dotnettables.DotNetTables;

public class Client implements DotNetTableEvents {

    public static void main(String[] args) {
        new Client().run();
    }

    public void run() {
        try {
            DotNetTables.startClient("127.0.0.1");
//            DotNetTables.startClient("4030");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        DotNetTable server = DotNetTables.subscribe("FromServer");
        server.onChange(this);
    }

    @Override
    public void changed(DotNetTable table) {
        System.out.println("*** Changed");
        String key;
        for (Enumeration it = table.keys(); it.hasMoreElements();) {
            key = (String) it.nextElement();
            if (!key.equals("_UPDATE_INTERVAL")) {
                System.out.println(key + " => " + table.getValue(key));
            }
        }
    }

    @Override
    public void stale(DotNetTable table) {
    }
}
