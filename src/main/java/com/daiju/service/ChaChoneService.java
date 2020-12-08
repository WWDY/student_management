package com.daiju.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author WDY
 * @Date 2020-12-05 15:08
 * @Description TODO
 */
public interface ChaChoneService {
    void chaChong(MultipartFile file, AtomicInteger flag, AtomicReference<String> fileName) throws UnsupportedEncodingException, Exception;
}
