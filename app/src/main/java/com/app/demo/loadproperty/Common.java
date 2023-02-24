package com.app.demo.loadproperty;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Common implements Property {
    protected String str;

    public void strProcess() {
        log.info("Field");
        log.info(str);
    }

    public void strInterfaceProcess() {
        log.info("Interface");
        log.info(getProperty());
    }

}
