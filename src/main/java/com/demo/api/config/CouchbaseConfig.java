package com.demo.api.config;
//
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.couchbase.client.java.Cluster;
//
@Configuration
public class CouchbaseConfig {
	
	@Bean
	public Cluster couchbaseCluster() {
		
		return Cluster.connect("127.0.0.1", "root@123","root@123");
	}

}
