package com.zzh.service.impl;

import com.zzh.service.aspects.log.SysLog;
import com.zzh.service.pointCut;
import org.springframework.stereotype.Service;

@Service
public class pointCutImpl implements pointCut {
    @Override
    @SysLog(description = "test 注解")
    public Boolean say() {
        System.out.println("zzh说");
        return true;
    }


}
