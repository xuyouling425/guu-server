package cn.bluejoe.elfinder.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import cn.bluejoe.elfinder.controller.executor.FsItemEx;
import cn.bluejoe.elfinder.service.FsItem;
import cn.bluejoe.elfinder.service.FsItemFilter;
import cn.bluejoe.elfinder.service.FsSecurityChecker;
import cn.bluejoe.elfinder.service.FsService;
import cn.bluejoe.elfinder.service.FsServiceConfig;
import cn.bluejoe.elfinder.service.FsVolume;

public class DefaultFsService implements FsService
{
	FsSecurityChecker _securityChecker;

	FsServiceConfig _serviceConfig;

	Map<String, FsVolume> _volumeMap = new HashMap<String, FsVolume>();

	// special characters should be encoded, avoid to be processed as a part of
	// URL
	String[][] escapes = { { "+", "_P" }, { "-", "_M" }, { "/", "_S" },
			{ ".", "_D" }, { "=", "_E" } };

	@Override
	/**
	 * find files by name pattern, this provides a simple recursively iteration based method
	 * lucene engines can be introduced to improve it!
	 * 
	 * @param filter
	 * @return
	 */
	public FsItemEx[] find(FsItemFilter filter)
	{
		List<FsItemEx> results = new ArrayList<FsItemEx>();
		for (FsVolume vol : _volumeMap.values())
		{
			FsItem root = vol.getRoot();
			results.addAll(findRecursively(filter, root));
		}

		return results.toArray(new FsItemEx[0]);
	}

	/**
	 * find files recursively in specific folder
	 * 
	 * @param filter
	 * @param root
	 * @return
	 */
	private Collection<FsItemEx> findRecursively(FsItemFilter filter,
			FsItem root)
	{
		List<FsItemEx> results = new ArrayList<FsItemEx>();
		FsVolume vol = root.getVolume();
		for (FsItem child : vol.listChildren(root))
		{
			if (vol.isFolder(child))
			{
				results.addAll(findRecursively(filter, child));
			}
			else
			{
				FsItemEx item = new FsItemEx(child, this);
				if (filter.accepts(item))
					results.add(item);
			}
		}

		return results;
	}

	@Override
	public FsItem fromHash(String hash)
	{
		for (FsVolume v : _volumeMap.values())
		{
			String prefix = getVolumeId(v) + "_";

			if (hash.equals(prefix))
			{
				return v.getRoot();
			}

			if (hash.startsWith(prefix))
			{
				String localHash = hash.substring(prefix.length());

				for (String[] pair : escapes)
				{
					localHash = localHash.replace(pair[1], pair[0]);
				}

				String relativePath = new String(Base64.decodeBase64(localHash));
				return v.fromPath(relativePath);
			}
		}

		return null;
	}

	@Override
	public String getHash(FsItem item) throws IOException
	{
		String relativePath = item.getVolume().getPath(item);
		String base = new String(Base64.encodeBase64(relativePath.getBytes()));

		for (String[] pair : escapes)
		{
			base = base.replace(pair[0], pair[1]);
		}

		return getVolumeId(item.getVolume()) + "_" + base;
	}

	public FsSecurityChecker getSecurityChecker()
	{
		return _securityChecker;
	}

	public FsServiceConfig getServiceConfig()
	{
		return _serviceConfig;
	}

	@Override
	public String getVolumeId(FsVolume volume)
	{
		for (Entry<String, FsVolume> en : _volumeMap.entrySet())
		{
			if (en.getValue() == volume)
				return en.getKey();
		}

		return null;
	}

	public Map<String, FsVolume> getVolumeMap()
	{
		return _volumeMap;
	}

	public FsVolume[] getVolumes()
	{
		return _volumeMap.values().toArray(new FsVolume[0]);
	}

	public void setSecurityChecker(FsSecurityChecker securityChecker)
	{
		_securityChecker = securityChecker;
	}

	public void setServiceConfig(FsServiceConfig serviceConfig)
	{
		_serviceConfig = serviceConfig;
	}

	public void setVolumeMap(Map<String, FsVolume> volumeMap)
	{
		this._volumeMap = volumeMap;
		for (Entry<String, FsVolume> en : _volumeMap.entrySet())
		{
			Logger.getLogger(this.getClass())
					.info(String.format("mounted %s: %s", en.getKey(),
							en.getValue()));
		}
	}

	/**
	 * @deprecated
	 * @param volumes
	 * @throws IOException
	 */
	public void setVolumes(FsVolume[] volumes) throws IOException
	{
		Logger.getLogger(getClass())
				.warn("calling setVolumes() is deprecated, please use setVolumeMap() to specify volume id explicitly");
		char vid = 'A';
		for (FsVolume volume : volumes)
		{
			_volumeMap.put("" + vid, volume);
			Logger.getLogger(this.getClass()).info(
					String.format("mounted %s: %s", "" + vid, volume));
			vid++;
		}
	}
}
