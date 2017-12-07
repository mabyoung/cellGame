package com.youmeek.ssm.module.user.dto;

import java.io.Serializable;
import java.util.Arrays;

public class ResultDto implements Serializable{
    private static final long serialVersionUID = 8383431126801518905L;
    private int[][] cellCube;

    public int[][] getCellCube() {
        return cellCube;
    }

    public void setCellCube(int[][] cellCube) {
        this.cellCube = cellCube;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "cellCube=" + Arrays.toString(cellCube) +
                '}';
    }
}
