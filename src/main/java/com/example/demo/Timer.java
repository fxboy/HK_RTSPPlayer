package com.example.demo;

import com.example.demo.config.HKVideo;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther Fanxing
 * 定时器
 */
@Component
@Data
@Scope("singleton")
public class Timer {
    Boolean isst  = false;
    Map<String, HKVideo> hkVideoMap;

    public Timer(){
        hkVideoMap = new HashMap<>();
    }

    public void SetTimer(Map<String, HKVideo> hkVideoMap){
        this.hkVideoMap = hkVideoMap;
        isst = true;
    }

    @Scheduled(fixedDelay=1000/25, initialDelay=1000/25)
    public void ds(){
       hkVideoMap.forEach((String a, HKVideo b) -> {
           b.setHKByte();
       });
    }

    public byte[] getImages(String name){
        if(!this.hkVideoMap.containsKey(name)) return null;
        return this.hkVideoMap.get(name).getByteImg();
    }

}
