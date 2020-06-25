/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Model.Cargador;
import Vistas.ConsultaStock;
import Vistas.Home;
import Vistas.HomeAdmin;
import Vistas.HomeEmpleado;
import Vistas.Login;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Esteban
 */
public class ControllerEmp {
    Cargador model;
    Home view;
	
	public ControllerEmp(Cargador model, Home view){
		this.model=model;
		this.view=view;
	}
      //  @Override
	public void iniciar() {//OK!
	}

        public void cambiarALogin(Home v) {//OK!
		v.setVisible(false);
		Login l = new Login(this);
        l.setVisible(true);
        //setborn("a");
	}
      //  @Override
	public void cambiarAHome2(Login l) {//OK!
		l.setVisible(false);
		HomeEmpleado gc =new HomeEmpleado(this,this.getModel());
		gc.setVisible(true);	
	}
	
	//@Override
	public void cambiarAHome(HomeEmpleado gc) {//OK!
		gc.setVisible(false);
		Home h= new Home();
		h.cambiarController(this);
		//h.initComponents();
		h.setVisible(true);
	}
	
	//@Override
	public void cambiarAHome2(CompraTickets_v1 ct) {
		ct.setVisible(false);
		HomeEmpleado gc =new HomeEmpleado(this,this.getModel());
		gc.setVisible(true);
	}

	@Override
	public void cambiarAHome2(Home v) {//OK!
	}
        @Override
	public void cambiarACompraTickets(CompraView c) {//OK!
		c.setVisible(false);
		CompraTickets_v1 ct;
		try {
			ct = new CompraTickets_v1(this);
		} catch (SQLException e) {
			ct=null;
			e.printStackTrace();
		}
		ct.setVisible(true);
	}

	@Override
	public void cambiarAPago(FormaPago fp) {//OK!
		fp.setVisible(false);
		Pago p= new Pago(this);
		p.setVisible(true);
	}

	@Override
	public void cambiarAFormaPago(Pago p) {//OK!
		p.setVisible(false);
		FormaPago fp=new FormaPago(this);
		fp.setVisible(true);
	}

	@Override
	public Cargador getModel() {//OK!
		return model;
	}

	@Override
	public boolean esValido(String a, String b) {
		try{
			return model.validarEmpleado(a,b);}
		catch (SQLException e){
			return false;
		}
		//return true;
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
	public void cambiarAFormaPago(CompraView c) {//OK!
		c.setVisible(false);
		FormaPago fp= new FormaPago(this);
		fp.setVisible(true);
	}

	@Override
	public void cambiarACompra(FormaPago fp) {//OK!
		fp.setVisible(false);
		CompraView c;
		try {
			c = new CompraView(this);
		} catch (SQLException e) {
			e.printStackTrace();
			c=null;
		}
		c.setVisible(true);
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
	public void cambiarAPelicula(HomeEmpleado gc) {
		gc.setVisible(false);
		CompraTickets_v1 ct;
		try {
			ct = new CompraTickets_v1(this);
		} catch (SQLException e) {
			ct=null;
			e.printStackTrace();
		}
		ct.setVisible(true);
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
		HomeEmpleado gc= new HomeEmpleado(this,this.getModel());
		gc.setVisible(true);
	}
	
	@Override
	public void cambiarAPelicula(HomeCliente hc) {//OK!
	}

	@Override
	public void cambiarARecTick(HomeEmpleado gc) {
		gc.setVisible(false);
		RecibirTiket rt= new RecibirTiket(this);
		rt.setVisible(true);
	}

	@Override
	public void cambiarAHomeEmpleado(RecibirTiket rt) {
		rt.setVisible(false);
		HomeEmpleado gc= new HomeEmpleado(this,this.getModel());
		gc.setVisible(true);
	}

	@Override
	public ResultSet getRSProd(String s) {
		return null;
	}
	
	@Override
	public boolean agregarStock(int nombre, int cant) {
		return false;
	}
}
