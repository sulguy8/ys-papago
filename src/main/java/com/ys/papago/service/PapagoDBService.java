package com.ys.papago.service;

import java.util.List;
import java.util.Map;

import com.ys.papago.vo.PapagoInfoVO;

public interface PapagoDBService {

	public List<PapagoInfoVO> getPapagoList(PapagoInfoVO param);
	public List<PapagoInfoVO> getPapagoVOList(PapagoInfoVO param);
	public Map<String,String> insertPapagoInfo(PapagoInfoVO user);
	public Map<String,String> updatePapagoInfo(PapagoInfoVO user);
	public Map<String,String> deletePapagoInfo(PapagoInfoVO user);
	public PapagoInfoVO login(PapagoInfoVO user);
}
