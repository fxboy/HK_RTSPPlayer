package com.example.demo.config;

import com.example.demo.core.SaveImg;
import lombok.Data;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.springframework.stereotype.Component;

import java.net.URL;

/**
 * @auther Fanxing
 * 视频帧截取工具类
 */
@Data
public class HKVideo {
    VideoCapture capture;
    Mat mat;
    byte[] byteImg;
    MatOfByte matOfByte;
    String ut;

    /**
     *  初始化
     * */
    public HKVideo(String url){
        System.out.println("LIB：" + System.getProperty("user.dir") );
        System.load(System.getProperty("user.dir") +  "\\lib\\opencv_java347.dll");
        System.load(System.getProperty("user.dir") +  "\\lib\\opencv_ffmpeg347_64.dll");
        this.capture = new VideoCapture(url);

        this.ut = url;
        mat =  new Mat();
        matOfByte = new MatOfByte();
    }

    public void setHKByte(){
        try{
            if(capture.isOpened()){
                capture.read(mat);
                capture.read(mat);
                Imgproc.resize(mat,mat,new Size(720,560));
                Imgcodecs.imencode(".jpg",mat,matOfByte);
                this.byteImg = matOfByte.toArray();
                //SaveImg.save(this.byteImg,System.currentTimeMillis()+"");
            }
        }catch (Exception ex) {
           // ex.printStackTrace();
            this.capture = new VideoCapture(ut);
            System.out.println("出现异常，已经断线重连");
        }
    }

}
