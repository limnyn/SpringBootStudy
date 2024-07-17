package com.naver.shopping;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;


@Configuration
@ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class), // 다른 Configurateion 클래스는 제외
        includeFilters = @Filter(type = FilterType.ANNOTATION, classes = Service.class) // Service 어노테이션이 붙은 클래스만 스캔
)
public class AutoAppConfig {
}
