package com.delivery.delivery.entity.enums;

public enum TipoUsuario {
    CLIENTE(1),
    ENTREGADOR(2),
    FORNECEDOR(3);

    private final int codigo;

    TipoUsuario(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoUsuario fromCodigo(int codigo) {
        for (TipoUsuario tipo : values()) {
            if (tipo.codigo == codigo) return tipo;
        }
        throw new IllegalArgumentException("Tipo de usuário inválido: " + codigo);
    }
}
