package com.example.djran.multithread.practice;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;

/**
 * @author d.djran@gmail.com
 * @function 大文件多线程分段下载
 * @Github：https://github.com/wrayzheng/java-multithread-downloader
 * @time 2018/5/15
 */
@Slf4j
public class MultiThreadDown {
    /**
     * 判断文件是否支持端点续传
     * 使用header中的range
     */
    private boolean resumable;

    private boolean multithreaded=true;

    private int THREAD_NUM=5;
    private final int MIN_SIZE=2<<20;

    private URL url;
    private File file;
    private int fileSize=0;

    /**
     * 断点数组
     */
    private int [] endpoint;



}
