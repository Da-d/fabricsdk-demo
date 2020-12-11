package com.esunego.fabric.service;

import com.esunego.fabric.handler.FabricExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BlockChainService {

    private static Logger logger = LoggerFactory.getLogger(BlockChainService.class);

    @Autowired
    private FabricExample fabricExample;

    public void addBlockChain(String[] args){
        boolean flag = fabricExample.invokeBlockchain("invoke", args);
        if(flag == false) {
            logger.info("insert new data <" + args[0] + ", " +  args[1] + ">" + " fail");
            return;
        }
        logger.info("insert new data <" + args[0] + ", " +  args[1] + ">" + " success");
    }

    public void queryBlockChain(String[] args){
        for(int i = 0 ;i < args.length;i++){
            String result = fabricExample.queryBlockchain("query", new String[] {args[i]});
            logger.info("query key <" + args[i] + "> value is " + result);
        }
    }
}
