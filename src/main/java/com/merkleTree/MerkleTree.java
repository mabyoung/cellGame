package com.merkleTree;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class MerkleTree {
    private String root;
    private HashMap<String, List<Object>> mt;
    private LinkedHashMap<String, String> hashList;
    private String topHash;

    public MerkleTree(String root){
        this.root = root;
        hashList = new LinkedHashMap<>();
        mt = new HashMap<>();
        MT2();
    }

    public void line(){
        System.out.println("----------------------");
    }

    public void printHashList(){
        line();
        Iterator iter = hashList.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.print(key);
            System.out.print(" : ");
            System.out.println(val);
        }
        line();
    }

    public void printMT(String hash){
        List<Object> value = mt.get(hash);
        if (value == null){
            return;
        }
        Object item = value.get(0);
        HashMap<String, String> child = (HashMap) value.get(1);
        System.out.print(hash);
        System.out.print(item);
        if (child == null){
            return;
        }
        Iterator iter = child.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.print(key);
            System.out.print(" : ");
            System.out.println(val);
        }
        iter = hashList.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String)entry.getKey();
            printMT(key);
        }
    }

    public void MT1(){
        Iterator iter = hashList.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String)entry.getKey();
            String val = (String)entry.getValue();
            List<String> items = getItems(key);
            List<Object> value = new ArrayList<>();
            HashMap<String,String> list = new HashMap<>();
            value.add(key);
            for (String item : items){
                if (key == this.root){
                    list.put(hashList.get(item),item);
                } else {
                    list.put(hashList.get(key+"\\"+item),key+"\\"+item);
                }
            }
            value.add(list);
            mt.put(val, value);
        }
        topHash = hashList.get(root);
    }

    public void MT2(){
        hashList(this.root);
        MT1();
        printHashList();
        //MT3();
        //printMT(topHash);
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
                String result = MD5Tools.MD5(sb.toString());
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return MD5Tools.MD5(data);
    }

    public List<String> getItems(String directory){
        List<String> value = new ArrayList<>();
        if (directory != this.root){
            directory = root+"\\"+directory;
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
        List<String> items = getItems(rootDir);
        if (items == null){
            hashList.put(rootDir,"");
        }
        String s = "";
        for (String item : items){
            s += hashList.get(item);
        }
        hashList.put(rootDir,md5sum(s));
    }

    public void hashListChild(String rootDir){
        List<String> items = getItems(rootDir);
        if (items == null){
            hashList.put(rootDir,"");
            return;
        }
        for (String item : items){
            String itemname = rootDir+"\\"+item;
            File f = new File(itemname);
            if (f.isDirectory()){
                hashListChild(item);
                List<String> subitems = getItems(item);
                String s = "";
                for (String subitem : subitems){
                    s += hashList.get(item +"\\" + subitem);
                }
                if (rootDir == this.root){
                    hashList.put(item,md5sum(s));
                } else {
                    hashList.put(itemname,md5sum(s));
                }
            } else {
                if (rootDir == this.root){
                    hashList.put(item,md5sum(item));
                } else {
                    hashList.put(itemname,md5sum(itemname));
                }
            }
        }
    }

    public static void main(String[] args) {
        MerkleTree mt1 = new MerkleTree("e:/testA");
    }
}
