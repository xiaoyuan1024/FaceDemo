package org.lanqiao.face;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

public class FaceRecongize {

	// �ٶ�AIӦ�õ�APP_ID��API_KEY��SECRET_KEYֵ
	public static final String APP_ID = "10542347";
	public static final String API_KEY = "T5TQ8AkdeaUUFex66ROS2peW";
	public static final String SECRET_KEY = "7gcxDRLVBIYkcwK51QIlfbUueBWXv8vm";

	// ���ðٶ�AI��API��������ʶ��
	public static Object faceRecognize(String imgPath) {
		// ��ʼ��AipFace��ٶ�AI��������
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		// �����������ӵĲ���
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		// ����ʶ��Ĳ���
		HashMap<String, String> options = new HashMap<String, String>();
		// ������ ��������ĸ���
		options.put("max_face_num", "1");
		// ������ ��ֵ������
		options.put("face_fields", "age,beauty");
		// ��ʼ���
		JSONObject response = client.detect(imgPath, options);
		return response;
	}
}
