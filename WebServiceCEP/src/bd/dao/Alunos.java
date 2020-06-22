package bd.dao;
import java.sql.*;

import javax.swing.JLabel;

import bd.*;
import bd.core.*;
import bd.dbo.*;


public class Alunos 
{
	public static boolean cadastrado (int ra) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM ALUNOS " +
                  "WHERE RA = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, ra);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); 
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao verificar se o aluno esta cadastrado!");
        }

        return retorno;
    }
	
	public static void incluir (Aluno aluno) throws Exception 
    {
        if (aluno == null)
            throw new Exception ("Aluno valido!");
        
        if (cadastrado(aluno.getRa()))
            throw new Exception ("Aluno ja existe!");
        
        try
        {
            String sql;

            sql = "INSERT INTO ALUNOS (RA, Nome, Email, CEP, Endereco, Bairro, Cidade, Estado, Complemento, Numero) VALUES (?,?,?,?,?,?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt(1, aluno.getRa ());
            BDSQLServer.COMANDO.setString(2, aluno.getNome ());
            BDSQLServer.COMANDO.setString(3, aluno.getEmail ());
            BDSQLServer.COMANDO.setString(4, aluno.getCep());
            BDSQLServer.COMANDO.setString(5, aluno.getEndereco());
            BDSQLServer.COMANDO.setString(6, aluno.getBairro());
            BDSQLServer.COMANDO.setString(7, aluno.getCidade());
            BDSQLServer.COMANDO.setString(8, aluno.getEstado());
            BDSQLServer.COMANDO.setString(9, aluno.getComplemento());
            BDSQLServer.COMANDO.setString(10, aluno.getNumero());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir aluno");
        }
    }
	
	public static void excluir (int ra) throws Exception  
    {
		if (!cadastrado (ra))
            throw new Exception ("Este aluno nao esta cadastrado!");

        try
        {
            String sql;
           
            sql = "DELETE FROM ALUNOS WHERE RA = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);
            BDSQLServer.COMANDO.setInt (1, ra);
            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
           
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir aluno");
        }
    }
	
	public static void atualizar (Aluno aluno) throws Exception 
    {
        if (aluno==null)
            throw new Exception ("Preencha todos os campos!");

        if (!cadastrado (aluno.getRa()))
            throw new Exception ("Este aluno nao esta cadastrado!");

        try
        {
            String sql;

            sql = "UPDATE ALUNOS " +
                  "SET NOME=? " +
                  "RA=?, EMAIL=?, CEP=?, Endereco=?, Bairro=?, Cidade=?, Complemento=?, Numero=? ";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString(1, aluno.getNome());
            BDSQLServer.COMANDO.setInt(2, aluno.getRa());
            BDSQLServer.COMANDO.setString(3, aluno.getEmail ());
            BDSQLServer.COMANDO.setString(4, aluno.getCep());
            BDSQLServer.COMANDO.setString(5, aluno.getEndereco());
            BDSQLServer.COMANDO.setString(6, aluno.getBairro());
            BDSQLServer.COMANDO.setString(7, aluno.getCidade());
            BDSQLServer.COMANDO.setString(8, aluno.getComplemento());
            BDSQLServer.COMANDO.setString(9, aluno.getNumero());
            
  
            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados do aluno");
        }
    }
	
	public static Aluno getAluno (int ra) throws Exception
    {
		if (ra <= 0)
            throw new Exception ("Digite um ra valido");
		
		 if (!cadastrado(ra))
	            throw new Exception ("Este Aluno nao esta cadastrado"); 
		 
        Aluno aluno = null;
        
        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM ALUNOS " +
                  "WHERE RA = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);
   
            BDSQLServer.COMANDO.setInt (1, ra); 

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");
           
            aluno = new Aluno (resultado.getInt("RA"),
                               resultado.getString("Nome"),
                               resultado.getString ("Email"),
                               resultado.getString("CEP"),
                               resultado.getString("Endereco"),
                               resultado.getString("Bairro"),
                               resultado.getString("Cidade"),
                               resultado.getString("Estado"),
                               resultado.getString("Complemento"),
                               resultado.getString("Numero"));
          
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar aluno");
        }

        return aluno;
    }
	
	public static Aluno getAluno(String nome) throws Exception
	{
		Aluno aluno = null;
		try
		{
			String sql = "SELECT * FROM AlUNOS WHERE NOME = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1, nome);
			MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
			if(!resultado.first())
				throw new Exception("Nao cadastrado");
			
			aluno = new Aluno( resultado.getInt("RA"),
                               resultado.getString("Nome"),
                               resultado.getString ("Email"),
                               resultado.getString("CEP"),
                               resultado.getString("Endereco"),
                               resultado.getString("Bairro"),
                               resultado.getString("Cidade"),
                               resultado.getString("Estado"),
                               resultado.getString("Complemento"),
                               resultado.getString("Numero"));
		}
		catch(SQLException erro) {
			
			throw new Exception(erro.getMessage());
		}
		return aluno;
	}
	
}
