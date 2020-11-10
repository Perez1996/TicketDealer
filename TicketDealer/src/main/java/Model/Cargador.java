package Model;
import Vistas.*;
import Controlador.*;
import Resources.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;
import java.util.ArrayList;

import java.util.HashMap;



public class Cargador implements ModelSubject{

	private ArrayList<ViewObserver> observers;
	CallableStatement cs;
	Connect cn;
    ResultSet rs ;
    PreparedStatement ps;
    Statement s ;
    ArrayList<Compra> compraEmpleado;
    ArrayList<Compra> compraCliente;
    
	public Cargador() throws SQLException{
		observers= new ArrayList<ViewObserver>();
		compraEmpleado=new ArrayList<Compra>();
		compraCliente=new ArrayList<Compra>();
        cn = new Connect();
	}
	
	public boolean validarAdmin(String usua, String pass) throws SQLException{ 
		s = cn.getConnection().createStatement();
        rs = s.executeQuery ("select * from usuarios where UsuTipo='a'");
        while (rs.next()){
        	if (rs.getString(2).toLowerCase().trim().equalsIgnoreCase(usua)&&rs.getString(3).toLowerCase().trim().equalsIgnoreCase(pass)){
        		return true;
        	}
        }
        return false;
	}
	
	public boolean validarEmpleado(String usua, String pass) throws SQLException{ 
		s = cn.getConnection().createStatement();
	    rs = s.executeQuery ("select * from usuarios where UsuTipo='e'");           
	    while (rs.next()){
		     if (rs.getString(2).equalsIgnoreCase(usua)&&rs.getString(3).equalsIgnoreCase(pass)){
		    	 return true;
		     }
	    }          
		return false;
	}
	
	public boolean cargarAdmin(String user, String pass) throws SQLException{
		cs=cn.getConnection().prepareCall("call crearAdmin(?,?)");
		cs.setString(1, user);
		cs.setString(2, pass);
		cs.executeUpdate();
                return true;
	}
	
        public void quitarStock (int idprod,int cant) throws SQLException{
        cs= cn.getConnection().prepareCall("{call RestaStock(?,?)}");
        cs.setInt("p_ProdId", idprod);
        cs.setInt("Cantidad", cant);
        cs.executeUpdate();
        this.notifyObserver();
        }
    
        public void agregarStock(int idprod,int cant) throws SQLException{
        cs= cn.getConnection().prepareCall("{call SumaStock(?,?)}");
        cs.setInt("p_ProdId", idprod);
        cs.setInt("Cantidad", cant);
        cs.executeUpdate();
        this.notifyObserver();
        }
        
	public boolean creaUser (String user,String pass, String tipo) throws SQLException{
        cs = cn.getConnection().prepareCall("{call CreaUsuario(?,?,?)}");
        cs.setString("username", user);
        cs.setString("pass", pass);
        cs.setString("tipouser", tipo);
        cs.executeUpdate();
        return true;
        }
	
        public boolean borraUser (String user) throws SQLException{
        cs = cn.getConnection().prepareCall("{call BorraUsuario(?)}");
        cs.setString("username", user);
        cs.executeUpdate();
        return true;
        }
    
        public int getStockProducto(int idProd) throws SQLException {
    	ps = cn.getConnection().prepareStatement("select ProdCant from productos where ProdId=?");
    	ps.setInt(1,idProd);
    	rs = ps.executeQuery();
    	int id =0;
    	while (rs.next()) {
    		id = rs.getInt(1);
    	}
    	return id;
    }
    
        public int getIdPorNombre(String nombreProd) throws SQLException {
    	ps = cn.getConnection().prepareStatement("select ProdId from productos where ProdNom = ?");
    	ps.setString(1,  nombreProd);
    	rs = ps.executeQuery();
    	int id=0;
		while(rs.next()) {
    		id = rs.getInt(1);
    	}
    	return id;
    }
    
    public boolean creaProducto (String prodnom,double prodprecio,String prodtipo, String prodcoment) throws SQLException{
        cs = cn.getConnection().prepareCall("{call CreaProducto(?,?,?,?)}");
        cs.setString("prodnom", prodnom);
        cs.setDouble("prodprecio", prodprecio);
        cs.setString("prodtipo", prodtipo);
        cs.setString("prodcoment", prodcoment);
        cs.executeUpdate();
        this.notifyObserver();
        return true;
        }
    
    public void borraProducto (int idProd) throws SQLException{
        cs = cn.getConnection().prepareCall("{call BorraProducto(?)}");
        cs.setInt("p_idprod", idProd);
        cs.executeUpdate();
        this.notifyObserver();
    }
        
    public int getIdProd() throws SQLException{
        s = cn.getConnection().createStatement();
        rs = s.executeQuery ("SELECT ProdId FROM productos ORDER BY ProdId DESC limit 1;");          
        while (rs.next()){
        	return rs.getInt(1)+1;
        }
        return 0;        
    }
    
    public int getCantidad(int idProd) throws SQLException {
    	ps = cn.getConnection().prepareStatement("select ProdCant from productos where ProdId=? limit 1");
    	ps.setInt(1, idProd);
    	rs = ps.executeQuery();
    	int cant = 0;
    	while (rs.next()){
    		cant=rs.getInt(1);
		}
    	return cant;
    }
             
    public void agregaItem(int idProd,String descProd,int cantidad,double prodPrecio,double precFinal, String codigoCompra) throws SQLException{
        cs = cn.getConnection().prepareCall("{call agregaItem(?,?,?,?,?,?)}");
        cs.setString(1, descProd);
        cs.setInt(2, cantidad);
        cs.setDouble(3, prodPrecio);
        cs.setDouble(4, precFinal);
        cs.setString(5, codigoCompra);
        cs.setInt(6, idProd);
        
        cs.executeUpdate();
    }
   
    public void quitarItem(int idcompra) throws SQLException{
       cs = cn.getConnection().prepareCall("call borrarItem(?)");
       cs.setInt(1,idcompra);
       cs.executeUpdate();
    }
    
    
   
    
    
    
    
    
    
    public ResultSet obtenerCompra(String codigoCompra) throws SQLException{
        ps = cn.getConnection().prepareStatement("select * from compra where codigocompra = ?");
        ps.setString(1, codigoCompra);
        rs = ps.executeQuery();
        return rs;
    }
        
    public int getIdRs(ResultSet rs, String ProdNom) throws SQLException {
        while (rs.next()){
        if (rs.getString(2).equalsIgnoreCase(ProdNom)){
            return rs.getInt(1);
        	}
        }
        return 0;
    }
        
    public double getPrecio(int idProd) throws SQLException{
        ps = cn.getConnection().prepareStatement("select prodPrecio from productos where ProdId = ?");
        ps.setDouble(1, idProd);
        rs = ps.executeQuery();
        while (rs.next()){
        	return rs.getDouble(1);
        } 
        return 0;
    }
    
  
        
    @Override
	public void registerObserver(ViewObserver o) {
		observers.add(o);			
	}

	@Override
	public void removeObserver(ViewObserver o) {
		int i= observers.indexOf(o);
		if(i>=0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObserver() {
		for(int i=0; i<observers.size(); i++) {
			ViewObserver observer = (ViewObserver) observers.get(i);
			observer.update();
		}
	}
		
	public ResultSet getAsientosLibres(int idEvento) throws SQLException{
        ps = cn.getConnection().prepareStatement("select * from eventos where ocupado = 0 and idevento = ?");
        ps.setInt(idEvento, idEvento);
        return ps.executeQuery();
   }
	
   public ResultSet getEventos() throws SQLException{//OK!
       s = cn.getConnection().createStatement();
       rs = s.executeQuery("select idevento,nombre,ubicacion,numentrada,precio from eventos");
       
       return rs;
   }
   
   public int getIdEvento(String nomEvento)throws SQLException{
	   int id=0;
	   ps=cn.getConnection().prepareStatement("select idevento from eventos where nombre= ?");
	   ps.setString(1, nomEvento);
	   rs=ps.executeQuery();
	   while(rs.next()){
		   id=rs.getInt(1);
	   }
	   return id;
   }
      
   public ResultSet getRecibo(String codCompra) throws SQLException{
       ps = cn.getConnection().prepareStatement("select * from compra where codigocompra = ?");
       ps.setString(1, codCompra);
       rs = ps.executeQuery();
       return rs;
   }
      
   public void setOcupado(int idevento,int numentrada) throws SQLException{
       cs = cn.getConnection().prepareCall("call setOcupado(?,?)");
       cs.setInt("p_idevento",idevento);
       cs.setInt("p_numentrada",numentrada);
       cs.executeUpdate();
   }
   public void setDescupado(int idevento,int numentrada) throws SQLException{
       cs = cn.getConnection().prepareCall("call setDesocupado(?,?)");
       cs.setInt("p_idevento",idevento);
       cs.setInt("p_numentrada",numentrada);
       cs.executeUpdate();
   }
      
        public ResultSet CargarStock() throws SQLException{
		s = cn.getConnection().createStatement();
		rs = s.executeQuery("select * from productos");
		return rs;
        }

	public ResultSet CargarCompra(String codigoCompra) throws SQLException {
		ps = cn.getConnection().prepareStatement("select * from compra where codigocompra = ?") ;
		ps.setString(1, codigoCompra);
		rs = ps.executeQuery();
		return rs;
	}
	
	public void GuardarCompra(Compra objCompra){
		compraCliente.add(objCompra);
	}
	
	public void GuardarCompraEmpleado(Compra objCompra){
		compraEmpleado.add(objCompra);
	}
	
	public Compra getObjCompra(){
		return compraCliente.get(0);
	}
	public Compra getObjCompraEmpleado(){
		return compraEmpleado.get(0);
	}
	
	public ResultSet getUsuarios() throws SQLException{
		s = cn.getConnection().createStatement();
		rs =s.executeQuery("select * from usuarios");
		return rs;
	}
	
	
	
   public void comprarEntrada(int p_idevento,String p_codCompra,int p_numentrada) throws SQLException{
       cs = cn.getConnection().prepareCall("call comprarEntrada(?,?,?)");
       cs.setInt(1, p_idevento);
       cs.setString(2, p_codCompra);
       cs.setInt(3, p_numentrada);
       cs.executeUpdate();
    }
   public void devolverEntrada() throws SQLException{
        ps = cn.getConnection().prepareStatement("Delete from compra where idcompra=? ");
    	ps.setInt(1, ultimaCompra());
    	ps.executeUpdate();
    }
   public int ultimaCompra() throws SQLException{
        s = cn.getConnection().createStatement();
        rs = s.executeQuery ("SELECT idcompra FROM compra ORDER BY idcompra DESC limit 1;");          
        while (rs.next()){
        	return rs.getInt(1);
        }
        return 0; 
   }
        
    public int getIdAsiento(String Fila, int Columna){
        if(Fila.equalsIgnoreCase("A")) return 0*8+Columna ;
        if(Fila.equalsIgnoreCase("B")) return 1*8+Columna ;
        if(Fila.equalsIgnoreCase("C")) return 2*8+Columna ;
        if(Fila.equalsIgnoreCase("D")) return 3*8+Columna ;
        if(Fila.equalsIgnoreCase("E")) return 4*8+Columna ;
        if(Fila.equalsIgnoreCase("F")) return 5*8+Columna ;
        if(Fila.equalsIgnoreCase("G")) return 6*8+Columna ;
        if(Fila.equalsIgnoreCase("H")) return 7*8+Columna ;
        if(Fila.equalsIgnoreCase("I")) return 8*8+Columna ;
        if(Fila.equalsIgnoreCase("J")) return 9*8+Columna ;
        return 0;
    }
        
    public boolean estaOcupado(int p_idevento,int p_numentrada) throws SQLException{
        ps = cn.getConnection().prepareStatement("select ocupado from eventos where idevento = ? and numentrada = ?");
        ps.setInt(1, p_idevento);
        ps.setInt(2, p_numentrada);
        rs= ps.executeQuery();
        int ocupado=0;
        while(rs.next()){
        	ocupado=rs.getInt(1);
        }
        if (ocupado == 0)
            return false;
        else 
            return true;
    }
        
        //metodos de finalizar compra
        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        
        
    public void finalizarCompra(String codCompra, String descCompra, Double total) throws SQLException{
        cs = cn.getConnection().prepareCall("call finalizarCompra(?,?,?,?)");
        cs.setString(1,codCompra);
        cs.setString(2, descCompra);
        cs.setDouble(3, total);
        cs.setString(4,getFecha().toString());
        cs.executeUpdate();
    }
    public void borrarCompraFinalizada(String codigocompra) throws SQLException {
        cs = cn.getConnection().prepareCall("Delete from finalizadas where codigocompra = ?");
    	cs.setString(1, codigocompra);
    	cs.executeUpdate();
    }
    
        
    public ResultSet getCompraFinalizada(String codCompra) throws SQLException{
        ps = cn.getConnection().prepareStatement("select * from finalizadas where codigoCompra = ?");
        ps.setString(1,codCompra);
        rs = ps.executeQuery();
        return rs;
    }
    
    public String getDescVenta(String codCompra) throws SQLException{
	ps = cn.getConnection().prepareStatement("select descProd from compra where codigoCompra = ?");
        ps.setString(1,codCompra);
        rs = ps.executeQuery();
        String text="";
        while(rs.next()){
        	text= text + rs.getString(1);	        	
        }
    	return text;
	}
    
    public double getPrecioFinal(String codCompra) throws SQLException{
    	double preciofinal=0.0;
    	ps = cn.getConnection().prepareStatement("select precfinal from compra where codigoCompra=?");
    	ps.setString(1,codCompra);
    	rs = ps.executeQuery();
    	while(rs.next()){
    		preciofinal = preciofinal + rs.getDouble(1)    ;    	
    	}
    	return preciofinal;
    }
        
    public Date getFecha()throws SQLException{
    	Date fec=null;
    	s = cn.getConnection().createStatement();
    	rs = s.executeQuery("select SYSDATE()");
    	while(rs.next()){
    		fec = rs.getDate(1);
    	}
    	return fec;
    }
    
    public String getFechaCompra(String codCompra)throws SQLException{
    	String fec="";
    	ps = cn.getConnection().prepareStatement("select fecha from finalizadas where idcompra = ?");
    	ps.setString(1, codCompra);
    	rs = ps.executeQuery();
    	while(rs.next()){
    		fec = rs.getString(1);
    	}
    	return fec;
	}
    
    public void asociarEmpleado(int empleado,String formaPago,String codCompra) throws SQLException{
    	cs = cn.getConnection().prepareCall("call asociarEmpleado(?,?,?)");
    	cs.setInt(1, empleado);
    	cs.setString(2, formaPago);
    	cs.setString(3, codCompra);
    	cs.executeUpdate();
           this.notifyObserver();
    }
    public String getFormaPago(String codCompra) throws SQLException{
        String formapago="";
        ps = cn.getConnection().prepareStatement("select formapago from finalizadas where codigocompra = ?");
    	ps.setString(1, codCompra);
    	rs = ps.executeQuery();
    	while(rs.next()){
    		formapago = rs.getString(1);
    	}
    	return formapago; 
    }
    public void quitarFormaPago(String codCompra) throws SQLException{
        cs = cn.getConnection().prepareCall("call quitarFormapago(?)");
    	cs.setString(1, codCompra);
    	cs.executeUpdate();
        
    }
    
    public ResultSet getTablaObserver() throws SQLException{
        s = cn.getConnection().createStatement();
        rs = s.executeQuery("select * from finalizadas where empleado = 1");
        return rs;
    }
        
    public void renovarClave(int idUsuario, String newPass) throws SQLException{
	    cs = cn.getConnection().prepareCall("call newPass(?,?)");
	    cs.setInt("idUsuario", idUsuario);
	    cs.setString("newPass", newPass);
	    cs.executeUpdate();
    }
    
    public int getIdUsuario(String user, String pass) throws SQLException{
	    ps = cn.getConnection().prepareStatement("select idUsuarios from usuarios where UsuNombre=? and UsuClave=?");
	    ps.setString(1, user);
	    ps.setString(2, pass);
	    rs=ps.executeQuery();
	    int id=0;
	    while(rs.next()){
	    	id = rs.getInt(1);
	    }
	    return id;
    }

    public ResultSet getCarrito(String idCompra) throws SQLException{
           ps =cn.getConnection().prepareStatement("select descprod,prodprecio from compra where idcompra like ?");
           ps.setString(1, idCompra);
           rs=ps.executeQuery();
           return rs;
    }
    
    
    
    
}
