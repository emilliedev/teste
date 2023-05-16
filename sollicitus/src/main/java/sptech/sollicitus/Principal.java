/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sptech.sollicitus;

import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Silvio Tavares
 */
public class Principal {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        String opcao = null;
        Boolean ficarNoMenu = true;
        while (ficarNoMenu) {
            System.out.println("Olá, você deseja executar a aplicação com interface gráfica ou através do terminal?"
                    + "\nDigite i para interface gráfica ou t para terminal:");
            opcao = leitor.nextLine();

            if (opcao.equalsIgnoreCase("i") || opcao.equalsIgnoreCase("t")) {
                ficarNoMenu = false;
            } else {
                System.out.println("Opção inválida");
            }
        }

        if (opcao.equalsIgnoreCase("i")) {
            TelaLogin login = new TelaLogin();
            login.setVisible(true);
        } else {
            SollicitusCollector collector = new SollicitusCollector();
            Conexao conexao = new Conexao();
            JdbcTemplate con = conexao.getConexaoDoBanco();

            Boolean logou = false;

            while (!logou) {
                System.out.println("Informe o e-mail de acesso:");
                String email = leitor.nextLine();

                System.out.println("Informe a senha:");
                String senha = leitor.nextLine();

                List<Usuario> Usuario = con.query("SELECT * FROM Usuario WHERE email = ? and senha = ?",
                        new BeanPropertyRowMapper<>(Usuario.class), email, senha);

                //william.nicolau@sptech.school
                if (Usuario.size() > 0) {
                    System.out.println("A coleta dos dados foi iniciada");
                    collector.inserirDados();
                    logou = true;
                } else {
                    System.out.println("Email ou senha incorretos");
                }
            }
        }
    }
}
