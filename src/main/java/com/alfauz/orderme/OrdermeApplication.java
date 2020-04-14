package com.alfauz.orderme;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdermeApplication {

    private static final Logger LOG = LogManager.getLogger(OrdermeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OrdermeApplication.class, args);
        LOG.debug("==============================================Debug===========================================");
        LOG.info("==============================================info============================================");
    }
}
