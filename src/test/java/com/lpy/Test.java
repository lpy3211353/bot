package com.lpy;

import com.lpy.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Test {

    Logger logger = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        redisUtil.set("aa", "dd");
        String aa = redisUtil.get("aa");
        Test test=new Test();
        test.log(aa);
    }

    void log(String str){
        logger.info(str);
    }
}
