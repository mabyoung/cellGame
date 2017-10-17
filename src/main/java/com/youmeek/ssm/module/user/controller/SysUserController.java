package com.youmeek.ssm.module.user.controller;

import com.youmeek.ssm.module.user.Dto.ResultDto;
import com.youmeek.ssm.module.user.pojo.SysUser;
import com.youmeek.ssm.module.user.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/sysUserController")
public class SysUserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SysUserController.class);
	
	@Resource
	private SysUserService sysUserService;
	
	@RequestMapping("/showUserToJspById/{userId}")
	public String showUser(Model model,@PathVariable("userId") Long userId){
		SysUser user = this.sysUserService.getById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}
	
	@RequestMapping("/showUserToJSONById/{userId}")
	@ResponseBody
	public SysUser showUser(@PathVariable("userId") Long userId){
		SysUser user = sysUserService.getById(userId);
		return user;
	}
	
	
	@RequestMapping("/test-logback")
	@ResponseBody
	public Date testLogback(){
		LOG.trace("-----------------------------------trace");
		LOG.debug("-----------------------------------debug");
		LOG.info("-----------------------------------info");
		LOG.warn("-----------------------------------warn");
		LOG.error("-----------------------------------error");
		return new Date();
	}

	@RequestMapping(value = "/changeCellState" ,method = RequestMethod.POST)
	@ResponseBody
	public ResultDto changeCellState(){
		int [][]cell = new int[10][10];
		ResultDto resultDto = new ResultDto();
		resultDto.setCellCube(cell);
		return resultDto;
	}

	@RequestMapping("/beginCellGame")
	public String beginCellGame(){
		return "cellGame";
	}



}
