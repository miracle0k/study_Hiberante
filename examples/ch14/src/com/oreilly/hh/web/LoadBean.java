package com.oreilly.hh.web;

import java.lang.annotation.*;
/**
 * An annotation used to mark methods with a bean to load.
 *
 * @author  Ryan Fowler
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LoadBean {
    /**
     * The name of the bean to load. 
     */
    String value();
}
