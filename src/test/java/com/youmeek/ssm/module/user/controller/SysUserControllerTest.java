package com.youmeek.ssm.module.user.controller;

import org.junit.Test;

import javax.annotation.Resource;


public class SysUserControllerTest {
    @Resource
    private SysUserController sysUserController;

    @Test
    public void showUser() throws Exception {
        sysUserController.showUser(1L);
    }

    @Test
    public void testLogback() throws Exception {
        sysUserController.testLogback();
    }

    @Test
    public void changeCellState() throws Exception {
        sysUserController.changeCellState();
    }

    @Test
    public void beginCellGame() throws Exception {
        sysUserController.beginCellGame();
    }

}