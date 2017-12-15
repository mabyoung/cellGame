package com.merkleTree;

import javax.sound.sampled.Line;
import java.io.File;
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
                }
            }
        }
    }

    public static void main(String[] args) {
        MerkleTree mt1 = new MerkleTree("testA");
    }
}
