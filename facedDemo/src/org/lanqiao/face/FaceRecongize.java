package org.lanqiao.face;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

public class FaceRecongize {

	// 百度AI应用的APP_ID、API_KEY、SECRET_KEY值
	public static final String APP_ID = "10542347";
	public static final String API_KEY = "T5TQ8AkdeaUUFex66ROS2peW";
	public static final String SECRET_KEY = "7gcxDRLVBIYkcwK51QIlfbUueBWXv8vm";

	// 调用百度AI的API进行人脸识别
	public static Object faceRecognize(String imgPath) {
		// 初始化AipFace与百度AI进行连接
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		// 设置网络连接的参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		// 设置识别的参数
		HashMap<String, String> options = new HashMap<String, String>();
		// 参数： 检测人脸的个数
		options.put("max_face_num", "1");
		// 参数： 颜值、年龄
		options.put("face_fields", "age,beauty");
		// 开始检测
		JSONObject response = client.detect(imgPath, options);
		return response;
	}
}
