package com.youmeek.ssm.module.user.Dto;

public class Singleton {
    private int[][] cube;
    private static Singleton instance = null;
    private Singleton(){}
    public static Singleton GetInstance(){
        if(instance == null){
             instance = new Singleton();
        }
         return instance;
    }
    public int[][] getCube() {
        return cube;
    }
    public void setCube(int[][] cube) {
        this.cube = cube;
    }
}
