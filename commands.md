### JFR
```
java 
--enable-preview -XX:StartFlightRecording=filename=jfr/VirtualThreadsDemo.jfr,duration=180s,settings=profile \
VirtualThreadsDemo
```