package com.mentevida.nucleo;

public abstract class Usuario {
    private int idUsuario;
    private String nome;
    private String senha;
    private String telefone;
    private String email;
    private boolean isAdmin;
    private String cargo;

    public Usuario(int idUsuario, String nome, String senha, String telefone, String email, boolean isAdmin, String cargo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.isAdmin = isAdmin;
        this.cargo = cargo;
    }
    
    public abstract String toString();

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
