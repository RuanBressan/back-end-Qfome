package com.delivery.delivery.controller;

import com.delivery.delivery.dto.auth.AuthFornecedorRegister;
import com.delivery.delivery.dto.auth.AuthRequest;
import com.delivery.delivery.dto.auth.AuthResponse;
import com.delivery.delivery.entity.FornecedorEntity;
import com.delivery.delivery.entity.enums.TipoUsuario;
import com.delivery.delivery.security.AppDetalhesFornecedorService;
import com.delivery.delivery.security.JwtUtil;
import com.delivery.delivery.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedor/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthFornecedorController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final AppDetalhesFornecedorService appDetalhesFornecedorService;
    private final FornecedorService fornecedorService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(request.getDsEmail(), request.getDsSenha());

        authManager.authenticate(authInputToken);

        UserDetails user = appDetalhesFornecedorService.loadUserByUsername(request.getDsEmail());
        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody AuthFornecedorRegister request) {
        FornecedorEntity fornecedor = new FornecedorEntity();
        fornecedor.setNmUsuario(request.getNmUsuario());
        fornecedor.setDsEmail(request.getDsEmail());
        fornecedor.setDsSenha(request.getDsSenha());
        fornecedor.setNuCnpj(request.getNuCnpj());
        fornecedor.setDtNascimento(request.getDtNascimento());
        fornecedor.setDsTelefone(request.getDsTelefone());
        fornecedor.setNuLatitude(request.getNuLatitude());
        fornecedor.setNuLongitude(request.getNuLongitude());
        fornecedor.setFlTipoUsuario(TipoUsuario.FORNECEDOR);
        FornecedorEntity novoFornecedor = fornecedorService.criarFornecedor(fornecedor);

        String token = jwtUtil.generateToken(novoFornecedor.getDsEmail());

        return new AuthResponse(token);
    }
}
