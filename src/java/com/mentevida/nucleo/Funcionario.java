package com.mentevida.nucleo;

public class Funcionario extends Usuario {

    public Funcionario(int idUsuario, String nome, String senha, String telefone, String email, boolean isAdmin, String cargo) {
        super(idUsuario, nome, senha, telefone, email, isAdmin, cargo);
    }
    
    @Override
    public String toString() {
        return "[idFuncionario=" + getIdUsuario() +
                "nome=" + getNome() +
                "senha=" + getSenha() +
                "telefone=" + getTelefone() +
                "email=" + getEmail() +
                "isAdmin=" + isAdmin() +
                "cargo=" + getCargo() +
                "]";
    }
}
