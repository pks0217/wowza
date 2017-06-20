// https://www.wowza.com/docs/how-to-use-the-wowza-streaming-engine-java-api-to-start-and-stop-mediacaster-streams

IApplicationInstance.startMediaCasterStream(streamName, mediaCasterType);
IApplicationInstance.stopMediaCasterStream(streamName);

appInstance.startMediaCasterStream("camera.stream", "rtp");
appInstance.stopMediaCasterStream("camera.stream", "rtp");
 
appInstance.startMediaCasterStream("radio.stream", "shoutcast");
appInstance.stopMediaCasterStream("radio.stream", "shoutcast");
 
appInstance.startMediaCasterStream("rtmp.stream", "liverepeater"); 
appInstance.stopMediaCasterStream("rtmp.stream", "liverepeater");
