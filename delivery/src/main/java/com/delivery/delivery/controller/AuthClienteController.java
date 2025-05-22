package com.delivery.delivery.controller;

import com.delivery.delivery.dto.auth.AuthRequest;
import com.delivery.delivery.dto.auth.AuthResponse;
import com.delivery.delivery.dto.auth.AuthClienteRegister;
import com.delivery.delivery.entity.ClienteEntity;
import com.delivery.delivery.entity.enums.TipoUsuario;
import com.delivery.delivery.security.AppDetalhesClienteService;
import com.delivery.delivery.security.JwtUtil;
import com.delivery.delivery.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthClienteController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final AppDetalhesClienteService appDetalhesClienteService;
    private final ClienteService clienteService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(request.getDsEmail(), request.getDsSenha());

        authManager.authenticate(authInputToken);

        UserDetails user = appDetalhesClienteService.loadUserByUsername(request.getDsEmail());
        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody AuthClienteRegister request) {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNmUsuario(request.getNmUsuario());
        cliente.setDsEmail(request.getDsEmail());
        cliente.setDsSenha(request.getDsSenha());
        cliente.setNuCpf(request.getNuCpf());
        cliente.setDtNascimento(request.getDtNascimento());
        cliente.setDsTelefone(request.getDsTelefone());
        cliente.setNuLatitude(request.getNuLatitude());
        cliente.setNuLongitude(request.getNuLongitude());
        cliente.setFlTipoUsuario(TipoUsuario.CLIENTE);
        ClienteEntity novoCliente = clienteService.criarCliente(cliente);

        String token = jwtUtil.generateToken(novoCliente.getDsEmail());

        return new AuthResponse(token);
    }
}
