// https://www.wowza.com/docs/how-to-get-the-current-video-frame-rate-from-a-live-stream

package com.wowza.wms.example;

import com.wowza.wms.amf.AMFDataList;
import com.wowza.wms.amf.AMFPacket;
import com.wowza.wms.application.IApplicationInstance;
import com.wowza.wms.client.IClient;
import com.wowza.wms.module.ModuleBase;
import com.wowza.wms.request.RequestFunction;
import com.wowza.wms.stream.IMediaStream;
import com.wowza.wms.stream.IMediaStreamLivePacketNotify;

public class ModuleLiveFrameRate extends ModuleBase
{
	class PacketListener implements IMediaStreamLivePacketNotify
	{
		/**
		* onLivePacket is called for every packet that is received for the live stream before the packet is processed for playback.
		* It is very important that this method returns quickly and is not delayed in any way.
		*/
		@Override
		public void onLivePacket(IMediaStream stream, AMFPacket packet)
		{
			if (packet.isVideo())
			{
				// packet.getTimecode(); returns the elapsed time, in milliseconds, between this packet and the last packet of the same type.
				double fps = (double)1000 / packet.getTimecode();
				stream.getProperties().setProperty("currentFPS", new Double(fps));
			}
		}

	}

	private PacketListener packetListener = new PacketListener();
	private IApplicationInstance appInstance;

	public void onAppStart(IApplicationInstance appInstance)
	{
		this.appInstance = appInstance;
	}

	public void onStreamCreate(IMediaStream stream)
	{
		stream.addLivePacketListener(packetListener);
	}

	public void onStreamDestroy(IMediaStream stream)
	{
		stream.removeLivePacketListener(packetListener);
	}

	public void getCurrentFPS(IClient client, RequestFunction function, AMFDataList params)
	{
		double fps = 0;
		String streamName = getParamString(params, PARAM1);
		if (streamName != null)
		{
			fps = getCurrentFPS(streamName);
		}

		sendResult(client, params, fps);
	}

	public double getCurrentFPS(String streamName)
	{
		double fps = 0;
		IMediaStream stream = appInstance.getStreams().getStream(streamName);
		if (stream != null)
		{
			fps = stream.getProperties().getPropertyDouble("currentFPS", fps);
		}
		return fps;
	}
}