package com.ys.papago.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PapagoTest {
	@Value("${client.id}")
	private String id;
	@Value("${client.secret}")
	private String secret;
	@Value("${client.api.url}")
	private String apiUrl;

	public void doPapagoTest() {
		try {
			String text = URLEncoder.encode("동민아, 일찍와라.","UTF-8");
			URL url = new URL(apiUrl);
			HttpURLConnection hc = (HttpURLConnection)url.openConnection();
			hc.setRequestMethod("POST");
			hc.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			hc.setRequestProperty("X-Naver-Client-Id", id);
			hc.setRequestProperty("X-Naver-Client-Secret", secret);
			String param = "source=ko&target=en&text=" + text;
			hc.setDoOutput(true);
			DataOutputStream dos = new DataOutputStream(hc.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close(); // 여기까지가 나의 output
			
			int status = hc.getResponseCode();
			if(status!=200) {
				System.out.println("에러남!!");
				return; // 여기는 서버의 응답
			}
			
			InputStreamReader isr = new InputStreamReader(hc.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String res = "";
			String str = null;
			while((str=br.readLine())!=null) {
				res += str;
			}
				System.out.println(res);
	
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		PapagoTest pt = new PapagoTest();
		System.out.println(pt.getId());
	}
}
