package com.devops.demo.security;

import java.security.Security;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

	@Bean("jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
		   PooledPBEStringEncryptor enc = new PooledPBEStringEncryptor();
	        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
	        config.setAlgorithm("PBEWithMD5AndDES");
	        config.setPassword("devops"); 
	        config.setKeyObtentionIterations("1000");
	        config.setPoolSize("1");
	        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	        config.setProviderName("BC");
	        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
	        config.setStringOutputType("base64");
	        enc.setConfig(config);
	        return enc;	
	}
}
