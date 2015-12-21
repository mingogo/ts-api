package com.mteng.service.impl;

import com.mteng.dto.TSRespContainer;
import com.mteng.service.TelephoneService;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.LinkedList;
import java.util.List;

@Service
public class TelephoneServiceImpl implements TelephoneService {

    public List<String> getPhoneCombination(String phoneNumber) {
        List<String> combos = new LinkedList<>();
        generateCombosHelper(combos, "", phoneNumber);
        return combos;
    }

    @Override
    public TSRespContainer getResp(String phoneNumber) {
        TSRespContainer tsRespContainer = new TSRespContainer();
        List list = getPhoneCombination(phoneNumber);
        tsRespContainer.setCount(String.valueOf(list.size()));
        tsRespContainer.setCombinations(list);
        return tsRespContainer;
    }

    private void generateCombosHelper(List<String> combos, String prefix, String remaining) {
        int digit = Integer.parseInt(remaining.substring(0, 1));

        if (remaining.length() == 1) {
            for (int i = 0; i < mappings[digit].length; i++) {
                combos.add(prefix + mappings[digit][i]);
            }
        } else {
            for (int i = 0; i < mappings[digit].length; i++) {
                generateCombosHelper(
                        combos,
                        prefix + mappings[digit][i],
                        remaining.substring(1)
                );
            }
        }
    }

    private String mappings[][] = {
            {"0"},
            {"1"},
            {"A", "B", "C"},
            {"D", "E", "F"},
            {"G", "H", "I"},
            {"J", "K", "L"},
            {"M", "N", "O"},
            {"P", "Q", "R", "S"},
            {"T", "U", "V"},
            {"W", "X", "Y", "Z"}
    };
}
