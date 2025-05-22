package com.delivery.delivery.controller;

import com.delivery.delivery.dto.auth.AuthEntregadorRegister;
import com.delivery.delivery.dto.auth.AuthRequest;
import com.delivery.delivery.dto.auth.AuthResponse;
import com.delivery.delivery.entity.EntregadorEntity;
import com.delivery.delivery.entity.enums.TipoUsuario;
import com.delivery.delivery.security.AppDetalhesEntregadorService;
import com.delivery.delivery.security.JwtUtil;
import com.delivery.delivery.service.EntregadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entregador/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthEntregadorController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final AppDetalhesEntregadorService appDetalhesEntregadorService;
    private final EntregadorService entregadorService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(request.getDsEmail(), request.getDsSenha());

        authManager.authenticate(authInputToken);

        UserDetails user = appDetalhesEntregadorService.loadUserByUsername(request.getDsEmail());
        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody AuthEntregadorRegister request) {
        EntregadorEntity entregador = new EntregadorEntity();
        entregador.setNmUsuario(request.getNmUsuario());
        entregador.setDsEmail(request.getDsEmail());
        entregador.setDsSenha(request.getDsSenha());
        entregador.setNuCpf(request.getNuCpf());
        entregador.setDtNascimento(request.getDtNascimento());
        entregador.setDsTelefone(request.getDsTelefone());
        entregador.setNuLatitude(request.getNuLatitude());
        entregador.setNuLongitude(request.getNuLongitude());
        entregador.setDsNumeroCnh(request.getDsNumeroCnh());
        entregador.setDsPlacaVeiculo(request.getDsPlacaVeiculo());
        entregador.setFlTipoUsuario(TipoUsuario.ENTREGADOR);
        EntregadorEntity novoEntregador = entregadorService.criarEntregador(entregador);

        String token = jwtUtil.generateToken(novoEntregador.getDsEmail());

        return new AuthResponse(token);
    }

}
