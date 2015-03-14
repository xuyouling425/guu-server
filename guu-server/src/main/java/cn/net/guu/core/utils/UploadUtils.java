package cn.net.guu.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.net.guu.core.config.CommonKey;

/**
 * 文件上传类
 * <p>
 * Title: UploadUtils
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年2月3日
 */
@Controller
@RequestMapping("/upload")
public class UploadUtils
{

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(UploadUtils.class);

	/**
	 * 通过spring解析器上传文件,返回上传后的文件结果集
	 * <p>
	 * Title: uploadFiles
	 * </p>
	 * 
	 * @param request
	 *            web请求
	 * @param filePath
	 *            文件路径,项目相对路径
	 * @return List<String>
	 */
	public static List<String> uploadFiles(HttpServletRequest request, String filePath)
	{
		log.info("Entering uploadFiles().");
		String localPath = CommonKey.getWebroot() + filePath;
		List<String> imgList = new ArrayList<String>();
		// 创建文件路径
		if (createDir(localPath))
		{
			// 创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			// 判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request))
			{
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext())
				{
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null)
					{
						// 取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();

						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (myFileName.trim() != "")
						{
							// 最后生成的文件
							String nowFileName = getFileName(localPath, file.getOriginalFilename());
							log.info("Uploading file name:[" + nowFileName + "]");
							// 相对上传路径
							String webPath = request.getContextPath() + "/" + filePath + nowFileName;

							File localFile = new File(localPath + nowFileName);
							try
							{
								log.info("Upload file path:[" + filePath + "]");
								file.transferTo(localFile);
								// 传输成功后，将文件所对应的相对路径放入list集合
								imgList.add(webPath);
								log.info("Uploading file successfully.File web path=[" + webPath + "], localPath=[" + localPath + "]");
							} catch (IllegalStateException e)
							{
								// TODO Auto-generated catch block
								log.error("upload faild.", e);
							} catch (IOException e)
							{
								// TODO Auto-generated catch block
								log.error("upload faild.", e);
							}
						}
					}
				}
			}
		}
		log.info("Exiting uploadFiles().");
		return imgList;
	}

	/**
	 * 上传文件，上传到默认的文件路径
	 * <p>
	 * Title: uploadFiles
	 * </p>
	 * 
	 * @param request
	 * @return 文件上传路径后的结果集
	 */
	public static List<String> uploadFiles(HttpServletRequest request)
	{
		return uploadFiles(request, CommonKey.UPLOAD_DEFAULT_PATH);
	}

	/**
	 * 上传文件，上传到默认的文件路径
	 * <p>
	 * Title: uploadFiles
	 * </p>
	 * 
	 * @param request
	 * @return 返回文件上传后的第一个文件的路径
	 */

	@ResponseBody
	@RequestMapping("/uploadImg")
	public static String uploadImg(HttpServletRequest request)
	{
		List<String> imgList = uploadFiles(request, CommonKey.UPLOAD_IMAGE_PATH);
		return CommonUtils.isEmpty(imgList) ? "" : imgList.get(0);
	}

	/**
	 * 判定当前文件名在该路径下是否已经存在，如果已经存在则重新命名，命名规则: 1_fileName
	 * <p>
	 * Title: getFileName
	 * </p>
	 * 
	 * @param localPath
	 *            上传文件的物理位置
	 * @param fileName
	 *            上传文件名
	 * @return
	 */
	public static String getFileName(String localPath, String fileName)
	{
		int filNamePrefix = 1;
		File file = new File(localPath + fileName);
		if (file.exists())
		{
			// 将文件名通过“_”拆分
			String[] fileNameTemp = fileName.split("_");
			String namePrefix = fileNameTemp[0];
			// 如果"_" 前面是一个数字，则去掉数字,重新添加数据
			if (Pattern.matches("\\d+", namePrefix))
			{
				// 在之前的前缀数字+1
				fileName = (NumberUtils.toInt(namePrefix) + 1) + fileName.substring(fileName.indexOf("_"));
			} else
			{
				// 第一次给文件名添加前缀
				fileName = filNamePrefix + "_" + fileName;
			}
			fileName = getFileName(localPath, fileName);
		}
		return fileName;
	}

	/**
	 * 创建文件夹
	 * <p>
	 * Title: createDir
	 * </p>
	 * 
	 * @param destDirName
	 * @return
	 */
	public static boolean createDir(String destDirName)
	{
		File dir = new File(destDirName);
		if (!dir.exists())
		{
			// 创建目录
			if (!dir.mkdirs())
			{
				return false;
			}
		}
		return true;

	}
}
