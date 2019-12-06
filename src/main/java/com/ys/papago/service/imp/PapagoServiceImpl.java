package com.ys.papago.service.imp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.papago.service.PapagoService;
import com.ys.papago.vo.Message;
import com.ys.papago.vo.TransVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PapagoServiceImpl implements PapagoService {
	@Value("${client.id}")
	private String id;
	@Value("${client.secret}")
	private String secret;
	@Value("${client.api.url}")
	private String apiUrl;
	
	private ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	@Override
	public Message doTranslate(TransVO tvo) {
		try {
			String text = URLEncoder.encode(tvo.getText(),"UTF-8");
			URL url = new URL(apiUrl);
			HttpURLConnection hc = (HttpURLConnection)url.openConnection();
			hc.setRequestMethod("POST");
			hc.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			hc.setRequestProperty("X-Naver-Client-Id", id);
			hc.setRequestProperty("X-Naver-Client-Secret", secret);
			String param = "source=" +tvo.getSource() + "&target=" + tvo.getTarget() + "&text=" + tvo.getText();
			hc.setDoOutput(true);
			DataOutputStream dos = new DataOutputStream(hc.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close(); // 여기까지가 나의 output
			
			int status = hc.getResponseCode();
			
			InputStreamReader isr = new InputStreamReader(hc.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			StringBuffer res = new StringBuffer();
			String str = null;
			while((str=br.readLine())!=null) {
				res.append(str);
			}
				TransVO resultTvo = om.readValue(res.toString(), TransVO.class);
				log.info(res.toString());
				log.info("tvo=>{}", resultTvo);
				return resultTvo.getMessage();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
