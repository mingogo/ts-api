package com.mteng.dto;

import lombok.Getter;
import lombok.Setter;

public class PageDto {
    @Getter @Setter private String totalPageNumber;
    @Getter @Setter private String nextPage;
    @Getter @Setter private String previousPage;
    @Getter @Setter private String lastPage;
}