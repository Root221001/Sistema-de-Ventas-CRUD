
package sistema_ventas;

public class Productos {
    private int id;
    private String nombre;
    private float precio;
    private String fecha; 
    private byte[] imagen;

   public Productos(int pid,String pnombre, float pprecio, String ffecha, byte[] iimagen ) {
       
       this.id = pid;
       this.nombre = pnombre;
       this.precio = pprecio;
       this.fecha = ffecha;
       this.imagen = iimagen;
       
}    
   public int getId(){ 
   return id;
   }
   
   public String getNombre(){
   return nombre;
   }
   
   public float getPrecio(){
   return precio;
   }
   
   public String getFecha(){
   return fecha;
   }
   
   public byte[] getImagen(){
   return imagen;
   }
}
