package cmd;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class comandos {

    private File dirActual;

    public comandos(String dirInicial) {
        dirActual = new File(dirInicial);
    }

    public String Mkdir(String direccion) {
        File carpeta = new File(direccion);
        if (carpeta.exists()) {
            return "Error: Esta carpeta ya existe.";
        } else {
            carpeta.mkdir();
            return "Carpeta creada existosamente.";
        }
    }

    public String Mfile(String path) {
        File archivo = new File(path);
        if (archivo.exists()) {
            return "Error: Este archivo ya existe.";
        } else {
            return "Archivo creado con éxito.";
        }
    }

    private void vaciarCarpeta(File aVaciar) {
        if (aVaciar.isDirectory()) {
            for (File vaciando : aVaciar.listFiles()) {
                vaciando.delete();
            }
        }
    }

    public String Rm(File aBorrar) {
        if (aBorrar.isDirectory()) {
            for (File borrando : aBorrar.listFiles()) {
                if (borrando.isDirectory()) {
                    vaciarCarpeta(borrando);
                    borrando.delete();
                } else {
                    borrando.delete();
                }
            }
            aBorrar.delete();
            return "Carpeta eliminada.";
        } else if (aBorrar.isFile()) {
            aBorrar.delete();
            return "Archivo eliminado.";
        }
        return "Error: La carpeta o archivo que desea eliminar no existe.";
    }

    public String Cd(String direccion) {
        if (direccion.charAt(0) != '/') {
            File nuevaDir = new File(dirActual.getAbsolutePath() + "/" + direccion);
            if (!nuevaDir.isDirectory()) {
                return "Error: La dirección ingresada debe ser una carpeta.";
            }
            dirActual = nuevaDir;
            System.out.println("La nueva dirección es: " + nuevaDir.getAbsolutePath());
            return "";
        }

        dirActual = new File(direccion);
        return "";
    }

    public String getCurrentPath() {
        try {
            return dirActual.getCanonicalPath();
        } catch (Exception e) {
            System.out.println("Error");
            return dirActual.getAbsolutePath();
        }

    }

    public String wr(String mensaje, String direccion) {
        File archEscribir = new File(direccion);
        if (archEscribir.exists()) {
            if (archEscribir.isFile()) {
                try {
                    FileWriter escribir = new FileWriter(archEscribir);
                    escribir.write(mensaje);
                    escribir.flush();
                } catch (IOException e) {
                    return "Error: No se pudo crear el archivo.";
                }
                return "Se escribió correctamente.";
            } else {
                return "Error: Primero debe seleccionar un archivo.";
            }
        } else {
            return "Error: Este archivo no existe.";
        }
    }

    public static String rd(String direccion) {
        File archLeer = new File(direccion);
        if (archLeer.exists()) {
            if (archLeer.isFile()) {
                try {
                    FileReader leer = new FileReader(archLeer);
                    String contenido = "";

                    for (int i = leer.read(); i != -1; i = leer.read()) {
                        contenido += (char) i;
                    }
                    return contenido;

                } catch (IOException e) {
                    return "Error: No se puede leer el archivo.";
                }
            } else {
                return "Error: Debe seleccionar un archivo para leerlo.";
            }
        } else {
            return "Error: Este archivo no existe.";
        }
    }

    public static String Dir(String direccion) {
        String lista = "";
        File listados = new File(direccion);

        if (listados.isDirectory()) {
            for (File archivos : listados.listFiles()) {
                lista += "\n->" + archivos.getName();
            }
            return lista;
        } else {
            return "Error: Debe seleccionar un directorio.";
        }
    }

   void date() {
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaForm = formatoFecha.format(fechaActual);
        System.out.println("Fecha actual: " + fechaForm);
    }
    void time() {
        Date horaActual = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaForm = formatoHora.format(horaActual);
        System.out.println("Hora actual: " + horaForm);
    }

    void escribir(String contenido) {
        try {
            if (dirActual == null) {
                JOptionPane.showMessageDialog(null, "Error, archivo inexistente.");
                return;
            }
            FileWriter escritor = new FileWriter(dirActual, true);
            BufferedWriter bw = new BufferedWriter(escritor);
            bw.write(contenido);
            bw.newLine();
            bw.close();
            JOptionPane.showMessageDialog(null, "Se ha escrito en el archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void leer() {
        try {
            if (dirActual == null) {
                JOptionPane.showMessageDialog(null, "Error, archivo inexistente.");

                return;
            }
            FileReader lector = new FileReader(dirActual);
            BufferedReader br = new BufferedReader(lector);
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
