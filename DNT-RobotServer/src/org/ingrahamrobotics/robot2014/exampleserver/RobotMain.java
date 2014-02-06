/*
 * Copyright (C) 2014 Ingraham Robotics Team 4030
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.ingrahamrobotics.robot2014.exampleserver;

import edu.wpi.first.wpilibj.SimpleRobot;
import java.io.IOException;
import org.ingrahamrobotics.dotnettables.DotNetTable;
import org.ingrahamrobotics.dotnettables.DotNetTables;

public class RobotMain extends SimpleRobot {

    protected void robotInit() {
        try {
            DotNetTables.startServer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void autonomous() {
    }

    public void operatorControl() {
        DotNetTable server = DotNetTables.publish("FromServer");
        for (int i = 0; true; i++) {
            try {
                server.setValue("k" + i % 3, "v" + i);
                System.out.println("Setting k" + i % 3 + " to v" + i);

                // This won't work too well unless the 'bump' hack is called.
//                bump(server);

                Thread.sleep(1000);
                System.out.println("Sending");
                server.send();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * When running a DotNetTables server on the robot, sending a table doesn't
     * work if the number of keys is the same as last time.
     * <br>
     * This method is a hack to get around that issue. It will add or remove the
     * 'bump' key depending if it already exists.
     *
     * @param table the table to 'bump'
     */
    private void bump(DotNetTable table) {
        if (table.exists("bump")) {
            table.remove("bump");
        } else {
            table.setValue("bump", "bump");
        }
    }

    public void test() {
    }

    protected void disabled() {
    }
}
