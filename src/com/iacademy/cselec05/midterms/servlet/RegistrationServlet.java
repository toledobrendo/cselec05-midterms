package com.iacademy.cselec05.midterms.servlet;

import com.iacademy.cselec05.midterms.factory.ObjectFactory;
import com.iacademy.cselec05.midterms.model.User;
import com.iacademy.cselec05.midterms.repository.UserRepository;
import com.iacademy.cselec05.midterms.service.CipherService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet {

    private UserRepository userRepository;
    private CipherService cipherService;

    public RegistrationServlet() {
        userRepository = ObjectFactory.getUserRepository();
        cipherService = ObjectFactory.getCipherService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (username == null || username.isEmpty()) {
                throw new RuntimeException("Username must not be empty");
            }

            if (password == null || password.isEmpty()) {
                throw new RuntimeException("Password must not be empty");
            }

            User existingUser = userRepository.findByUsername(username);
            if (existingUser != null) {
                throw new RuntimeException("User name " + username + " already exists");
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(cipherService.encrypt(password));
            userRepository.save(user);

            req.setAttribute("success", true);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (RuntimeException e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/registration-form.jsp").forward(req, resp);
        }
    }
}
