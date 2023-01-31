package ru.ambulatory.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Authorities {

    public static final String ADMIN = "ADMIN";
    public static final String CHIEF = "CHIEF";
    public static final String DOC = "DOC";
    public static final String REGISTER = "REGISTER";
    public static final String PHARMACY = "PHARMACY";


    public boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(ADMIN::equals);
    }

    public boolean isChief() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(CHIEF::equals);
    }

    public boolean isDoc() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(DOC::equals);
    }

    public boolean isRegister() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(REGISTER::equals);
    }

    public boolean isPharmacy() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(PHARMACY::equals);
    }
}
