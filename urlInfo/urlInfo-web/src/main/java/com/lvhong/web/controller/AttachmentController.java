package com.lvhong.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.lvhong.core.pojo.PageList;
import com.lvhong.core.pojo.ResponseResult;
import com.lvhong.core.pojo.User;
import com.lvhong.web.pojo.TmFileRoute;
import com.lvhong.web.service.AttachmentService;

@RequestMapping("/urlInfo")
@Controller
public class AttachmentController {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	@Resource
	private HttpSession session;

	@Resource
	private AttachmentService attachmentService;

	@Value("${macPath}")
	private String macPath;

	@Value("${windowsPath}")
	private String windowsPath;

	@Value("${LinuxPath}")
	private String LinuxPath;

	@RequestMapping("/attachment")
	public String attachment() {
		return "/views/pages/attachment";
	}

	@RequestMapping("/attachment/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
		List<TmFileRoute> list = new ArrayList<TmFileRoute>();
		Date date = new Date();
		try {
			for (MultipartFile multipartFile : file) {
				FileInputStream is = (FileInputStream) multipartFile.getInputStream();
				FileChannel isc = is.getChannel();
				Date time = Calendar.getInstance().getTime();
				String format = sdf.format(time);
				String replaceAll = UUID.randomUUID().toString().replaceAll("-", "");
				String property = System.getProperty("os.name");
				File fi = null;
				if (property.indexOf("Mac OS") != -1) {
					fi = new File(macPath + format + "/" + replaceAll);
				} else if (property.indexOf("Windows") != -1) {
					fi = new File(windowsPath + format + "/" + replaceAll);
				} else if (property.indexOf("Linux") != -1) {
					fi = new File(LinuxPath + format + "/" + replaceAll);
				} else {

				}
				if (!fi.getParentFile().exists()) {
					fi.getParentFile().mkdirs();
				}
				FileOutputStream os = new FileOutputStream(fi);
				FileChannel osc = os.getChannel();
				ByteBuffer allocate = ByteBuffer.allocate(1024);
				while ((isc.read(allocate)) != -1) {
					allocate.flip();
					osc.write(allocate);
					allocate.clear();
				}
				os.close();
				insertFileInfo(multipartFile.getOriginalFilename(), multipartFile.getSize(), fi.getPath(), date, list);
				attachmentService.insertFileInfo(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "上传成功";
	}

	public void insertFileInfo(String fileName, Long fileSize, String routeName, Date date, List<TmFileRoute> list) {
		TmFileRoute tmFileRoute = new TmFileRoute.Builder().fileName(fileName).fileSize(fileSize).routeName(routeName)
				.createBy(((User) session.getAttribute("user")).getId()).createTime(date).build();
		list.add(tmFileRoute);
	}
	
	@RequestMapping("/myFile")
	public String myFile(Model model) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("index", 0);
		map.put("size", 10);
		List<TmFileRoute> list = attachmentService.queryMyFile(map);
		Integer pageSize = attachmentService.queryFilePageSize();
		Double ceil = Math.ceil(pageSize*1.0/10);
		model.addAttribute("tmFileRoute", list);
		model.addAttribute("pageSize",ceil.longValue());
		return "/views/pages/myFile";
	}
	
	@RequestMapping("/attachment/fileInfo")
	@ResponseBody
	public PageList<TmFileRoute> fileInfo(Integer page) {
		PageList<TmFileRoute> result = new PageList<TmFileRoute>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("index", (page - 1)*10);
		map.put("size", 10);
		List<TmFileRoute> list = attachmentService.queryMyFile(map);
		Integer size = attachmentService.queryFilePageSize();
		Long pageSize = ((Double)Math.ceil(size*1.0/10)).longValue();
		result.setRows(list);
		result.setPageSize(pageSize);
		result.setCurrentPage(page);
		return result;
	}
	
	@RequestMapping("/attachment/uploadFile")
	public void uploadFile(String routeName, String fileName,HttpServletResponse response) {
		OutputStream os = null;
		FileInputStream is = null;
		try {
			//读取文件
			File file = new File(routeName);
			is = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
			byte[] byteArr = new byte[bufferedInputStream.available()];
			bufferedInputStream.read(byteArr);
			//获取响应流，设置响应头
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/octet-stream;charset=utf-8");
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);// 下载文件
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(os);
			bufferedOutputStream.write(byteArr);
			bufferedOutputStream.flush();
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(os != null) {				
				try {
					is.close();
					os.close();
				} catch (IOException e) {
					
				}
			}
		}
	}
	
	@RequestMapping("/attachment/deleteFile")
	@ResponseBody
	public ResponseResult deleteFile(Long id , String routeName) {
		ResponseResult rs = new ResponseResult();
		try {			
			File file = new File(routeName);
			if(file.exists()) {
				file.delete( );
				attachmentService.deleteFile(id);
				rs.setStatusCode(200);
				rs.setSuccessOrError("success");
				rs.setStatusInfo("文件删除成功");
			}else {				
				rs.setStatusCode(201);
				rs.setSuccessOrError("no");
				rs.setStatusInfo("指定位置的文件已被移除或文件不存在");
			}
			return rs;
		}catch(Exception e) {
			e.printStackTrace();
			rs.setStatusCode(500);
			rs.setSuccessOrError("fail");
			rs.setStatusInfo("文件删除失败");
			return rs;
		}
	}

}
