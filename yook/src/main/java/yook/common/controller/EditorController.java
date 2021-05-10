package yook.common.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;  

import yook.common.util.CommonUtils;

@Controller
@RequestMapping("/ckeditor")
public class EditorController {
	
Logger log = Logger.getLogger(this.getClass());
	
	
	
	@ResponseBody
	@RequestMapping("fileupload.do")
	public void imageUpload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam MultipartFile upload)
	throws Exception{
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String fileName=upload.getOriginalFilename();
		
		byte[] bytes=upload.getBytes();
		
		String uploadPath = "C:/java2/SpringApp/yook/src/main/webapp/img/goods_upload/"; 
		
		
		System.out.println(request.getSession().getServletContext().getRealPath("/"));
		
	
		OutputStream out=new FileOutputStream(
				new File(uploadPath + fileName)); 		
		

		
		
		request.getSession().setAttribute("GOODS_IMAGE", fileName);
		System.out.println("fileName: " + fileName);
		
		out.write(bytes);
		String callback=request.getParameter("CKEditorFuncNum");
		PrintWriter printWriter=response.getWriter();
		String fileUrl
			=request.getContextPath()+"/img/goods_upload/"+fileName;
		System.out.println("fileUrl:"+fileUrl);
		
		printWriter.flush();
	}
	
}