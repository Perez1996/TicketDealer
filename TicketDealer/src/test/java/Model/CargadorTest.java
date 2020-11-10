/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Resources.Compra;
import Vistas.ConsultaStock;
import Vistas.ViewObserver;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    ModelSubject o;
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
        System.out.println("test_ValidarAdmin : OK");
    }
    @Test
    public void test_ValidarEmpleado() throws Exception 
    {
     
        String usua = "PerezEs";
        String pass = "1234";
        assertEquals(true, cargador.validarEmpleado(usua, pass));
        System.out.println("test_ValidarEmpleado : OK");
    }

    @Test
    public void test_getStockProducto() throws SQLException {
        boolean test = false;
        if(cargador.getStockProducto(12)!=0)
            test = true;
        else;
        assertTrue(test);
       System.out.println("test_getStockProducto : OK");
    }
    @Test
    public void test_agregarStock() throws SQLException {
       int cantidadAgregada = 3;
       int cantidadActual = cargador.getStockProducto(12);
       cargador.agregarStock(12,cantidadAgregada);
       int cantidadEsperada = cantidadActual +cantidadAgregada;
       assertEquals(cantidadEsperada , cargador.getStockProducto(12));
       cargador.quitarStock(12, cantidadAgregada);
       System.out.println("test_agregarStock : OK");
    }
    @Test
    public void test_quitarStock() throws SQLException {
       int cantidadQuitada = 3;
       int cantidadActual = cargador.getStockProducto(12);
       cargador.quitarStock(12,cantidadQuitada);
       int cantidadEsperada =  cantidadActual -cantidadQuitada;
       assertEquals(cantidadEsperada , cargador.getStockProducto(12) );
       cargador.agregarStock(12, cantidadQuitada);
       System.out.println("test_quitarStock : OK");
    }
    @Test
    public void test_creaProducto() throws SQLException{
        assertEquals(true, cargador.creaProducto("gomitas de test", 100, "Golosinas", "TEST"));
        int id = cargador.getIdProd() - 1;
        cargador.borraProducto(id);
        System.out.println("test_creaProducto : OK");
    }
    @Test
    public void test_creaAdmin() throws SQLException{
        assertEquals(true, cargador.cargarAdmin("TESTING", "TESTING"));
        cargador.borraUser("TESTING");
        System.out.println("test_creaAdmin : OK");
    }
    @Test
    public void test_creaUser() throws SQLException{
        assertEquals(true, cargador.creaUser("TESTING", "TESTING", "e"));
        cargador.borraUser("TESTING");
        System.out.println("test_creaUser : OK");
    }
    @Test
    public void test_getStockProd() throws SQLException{
        assertEquals(666, cargador.getStockProducto(24));
        System.out.println("test_getStockProd : OK");
    }
    @Test
    public void test_getidPorNombre() throws SQLException{
        assertEquals(24, cargador.getIdPorNombre("PRODTEST"));
        System.out.println("test_getidPorNombre : OK");
    }
    @Test
    public void test_getcantidad() throws SQLException{
        assertEquals(666, cargador.getCantidad(24));
        System.out.println("test_getcantidad : OK");
    }
    @Test
    public void test_getPrecio() throws SQLException{
        assertEquals(666, cargador.getPrecio(24));
        System.out.println("test_getPrecio : OK");
    }
   
    @Test
    public void test_getEventos() throws SQLException{
        boolean test = false;
        if(cargador.getEventos()!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getEventos : OK");
    }
    @Test
    public void test_getIdEvento() throws SQLException{
        assertEquals(4,cargador.getIdEvento("EVENTO_TEST"));
        System.out.println("test_getIdEvento : OK");
    }
    @Test
     public void test_SetOcupado() throws SQLException{
        cargador.setOcupado(4,666);
        assertTrue(cargador.estaOcupado(4, 666));
        cargador.setDescupado(4, 666);
        System.out.println("test_SetOcupado : OK");
    }
    @Test
    public void test_getUsuarios() throws SQLException{
        boolean test = false;
        if(cargador.getUsuarios()!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getUsuarios : OK");
    }
    @Test
     public void test_getFecha() throws SQLException{

        boolean test = false;
        if(cargador.getFecha()!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getFecha : OK");
    }
    @Test
     public void test_getIdUsuario() throws SQLException{
         boolean test = false; 
         int id = cargador.getIdUsuario("admin", "admin");
         if (id == 1)
             test=true;
                     else;
         assertTrue(test);
         System.out.println("test_getIdUsuario : OK");
    }
    @Test
     public void test_RenovarClave() throws SQLException{
        int id = cargador.getIdUsuario("admin", "admin");
        String nuevaClave = "1234TEST";
        cargador.renovarClave(id, nuevaClave);
        assertTrue(cargador.validarAdmin("admin", nuevaClave));
        cargador.renovarClave(id, "admin");
        System.out.println("test_RenovarClave : OK");
    }
     @Test
    public void test_getIdRs() throws SQLException{
        boolean test = false;
        if(cargador.getIdRs(cargador.CargarStock(), "PRODTEST")!=0)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getIdRs : OK");
    }
    @Test
    public void test_cargarStock() throws SQLException{

        boolean test = false;
        if(cargador.CargarStock()!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_cargarStock : OK");
    }
   @Test
    public void test_cargarCompra() throws SQLException{

        boolean test = false;
        if(cargador.CargarCompra("test1234")!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_cargarCompra : OK");
    }
   
    @Test
    public void test_getIdAsiento1() throws SQLException{
        assertEquals(0,cargador.getIdAsiento("A", 0));
        System.out.println("test_getIdAsiento1 : OK");
    }
     @Test
    public void test_getIdAsiento2() throws SQLException{
        assertEquals(8,cargador.getIdAsiento("B", 0));
        System.out.println("test_getIdAsiento2 : OK");
    }
     @Test
    public void test_getIdAsiento3() throws SQLException{
        assertEquals(16,cargador.getIdAsiento("C", 0));
        System.out.println("test_getIdAsiento3 : OK");
    }
     @Test
    public void test_getIdAsiento4() throws SQLException{
        assertEquals(24,cargador.getIdAsiento("D", 0));
        System.out.println("test_getIdAsiento4 : OK");
    }
     @Test
    public void test_getIdAsiento5() throws SQLException{
        assertEquals(32,cargador.getIdAsiento("E", 0));
        System.out.println("test_getIdAsiento5 : OK");
    }
     @Test
    public void test_getIdAsiento6() throws SQLException{
        assertEquals(40,cargador.getIdAsiento("F", 0));
        System.out.println("test_getIdAsiento6 : OK");
    }
     @Test
    public void test_getIdAsiento7() throws SQLException{
        assertEquals(48,cargador.getIdAsiento("G", 0));
        System.out.println("test_getIdAsiento7 : OK");
    }
     @Test
    public void test_getIdAsiento8() throws SQLException{
        assertEquals(56,cargador.getIdAsiento("H", 0));
        System.out.println("test_getIdAsiento8 : OK");
    }
     @Test
    public void test_getIdAsiento9() throws SQLException{
        assertEquals(64,cargador.getIdAsiento("I", 0));
        System.out.println("test_getIdAsiento9 : OK");
    }
     @Test
    public void test_getIdAsiento10() throws SQLException{
        assertEquals(72,cargador.getIdAsiento("J", 0));
        System.out.println("test_getIdAsiento10 : OK");
    }
    
    
    //-------
    @Test
    public void test_conexionDB()throws SQLException{
        
        boolean test = false;
        if(cargador.cn!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_conexionDB : OK");
    }
    @Test
    public void test_LoginObj() {
        login log = new login("test","test","e");
        boolean test = false;
        if(log.getPass().equals("test") && log.getUser().equals("test") && log.getTipo().equals("e"))
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_LoginObj : OK");
    }
    @Test 
    public void test_ProductoObj(){
        Producto producto = new Producto("Caramelos",4,"golosinas","test");
        boolean test = false;
        if(producto.getProdComent().equals("test")&&producto.getProdNom().equals("Caramelos")&&producto.getProdPrecio()==4&&producto.getProdTipo().equals("golosinas"))
        test = true;
        else;
        assertTrue(test);        
        System.out.println("test_ProductoObj : OK");
    }
    @Test
    public void test_getCarrito() throws SQLException{
        String codCompra = "test1234"; 
        boolean test = false;
        if(cargador.getCarrito(codCompra)!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getCarrito : OK");
    }
    @Test
    public void test_getTablaObserver() throws SQLException{
        boolean test = false;
        if(cargador.getTablaObserver()!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getTablaObserver : OK");
    }
    @Test
    public void test_getprecioFinal() throws SQLException{
        String codCompra = "test1234"; 
        boolean test = false;
        if(cargador.getPrecioFinal(codCompra)==8000)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getprecioFinal : OK");
    }
    @Test
    public void test_getDescVenta() throws SQLException{
        String codCompra = "test1234"; 
        assertEquals("metallica",cargador.getDescVenta(codCompra));
        System.out.println("test_getDescVenta : OK");
           
    }
    @Test
    public void test_getCompraFinalizada() throws SQLException{
        String codCompra = "test1234"; 
        boolean test = false;
        if(cargador.getCompraFinalizada(codCompra)!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getprecioFinal : OK");
    }
    @Test
    public void test_getFechaCompra() throws SQLException{
        String codCompra = "test1234"; 
        boolean test = false;
        if(cargador.getFechaCompra(codCompra)!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getFechaCompra : OK");
    }
    @Test
    public void test_GuardarCompraObj(){
        Compra compra = new Compra();
        cargador.GuardarCompra(compra);
        boolean test = false;
        if(cargador.getObjCompra()!=null)
            test = true ; 
        else ;
        assertTrue(test);
        System.out.println("test_GuardarCompraObj : OK");   
    }
    @Test
    public void test_GuardarCompraEmpObj(){
        Compra compra = new Compra();
        cargador.GuardarCompraEmpleado(compra);
        boolean test = false;
        if(cargador.getObjCompraEmpleado()!=null)
            test = true ; 
        else ;
        assertTrue(test);
        System.out.println("test_GuardarCompraEmpObj : OK");   
    }
    @Test
    public void test_obtenerCompra() throws SQLException{
        boolean test = false;
        String codCompra = "test1234"; 
        if(cargador.obtenerCompra(codCompra)!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_obtenerCompra : OK");
    }
    @Test
    public void test_getRecibo() throws SQLException{
        boolean test = false;
        String codCompra = "test1234"; 
        if(cargador.getRecibo(codCompra)!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getRecibo : OK");
    }
    @Test
    public void test_getAsientosLibres() throws SQLException{
        boolean test = false;
        String codCompra = "test1234"; 
        if(cargador.getAsientosLibres(1)!=null)
            test = true;
        else;
        assertTrue(test);
        System.out.println("test_getAsientosLibres : OK");
    }
    @Test
    public void test_finalizarCompra() throws SQLException{
        boolean test = false;
        cargador.finalizarCompra("testing", "testing" , 1234.0);
        if(cargador.getCompraFinalizada("testing")!=null)
            test = true;
        else;
        assertTrue(test);
        cargador.borrarCompraFinalizada("testing");
        System.out.println("test_finalizarCompra : OK");
    }
    @Test
    public void test_asociarEmpleado() throws SQLException{
        boolean test = false;
        String codigocompra = "test1234";
        cargador.asociarEmpleado(1, "tarjeta", codigocompra);
        assertEquals("tarjeta",cargador.getFormaPago(codigocompra));
        cargador.quitarFormaPago(codigocompra);
        System.out.println("test_asociarEmpleado : OK");
    }
    @Test 
    public void test_comprarEntrada() throws SQLException{
        boolean test = false ;
        cargador.comprarEntrada(4, "test1234", 666);
        if(cargador.estaOcupado(4, 666)){
            test = true;
        }
        else;
        assertTrue(test);
        cargador.devolverEntrada();
        cargador.setDescupado(4, 666);
        System.out.println("test_comprarEntrada : OK");
    }
    @Test
        public void test_getok() throws SQLException{
        String codCompra = "TEST2";
        cargador.agregaItem(0, codCompra, 0, 0, 0, codCompra);
        assertEquals(codCompra,cargador.getDescVenta(codCompra));
        cargador.quitarItem(666);
        System.out.println("GETOK : OK");
        
    }
    
}