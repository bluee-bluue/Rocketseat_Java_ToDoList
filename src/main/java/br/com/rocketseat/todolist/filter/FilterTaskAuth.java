package br.com.rocketseat.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.rocketseat.todolist.user.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {
    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var servletPath = request.getServletPath();
        if (servletPath.startsWith("/tasks/")) {
            // pegar autenticação (user e senha)
            var autorizacao = request.getHeader("Authorization");
//        System.out.println(autorizacao);
            var authEncoded_user_senha = autorizacao.substring("Basic".length()).trim();
//        System.out.println(authEncoded_user_senha);
            byte[] authDecoded_user_senha = Base64.getDecoder().decode(authEncoded_user_senha);
//        System.out.println(authDecoded_user_senha);
            var authString_user_senha = new String(authDecoded_user_senha);
//        System.out.println(authString_user_senha);
            String[] credenciais = authString_user_senha.split(":");
            String username = credenciais[0];
            String senha = credenciais[1];

            // validar user
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                // validar senha
                var verificaSenha = BCrypt.verifyer().verify(senha.toCharArray(), user.getSenha());
                if (verificaSenha.verified) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
