// https://www.wowza.com/docs/how-to-use-imediawriteractionnotify-to-programmatically-move-and-rename-recordings-of-live-streams

package com.wowza.wms.plugin.test.module;

import java.io.*;
import java.util.*;

import com.wowza.wms.application.*;
import com.wowza.wms.module.*;
import com.wowza.wms.stream.*;

public class ModuleWriteListener extends ModuleBase
{
	class WriteListener implements IMediaWriterActionNotify
	{
		public void onFLVAddMetadata(IMediaStream stream, Map<String, Object> extraMetadata)
		{
			getLogger().info("ModuleWriteListener.onFLVAddMetadata["+stream.getContextStr()+"]");
		}

		public void onWriteComplete(IMediaStream stream, File file)
		{
			getLogger().info("ModuleWriteListener.onWriteComplete["+stream.getContextStr()+"]: "+file);
		}
	}

	public void onAppStart(IApplicationInstance appInstance)
	{
		appInstance.addMediaWriterListener(new WriteListener());
	}
} 