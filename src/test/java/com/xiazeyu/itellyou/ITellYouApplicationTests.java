package com.xiazeyu.itellyou;

import com.xiazeyu.itellyou.rabbitmq.TestProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ITellYouApplicationTests {

    @Autowired
    private TestProducer testProducer;

    @Test
    public void contextLoads() {
        testProducer.sendMessage();
        while (true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
