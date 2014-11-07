package com.smart.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <br><b>类描述:</b>
 * <pre>所示PO的父类</pre>
 *
 * @see
 */
public class BaseDomain implements Serializable {
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}