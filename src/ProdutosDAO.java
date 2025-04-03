/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO(){
        this.conn = new conectaDAO().connectDB();
    }
    
    public void cadastrarProduto (ProdutosDTO produto){
        String sql = "insert into produtos(nome, valor, status) values (?,?,?)";
        
        try{
            prep = this.conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto!");
        }
        
        //conn = new conectaDAO().connectDB();
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        String sql = "select * from produtos";
        try{
          prep = this.conn.prepareStatement(sql);  
          resultset = prep.executeQuery();
          
          while(resultset.next()){
              ProdutosDTO produtoAtual = new ProdutosDTO();
              
              produtoAtual.setId(resultset.getInt("id"));
              produtoAtual.setNome(resultset.getString("nome"));
              produtoAtual.setValor(resultset.getInt("valor"));
              produtoAtual.setStatus(resultset.getString("status"));
              listagem.add(produtoAtual);
          }
          return listagem;
          
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Falha ao listar os produtos.");
            return null;
        }
        
     
    }
    
    public void venderProduto(int id){
      String sql = "update produtos set status = 'Vendido' where id = ?";
      
      try{
          prep = this.conn.prepareStatement(sql);
          prep.setInt(1, id);
          prep.executeUpdate();
          
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null, "Falha ao atualizar venda");
      }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        String sql = "select * from produtos where status = 'Vendido'";
        ArrayList produtosVendidos = new ArrayList();
        try{
            prep = this.conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while(resultset.next()){
                ProdutosDTO produtoAtual = new ProdutosDTO();
                
                produtoAtual.setId(resultset.getInt("id"));
                produtoAtual.setNome(resultset.getString("nome"));
                produtoAtual.setValor(resultset.getInt("valor"));
                produtoAtual.setStatus(resultset.getString("status"));
                produtosVendidos.add(produtoAtual);
            }
            
            return produtosVendidos;
        }catch(Exception ex){
            return null;
                    }
    }
        
}

