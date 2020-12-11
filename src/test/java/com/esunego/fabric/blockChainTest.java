package com.esunego.fabric;

import com.esunego.fabric.handler.FabricHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class blockChainTest {

    private static Logger logger = LoggerFactory.getLogger(blockChainTest.class);

    static FabricHelper helper = FabricHelper.getInstance();

    public static void main(String args[]){
        helper.setConfigCtx("E:/workspace/yg/fabricsdk-demo/config/bcs-demo-channel-sdk-config.yaml");
        add();
        query();
    }

    public static void add(){
        boolean resp;
        String[] data = {"a", "b", "100"};
        resp = helper.invokeBlockchain("invoke", data);
        if(resp){
            logger.info("insert new data <" + data[0] + ", " + data[1] + ">" + " success");
        }else {
            logger.info("insert new data <" + data[0] + ", " + data[1] + ">" + " fail");
        }
    }

    public static void query(){
        String[] name = {"a"};
        String value = helper.queryBlockchain("query", name);
        logger.info("query key <" + name[0] + "> value is: " + value);
    }

}
