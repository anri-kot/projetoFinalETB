
package com.mentevida.nucleo;

public class Medico extends Usuario {
    private String especialidade;

    public Medico(String especialidade, int idUsuario, String nome, String senha, String telefone, String email, boolean isAdmin, String cargo) {
        super(idUsuario, nome, senha, telefone, email, isAdmin, cargo);
        this.especialidade = especialidade;
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
                "especialidade=" + getEspecialidade() +
                "]";
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
    
}
