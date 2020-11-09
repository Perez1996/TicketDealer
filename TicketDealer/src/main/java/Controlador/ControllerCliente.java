package Controlador;
import Model.*;
import Vistas.*;
import Resources.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class ControllerCliente implements ControllerInterface{
	Cargador model;
    Home view;
    String args = "iniciar";
    Connect c;
	
	public ControllerCliente(Cargador model, Home view){
		this.model=model;
		this.view=view;
		//iniciar();
	}
	
	public void iniciar(){
//            try {	
//                c = new Connect();
//            } catch (SQLException ex) {
//                Logger.getLogger(ControllerCine.class.getName()).log(Level.SEVERE, null, ex);
//            }
            //Home h = new Home();
            view.initComponents();
            view.setVisible(true);     
	}

	public void cambiarAHome2(Home v) {//OK!
		v.setVisible(false);
		HomeCliente hc= new HomeCliente(this);
		hc.setVisible(true);
	}
	
	@Override
	public void cambiarACompra(CompraTickets_v1 ct) {//OK!
		ct.setVisible(false);
		CompraView c;
		try {
			c = new CompraView(this);
		} catch (SQLException e) {
			c=null;
			e.printStackTrace();
		}
		c.setVisible(true);
	}
	
	@Override
	public void cambiarAFormaPago(CompraView c) {//OK!
		c.setVisible(false);
		FormaPago fp= new FormaPago(this);
		fp.setVisible(true);
	}
	
	public void cambiarAPago(FormaPago fp) {//OK!
		fp.setVisible(false);
		Pago p= new Pago(this);
		p.setVisible(true);
	}
	
	public void	cambiarAFormaPago(Pago p) {//OK!
		p.setVisible(false);
		FormaPago fp=new FormaPago(this);
		fp.setVisible(true);
	}
	
	@Override
	public void cambiarACompra(FormaPago fp) {//OK!
		fp.setVisible(false);
		CompraView c;
		try {
			c = new CompraView(this);
		} catch (SQLException e) {
			c=null;
			e.printStackTrace();
		}
		c.setVisible(true);
	}
	
	@Override
	public void cambiarACompraTickets(CompraView c) {//OK!
		c.setVisible(false);
		CompraTickets_v1 ct;
            try {
                ct = new CompraTickets_v1(this);
                ct.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
	
	@Override
	public void cambiarAHome2(CompraTickets_v1 ct) {//OK!
		ct.setVisible(false);
		HomeCliente hc= new HomeCliente(this);
		hc.setVisible(true);
	}
	
	public void cambiarAHome(Login l) {//OK!
	}
	
	public void	cambiarALogin(Home v) {//OK!
	}
	
	public Cargador getModel(){//OK!
		return model;
	}

	@Override
	public void cambiarAHome2(Login l) {//OK!
	}

	@Override
	public boolean esValido(String a, String b) {//OK!
		return false;
	}

	@Override
	public void cambiarALogin(HomeAdmin home) {//OK!	
	}

	@Override
	public void cambiarACargaStock(HomeAdmin h) {//OK!	
	}

	@Override
	public void cambiarAConsultaStock(HomeAdmin h) {//OK!
	}

	@Override
	public void cambiarAHomeAdmin(ConsultaStock cs) {//OK!	
	}

	@Override
	public void cambiarAHomeAdmin(CargaStock cs) {//OK!
	}
	
	@Override
	public void cambiarAEntradas(HomeEmpleado gc) {//OK!
	}

	@Override
	public void cambiarAHome(HomeEmpleado gc) {//OK!
	}

	@Override
	public void cambiarAConfirma(FormaPago fp) {
		fp.setVisible(false);
		Recibo r=new Recibo(this);
		r.setVisible(true);
	}

	@Override
	public void cambiarAConfirma(Pago p) {//OK!
		p.setVisible(false);
		Recibo r=new Recibo(this);
		r.setVisible(true);
	}
	
	@Override
	public void cambiarAFormaPago(Recibo r) {//OK!
		r.setVisible(false);
		FormaPago fp=new FormaPago(this);
		fp.setVisible(true);
	}

	@Override
	public void cambiarAHome2(Recibo r) {//OK!
		r.setVisible(false);
		HomeCliente hc= new HomeCliente(this);
		hc.setVisible(true);
	}

	@Override
	public void cambiarAEntradas(HomeCliente hc) {
		hc.setVisible(false);
		CompraTickets_v1 ct;
            try {
                ct = new CompraTickets_v1(this);
                ct.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
	}	
	
	@Override
	public void cambiarARecTick(HomeEmpleado gc) {//OK!
	}
	
	@Override
	public void cambiarAHomeEmpleado(RecibirTiket rt) {
	}

	@Override
	public ResultSet getRSProd(String s) {
		return null;
	}

	@Override
	public boolean agregarStock(int nombre, int cant) {
		return false;
	}
	
	@Override
	public boolean quitarStock(int nombre, int cant) {
		return false;
	}

	@Override
	public boolean addProducto(String nombre, double precio, String string, String string2) {
		return false;
	}
	
	@Override
	public boolean clearProducto(int text) {
		return false;
	}

	@Override
	public ResultSet getRSStock() {
		try {
			return model.CargarStock();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultSet setEventosBox() {
		try {
			return model.getEventos();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean esDisponible(int idEvento, int idAsiento) {
		try {
			return model.estaOcupado(idEvento,idAsiento);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void iniciarCompra(int idEvento,int numEntrada) {
		String codigoCompra= model.getObjCompra().getCodigo();
		try {
			model.comprarEntrada(idEvento, codigoCompra, numEntrada);
			
			model.setOcupado(idEvento, numEntrada);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Compra getCompraActual() {
		return model.getObjCompra();
	}
       

	@Override
	public boolean cantStock(int id, int cantidad) {
		return false;
	}

	@Override
	public int getIdProd(String nombre) {
		return 0;
	}

    @Override
    public void iniciarCompra(int idEvento, String fila, int columna) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getCarro(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}
