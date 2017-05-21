package de.fau.amos.virtualledger.server.banking.api;

/**
 * Created by Georg on 18.05.2017.
 */

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Annotation for dummies of the banking api.
 */
@Qualifier @Retention(RUNTIME) @Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface BankingApiDummy {
}