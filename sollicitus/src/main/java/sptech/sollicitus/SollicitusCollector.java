/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sptech.sollicitus;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeParametros;
import com.github.britooo.looca.api.util.Conversor;
import java.text.DateFormat;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
/**
 *
 * @author Silvio Tavares ADM
 */
public class SollicitusCollector {
        Looca looca = new Looca();
        Sistema sistema = looca.getSistema();

        Processador processador = looca.getProcessador();
        
        Memoria memoria = looca.getMemoria();
        
        Double capacidadeRam = (memoria.getTotal().doubleValue())/1000000000.0;
        Double usoRam = (memoria.getEmUso().doubleValue()/memoria.getTotal().doubleValue())*100.0;
        
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();
        List<Volume> volumes = grupoDeDiscos.getVolumes();
       
        Double capacidadeDisco = (grupoDeDiscos.getTamanhoTotal().doubleValue())/1000000000.0;
        
        JanelaGrupo grupoDeJanelas = looca.getGrupoDeJanelas();
        Integer qtdJanelas = grupoDeJanelas.getTotalJanelasVisiveis();
        
        ProcessoGrupo grupoDeProcessos = looca.getGrupoDeProcessos();
        Integer qtdProcessos = grupoDeProcessos.getTotalProcessos();
        
        Rede rede = looca.getRede();
        RedeParametros redeParametros = rede.getParametros();
        
        public void inserirDados(){
        Conexao conexao = new Conexao();
        ConexaoMySQL conexaomysql = new ConexaoMySQL();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate con1 = conexaomysql.getConexaoDoBanco();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
            try {
                 con.update("update maquina set ucp = ?, so = ?, ram = ?, armazenamento = ? where hostname = ?", processador.getNome(), sistema.getSistemaOperacional(), Conversor.formatarBytes(memoria.getTotal()), Conversor.formatarBytes(grupoDeDiscos.getTamanhoTotal()), redeParametros.getHostName());
            } catch (Exception e) {
                System.out.println(e);
            }
            
            try {
                con.update("insert into logDesempenho (dataHora ,nivelCPU, nivelRAM, fkMaquina) values(?, ?, ?, (SELECT idMaquina from maquina WHERE hostName = ?))", dtf.format(now), processador.getUso(), Conversor.formatarBytes(memoria.getEmUso()), redeParametros.getHostName());
            } catch (Exception e) {
                System.out.println(e);
            }
            
            /*try {
                for (Processo p : grupoDeProcessos.getProcessos()) {
                    con.update("insert into processos (nome, usoRam, usoCPU, fklogDesempenho) values (?, ?, ?, (SELECT max(idlogDesempenho) FROM logDesempenho))",p.getNome(), p.getUsoMemoria(), p.getUsoCpu());
                    }
            } catch (Exception e) {
                System.out.println(e);
            }*/
            
            //Daqui para baixo os dados são inseridos no banco local mysql
            try {
                 con1.update("update Maquina set ucp = ?, so = ?, ram = ?, armazenamento = floor(?) where hostname = ?", processador.getNome(), sistema.getSistemaOperacional(), capacidadeRam, capacidadeDisco.toString(), redeParametros.getHostName());
           } catch (Exception e) {
                System.out.println(e);
            }
            
            try {
                con1.update("insert into logDesempenho (nivelCPU, nivelRAM) values(?, ?)", processador .getUso(), usoRam);
            } catch (Exception e) {
                System.out.println(e);
            }
            
            try {
                for (Processo p : grupoDeProcessos.getProcessos()) {
                    con1.update("insert into processos (nome, usoRam, usoCPU) values (?, ?, ?)", p.getNome(), p.getUsoMemoria(), p.getUsoCpu());
                    }
            } catch (Exception e) {
                 System.out.println(e);
            }
                
                    System.out.println(String.format("Processador:\n"
                    + "Em uso: %.0f%%\n"
                    + "Memória Ram:\n"
                    + "Total: %s\n"
                    + "Em uso %s\n"
                    + "Disco rigido:\n"
                    + "Capaciade: %s\n"
                    + "Disponível: %s\n"
                    + "SO:\n"
                    + "%s",
                    processador.getUso(), Conversor.formatarBytes(memoria.getTotal()), Conversor.formatarBytes(memoria.getEmUso()), Conversor.formatarBytes(grupoDeDiscos.getTamanhoTotal()), Conversor.formatarBytes(volumes.get(0).getDisponivel()), sistema.getSistemaOperacional()));
        }
    }