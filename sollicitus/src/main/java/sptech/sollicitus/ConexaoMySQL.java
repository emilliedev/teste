/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sptech.sollicitus;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author sctavares1
 */
public class ConexaoMySQL {
       private JdbcTemplate conexaoDoBanco;

  public ConexaoMySQL() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
// exemplo para MySql: "com.mysql.cj.jdbc.Driver"
dataSource.setUrl("jdbc:mysql://localhost:3306/sollicitus");
// exemplo para MySql: "jdbc:mysql://localhost:3306/meubanco"
dataSource.setUsername("root");
dataSource.setPassword("urubu100");
    this.conexaoDoBanco = new JdbcTemplate(dataSource);
  }

  public JdbcTemplate getConexaoDoBanco() {
    return conexaoDoBanco;
  }
}
