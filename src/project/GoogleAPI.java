package project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.ImageIcon;

public class GoogleAPI {
	/**
     * Google Maps Static API에서 주어진 위치에 대한 지도 이미지를 다운로드합니다.
     */
	public void downloadMap(String location) {
		try {
			// Google Maps Static API 요청 URL 생성
			String imageURL ="https://maps.googleapis.com/maps/api/staticmap?center="
					+ URLEncoder.encode(location, "UTF-8") 
					+ "zoom=15&size=612x612&scale=2&key=AIzaSyABAqZvMLNZjfB6hSBEQlxefLYGLcJzTWo";
			
			// URL에 대한 연결
			URL url = new URL(imageURL);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(location);
			
			// 데이터를 읽기 위한 버퍼
			byte[] b = new byte[2048];
			int length;
			
			// 입력 스트림에서 데이터를 읽고 출력 스트림에 씀
			while((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();
		} catch(Exception e) {  // 예외 발생 시 스택 트레이스를 출력합니다.
			e.printStackTrace();
		}
	}
	
	/**
     * 다운로드한 지도의 ImageIcon을 반환하며, 612x612 픽셀로 스케일링
     */
	public ImageIcon getMap(String location) {
		return new ImageIcon((new ImageIcon(location)).getImage().getScaledInstance(612, 612, java.awt.Image.SCALE_SMOOTH));
	}
	
	/**
     * 주어진 파일 이름의 파일을 삭제
     */
	public void fileDelete(String fileName) {
		File f = new File(fileName);
		f.delete();
	}
}
