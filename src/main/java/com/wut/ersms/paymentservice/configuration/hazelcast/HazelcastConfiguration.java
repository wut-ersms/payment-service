package com.wut.ersms.paymentservice.configuration.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.RestApiConfig;
import com.hazelcast.config.RestEndpointGroup;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.wut.ersms.paymentservice.auth.TPayAuthResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {

    public static final String AUTH_MAP_NAME = "auth";

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        config.setClusterName("dev");

        SerializerConfig serializerConfig = new SerializerConfig()
                .setImplementation(new TPayAuthResponseSerializer())
                .setTypeClass(TPayAuthResponse.class);

        config.getSerializationConfig().addSerializerConfig(serializerConfig);

        NetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.getInterfaces().addInterface("127.0.0.1");

        RestApiConfig restApiConfig = new RestApiConfig()
                .setEnabled(true)
                .enableGroups(
                        RestEndpointGroup.HEALTH_CHECK,
                        RestEndpointGroup.CLUSTER_READ,
                        RestEndpointGroup.CLUSTER_WRITE,
                        RestEndpointGroup.DATA,
                        RestEndpointGroup.WAN
                );

        networkConfig.setRestApiConfig(restApiConfig);

        config.addMapConfig(new MapConfig(AUTH_MAP_NAME));
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public IMap<String, TPayAuthResponse> authMap(HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getMap(AUTH_MAP_NAME);
    }

}
