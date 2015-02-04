package cn.net.guu.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.net.guu.cms.controller.AboutController;

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
public class UploadUtils
{

	/**
	 * log日志
	 */
	private static Log log = LogFactory.getLog(AboutController.class);

	/**
	 * 通过spring解析器上传文件,返回上传后的文件结果集
	 * <p>
	 * Title: uploadFiles
	 * </p>
	 * 
	 * @param request
	 *            web请求
	 * @param filePath
	 *            文件路径
	 * @return List<String>
	 */
	public static List<String> uploadFiles(HttpServletRequest request, String filePath)
	{
		log.info("##########Entering uploadFiles().");
		log.info("##########Upload file path:[" + filePath + "]");
		List<String> imgList = new ArrayList<String>();
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
					log.info("##########Uploading file name:[" + myFileName + "]");
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "")
					{
						// 重命名上传后的文件名
						String fileName = "demoUpload" + file.getOriginalFilename();
						// 定义上传路径
						String path = filePath + fileName;
						File localFile = new File(path);
						try
						{
							file.transferTo(localFile);
							// 传输成功后，将文件所对应的相对路径放入list集合
							imgList.add(path);
							
							log.info("##########Uploading file successfully.File local path:[" + localFile + "]");
						} catch (IllegalStateException e)
						{
							// TODO Auto-generated catch block
							log.error("##########upload faild.", e);
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							log.error("##########upload faild.", e);
						}
					}
				}
			}
		}
		log.info("##########Exiting uploadFiles().");
		return imgList;
	}
}
