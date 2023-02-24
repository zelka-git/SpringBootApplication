package com.app.demo.loadproperty;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Class2 extends Common{

    @Getter
    @Value("${string2}")
    private String property;

    @Autowired
    public void setStr(@Value("${string2}") String str){
        this.str = str;
    }
}
