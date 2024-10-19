package com.moritz.process;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class ProcessManager {

    public void showAllProcesses() {
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem os = systemInfo.getOperatingSystem();
        int logicalProcessorCount = systemInfo.getHardware().getProcessor().getLogicalProcessorCount();

        List<OSProcess> processes = os.getProcesses(
            null, 
            Comparator.comparingDouble(OSProcess::getProcessCpuLoadCumulative).reversed(), 
            0
        );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (OSProcess process : processes) {
            if (process.getProcessID() == 1 || process.getProcessID() == 0){
                continue;
            }
            if (!process.getName().equals("chrome")){
                continue;
            }
            double cpuPercent = (process.getProcessCpuLoadCumulative() * 100) / logicalProcessorCount;
            double rssInMB = process.getResidentSetSize() / (1024.0 * 1024.0);
            System.out.printf("Prozess-ID: %d%n", process.getProcessID());
            System.out.printf("Name: %s%n", process.getName());
            System.out.printf("CPU-Auslastung: %.2f%%%n", cpuPercent);
            System.out.printf("RSS: %.2f MB%n", rssInMB);
            System.out.printf("Startzeit: %s%n", sdf.format(new Date(process.getStartTime())));
            System.out.println("--------------------------------");

    }
}
}