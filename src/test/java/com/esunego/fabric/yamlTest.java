package com.esunego.fabric;

import com.esunego.fabric.service.BlockChainService;
import com.esunego.fabric.util.ConfigUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class yamlTest {

    @Autowired
    private BlockChainService blockChainService;

    /*
     *  添加一条数据
     * */
    @Test
    public void add(){
        String[] data = {"a","b","-500"};
        blockChainService.addBlockChain(data);

    }

    /*
     *  查找数据
     * */
    @Test
    public void query(){
        blockChainService.queryBlockChain(new String[] {"a","b"});
    }
}
