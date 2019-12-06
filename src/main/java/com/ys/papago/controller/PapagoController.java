package com.ys.papago.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ys.papago.service.PapagoService;
import com.ys.papago.vo.Message;
import com.ys.papago.vo.TransVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PapagoController {
	
	@Resource
	private PapagoService ps;
	
	@PostMapping("/papago")
	public Message doTranslate(@ModelAttribute TransVO tvo) {
		log.info("tvo=>{}", tvo);
		return ps.doTranslate(tvo);
	}
}
