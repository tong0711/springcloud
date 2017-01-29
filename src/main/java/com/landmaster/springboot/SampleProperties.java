package com.landmaster.springboot;

/**
 * Created by tdl on 2017/1/28.
 */
import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Spencer Gibb
 */
@ConfigurationProperties("sample")
@Data
public class SampleProperties {

    private String prop = "default value";
}
