/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sptech.sollicitus;

/**
 *
 * @author sctavares1
 */
public class Usuario {
    private Integer idUsuario;
    private String nome;
    private String senha;
    private String email;
    private String cargo;
    private Integer fkEmpressa;
    private Integer fkGerente;

    public Usuario(){
    
    }
    
    public Usuario(Integer idUsuario, String nome, String senha, String email, String cargo, Integer fkEmpressa, Integer fkGerente) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cargo = cargo;
        this.fkEmpressa = fkEmpressa;
        this.fkGerente = fkGerente;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getFkEmpressa() {
        return fkEmpressa;
    }

    public void setFkEmpressa(Integer fkEmpressa) {
        this.fkEmpressa = fkEmpressa;
    }

    public Integer getFkGerente() {
        return fkGerente;
    }

    public void setFkGerente(Integer fkGerente) {
        this.fkGerente = fkGerente;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nome=" + nome + ", senha=" + senha + ", email=" + email + ", cargo=" + cargo + ", fkEmpressa=" + fkEmpressa + ", fkGerente=" + fkGerente + '}';
    }
}
