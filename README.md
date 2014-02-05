DotNetTables-Debug
==================

This has three projects in it.
* DNT-LaptopClient - Test client, run on laptop
* DNT-LaptopServer - Test server, run on laptop
* DNT-RobotServer  - Test server, run on robot

When using DNT-LaptopClient and DNT-LaptopServer, it works as expected. Output of the LaptopServer:
```
Setting k0 to v0
Sending
Setting k1 to v1
Sending
Setting k2 to v2
Sending
Setting k0 to v3
Sending
Setting k1 to v4
Sending
Setting k2 to v5
Sending
Setting k0 to v6
Sending
```
And output from the LaptopClient when running with the LaptopServer:
```
*** Changed
*** Changed
k0 => v0
*** Changed
k0 => v0
k1 => v1
*** Changed
k0 => v0
k2 => v2
k1 => v1
*** Changed
k0 => v3
k2 => v2
k1 => v1
*** Changed
k0 => v3
k2 => v2
k1 => v4
*** Changed
k0 => v3
k2 => v5
k1 => v4
*** Changed
k0 => v6
k2 => v5
k1 => v4
```
... And it just keeps going.


When running with the DNT-LaptopClient and DNT-RobotServer, results are a bit different. It starts out the same, but then DNT-LaptopClient stops getting updates as soon as all values are initiated. Output of the RobotServer:
```
[cRIO] Setting k0 to v0
[cRIO] Sending
[cRIO] Setting k1 to v1
[cRIO] Sending
[cRIO] Setting k2 to v2
[cRIO] Sending
[cRIO] Setting k0 to v3
[cRIO] Sending
[cRIO] Setting k1 to v4
[cRIO] Sending
[cRIO] Setting k2 to v5
[cRIO] Sending
[cRIO] Setting k0 to v6
```
And output from the LaptopClient when running with the RobotServer:
```
*** Changed
*** Changed
k0 => v0
*** Changed
k0 => v0
k1 => v1
*** Changed
k0 => v0
k2 => v2
k1 => v1
```
... And then it just stops. No more updates after all keys have values.

However, if you uncomment two lines in RobotServer, to add new values after every update, the values that weren't being updated before are now updated. So as long as you add new values, the existing values in the table are updated as well.
