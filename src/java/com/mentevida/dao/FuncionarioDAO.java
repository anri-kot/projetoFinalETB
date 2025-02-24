package com.mentevida.dao;

import com.mentevida.nucleo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private final Connection con;
    private UsuarioDAO usu;
    
    public FuncionarioDAO() throws Exception {
        con = ConnectionManager.getConnection();
        usu = new UsuarioDAO();
    }
    
    /* -- SQL Operations -- */
    
    // Select
    
    public List<Funcionario> mostrarTodosFuncionarios() throws Exception {
        List<Funcionario> list = new ArrayList<>();
        
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("select * from funcionario");
            rs = st.executeQuery();
            
            // loop adiciona o objeto FuncionarioDAO numa lista
            while (rs.next()) {
                Funcionario tempFuncionario = rowToFuncionario(rs);
                list.add(tempFuncionario);
            }
            return list;
            
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public List<Funcionario> mostrarIdFuncionario(int id) throws Exception {
        List<Funcionario> list = new ArrayList<>();
        
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("select * from funcionario where id_funcionario = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            
            // loop adiciona o objeto FuncionarioDAO numa lista
            while (rs.next()) {
                Funcionario tempFuncionario = rowToFuncionario(rs);
                list.add(tempFuncionario);
            }
            return list;
            
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public List<Funcionario> mostrarUserIdFuncionario(int id) throws Exception {
        List<Funcionario> list = new ArrayList<>();
        
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("select * from funcionario where idUsuario = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            
            // loop adiciona o objeto FuncionarioDAO numa lista
            while (rs.next()) {
                Funcionario tempFuncionario = rowToFuncionario(rs);
                list.add(tempFuncionario);
            }
            return list;
            
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public List<Funcionario> mostrarNomeFuncionarios(String nome) throws Exception {
        List<Funcionario> list = new ArrayList<>();
        
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("select * from funcionario where nome like ?");
            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();
            
            // loop adiciona o objeto Funcionario numa lista
            while (rs.next()) {
                Funcionario tempFuncionario = rowToFuncionario(rs);
                list.add(tempFuncionario);
            }
            return list;
            
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public List<Funcionario> pesquisarFuncionarios(String pesquisa) throws Exception {
        List<Funcionario> list = new ArrayList<>();
        
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("select * from funcionario where nome LIKE ? OR cpf LIKE ?");
            st.setString(1, "%" + pesquisa + "%");
            st.setString(2, "%" + pesquisa + "%");
            rs = st.executeQuery();
            
            // loop adiciona o objeto Funcionario numa lista
            while (rs.next()) {
                Funcionario tempFuncionario = rowToFuncionario(rs);
                list.add(tempFuncionario);
            }
            return list;
            
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public void cadastrarFuncionario(Funcionario oFuncionario) throws Exception {
        PreparedStatement st = null;
        try {
            /*
            // Caso queira criar um novo usuário justo ao perfil do médico
            if (oFuncionario.getIdUsuario() <= 0) {
                // Gerar usuário do médico
                String username = oFuncionario.getUsername();
                String senha = oFuncionario.getSenha();
                String admin = Boolean.toString(oFuncionario.isAdmin());
                String[] usuario = {"", username, senha, admin}; // [idUsuario, username, senha, admin] -- admin = "true" / "false", senha não criptografada
                usu.cadastrarUsuario(usuario);
            }
            */
            
            st = con.prepareStatement("insert into funcionario (nome, telefone, email, cargo, idUsuario) values (?, ?, ?, ?, ?)");
            st.setString(1, oFuncionario.getNome());
            st.setString(2, oFuncionario.getTelefone());
            st.setString(3, oFuncionario.getCpf());
            st.setString(4, oFuncionario.getEmail());
            st.setString(5, oFuncionario.getCargo());
            if (oFuncionario.getIdUsuario() != 0) {
                st.setInt(6, oFuncionario.getIdUsuario());
            } else {
                st.setNull(6, 0);
            }
            
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
    
    public void alterarFuncionario(Funcionario oFuncionario) throws Exception {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("update funcionario set nome = ?, telefone = ?, cpf = ?, email = ?, cargo = ?, idUsuario = ? where id_funcionario = ?");
            st.setString(1, oFuncionario.getNome());
            st.setString(2, oFuncionario.getTelefone());
            st.setString(4, oFuncionario.getTelefone());
            st.setString(4, oFuncionario.getEmail());
            st.setString(5, oFuncionario.getCargo());
            if (oFuncionario.getIdUsuario() > 0) {
                st.setInt(6, oFuncionario.getIdUsuario());
            } else { 
                st.setNull(6, 0);
            }
            st.setInt(7, oFuncionario.getIdFuncionario());
            
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
    
    public void deletarFuncionario(int id) throws Exception {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("delete from funcionario where id_funcionario = ?");
            st.setInt(1, id);
            
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    private Funcionario rowToFuncionario(ResultSet rs) throws Exception {
        int idUsuario = 0;
        String username = null;
        String senha = null;
        Boolean admin = false;
        
        if (rs.getString("idUsuario") != null) {
            idUsuario = rs.getInt("idUsuario");
            String[] usuario = usu.mostrarIdUsuario(idUsuario).get(0); // [id, username, senha, admin]
            username = usuario[1];
            senha = usuario[2];
            admin = Boolean.getBoolean(usuario[3]);
        }
        
        int idFuncionario = rs.getInt("id_funcionario");
        String nome = rs.getString("nome");
        String telefone = rs.getString("telefone");
        String cpf = rs.getString("cpf");
        String email = rs.getString("email");
        String cargo = rs.getString("cargo");
        
        return new Funcionario(idFuncionario, nome, telefone, cpf, email, cargo, idUsuario, username, senha, admin);
    }
}
