package com.petsknow.doctor.sessionmodule.bean;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class FaceList {
    private static FaceList mFaceList;

    private String mFace = "[{\"image\":\"1\",\"text\":\"微笑\",\"sign\":\"[):]\"},{\"image\":\"2\",\"text\":\"伤心\",\"sign\":\"[:D]\"},{\"image\":\"3\",\"text\":\"美女\",\"sign\":\"[;)]\"},{\"image\":\"4\",\"text\":\"发呆\",\"sign\":\"[:-o]\"},{\"image\":\"5\",\"text\":\"墨镜\",\"sign\":\"[:p]\"},{\"image\":\"6\",\"text\":\"哭\",\"sign\":\"[(H)]\"},{\"image\":\"7\",\"text\":\"羞\",\"sign\":\"[:@]\"},{\"image\":\"8\",\"text\":\"哑\",\"sign\":\"[:s]\"},{\"image\":\"9\",\"text\":\"睡\",\"sign\":\"[:$]\"},{\"image\":\"10\",\"text\":\"大哭\",\"sign\":\"[:(]\"},{\"image\":\"11\",\"text\":\"囧\",\"sign\":\"[:'(]\"},{\"image\":\"12\",\"text\":\"怒\",\"sign\":\"[:|]\"},{\"image\":\"13\",\"text\":\"调皮\",\"sign\":\"[(a)]\"},{\"image\":\"14\",\"text\":\"笑\",\"sign\":\"[8o|]\"},{\"image\":\"15\",\"text\":\"惊讶\",\"sign\":\"[8-|]\"},{\"image\":\"16\",\"text\":\"难过\",\"sign\":\"[+o(]\"},{\"image\":\"17\",\"text\":\"酷\",\"sign\":\"[<o)]\"},{\"image\":\"18\",\"text\":\"汗\",\"sign\":\"[|-)]\"},{\"image\":\"19\",\"text\":\"抓狂\",\"sign\":\"[*-)]\"},{\"image\":\"20\",\"text\":\"吐\",\"sign\":\"[:-#]\"},{\"image\":\"21\",\"text\":\"笑\",\"sign\":\"[:-*]\"},{\"image\":\"22\",\"text\":\"快乐\",\"sign\":\"[^o)]\"},{\"image\":\"23\",\"text\":\"奇\",\"sign\":\"[8-)]\"},{\"image\":\"24\",\"text\":\"傲\",\"sign\":\"[(|)]\"},{\"image\":\"25\",\"text\":\"饿\",\"sign\":\"[(u)]\"},{\"image\":\"26\",\"text\":\"累\",\"sign\":\"[(S)]\"},{\"image\":\"27\",\"text\":\"吓\",\"sign\":\"[(*)]\"},{\"image\":\"28\",\"text\":\"汗\",\"sign\":\"[(#)]\"},{\"image\":\"29\",\"text\":\"高兴\",\"sign\":\"[(R)]\"},{\"image\":\"30\",\"text\":\"闲\",\"sign\":\"[({)]\"},{\"image\":\"31\",\"text\":\"努力\",\"sign\":\"[(})]\"},{\"image\":\"32\",\"text\":\"骂\",\"sign\":\"[(k)]\"},{\"image\":\"33\",\"text\":\"疑问\",\"sign\":\"[(F)]\"},{\"image\":\"34\",\"text\":\"秘密\",\"sign\":\"[(W)]\"},{\"image\":\"35\",\"text\":\"乱\",\"sign\":\"[(D)]\"}]";
    private HashMap<String, FaceContent> mFaceMap = new HashMap<>();

    public static FaceList getInstance() {
        if (mFaceList == null) {
            mFaceList = new FaceList();
            mFaceList.init();
        }
        return mFaceList;
    }

    public List<FaceContent> getList() {
        Collection<FaceContent> collection = mFaceMap.values();
        List<FaceContent> faceContents = new ArrayList<>();
        faceContents.addAll(collection);
        return faceContents;
    }

    public void init() {
        FaceContent[] faceContents = JSON.parseObject(mFace, FaceContent[].class);
        for (FaceContent faceContent : faceContents) {
            mFaceMap.put(faceContent.getSign(), faceContent);
        }
    }

    public FaceContent get(String sign) {
        return mFaceMap.get(sign);
    }

}
