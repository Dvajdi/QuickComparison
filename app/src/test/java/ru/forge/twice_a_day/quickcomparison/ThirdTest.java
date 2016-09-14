package ru.forge.twice_a_day.quickcomparison;
import junit.framework.Assert;

import org.junit.Test;

import ru.forge.twice_a_day.quickcomparison.models.work_with_units.Unit;

public class ThirdTest {
    @Test
    public void outPredel(){
        Unit u = new Unit("unit","u",1-0.009,true,false);
        if((u.getValue()<0.001)&(u.getValue()>0)){
            Assert.assertTrue("pupsiki",true);}else{ Assert.assertTrue(false);}
    }

    @Test
    public void inPredel(){
        Unit u = new Unit("unit","u",1-0.009,true,false);
        if((u.getValue()<0.001)&(u.getValue()>0)){
            Assert.assertFalse("pupsiki",true);}else{ Assert.assertFalse(false);}
    }
}
