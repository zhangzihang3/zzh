package com.zzh.controller;

import com.zzh.util.pluginFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pluginController {
    @Autowired
    pluginFactory pluginFactory;
    @RequestMapping("/test/{id}")
    public String test(@PathVariable("id") String id) {
        return "testsuccess";
    }
    @RequestMapping("/plugin/active/{id}")
    public String testadd(@PathVariable("id") String id) {
        pluginFactory.activePlugin(id);
        return "activesuccess";
    }
    @RequestMapping("/plugin/remove/{id}")
    public String testremove(@PathVariable("id") String id) {
        pluginFactory.removePlugin(id);
        return "removesuccess";
    }
}
