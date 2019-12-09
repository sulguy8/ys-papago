package com.ys.papago.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ys.papago.service.PapagoDBService;
import com.ys.papago.vo.PapagoInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PapagoDBController {
	@Resource
	private PapagoDBService ps;
	
	@GetMapping("/papago/get")
	public List<PapagoInfoVO> getPapagoList(@ModelAttribute PapagoInfoVO param){
		System.out.println(ps.getPapagoVOList(param));
		return ps.getPapagoVOList(param);
	}
	
	@PostMapping("/papago/insert") 
	public @ResponseBody Map<String,String> insertPapago(@ModelAttribute PapagoInfoVO user){
		System.out.println(user);
		return ps.insertPapagoInfo(user);
	}
	
}
