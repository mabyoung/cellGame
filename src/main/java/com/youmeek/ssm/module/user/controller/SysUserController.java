package com.youmeek.ssm.module.user.controller;

import com.youmeek.ssm.module.user.Dto.ResultDto;
import com.youmeek.ssm.module.user.Dto.Singleton;
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
import javax.xml.transform.Result;
import java.util.Date;
import java.util.Random;

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

	@RequestMapping("/changeCellState")
	@ResponseBody
	public ResultDto changeCellState(){
		Singleton singleton = Singleton.GetInstance();
		int [][]cell = singleton.getCube();
		int count = 0;
		for (int i = 0; i< singleton.getM(); i++){
			for (int j = 0; j < singleton.getN(); j++){
				count = count + cell[(i-1+singleton.getM())%singleton.getM()][j] + cell[(i-1+singleton.getM())%singleton.getM()][(j-1+singleton.getN())%singleton.getN()]
						+ cell[(i-1+singleton.getM())%singleton.getM()][(j+1)%singleton.getN()] + cell[i][(j-1+singleton.getN())%singleton.getN()] + cell[i][(j+1)%singleton.getN()]
						+ cell[(i+1)%singleton.getM()][(j-1+singleton.getN())%singleton.getN()] + cell[(i+1)%singleton.getM()][j]
						+ cell[(i+1)%singleton.getM()][(j+1)%singleton.getN()];
				if (count == 3){
					cell[i][j] = 1;
				} else if (count ==2){

				} else {
					cell[i][j] = 0;
				}
				count = 0;
			}
		}
		singleton.setCube(cell);
		ResultDto resultDto = new ResultDto();
		resultDto.setCellCube(cell);
		return resultDto;
	}

	@RequestMapping("/beginCellGame")
	public String beginCellGame(){
		Singleton singleton = Singleton.GetInstance();
		Random random=new Random();
		int M =20;
		int N =20;
		int [][]cube = new int[M][N];
		for (int i = 0; i < M; i++){
			for (int j = 0; j < N; j++){
				cube[i][j] = random.nextInt(2);
			}
		}
		singleton.setCube(cube);
		singleton.setM(M);
		singleton.setN(N);
		return "cellGame";
	}

	@RequestMapping("/test")
	@ResponseBody
	public ResultDto test(ResultDto resultDto){
		int[][]cube = resultDto.getCellCube();
		for (int i = 0; i < cube.length; i++){
			for (int j = 0; j< cube[i].length; j++){
				cube[i][j] +=1;
			}
		}
		resultDto.setCellCube(cube);
		return resultDto;
	}

}
