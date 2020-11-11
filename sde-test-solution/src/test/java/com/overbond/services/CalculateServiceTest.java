package com.overbond.services;

import com.overbond.entities.GenericBond;
import com.overbond.entities.Spread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.List;

public class CalculateServiceTest {
    private static Logger logger = LoggerFactory.getLogger(CalculateServiceTest.class);
    GenericBond[] genericBonds = new GenericBond[4];

    public void setupInit() {
        System.out.println("Initialize the setup");
        genericBonds[0] = createGenericBond("c1", "corporate", "10.3 years", "5.30%", 1200000 );
        genericBonds[1] = createGenericBond("g1", "government", "9.4 years", "3.70%", 2500000 );
        genericBonds[2] = createGenericBond("c2", "corporate", "13.5 years", "6.70%", 1100000 );
        genericBonds[3] = createGenericBond("g2", "government", "12.0 years", "4.80%", 1750000 );

    }

    @Test
    public void calculateSpreadTestCleaned()  {
        setupInit();
        // Test with the sample
        List<Spread> spreads = CalculateService.calculateSpread(genericBonds);
        System.out.println(spreads.size());

        // Test for cleaned output, check if null is cleaned
        Assert.assertEquals(spreads.size(), 5);

    }

    @Test
    public void calculateSpreadTest()  {
        setupInit();
        genericBonds[2] = createGenericBond("c2", "corporate", "13.5 years", null, 1750000 );
        // Test with the sample
        List<Spread> spreads = CalculateService.calculateSpread(genericBonds);
        System.out.println(spreads.size());

        // Test for cleaned output, check if null is cleaned
        Assert.assertEquals(spreads.size(), 3);

    }

    @Test
    public void calculateSpreadEqualTenorGap()  {
        setupInit();
        genericBonds[3] = createGenericBond("g2", "government", "9.4 years", "6.70%", 1100000 );
        // Test with the sample
        List<Spread> spreads = CalculateService.calculateSpread(genericBonds);
        Spread secondSpread = spreads.get(1);

        // Test for cleaned output, check if null is cleaned
        Assert.assertEquals(secondSpread.getGovernment_bond_id(), "g1");

    }

    private GenericBond createGenericBond(String id, String type, String tenor, String yield, Integer amount_outstanding) {
        GenericBond genericBond = new GenericBond();
        genericBond.setId(id);
        genericBond.setType(type);
        genericBond.setTenor(tenor);
        genericBond.setYield(yield);
        genericBond.setAmount_outstanding(amount_outstanding);
        return genericBond;
    }
}
