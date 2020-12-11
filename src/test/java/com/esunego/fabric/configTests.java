package com.esunego.fabric;

import com.esunego.fabric.util.ConfigUtil;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.json.JsonObject;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.PrivateKey;

@SpringBootTest
@RunWith(SpringRunner.class)
public class configTests {

    @Autowired
    private ConfigUtil configUtil;



    /*
    *  test 远程获取 keystore，
    *  然后转换为密钥PrivateKey
    * */
    @Test
    public void Test01() throws IOException {
        String content = configUtil.getContents("cipher-kms-aes-128-com.esunego.fabric:peer1_msp_keystore");
        System.out.println(content);
        final Reader pemReader = new StringReader(content);
        PrivateKeyInfo pemPair;
        PEMParser pemParser = new PEMParser(pemReader);
        pemPair = (PrivateKeyInfo) pemParser.readObject();
        PrivateKey privateKey = new JcaPEMKeyConverter().getPrivateKey(pemPair);
        System.out.println(privateKey);
    }

    /*
    *   test 获取 admincerts
    *   配置中心id：cipher-kms-aes-128-com.esunego.fabric:peer1_msp_admincerts
    * */
    @Test
    public void Test02() {
        String content = configUtil.getContents("cipher-kms-aes-128-com.esunego.fabric:peer1_msp_admincerts");
        System.out.println(content);
    }

    /*
     *   test 从yaml配置文件获取路径后，
     *   再从配置中心获取对应的数据
     * */
    @Test
    public void Test03() {
        JsonObject configContent = configUtil.getConfigContentForJson();
        String jsonValue = configContent.getJsonObject("organizations").getJsonObject("1117a6c9543743f3db06394661dcdab7d9c58b4f").get("tlsCryptoKeyPath").toString();
        System.out.println(jsonValue);
        String content = configUtil.getContent(jsonValue);
        System.out.println(content);
    }



}
