/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Init;
import  Vistas.*;
import  Controlador.*;
import  Model.*;
import java.sql.SQLException;
/**
 *
 * @author Macbook de Esteban
 */
public class Main {
    
    public static void main(String args[]) throws SQLException {
        Iniciar();
    }
    public static void Iniciar() throws SQLException{
        Home h= new Home();
	Cargador c=new Cargador();
	ControllerInterface controller = new ControllerCliente(c,h);
	h.cambiarController(controller);
	controller.iniciar();
        }
    
}
