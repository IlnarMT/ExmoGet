package org.example;

import static org.junit.Assert.assertTrue;

import org.example.service.NTPService;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testNTPTime_getUnixTime() throws IOException {
        NTPService ntpService =new NTPService();
        ntpService.getUnixTime();
        assertTrue( true );
    }

    @Test
    public void testNTPTime_getUnixTimeOffset() throws IOException {
        NTPService ntpService =new NTPService();
        ntpService.getUnixTimeOffset();
        assertTrue( true );
    }
}
