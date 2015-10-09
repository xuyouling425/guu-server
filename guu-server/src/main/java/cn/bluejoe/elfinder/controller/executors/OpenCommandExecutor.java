package cn.bluejoe.elfinder.controller.executors;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import cn.bluejoe.elfinder.controller.executor.AbstractJsonCommandExecutor;
import cn.bluejoe.elfinder.controller.executor.CommandExecutor;
import cn.bluejoe.elfinder.controller.executor.FsItemEx;
import cn.bluejoe.elfinder.impl.DefaultFsService;
import cn.bluejoe.elfinder.localfs.LocalFsVolume;
import cn.bluejoe.elfinder.service.FsService;
import cn.bluejoe.elfinder.service.FsVolume;
import cn.net.guu.core.common.SystemPath;

public class OpenCommandExecutor extends AbstractJsonCommandExecutor implements CommandExecutor
{
	@Override
	public void execute(FsService fsService, HttpServletRequest request, ServletContext servletContext, JSONObject json) throws Exception
	{
		boolean init = request.getParameter("init") != null;
		boolean tree = request.getParameter("tree") != null;
		String target = request.getParameter("target");
		// 自定义从前台传递过来的rootDir，rootName
		String rootDir = request.getParameter("rootDir");
		String rootName = request.getParameter("rootName");
		// 重置rootDir ,rootNmae
		if (!StringUtils.isEmpty(rootDir))
		{
			for (FsVolume fsVolume : fsService.getVolumes())
			{
				LocalFsVolume localFsVolume = (LocalFsVolume) fsVolume;
				localFsVolume.setName(rootName);
				localFsVolume.setRootDir(new File(SystemPath.getWebroot()+rootDir));
			}
		}

		// fsService.getVolumes()

		Map<String, FsItemEx> files = new LinkedHashMap<String, FsItemEx>();
		if (init)
		{
			json.put("api", 2.1);
			json.put("netDrivers", new Object[0]);
		}

		if (tree)
		{
			for (FsVolume v : fsService.getVolumes())
			{

				FsItemEx root = new FsItemEx(v.getRoot(), fsService);
				files.put(root.getHash(), root);
				addSubfolders(files, root);
			}
		}

		FsItemEx cwd = findCwd(fsService, target);
		files.put(cwd.getHash(), cwd);
		String[] onlyMimes = request.getParameterValues("mimes[]");
		addChildren(files, cwd, onlyMimes);

		json.put("files", files2JsonArray(request, files.values()));
		json.put("cwd", getFsItemInfo(request, cwd));
		json.put("options", getOptions(request, cwd));
	}
}
