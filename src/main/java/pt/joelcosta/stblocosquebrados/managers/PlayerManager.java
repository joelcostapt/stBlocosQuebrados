package pt.joelcosta.stblocosquebrados.managers;

import pt.joelcosta.stblocosquebrados.objects.PlayerOBJ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static pt.joelcosta.stblocosquebrados.Main.*;

public class PlayerManager {
    private ArrayList<PlayerOBJ> lista;

    public PlayerManager() {
        lista = new ArrayList<>();
    }

    private void createPlayer(PlayerOBJ pb){
        lista.add(pb);
    }

    public PlayerOBJ getPlayer(String nome) {
        for (PlayerOBJ user : lista) {
            if (user.getNome().equalsIgnoreCase(nome)) return user;
        }
        return null;
    }

    public void salvarPlayer(PlayerOBJ p) {
        try{
            getMySQL().openConnection();
            Connection connection = getMySQL().getConnection();

            PreparedStatement ps = connection.prepareStatement("UPDATE informacoes SET blocos = '"+ p.getQuantidade()+"' WHERE  nome = '"+ p.getNome() +"'");
            ps.execute();
            ps.close();

            getMySQL().closeConnection();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void loadPlayer(String nome) {
        try {
            getMySQL().openConnection();
            Connection c = getMySQL().getConnection();

            PreparedStatement ps = c.prepareStatement("SELECT * FROM informacoes WHERE nome = '"+ nome +"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                createPlayer(new PlayerOBJ(rs.getString("nome"), rs.getInt("blocos")));
            } else {
                ps = c.prepareStatement("INSERT INTO informacoes VALUES ('"+ nome +"', '" + 0 +"')");
                ps.execute();
                ps.close();
                createPlayer(new PlayerOBJ(nome, 0));
            }

            getMySQL().closeConnection();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
