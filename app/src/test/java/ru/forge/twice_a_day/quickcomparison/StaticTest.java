package ru.forge.twice_a_day.quickcomparison;

import org.junit.Assert;
import org.junit.Test;

import ru.forge.twice_a_day.quickcomparison.util.FormatAdapter;

/**
 * Created by Pavel M. on 15.06.16.
 */
public class StaticTest {
    @Test
    public void testFormatAdapter(){
        int i =FormatAdapter.getKoef("0.001");
        Assert.assertTrue(i==3);
    }
    @Test
    public void antitestFormatAdapter(){
        int i =FormatAdapter.getKoef("0.01");
        Assert.assertTrue(i==2);
    }
}
