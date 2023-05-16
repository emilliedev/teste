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
public class Conexao {
    private JdbcTemplate conexaoDoBanco;

  public Conexao() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    dataSource.setUrl("jdbc:sqlserver://svr-sollicitus.database.windows.net:1433;database=bd-sollicitus;encryp\n" +
"t=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net");
    dataSource.setUsername("admSollicitus");
    dataSource.setPassword("#Gfgrupo5");
    
    this.conexaoDoBanco = new JdbcTemplate(dataSource);
  }

  public JdbcTemplate getConexaoDoBanco() {
    return conexaoDoBanco;
  }
}
