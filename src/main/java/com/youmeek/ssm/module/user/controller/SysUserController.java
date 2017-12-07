package com.youmeek.ssm.module.user.controller;

import com.youmeek.ssm.module.user.dto.ResultDto;
import com.youmeek.ssm.module.user.dto.Singleton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("/sysUserController")
public class SysUserController {

	@RequestMapping("/changeCellState")
	@ResponseBody
	public ResultDto changeCellState(){
		Singleton singleton = Singleton.GetInstance();
		int [][]cell = singleton.getCube();
		int [][]tmp = new int[singleton.getM()][singleton.getN()];
		int count = 0;
		for (int i = 0; i< singleton.getM(); i++){
			for (int j = 0; j < singleton.getN(); j++){
				count = count + cell[(i-1+singleton.getM())%singleton.getM()][j] + cell[(i-1+singleton.getM())%singleton.getM()][(j-1+singleton.getN())%singleton.getN()]
						+ cell[(i-1+singleton.getM())%singleton.getM()][(j+1)%singleton.getN()] + cell[i][(j-1+singleton.getN())%singleton.getN()] + cell[i][(j+1)%singleton.getN()]
						+ cell[(i+1)%singleton.getM()][(j-1+singleton.getN())%singleton.getN()] + cell[(i+1)%singleton.getM()][j]
						+ cell[(i+1)%singleton.getM()][(j+1)%singleton.getN()];
				if (count == 3){
					tmp[i][j] = 1;
				} else if (count ==2){
					tmp[i][j] = cell[i][j];
				} else {
					tmp[i][j] = 0;
				}
				count = 0;
			}
		}
		singleton.setCube(tmp);
		ResultDto resultDto = new ResultDto();
		resultDto.setCellCube(tmp);
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
				cube[i][j] = 0;
				cube[i][j] = random.nextInt(2);
			}
		}
		singleton.setCube(cube);
		singleton.setM(M);
		singleton.setN(N);
		return "cellGame";
	}

}
