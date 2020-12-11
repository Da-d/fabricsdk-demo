package com.esunego.fabric.util;

import com.alibaba.edas.acm.ConfigService;
import com.alibaba.edas.acm.exception.ConfigException;
import com.alibaba.edas.acm.listener.PropertiesListener;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Map;
import java.util.Properties;


/**
 * 获取配置工具类
 */
@Service
public class ConfigUtil {

    public ConfigUtil() {
        Properties properties = new Properties();
        properties.put("endpoint","acm.aliyun.com");
        properties.put("namespace", "bfe68cc5-67ad-4fdb-bfd0-67b11cfcde6b");
        properties.put("accessKey", "LTAI4G6m9oAuuKzanb5nasNk");
        properties.put("secretKey", "WaGacgpEZOhcmDUWotYqmbMjw0riIV");
        properties.put("openKMSFilter", true);
        properties.put("regionId", "cn-shanghai");
        ConfigService.init(properties);
    }

    public JsonObject getConfigContentForJson() {
        String ConfigContent = null;
        try {
            ConfigContent = ConfigService.getConfig("cipher-kms-aes-128-com.esunego.fabric.congfig", "com.esunego", 6000);
        } catch (ConfigException e) {
            e.printStackTrace();
        }
        Yaml yaml = new Yaml();
        Map<String, Object> confYaml = yaml.load(ConfigContent);
        JsonObject confJson = Json.createObjectBuilder(confYaml).build();
        return confJson;
    }
    public String getConfigContents() {
        String configContent = null;
        try {
            configContent = ConfigService.getConfig("cipher-kms-aes-128-com.esunego.fabric.congfig", "com.esunego", 6000);
        } catch (ConfigException e) {
            e.printStackTrace();
        }
        return configContent;
    }

    public String getContents(String dataId) {
        String ConfigContent = null;
        try {
            ConfigContent = ConfigService.getConfig(dataId, "com.esunego", 6000);
        } catch (ConfigException e) {
            e.printStackTrace();
        }
        return ConfigContent;
    }

    public String getContent(String dataId) {
        if(!dataId.contains("$$$")){
            return "";
        }else{
            dataId = "cipher-kms-aes-128-com.esunego.fabric:" + dataId.replace("$","").replace("\"","");
        }
        String content = null;
        try {
            content = ConfigService.getConfig(dataId, "com.esunego", 6000);
        } catch (ConfigException e) {
            e.printStackTrace();
        }
        return content;
    }
}
