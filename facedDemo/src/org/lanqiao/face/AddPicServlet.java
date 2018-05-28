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
		// �ϴ���Ƭ����
				try {
					// ͳһ����
					request.setCharacterEncoding("utf-8");// ��������ı��뷽ʽ
					response.setContentType("text/html;charset=utf-8");// ���� out �ı��뷽ʽ
					// ����PrintWriter���󣬽�����������ظ�ǰ̨��AJAX
					PrintWriter out = response.getWriter();
					// ���ñ����ͣ������ļ����͵��ֶ�
					boolean isMultipart = ServletFileUpload.isMultipartContent(request);
					// �����ϴ�·��
					String uploadFilePath = "e:\\imgs";
					if (isMultipart) {
						FileItemFactory factory = new DiskFileItemFactory();
						ServletFileUpload upload = new ServletFileUpload(factory);
						List<FileItem> items;
						String filePath = "";
						items = upload.parseRequest(request);
						Iterator<FileItem> iter = items.iterator();
						while (iter.hasNext()) {
							// ��ȡ��Ƭ
							FileItem item = iter.next();
							if (!item.isFormField()) {
								// ��ȡ�ļ���
								String fileName = item.getName();
								if (fileName != null && !fileName.equals("")) {
									File saveFile = new File(uploadFilePath, fileName);
									filePath = saveFile.getPath();
									// ������Ƭ
									item.write(saveFile);
									System.out.println("�ϴ��ɹ���");
									// ��������ʶ��
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
