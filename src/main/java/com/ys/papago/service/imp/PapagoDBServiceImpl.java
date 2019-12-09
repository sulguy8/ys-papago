package com.ys.papago.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.papago.dao.PapagoInfoDAO;
import com.ys.papago.service.PapagoDBService;
import com.ys.papago.vo.PapagoInfoVO;

@Service("PapagoInfoDAOimpl")
public class PapagoDBServiceImpl implements PapagoDBService {
	@Autowired
	private PapagoInfoDAO pdao;
	
	
	@Override
	public List<PapagoInfoVO> getPapagoList(PapagoInfoVO param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PapagoInfoVO> getPapagoVOList(PapagoInfoVO param) {
		return pdao.selectPapagoVOList(param);
	}

	@Override
	public Map<String, String> insertPapagoInfo(PapagoInfoVO user) {
		Map<String,String> rMap = new HashMap<String,String>();
		rMap.put("cnt", pdao.insertPapagoInfo(user)+"");
		return rMap;
	}

	@Override
	public Map<String, String> updatePapagoInfo(PapagoInfoVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> deletePapagoInfo(PapagoInfoVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PapagoInfoVO login(PapagoInfoVO user) {
		// TODO Auto-generated method stub
		return null;
	}

}
