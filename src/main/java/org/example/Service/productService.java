package org.example.Service;

import org.example.Model.Product;
import org.example.Model.RemoveToString;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class productService implements IProductservices {
    private List<Product> productList;
    private int code = 1;//autoincrementable

    public List<Product> getProductList() {
        return productList;
    }

    public productService() {
        productList = new ArrayList<>();
    }
    public List<Product> obtenerProductos() {
        return productList;
    }

    @Override
    public void agregarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un nuevo producto: ");
        String name = scanner.next();
        System.out.print("Ingrese una descripcion ");
        String description = scanner.next();
        System.out.print("Ingrese la categoria ");
        String category = scanner.next();
        System.out.print("Ingrese  la etiqueta: ");
        String label = scanner.next();
        System.out.print("Ingrese el precio: ");
        Double price = scanner.nextDouble();
        System.out.print("Ingrese  la imagen del producto: ");
        String url = scanner.next();
        // Añadiendo los productos
        productList.add(new Product(code, name, description, category, label, price, url));
        productList.stream().forEach(x -> System.out.println(x));
        System.out.println(toString());


    }
    public void cargarProductosDesdeCSV(String archivoCSV) {
        try {
            File file = new File(archivoCSV);
            Scanner scanner = new Scanner(file);
            // Saltar la primera línea si contiene encabezados
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(",");

                if (campos.length == 7) {
                    int code = Integer.parseInt(campos[0].trim());
                    String nombre = campos[1].trim();
                    String descripcion = campos[2].trim();
                    String categoria = campos[3].trim();
                    String etiqueta = campos[4].trim();
                    double precio = Double.parseDouble(campos[5].trim());
                    String url = campos[6].trim();
                    Product producto = new Product(code, nombre, descripcion, categoria, etiqueta, precio, url);
                    productList.add(producto);
                    RemoveToString remove = new RemoveToString(code, nombre, descripcion, categoria, etiqueta, precio, url);
                    System.out.println(remove);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
