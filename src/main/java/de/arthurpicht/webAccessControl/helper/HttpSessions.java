package de.arthurpicht.webAccessControl.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessions {

    public static HttpSession createSession(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getSession(true);
    }

    public static boolean hasRunningSession(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        return httpSession != null;
    }

    public static HttpSession getRunningSession(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession == null) throw new IllegalStateException("No running HttpSession.");
        return httpSession;
    }

    public static void rememberMe(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession(false).setMaxInactiveInterval(0);
    }

}
