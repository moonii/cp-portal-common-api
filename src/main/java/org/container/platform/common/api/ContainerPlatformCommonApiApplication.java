package org.container.platform.common.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.container.platform"})
public class ContainerPlatformCommonApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContainerPlatformCommonApiApplication.class, args);
    }

}
