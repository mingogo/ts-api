package com.mteng.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by mteng on 12/21/2015.
 */
public class TSRespContainer {
    @Setter @Getter private String count;
    @Setter @Getter private List<String> combinations;
}
