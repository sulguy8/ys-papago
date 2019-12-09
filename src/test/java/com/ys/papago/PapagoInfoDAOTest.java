package com.ys.papago;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.ys.papago.dao.PapagoInfoDAO;
import com.ys.papago.vo.PapagoInfoVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Slf4j
public class PapagoInfoDAOTest {
	
	@Resource
	private PapagoInfoDAO pidao;
	@Test
	public void test() {
		Assert.notNull(pidao,"그럼 그렇지~");
		List<PapagoInfoVO> pList = pidao.selectPapagoVOList(null);
		Assert.isTrue(pList.size()==1,"아 맞아 목록 1개였지~");
		
	}

}
