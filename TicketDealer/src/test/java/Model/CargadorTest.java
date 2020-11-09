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
    
    public CargadorTest() throws SQLException {
        cargador= new Cargador();
    }
    
    @BeforeAll
    public static void setUpClass() throws SQLException 
    {
        // cargador=();
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
    public void test_ValidarAdmin() throws Exception 
    {
        String usua = "pepito";
        String pass = "1234";
        assertEquals(true, cargador.validarAdmin(usua, pass));
        System.out.println("ValidarAdmin : OK");
    }
    @Test
    public void test_ValidarEmpleado() throws Exception 
    {
     
        String usua = "PerezEs";
        String pass = "1234";
        assertEquals(true, cargador.validarEmpleado(usua, pass));
        System.out.println("ValidarAdmin : OK");
    }

    @Test
    public void test_getStockProducto() throws SQLException {
       
       int cantidadEsperada =6 ;
       assertEquals(cantidadEsperada, cargador.getStockProducto(12) );
       System.out.println("getStockProducto : OK");
    }
    @Test
    public void test_agregarStock() throws SQLException {
       int cantidadAgregada = 3;
       int cantidadActual = cargador.getStockProducto(12);
       int cantidadEsperada = cantidadActual +cantidadAgregada;

       cargador.agregarStock(12,cantidadAgregada);
       assertEquals(cantidadEsperada , cantidadActual);
       cargador.quitarStock(12, cantidadAgregada);
       System.out.println("agregarStock : OK");
    }
    @Test
    public void test_quitarStock() throws SQLException {
       int cantidadQuitada = 3;
       int cantidadActual = cargador.getStockProducto(12);
       int cantidadEsperada =  cantidadActual +cantidadQuitada;
       cargador.quitarStock(12,cantidadQuitada);
       assertEquals(cantidadEsperada , cantidadActual );
       cargador.agregarStock(12, cantidadQuitada);
       System.out.println("quitarStock : OK");
    }
    @Test
    public void test_creaProducto() throws SQLException{
        assertEquals(true, cargador.creaProducto("gomitas de test", 100, "Golosinas", "TEST"));
        int id = cargador.getIdProd() - 1;
        cargador.borraProducto(id);
    }
    @Test
    public void test_creaAdmin() throws SQLException{
        assertEquals(true, cargador.cargarAdmin("TESTING", "TESTING"));
        cargador.borraUser("TESTING");
    }
    @Test
    public void test_creaUser() throws SQLException{
        assertEquals(true, cargador.creaUser("TESTING", "TESTING", "e"));
        cargador.borraUser("TESTING");
    }
    @Test
    public void test_getStockProd() throws SQLException{
        assertEquals(666, cargador.getStockProducto(24));
    }
    @Test
    public void test_getidPorNombre() throws SQLException{
        assertEquals(24, cargador.getIdPorNombre("PRODTEST"));
    }
    @Test
    public void test_getcantidad() throws SQLException{
        assertEquals(666, cargador.getCantidad(24));
    }
    @Test
    public void test_getPrecio() throws SQLException{
        assertEquals(666, cargador.getPrecio(24));
    }
   /* @Test
    public void test_getIdRs() throws SQLException{
        CargaBox cargabox = new CargaBox();
        boolean test = false;
        if(cargador.getIdRs(cargabox.CargarStock(), "PRODTEST")!=0)
            test = true;
        else;
        assertTrue(test);
    }*/
    @Test
    public void test_getEventos() throws SQLException{
        boolean test = false;
        if(cargador.getEventos()!=null)
            test = true;
        else;
        assertTrue(test);
    }
    @Test
    public void test_getIdEvento() throws SQLException{
        assertEquals(4,cargador.getIdEvento("EVENTO_TEST"));
    }
    @Test
     public void test_SetOcupado() throws SQLException{
        cargador.setOcupado(4,666);
        assertTrue(cargador.estaOcupado(4, 666));
        cargador.setDescupado(4, 666);
    }
    @Test
    public void test_getUsuarios() throws SQLException{
        boolean test = false;
        if(cargador.getUsuarios()!=null)
            test = true;
        else;
        assertTrue(test);
    }
    @Test
     public void test_getFecha() throws SQLException{

        boolean test = false;
        if(cargador.getFecha()!=null)
            test = true;
        else;
        assertTrue(test);
    }
    @Test
     public void test_getIdUsuario() throws SQLException{

        assertEquals(6, cargador.getIdUsuario("TEST", "TEST"));
    }
    @Test
     public void test_RenovarClave() throws SQLException{

        String nuevaClave = "1234TEST";
        cargador.renovarClave(6, nuevaClave);
        assertTrue(cargador.validarAdmin("TEST", "1234TEST"));
        cargador.renovarClave(6, "TEST");
    }
    @Test
    public void test_cargarStock() throws SQLException{

        CargaBox cargabox = new CargaBox();
        boolean test = false;
        if(cargador.CargarStock()!=null)
            test = true;
        else;
        assertTrue(test);
    }
   @Test
    public void test_cargarCompra() throws SQLException{

        boolean test = false;
        if(cargador.CargarCompra("test1234")!=null)
            test = true;
        else;
        assertTrue(test);
    }
    @Test
    public void test_imprimirUsers() throws SQLException{

        assertTrue(cargador.imprimirUsers());
    }
    @Test
    public void test_getIdAsiento() throws SQLException{

        assertEquals(0,cargador.getIdAsiento("A", 0));
    }
    @Test
    public void test_conexionDB()throws SQLException{
        
        boolean test = false;
        if(cargador.cn!=null)
            test = true;
        else;
        assertTrue(test);
    }
    @Test
    public void test_LoginObj() throws SQLException{
        login log = new login("test","test","e");
        boolean test = false;
        if(log.getPass().equals("test") && log.getUser().equals("test") && log.getTipo().equals("e"))
            test = true;
        else;
        assertTrue(test);
    }
}