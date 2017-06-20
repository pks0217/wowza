// https://www.wowza.com/docs/how-to-shut-down-a-running-application-from-a-module-moduleshutdownapplication

import com.wowza.wms.application.*;
import com.wowza.wms.vhost.*;

public class ModuleShutdownApplication
{
	private IApplicationInstance appInstance = null;

	public void onAppStart(IApplicationInstance appInstance)
	{
		this.appInstance =appInstance;
	}

	public void shutdownApplication()
	{
		String appName = this.appInstance.getApplication().getName();
		IVHost vhost = this.appInstance.getVHost();

		vhost.shutdownApplication(appName);
	}
}