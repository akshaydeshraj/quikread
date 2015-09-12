package com.katana.quikread;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}

