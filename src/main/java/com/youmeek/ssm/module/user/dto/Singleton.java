package com.youmeek.ssm.module.user.dto;

public class Singleton {
    private int[][] cube;
    private int M;
    private int N;
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

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }
}
