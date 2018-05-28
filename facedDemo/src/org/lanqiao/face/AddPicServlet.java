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
/*
 * 涓婁紶鐓х墖杩囩▼鐨勫叿浣撴搷浣�
 */
public class AddPicServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 涓婁紶鐓х墖杩囩▼
				try {
					// 缁熶竴缂栫爜
					request.setCharacterEncoding("utf-8");// 璁剧疆璇锋眰鐨勭紪鐮佹柟寮�
					response.setContentType("text/html;charset=utf-8");// 璁剧疆 out 鐨勭紪鐮佹柟寮�
					// 鍒涘缓PrintWriter瀵硅薄锛屽皢鍒嗘瀽缁撴灉杩斿洖缁欏墠鍙扮殑AJAX
					PrintWriter out = response.getWriter();
					// 璁剧疆琛ㄥ崟绫诲瀷锛氬寘鍚枃浠剁被鍨嬬殑瀛楁
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
							// 鑾峰彇鐓х墖
							FileItem item = iter.next();
							if (!item.isFormField()) {
								// 鑾峰彇鏂囦欢鍚�
								String fileName = item.getName();
								if (fileName != null && !fileName.equals("")) {
									File saveFile = new File(uploadFilePath, fileName);
									filePath = saveFile.getPath();
									// 淇濆瓨鐓х墖
									item.write(saveFile);
									System.out.println("涓婁紶鎴愬姛锛�");
									// 杩涜浜鸿劯璇嗗埆
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
