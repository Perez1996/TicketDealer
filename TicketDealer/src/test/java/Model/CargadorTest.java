/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Resources.Compra;
import Vistas.ViewObserver;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Scoles
 */
public class CargadorTest {
    private static Cargador cargador;
    CallableStatement cs;
    Connect cn;
    ResultSet rs ;
    PreparedStatement ps;
    Statement s ;
    
    public CargadorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() throws SQLException 
    {
        cargador=new Cargador();
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of validarAdmin method, of class Cargador.
     */
    @Test
    public void testValidarAdminA1() throws Exception 
    {
        System.out.println("validarAdmin");
        String usua = "pepito";
        String pass = "1234";
        Cargador cargador = new Cargador();
        assertEquals(true, cargador.validarAdmin(usua, pass));
        //fail("The test case is a prototype.");
    }
    
    
    public void test_getStockProducto() throws SQLException {
       int cantidadEsperada =6 ;
       Cargador cargador = new Cargador();
       assertEquals(cantidadEsperada, cargador.getStockProducto(12) );
    }
    
    public void test_agregarStock() throws SQLException {
       Cargador cargador = new Cargador();
       int cantidadEsperada = 9 ;
               System.out.println(cantidadEsperada);
       int cantidadAgregada = 3;
               System.out.println(cantidadAgregada);
       int cantidadActual = cargador.getStockProducto(12);
               System.out.println(cantidadActual);
       cargador.agregarStock(12,cantidadAgregada);
       assertEquals(cantidadEsperada , cantidadActual + cantidadAgregada);
       cargador.quitarStock(12, cantidadAgregada);
                      System.out.println("agregarStock : OK");

    }
     
}
      /* public int getStockProducto(int idProd) throws SQLException {
    	
        
        
        //agregar
              cs= cn.getConnection().prepareCall("{call SumaStock(?,?)}");
        System.out.println(".."+idprod+"----"+cant);
        cs.setInt("p_ProdId", idprod);
        cs.setInt("Cantidad", cant);
        cs.executeUpdate();
        System.out.println("se agrego");
    }
    
}
/*/