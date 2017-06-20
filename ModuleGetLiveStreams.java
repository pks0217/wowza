// https://www.wowza.com/docs/how-to-use-modulegetlivestreams

package com.wowza.wms.example.module;

import java.util.Iterator;
import java.util.List;

import com.wowza.wms.amf.*;
import com.wowza.wms.client.*;
import com.wowza.wms.module.*;
import com.wowza.wms.request.*;

public class ModuleGetLiveStreams extends ModuleBase {

	public void getLiveStreams(IClient client, RequestFunction function,
			AMFDataList params) {

		AMFDataObj streams = new AMFDataObj();

		List<String> list = client.getAppInstance().getPublishStreamNames();

		Iterator<String> iter = list.iterator();
		int i=0;
		while (iter.hasNext())
		{
			streams.put("stream_" + i++,iter.next());
		}
		sendResult(client, params, streams); 
	}
}