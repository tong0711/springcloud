package com.landmaster.springboot;

import java.lang.management.ManagementFactory;
//import java.lang.management.OperatingSystemMXBean;
//import sun.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

/**
 * Created by tdl on 2017/5/12.
 */
public class MonitorTest {
    public static  void main(String[] args){
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        // 操作系统
        String osName = System.getProperty("os.name");
        int kb = 1024;
        // 总的物理内存
        long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;
        // 剩余的物理内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / kb;
        // 已使用的物理内存
        long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb
                .getFreePhysicalMemorySize())
                / kb;
        System.out.println("================== use"+usedMemory);
    }
}
