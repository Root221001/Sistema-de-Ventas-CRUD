package sistema_ventas;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafaelito AA
 */
public class ventana_principal extends javax.swing.JFrame {

    /**
     * Creates new form ventana_principal
     */
    public ventana_principal() {
        initComponents();
        SHOW_PRODUCT_IN_JTABLE();

    }

    String ImgPath = null;
    int pos= 0;

    public Connection getConection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/productos_db", "root", "");
           // JOptionPane.showMessageDialog(null, "Conectado");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(ventana_principal.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showMessageDialog(null, "No Conectado");
            return null;
        }
    }
    
    //Verificar campos de entrada
     public boolean checkInputs(){
         if(txt_nombre.getText() == null  || txt_precio.getText() == null || txt_fecha.getDate() == null){
         return false;
         }else{
                try{
                Float.parseFloat(txt_precio.getText());
                return true;
                }catch(Exception ex){
                return false;
                }
         }
     }

    //Redimensionar imagen
    public ImageIcon RedimensionarImg(String imagePath, byte[] pic) {
        ImageIcon myImage = null;

        if (imagePath != null) {
            myImage = new ImageIcon(imagePath);
        } else {
            myImage = new ImageIcon(pic);
        }
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lbl_imagen.getWidth(), lbl_imagen.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

// Mostrar datos en jtable

//  Llenar la lista de matriz con los datos
    public ArrayList<Productos> getProductList(){
        
         ArrayList<Productos> productolist = new ArrayList<Productos>();
            Connection con = getConection();
            String query = "SELECT * FROM productos";
            
            Statement st;
            ResultSet rs;
        try {
           
            st = con.createStatement();
            rs = st.executeQuery(query); 
            Productos product;
            
            while(rs.next()){
            product = new Productos(rs.getInt("id"),rs.getString("nombre"), 
                    Float.parseFloat(rs.getString("precio")), rs.getString("fecha"), rs.getBytes("imagen"));
            
            productolist.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventana_principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productolist;
    }
    
    //Mostrar productos en el Jtable
    public void SHOW_PRODUCT_IN_JTABLE(){
    
        ArrayList<Productos> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)JTable_Productos.getModel();
        
        //Limpiar contenido
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i= 0 ; i < list.size(); i++){
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getNombre();
                row[2] = list.get(i).getPrecio();
                row[3] = list.get(i).getFecha();
               
                model.addRow(row);
                   }    
    }
    
    public void SHOW_ITEM(int index){
    
            txt_id.setText(Integer.toString(getProductList().get(index).getId()));
            txt_nombre.setText(getProductList().get(index).getNombre());
            txt_precio.setText(Float.toString(getProductList().get(index).getPrecio()));
        try {
            
            Date fecha = null;
            fecha = new  SimpleDateFormat("yyyy-MM-dd").parse(((String)getProductList().get(index).getFecha()));
            txt_fecha.setDate(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(ventana_principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl_imagen.setIcon(RedimensionarImg(null, getProductList().get(index).getImagen()));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        lbl_imagen = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Productos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btn_elegir_imagen = new javax.swing.JButton();
        btn_insertar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_Ultimo = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_Primero = new javax.swing.JButton();
        btn_Siguiente = new javax.swing.JButton();
        btn_Anterior = new javax.swing.JButton();
        txt_fecha = new com.toedter.calendar.JDateChooser();
        btn_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel1.setText("ID :");

        jLabel2.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel3.setText("Fecha :");

        jLabel4.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel4.setText("Precio :");

        jLabel5.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel5.setText("Imagen :");

        txt_id.setFont(new java.awt.Font("Century Schoolbook", 1, 12)); // NOI18N
        txt_id.setEnabled(false);
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        txt_precio.setFont(new java.awt.Font("Century Schoolbook", 1, 12)); // NOI18N
        txt_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_precioActionPerformed(evt);
            }
        });

        txt_nombre.setFont(new java.awt.Font("Century Schoolbook", 1, 12)); // NOI18N
        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });

        lbl_imagen.setBackground(new java.awt.Color(102, 102, 102));
        lbl_imagen.setOpaque(true);

        JTable_Productos.setBackground(new java.awt.Color(102, 102, 102));
        JTable_Productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Precio", "Fecha"
            }
        ));
        JTable_Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Productos);

        jLabel7.setFont(new java.awt.Font("Century Schoolbook", 1, 24)); // NOI18N
        jLabel7.setText("Sistema de Ventas");

        btn_elegir_imagen.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        btn_elegir_imagen.setText("Elegir Imagen");
        btn_elegir_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_elegir_imagenActionPerformed(evt);
            }
        });

        btn_insertar.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        btn_insertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_ventas/iconos/099-floppy-disk_1.png"))); // NOI18N
        btn_insertar.setText("Insertar");
        btn_insertar.setIconTextGap(15);
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarActionPerformed(evt);
            }
        });

        btn_eliminar.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_ventas/iconos/272-cross.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setIconTextGap(15);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_Ultimo.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        btn_Ultimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_ventas/iconos/291-last.png"))); // NOI18N
        btn_Ultimo.setText("Ultimo");
        btn_Ultimo.setIconTextGap(15);
        btn_Ultimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UltimoActionPerformed(evt);
            }
        });

        btn_actualizar.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_ventas/iconos/103-redo.png"))); // NOI18N
        btn_actualizar.setText("Actualizar");
        btn_actualizar.setIconTextGap(15);
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });

        btn_Primero.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        btn_Primero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_ventas/iconos/290-first.png"))); // NOI18N
        btn_Primero.setText("Primero");
        btn_Primero.setIconTextGap(15);
        btn_Primero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrimeroActionPerformed(evt);
            }
        });

        btn_Siguiente.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        btn_Siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_ventas/iconos/293-next2.png"))); // NOI18N
        btn_Siguiente.setText("Siguiente");
        btn_Siguiente.setIconTextGap(15);
        btn_Siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SiguienteActionPerformed(evt);
            }
        });

        btn_Anterior.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        btn_Anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_ventas/iconos/292-previous2.png"))); // NOI18N
        btn_Anterior.setText("Anterior");
        btn_Anterior.setIconTextGap(15);
        btn_Anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AnteriorActionPerformed(evt);
            }
        });

        txt_fecha.setDateFormatString("yyyy-MM-dd");

        btn_salir.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        btn_salir.setText("Cerrar");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_precio)
                                    .addComponent(txt_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_elegir_imagen)
                        .addGap(44, 44, 44)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(btn_Primero)
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_Ultimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Siguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_Anterior)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)))))))
                .addGap(11, 11, 11))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_insertar)
                .addGap(30, 30, 30)
                .addComponent(btn_eliminar)
                .addGap(18, 18, 18)
                .addComponent(btn_actualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(3, 3, 3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_elegir_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_actualizar))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Primero, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Anterior))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Ultimo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(57, 57, 57))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precioActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void btn_elegir_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_elegir_imagenActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_imagen.setIcon(RedimensionarImg(path, null));
            ImgPath = path;

        } else {
            System.out.println("Archivo no seleccionado");
        }

    }//GEN-LAST:event_btn_elegir_imagenActionPerformed

    private void btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarActionPerformed
      if( checkInputs() && ImgPath != null){
          
          
          try {
              Connection con = getConection();
              PreparedStatement ps = con.prepareStatement("INSERT INTO productos (nombre, precio, fecha, imagen)"
                      + "VALUES (?,?,?,?)");
              ps.setString(1, txt_nombre.getText());
              ps.setString(2, txt_precio.getText());
              
              SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
              String fecha = dateFormat.format(txt_fecha.getDate());
              ps.setString(3, fecha);
              
              InputStream img = new FileInputStream(new File(ImgPath));
              ps.setBlob(4, img);
              ps.executeUpdate();
              SHOW_PRODUCT_IN_JTABLE();
              
               JOptionPane.showMessageDialog(null, "Datos Guardados");
              
           } catch (Exception ex) {
              JOptionPane.showMessageDialog(null, ex.getMessage());
          }  
      }else{
           JOptionPane.showMessageDialog(null, "Uno o mas campos vacios");
      }    
        
    }//GEN-LAST:event_btn_insertarActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
       
        if(checkInputs() && txt_id.getText() != null){
         
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConection();
            
             //Actualizar sin Imagen
            if(ImgPath == null){
                try {
                    UpdateQuery = "UPDATE productos SET nombre= ?, precio= ?"
                            + ", fecha= ? WHERE id= ?";
                    
                    ps = con.prepareStatement(UpdateQuery);
                    ps.setString(1, txt_nombre.getText());
                    ps.setString(2, txt_precio.getText());
                    
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    String fecha = dateformat.format(txt_fecha.getDate());
                    ps.setString(3, fecha);
                    
                    ps.setInt(4, Integer.parseInt(txt_id.getText()));
                    
                    ps.executeUpdate();
                    SHOW_PRODUCT_IN_JTABLE();
                    JOptionPane.showMessageDialog(null, "Producto Actualizado");
                     
                } catch (SQLException ex) {
                    Logger.getLogger(ventana_principal.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
            //Actualizar con imagen
                else{
                   try{
                           InputStream img = new FileInputStream(new File(ImgPath));
                           UpdateQuery = "UPDATE productos SET nombre= ?, precio= ?,"
                            + "fecha= ?, imagen= ?  WHERE id= ?";
                           
                    ps = con.prepareStatement(UpdateQuery);      
                    ps.setString(1, txt_nombre.getText());
                    ps.setString(2, txt_precio.getText());
                    
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    String fecha = dateformat.format(txt_fecha.getDate());
                    ps.setString(3, fecha);
                    
                    ps.setBlob(4, img);
                    
                    ps.setInt(5, Integer.parseInt(txt_id.getText()));
                    
                   ps.executeUpdate();
                   SHOW_PRODUCT_IN_JTABLE();
                   JOptionPane.showMessageDialog(null, "Producto Actualizado");
                   }catch(Exception ex){
                   
                   JOptionPane.showMessageDialog(null, ex.getMessage());
                   }
            }  
        }
                   else{
                    JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios o son incorrectos");
                    }
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        
        if(!txt_id.getText().equals("")){
            
            try {
                Connection con = getConection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM productos WHERE id = ?");
                int id = Integer.parseInt(txt_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                SHOW_PRODUCT_IN_JTABLE();
                JOptionPane.showMessageDialog(null, "Producto Borrado");
                
            } catch (SQLException ex) {
                Logger.getLogger(ventana_principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Producto No Borrado");
            }
        }
        else{
            
            JOptionPane.showMessageDialog(null, "Producto Borrado: No id a eliminar");
        
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void JTable_ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductosMouseClicked
       
        int index = JTable_Productos.getSelectedRow();
        SHOW_ITEM(index);
        
    }//GEN-LAST:event_JTable_ProductosMouseClicked

    private void btn_PrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrimeroActionPerformed
       
        pos = 0;
        SHOW_ITEM(pos);
    }//GEN-LAST:event_btn_PrimeroActionPerformed

    private void btn_UltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UltimoActionPerformed
       
        pos = getProductList().size() -1;
        SHOW_ITEM(pos);
    }//GEN-LAST:event_btn_UltimoActionPerformed

    private void btn_SiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SiguienteActionPerformed
       
        pos ++;
        if(pos >= getProductList().size()){
        pos = getProductList().size() -1;
        
        }
        SHOW_ITEM(pos);
    }//GEN-LAST:event_btn_SiguienteActionPerformed

    private void btn_AnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AnteriorActionPerformed
   
        pos --;
        if(pos < 0){
        
        pos = 0;
        }
         SHOW_ITEM(pos);
    }//GEN-LAST:event_btn_AnteriorActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
         System.exit(0);
    }//GEN-LAST:event_btn_salirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventana_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventana_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventana_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventana_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventana_principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Productos;
    private javax.swing.JButton btn_Anterior;
    private javax.swing.JButton btn_Primero;
    private javax.swing.JButton btn_Siguiente;
    private javax.swing.JButton btn_Ultimo;
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_elegir_imagen;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_imagen;
    private com.toedter.calendar.JDateChooser txt_fecha;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    // End of variables declaration//GEN-END:variables
}
