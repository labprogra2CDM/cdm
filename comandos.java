package cmd;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class comandos {

    private File comandos = null;

    void setFile(String direccion) {
        comandos = new File(direccion);
    }

    void info() {
        if (comandos.exists()) {
            System.out.println("\nNombre: " + comandos.getName());
            System.out.println("Path: " + comandos.getPath());
            System.out.println("Absoluta: " + comandos.getAbsolutePath());
            System.out.println("Bytes: " + comandos.length());
            System.out.println("Modificado en: " + new Date(comandos.lastModified()));
            System.out.println("Padre: " + comandos.getAbsoluteFile().getParentFile().getName());
            if (comandos.isFile()) {
                System.out.println("Es file.");
            } else if (comandos.isDirectory()) {
                System.out.println("Es folder.");
            }
        } else {
            System.out.println("Error: Este objeto no existe");
        }
    }

    boolean Mfile() throws IOException {
        return comandos.createNewFile();
    }

    boolean Mkdir() {
        return comandos.mkdirs();
    }

    boolean rm(File f) {
        if (f.isDirectory()) {
            for (File child : f.listFiles()) {
                rm(child);
            }
        }
        return f.delete();
    }

    boolean Rm() {
        return rm(comandos);
    }

    void dir() {
        if (comandos.isDirectory()) {
            System.out.println("Directorio de: " + comandos.getAbsolutePath());
            System.out.println(" ");
            int cfiles = 0, cdirs = 0, tbytes = 0;

            for (File child : comandos.listFiles()) {
                if (!child.isHidden()) {
                    //Ultima modificacion
                    Date ultima = new Date(child.lastModified());
                    System.out.println(ultima + "\t");
                    //Si es File o Dir
                    if (child.isDirectory()) {
                        cdirs++;
                        System.out.println("<DIR>\t\t");
                    } else {
                        cfiles++;
                        tbytes += child.length();
                    }
                    System.out.println(child.getName());
                }
            }
            System.out.println(cfiles + " archivos\t" + tbytes + " bytes"
                    + "\n" + cdirs + " dirs");
        }
    }
}
