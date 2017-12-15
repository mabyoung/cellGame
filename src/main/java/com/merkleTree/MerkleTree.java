package com.merkleTree;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class MerkleTree {
    private Integer lineLength = 30;
    private String root;
    private List<String> mt;
    private HashMap<String, String> hashList;
    private String topHash;

    public MerkleTree(String root){
        this.root = root;
        MT2();
    }

    public void line(){
        System.out.print(this.lineLength);
    }

    public void printHashList(){
        line();
        Iterator iter = hashList.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object val = entry.getValue();
            System.out.print(val);
        }
        line();
    }

    public void MT1(){

    }

    public void MT2(){

    }

    public String md5sum(String data){
        String fn = root+"\\"+data;
        File f = new File(fn);
        if (f.isFile()){
            // 以字节流方法读取文件
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
                // 设置一个，每次 装载信息的容器
                byte[] buf = new byte[1024];
                // 定义一个StringBuffer用来存放字符串
                StringBuffer sb = new StringBuffer();
                // 开始读取数据
                int len = 0;// 每次读取到的数据的长度
                while ((len = fis.read(buf)) != -1) {// len值为-1时，表示没有数据了
                    // append方法往sb对象里面添加数据
                    sb.append(new String(buf, 0, len, "utf-8"));
                }
                // 输出字符串
                System.out.println(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            while(true){
                String d = f.read
            }
        }
    }

    public List<String> getItems(String directory){
        List<String> value = new ArrayList<>();
        if (directory != this.root){

        }
        File f = new File(directory);
        if (f.isDirectory()){
            String[] items = f.list();
            for (String item : items){
                value.add(item);
            }
            Collections.sort(value);
        }
        return value;
    }

    public void hashList(String rootDir){
        hashListChild(rootDir);
    }

    public void hashListChild(String rootDir){
        List<String> items = getItems(rootDir);
        if (items == null){
            return;
        }
        for (String item : items){
            String itemname = rootDir+"\\"+"item";
            File f = new File(itemname);
            if (f.isDirectory()){
                hashListChild(item);
            } else {
                if (rootDir == this.root){
                    hashList.put(item,md5sum(item));
                }
            }
        }
    }

    public static void main(String[] args) {
        MerkleTree mt1 = new MerkleTree("testA");
    }
}
