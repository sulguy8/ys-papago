package com.ys.papago.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ys.papago.dao.PapagoInfoDAO;
import com.ys.papago.vo.PapagoInfoVO;

@Repository
public class PapagoInfoDAOimpl implements PapagoInfoDAO {

	@Resource
	private SqlSessionFactory ssf;
	
	@Override
	public List<PapagoInfoVO> selectPapagoList(PapagoInfoVO param) {
		SqlSession ss = ssf.openSession();
		try {
			return ss.selectList("com.ys.papago.PapagoInfoMapper.selectPapagoInfoList", param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();

		}
		return null;
	}

	@Override
	public List<PapagoInfoVO> selectPapagoVOList(PapagoInfoVO param) {
		SqlSession ss = ssf.openSession();
		
		try {
			return ss.selectList("com.ys.papago.PapagoInfoMapper.selectPapagoInfoList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();

		}
		return null;
	}

	@Override
	public int insertPapagoInfo(PapagoInfoVO user) {
		SqlSession ss = ssf.openSession();
		try {
			return ss.insert("com.ys.papago.PapagoInfoMapper.insertPapagoInfo", user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.commit();
			ss.close();

		}
		return 0;
	}

	@Override
	public int updatePapagoInfo(PapagoInfoVO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePapagoInfo(PapagoInfoVO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PapagoInfoVO selectPapagoInfo(PapagoInfoVO user) {
		// TODO Auto-generated method stub
		return null;
	}

}
