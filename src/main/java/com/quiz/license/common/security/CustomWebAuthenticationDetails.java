package com.quiz.license.common.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Getter
@Setter
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private String accountType;
    private HttpSession session;
    private HttpServletRequest request;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        session = request.getSession();
        accountType = request.getParameter("accountType");
        this.request = request;
    }
}
