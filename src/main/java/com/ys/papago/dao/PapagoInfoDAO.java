package com.ys.papago.dao;

import java.util.List;

import com.ys.papago.vo.PapagoInfoVO;

public interface PapagoInfoDAO {
	public List<PapagoInfoVO> selectPapagoList(PapagoInfoVO param);
	public List<PapagoInfoVO> selectPapagoVOList(PapagoInfoVO param);
	public int insertPapagoInfo(PapagoInfoVO user);
	public int updatePapagoInfo(PapagoInfoVO user);
	public int deletePapagoInfo(PapagoInfoVO user);
	public PapagoInfoVO selectPapagoInfo(PapagoInfoVO user);
	public int updatePapagoInfoCnt(PapagoInfoVO user);
	
}
