// src/main/java/com/example/psk/JSFConfig.java
package com.example.psk;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;

/**
 * Activates CDI integration in JSF 2.3.
 */
@FacesConfig               // from javax.faces.annotation
@ApplicationScoped         // from javax.enterprise.context
public class JSFConfig { }
