package com.example.demo.controller;

import com.example.demo.Timer;
import com.example.demo.config.CemeraConfig;
import com.example.demo.config.HKVideo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Fanxing
 * 这是一个简介
 */


@RestController

public class VideoController {
    //@Autowired
    //MatUtils matUtils;

    Boolean isst = false;

    @Autowired
    Timer timer;

    @Autowired
    CemeraConfig cemeraConfig;

    @RequestMapping("/start")
    public Object start(){
        if(timer.getIsst()) return "告诉你，你摊上事了，现在冲突严重，摄像头坏了，自己掏钱赔吧";
        Map<String, HKVideo> mk = new HashMap<>();
        cemeraConfig.getConfig().forEach(_map ->{
            String url = new StringBuffer("rtsp://").append(_map.get("user")).append(":").append(_map.get("password")).append("@").append(_map.get("host")).append(":").append(_map.get("port"))
                    .append("/h.264/ch1/sub/av_stream").toString();
            mk.put(_map.get("name"),new HKVideo(url));
            _map.remove("password");// 删除不想显示的内容
        } );
        timer.SetTimer(mk);
        return cemeraConfig.getConfig();
    }

    @ResponseBody
    @RequestMapping(value = "/get",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] get(String id){
        return  timer.getImages(id);
    }
}
