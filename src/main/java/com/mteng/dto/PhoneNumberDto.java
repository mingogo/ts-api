package com.mteng.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PhoneNumberDto {
    @Setter @Getter private String count;
    @Setter @Getter private List<String> combinations;
    @Setter @Getter private PageDto pagination;
}