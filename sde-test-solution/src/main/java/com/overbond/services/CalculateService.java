package com.overbond.services;

import com.overbond.entities.*;
import com.overbond.utilities.BondType;
import org.apache.commons.lang3.StringUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculateService {
    private static List<GenericBond> governmentBondList = new ArrayList<>();
    private static List<GenericBond> corporateBondsList = new ArrayList<>();

    private static void separateBondsBasedOnCategory(GenericBond[] genericBonds) {
        System.out.println("Separate the bonds by type");
        Stream.of(genericBonds).filter(CalculateService::checkDataSanity).forEach(bond -> {
            if(bond.getType().equalsIgnoreCase(BondType.GOVERNMENT.getStrValue())){
                governmentBondList.add(bond);
            } else {
                corporateBondsList.add(bond);
            }
        });
    }

    public static List<Spread> calculateSpread(GenericBond[] genericBonds) {
        separateBondsBasedOnCategory(genericBonds);
        System.out.println("Calculating the spread");

        List<Spread> spreadCollection = corporateBondsList.stream().map(corBond -> {
            Spread spread = new Spread();

            // initialize value
            Float selectedTenorGap = 9999f;

            /**
                Iterate through government bonds to check for
                1. closest tenor
                2. amount outstanding
             */

            for(GenericBond govBond : governmentBondList ) {
                float calculatedTenorGap = calculateTenorGap(corBond.getTenor(), govBond.getTenor());

                if (calculatedTenorGap < selectedTenorGap) { // find the closest tenor gap
                    spread.setCorporate_bond_id(corBond.getId());
                    spread.setGovernment_bond_id(govBond.getId());
                    spread.setSpread_to_benchmark(calculatedYield(corBond.getYield(), govBond.getYield()));
                    selectedTenorGap = calculatedTenorGap;
                } else if (calculatedTenorGap == selectedTenorGap) { // When equal check for the outstanding amount
                    if (govBond.getAmount_outstanding() > corBond.getAmount_outstanding()) {
                        spread.setCorporate_bond_id(corBond.getId());
                        spread.setGovernment_bond_id(govBond.getId());
                        spread.setSpread_to_benchmark(calculatedYield(corBond.getYield(), govBond.getYield()));
                        selectedTenorGap = calculatedTenorGap;
                    }
                }

            };

            return (Spread) spread;
        }).collect(Collectors.toList());


        return spreadCollection;
    }

    private static float calculateTenorGap(String corporate, String government)  {
        float result = 9999;
        if(StringUtils.isNoneBlank(corporate) && StringUtils.isNoneBlank(government)) {
            result = Math.abs(Float.valueOf(corporate.split(" ")[0]) - Float.valueOf(government.split(" ")[0]));

        } else {
            System.out.println("ERROR: Invalid values passed for Tenor Calculation");
        }

        return result;
    }


    private static String calculatedYield(String corporate, String government) {
        float result = 9999;
        if(StringUtils.isNoneBlank(corporate) && StringUtils.isNoneBlank(government)) {
            result = Math.abs(Float.valueOf(corporate.split("%")[0]) - Float.valueOf(government.split("%")[0]));

        } else {
            System.out.println("ERROR: Invalid values passed calculation of yield.");
        }
        return (int)(result * 100) + " bps";
    }

    //TODO: Handle a few cases here
    private static boolean checkDataSanity(GenericBond bond) {
        if( StringUtils.isNoneEmpty(bond.getId()) && StringUtils.isNoneEmpty(bond.getTenor()) &&
                StringUtils.isNoneEmpty(bond.getType()) && StringUtils.isNoneEmpty(bond.getYield()) &&
                StringUtils.isNoneEmpty(String.valueOf(bond.getAmount_outstanding()))) {
            return true;
        } else {
            return false;
        }
    }
}
