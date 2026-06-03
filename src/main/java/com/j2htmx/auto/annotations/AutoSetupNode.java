package com.j2htmx.auto.annotations;


import com.j2htmx.auto.config.NodeAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(NodeAutoConfiguration.class)
public @interface AutoSetupNode {
}
