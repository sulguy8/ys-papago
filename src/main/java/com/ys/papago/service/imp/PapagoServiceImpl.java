package com.ys.papago.service.imp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.papago.dao.PapagoInfoDAO;
import com.ys.papago.service.PapagoService;
import com.ys.papago.vo.Message;
import com.ys.papago.vo.PapagoInfoVO;
import com.ys.papago.vo.Result;
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
	
	@Autowired
	private PapagoInfoDAO pidao;
	
	private ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	@Override
	public Message doTranslate(TransVO tvo) {
		
		
		try {
			System.out.println("서비스 들어왔지");
			PapagoInfoVO pi = new PapagoInfoVO();
			pi.setPiSource(tvo.getSource());
			pi.setPiTarget(tvo.getTarget());
			pi.setPiText(tvo.getText());
			pi = pidao.selectPapagoInfo(pi);
			
			if(pi!=null) {
				Result r = new Result();
				r.setSrcLangType(pi.getPiSource());
				r.setTarLangType(pi.getPiTarget());
				r.setTranslatedText(pi.getPiResult());
				Message m = new Message();
				m.setResult(r);
				pidao.updatePapagoInfoCnt(pi);
				System.out.println("나는 데이터 베이스지롱");
				return m;
			}
			
			String text = URLEncoder.encode(tvo.getText(),"UTF-8");
			
			URL url = new URL(apiUrl);
			HttpURLConnection hc = (HttpURLConnection)url.openConnection();
			hc.setRequestMethod("POST");
			hc.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			hc.setRequestProperty("X-Naver-Client-Id", id);
			hc.setRequestProperty("X-Naver-Client-Secret", secret);
			String param = "source=" +tvo.getSource() + "&target=" + tvo.getTarget() + "&text=" + text;
			hc.setDoOutput(true);
			DataOutputStream dos = new DataOutputStream(hc.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close(); // 여기까지가 나의 output
			
			int status = hc.getResponseCode();
			
			InputStreamReader isr = new InputStreamReader(hc.getInputStream(),"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			StringBuffer res = new StringBuffer();
			String str = null;
			while((str=br.readLine())!=null) {
				res.append(str);
			}
				
				TransVO resultTvo = om.readValue(res.toString(), TransVO.class);
				Result r = resultTvo.getMessage().getResult();
				pi = new PapagoInfoVO();
				pi.setPiSource(r.getSrcLangType());
				pi.setPiTarget(r.getTarLangType());
				pi.setPiResult(r.getTranslatedText());
				pi.setPiText(tvo.getText());
				
				System.out.println("나는 insert 됐지롱 : " + r);
				pidao.insertPapagoInfo(pi);
				
				return resultTvo.getMessage();
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
