package com.app.demo;

import com.app.demo.loadproperty.Class1;
import com.app.demo.loadproperty.Class2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ApplicationApp implements CommandLineRunner {

    @Autowired
    public Class2 class2;

    @Autowired
    public Class1 class1;

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(ApplicationApp.class, args);
        log.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        log.info("EXECUTING : command line runner");

        for (int i = 0; i < args.length; ++i) {
            log.info("args[{}]: {}", i, args[i]);
        }

        class1.strProcess();
        class2.strProcess();

        class1.strInterfaceProcess();
        class2.strInterfaceProcess();
    }
}
