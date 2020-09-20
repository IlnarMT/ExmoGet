package org.example.service;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;
import org.example.model.NTPClient;

import java.io.IOException;
import java.net.InetAddress;

public class NTPService {
    private static final String SERVER_NAME = "pool.ntp.org";
    NTPUDPClient client;
    InetAddress inetAddress;

    public NTPService() throws IOException {

        client = new NTPUDPClient();
        // We want to timeout if a response takes longer than 10 seconds
        client.setDefaultTimeout(10_000);

        inetAddress = InetAddress.getByName(SERVER_NAME);
    }

    public long getUnixTime() throws IOException {
        TimeInfo timeInfo = client.getTime(inetAddress);
        timeInfo.computeDetails();
        NTPClient ntpClient = new NTPClient();
        if (timeInfo.getOffset() != null) {
            ntpClient.timeInfo = timeInfo;
            ntpClient.offset = timeInfo.getOffset();
        }

        // This system NTP time
        TimeStamp systemNtpTime = TimeStamp.getCurrentTime();
        //System.out.println("System time:\t" + systemNtpTime + "  " + systemNtpTime.toDateString());

        // Calculate the remote server NTP time
        long currentTime = System.currentTimeMillis();
        System.out.println("currentTime= "+currentTime);
        long atomicNtpTime = TimeStamp.getNtpTime(currentTime + ntpClient.offset).getTime();
        //TimeStamp atomicNtpTime2 = TimeStamp.getNtpTime(currentTime + ntpClient.offset);
        long unixTime=Math.round((double) atomicNtpTime / 1000);

        System.out.println("Atomic time:\t" + atomicNtpTime + " currentUnixTime  " + unixTime+ " "+ TimeStamp.getNtpTime(currentTime + ntpClient.offset).toDateString());
        //System.out.println("atomicNtpTime2 " + atomicNtpTime2.toDateString());
        return unixTime;
    }

    public long getUnixTimeOffset() throws IOException {
        TimeInfo timeInfo = client.getTime(inetAddress);
        timeInfo.computeDetails();
        NTPClient ntpClient = new NTPClient();
        if (timeInfo.getOffset() != null) {
            ntpClient.timeInfo = timeInfo;
            ntpClient.offset = timeInfo.getOffset();
        }
        long unixTimeOffset=Math.round((double)ntpClient.offset / 1000);
        System.out.println("unixTimeOffset " + unixTimeOffset);
        return unixTimeOffset;
    }

    public long getOffsetTimeMilli() throws IOException {
        TimeInfo timeInfo = client.getTime(inetAddress);
        timeInfo.computeDetails();
        NTPClient ntpClient = new NTPClient();
        if (timeInfo.getOffset() != null) {
            ntpClient.timeInfo = timeInfo;
            ntpClient.offset = timeInfo.getOffset();
        }
        long offsetTimeMilli=ntpClient.offset;
        System.out.println("offsetTimeMilli " + offsetTimeMilli);
        return offsetTimeMilli;
    }
}
