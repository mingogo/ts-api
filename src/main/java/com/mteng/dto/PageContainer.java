package com.mteng.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mteng on 12/21/2015.
 */
public class PageContainer {
    @Getter @Setter private String totalPageNumber;
    @Getter @Setter private String nextPage;
    @Getter @Setter private String previousPage;
    @Getter @Setter private String lastPage;
}
