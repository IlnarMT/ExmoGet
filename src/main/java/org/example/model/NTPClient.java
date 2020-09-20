package org.example.model;

import org.apache.commons.net.ntp.TimeInfo;

public class NTPClient {
    public volatile TimeInfo timeInfo;
    public volatile Long offset;

}
