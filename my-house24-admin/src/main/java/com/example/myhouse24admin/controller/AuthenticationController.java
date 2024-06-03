package com.example.myhouse24admin.controller;

import com.example.myhouse24admin.model.authentication.EmailRequest;
import com.example.myhouse24admin.model.authentication.ForgotPasswordRequest;
import com.example.myhouse24admin.service.MailService;
import com.example.myhouse24admin.service.PasswordResetTokenService;
import com.example.myhouse24admin.service.RoleService;
import com.example.myhouse24admin.service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {
    private final PasswordResetTokenService passwordResetTokenService;
    private final MailService mailService;
    private final RoleService roleService;

    public AuthenticationController(PasswordResetTokenService passwordResetTokenService,
                                    MailService mailService, RoleService roleService) {
        this.passwordResetTokenService = passwordResetTokenService;
        this.mailService = mailService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return new ModelAndView("security/login");
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            String uri = roleService.getAllowedEndPoint(email);
            return new ModelAndView("redirect:"+uri);
        }
    }

    @GetMapping("/forgotPassword")
    public ModelAndView getForgotPasswordPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return new ModelAndView("security/forgotPassword");
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            String uri = roleService.getAllowedEndPoint(email);
            return new ModelAndView("redirect:"+uri);
        }
    }

    @PostMapping("/forgotPassword")
    public @ResponseBody ResponseEntity<?> sendPasswordResetToken(@Valid EmailRequest emailRequest,
                                                                  HttpServletRequest httpServletRequest) {
        String token = passwordResetTokenService.createOrUpdatePasswordResetToken(emailRequest);
        mailService.sendToken(token,emailRequest, String.valueOf(httpServletRequest.getRequestURL()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/sentToken")
    public ModelAndView getSentTokenPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return new ModelAndView("security/sentToken");
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            String uri = roleService.getAllowedEndPoint(email);
            return new ModelAndView("redirect:"+uri);
        }
    }
    @GetMapping("/changePassword")
    public ModelAndView changePassword(@RequestParam("token")String token){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            if (passwordResetTokenService.isPasswordResetTokenValid(token)) {
                ModelAndView modelAndView = new ModelAndView("security/changePassword");
                modelAndView.addObject("token", token);
                return modelAndView;
            } else {
                return new ModelAndView("security/tokenExpired");
            }
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            String uri = roleService.getAllowedEndPoint(email);
            return new ModelAndView("redirect:"+uri);
        }
    }

    @PostMapping("/changePassword")
    public @ResponseBody ResponseEntity<?> setNewPassword(@RequestParam("token")String token,@Valid @ModelAttribute ForgotPasswordRequest forgotPasswordRequest){
        if(passwordResetTokenService.isPasswordResetTokenValid(token)){
            passwordResetTokenService.updatePassword(token, forgotPasswordRequest.password());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/success")
    public ModelAndView getSuccessPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return new ModelAndView("security/success");
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            String uri = roleService.getAllowedEndPoint(email);
            return new ModelAndView("redirect:"+uri);
        }
    }
    @GetMapping("/tokenExpired")
    public ModelAndView getTokenExpiredPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return new ModelAndView("security/tokenExpired");
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            String uri = roleService.getAllowedEndPoint(email);
            return new ModelAndView("redirect:"+uri);
        }
    }


}
