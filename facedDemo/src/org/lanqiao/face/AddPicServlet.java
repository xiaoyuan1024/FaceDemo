package org.lanqiao.face;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class AddPicServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 上传照片过程
				try {
					// 统一编码
					request.setCharacterEncoding("utf-8");// 设置request请求的编码格式
					response.setContentType("text/html;charset=utf-8");// 设置response响应的编码格式
					// 通过PrintWriter对象，将分析的结果返回给前台Ajax
					PrintWriter out = response.getWriter();
					// 设置表单类型：包含文件类型字段
					boolean isMultipart = ServletFileUpload.isMultipartContent(request);
					// 设置上传路径
					String uploadFilePath = "e:\\imgs";
					if (isMultipart) {
						FileItemFactory factory = new DiskFileItemFactory();
						ServletFileUpload upload = new ServletFileUpload(factory);
						List<FileItem> items;
						String filePath = "";
						items = upload.parseRequest(request);
						Iterator<FileItem> iter = items.iterator();
						while (iter.hasNext()) {
							// 获取每一个照片
							FileItem item = iter.next();
							if (!item.isFormField()) {
								// 获取文件名
								String fileName = item.getName();
								if (fileName != null && !fileName.equals("")) {
									File saveFile = new File(uploadFilePath, fileName);
									filePath = saveFile.getPath();
									// 保存图片
									item.write(saveFile);
									System.out.println("上传成功！！！");
									// 进行人脸识别
									Object result = FaceRecongize.faceRecognize(filePath);
									out.print(result);
									return;
								} // if
							} // if
						} // while

					} // if
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}
}
