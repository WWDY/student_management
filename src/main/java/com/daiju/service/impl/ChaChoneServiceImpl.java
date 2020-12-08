package com.daiju.service.impl;

import com.daiju.domain.CheckDuplicate;
import com.daiju.service.ChaChoneService;
import com.spire.doc.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author WDY
 * @Date 2020-12-05 15:10
 * @Description TODO
 */
@Service
public class ChaChoneServiceImpl implements ChaChoneService {

    @Value(value = "${spring.checkResultPath}")
    private String checkResultPath;

    @Autowired
    CheckDuplicate checkDuplicate;

    private static int nThreads = Runtime.getRuntime().availableProcessors() / 2;

    ThreadFactory checkDuplicateFactory = new CustomizableThreadFactory("checkDuplicateThread-pool-");

    ExecutorService executor = new ThreadPoolExecutor(nThreads,nThreads,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),checkDuplicateFactory);

    @Override
    public void chaChong(MultipartFile file , AtomicInteger flag, AtomicReference<String> fileName) throws Exception {
        AtomicInteger index = new AtomicInteger();
        Document document = new Document(file.getInputStream());
        String[] paragraphs = document.getText().split("[\\n\\r\\s\\t]");
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 1; i < paragraphs.length; i++) {
            int finalI = i;
            if (paragraphs[finalI].length() > 0){
                CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                    List<String> res = checkDuplicate.divideSentences(paragraphs[finalI]);
                    return res;
                }, executor).
                        thenAccept((res) -> {
                            try {
                                checkDuplicate.checkSearch(res,document);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).
                        whenComplete((res,e)->{
                            index.incrementAndGet();
                            if (index.get() == futures.size()) {
                                long timeMillis = System.currentTimeMillis();
                                document.saveToFile(checkResultPath+timeMillis+file.getOriginalFilename());
                                flag.set(1);
                                fileName.set(checkResultPath+timeMillis+file.getOriginalFilename());
                            }

                        });
                futures.add(future);
            }
        }
    }


}
