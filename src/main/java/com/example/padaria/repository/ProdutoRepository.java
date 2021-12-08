package com.example.padaria.repository;

import com.example.padaria.model.Produto;
import com.example.padaria.model.ProdutoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProdutoRepository {

    private Connection connection;

    public ProdutoRepository() throws ClassNotFoundException, SQLException {
        GenericDao dao = new GenericDao();
        connection = dao.getConnection();
    }

    public void remover(Long id) {
        try {
            String sql = "DELETE FROM produtos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public void adicionar(Produto produto) {
        try {
            String sql = "INSERT INTO produtos (nome, preco, dataInclusao, descricao) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.setDate(3, new java.sql.Date(produto.getData().getTime()));
            stmt.setString(4, produto.getDescricao());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public void atualizar(Produto produto, Long id) {
        try {
            String sql = "UPDATE produtos SET nome = ?, preco = ?, dataInclusao = ?, descricao = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.setDate(3, new java.sql.Date(produto.getData().getTime()));
            stmt.setString(4, produto.getDescricao());
            stmt.setLong(5, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public Produto buscarProduto(Long id){
        Produto produto = new Produto();
        try {
            String sql = "SELECT nome, preco, dataInclusao, descricao FROM produtos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getBigDecimal("preco"));
            produto.setData(rs.getDate("dataInclusao"));
            produto.setDescricao(rs.getString("descricao"));
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

    public List<Produto> listarProdutos() {
        List<Produto> lista = new ArrayList<>();
        try {
            String sql = "SELECT id, nome, preco, dataInclusao, descricao FROM produtos";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();


            while(rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getBigDecimal("preco"));
                p.setData(rs.getDate("dataInclusao"));
                p.setDescricao(rs.getString("descricao"));
                lista.add(p);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<ProdutoDTO> gerarRelatorio(Date data) {
        List<ProdutoDTO> lista = new ArrayList<>();
        try {
            String sql = "CALL Relatorio_Produto(?);";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, data);
            ResultSet rs = stmt.executeQuery();


            while(rs.next()) {
                ProdutoDTO p = new ProdutoDTO();
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getBigDecimal("preco"));
                lista.add(p);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
