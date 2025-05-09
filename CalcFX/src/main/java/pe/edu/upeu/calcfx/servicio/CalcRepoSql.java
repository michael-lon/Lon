package pe.edu.upeu.calcfx.servicio;

import org.springframework.stereotype.Component;
import pe.edu.upeu.calcfx.conn.Conn;
import pe.edu.upeu.calcfx.modelo.CalcTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class CalcRepoSql {
    //ConnS instance = ConnS.getInstance();
    //Connection connection = instance.getConnection();
    Connection connection = new Conn().connectSQLite();
    PreparedStatement ps;
    ResultSet rs;

    public List<CalcTO> listarEntidad() {
        System.out.println("Hola SQL Nativo Lista");
        List<CalcTO> lista = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * from calculadora ");
            rs = ps.executeQuery();
            while (rs.next()) {
                CalcTO calcTO = new CalcTO();
                calcTO.setNum1(rs.getString("num1"));
                calcTO.setNum2(rs.getString("num2"));
                calcTO.setOperador(rs.getString("operador").charAt(0));
                calcTO.setResultado(rs.getString("resultado"));
                lista.add(calcTO);
                calcTO.setId(rs.getLong("id"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
    public Long maxId() {
        Long i=0L;
        try {
            ps = connection.prepareStatement("SELECT (max(id)+1) as idx from calculadora ");
            rs = ps.executeQuery();
            if (rs.next()) {
                i= rs.getLong("idx");
            }
            return i;
        } catch (Exception e) {
            return i;
        }
    }

    public int guardarEntidad(CalcTO c) {
        int result=0;
        c.setId(maxId());
        try {
            ps = connection.prepareStatement("INSERT INTO calculadora(id, num1,num2, operador,resultado)" + " VALUES("+c.getId()+", '"+c.getNum1()+"', '"+c.getNum2()+"','"+c.getOperador()+"', '"+c.getResultado()+"')");
            result= ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
        return result;
    }

    public boolean eliminarDatos(CalcTO to){
        int result=0;
        try {
            ps = connection.prepareStatement("DELETE FROM calculadora WHERE id = "+to.getId());
            ps.executeUpdate();
            result=ps.executeUpdate();

        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
        return result==1?true:false;
    }
    public boolean actualizarDatos(CalcTO to, int id) {
        int result=0;
        try {
            ps = connection.prepareStatement("update calculadora set num1="+to.getNum1()+", num2="+to.getNum2()+", operador='"+to.getOperador()+"', resultado="+to.getResultado()+" where id="+id);
            result=ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
        return result ==1;
    }

}