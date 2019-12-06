package com.ys.papago.service;

import com.ys.papago.vo.Message;
import com.ys.papago.vo.TransVO;

public interface PapagoService {
	
	public Message doTranslate(TransVO tvo);
}
