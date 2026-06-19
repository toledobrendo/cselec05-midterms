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

public class LoginServlet extends HttpServlet {

    private UserRepository userRepository;
    private CipherService cipherService;

    public LoginServlet() {
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

            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new RuntimeException("Invalid Credentials");
            }

            if (!user.getPassword().equals(cipherService.encrypt(password))) {
                throw new RuntimeException("Invalid Credentials");
            }

            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/WEB-INF/views/").forward(req, resp);
        } catch (RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
