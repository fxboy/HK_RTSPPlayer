package com.example.demo.core;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @auther Fanxing
 * 保存图片
 */

public class SaveImg {
//    public static ExecutorService newCachedThreadPool(){
//        return new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L,TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>());
//    }

    public static void save(byte[] bt,String name) throws IOException {
        BASE64Encoder base64Encoder = new BASE64Encoder();

        OutputStream os = new FileOutputStream("C:\\Users\\Anonymous\\Pictures\\UbisoftConnect\\"+ name + ".txt");
        os.write(("data:image/jpeg;base64," + base64Encoder.encode(bt)).getBytes(StandardCharsets.UTF_8), 0, bt.length);
        os.flush();
        os.close();
    }
}
