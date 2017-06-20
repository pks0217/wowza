// https://www.wowza.com/docs/how-to-switch-streams-using-stream-class-streams

package com.wowza.wms.example.module;

import com.wowza.wms.amf.*;
import com.wowza.wms.application.*;
import com.wowza.wms.client.*;
import com.wowza.wms.module.*;
import com.wowza.wms.request.*;
import com.wowza.wms.stream.publish.Playlist;
import com.wowza.wms.stream.publish.Stream;

public class ModuleStreamSwitch extends ModuleBase {

	public void switchPlaylist(IClient client, RequestFunction function,
			AMFDataList params) {

		String streamName = getParamString(params, PARAM1);
		String playlistName = getParamString(params, PARAM2);

		Stream stream = (Stream)client.getAppInstance().getProperties().getProperty(streamName);
		Playlist playlist = (Playlist)client.getAppInstance().getProperties().getProperty(playlistName);

		playlist.open(stream);
	}

	public void onAppStart(IApplicationInstance appInstance) {

		appInstance.startMediaCasterStream("axis.stream", "rtp");

		String streamName = "Stream1";
		Playlist playlist1 = new Playlist("pl1");	
		Playlist playlist2 = new Playlist("pl2");
		Playlist playlist3 = new Playlist("pl3");

		playlist1.setRepeat(true);
		playlist2.setRepeat(true);
		playlist3.setRepeat(true);

		playlist1.addItem("myStream", -2, -1);
		playlist2.addItem("axis.stream", -2, -1);
		playlist3.addItem("mp4:sample.mp4", 0, -1);

		Stream stream = Stream.createInstance(appInstance, streamName);

		appInstance.getProperties().setProperty(streamName, stream);
		appInstance.getProperties().setProperty(playlist1.getName(), playlist1);
		appInstance.getProperties().setProperty(playlist2.getName(), playlist2);
		appInstance.getProperties().setProperty(playlist3.getName(), playlist3);
	}

	public void onAppStop(IApplicationInstance appInstance) {

	}
}