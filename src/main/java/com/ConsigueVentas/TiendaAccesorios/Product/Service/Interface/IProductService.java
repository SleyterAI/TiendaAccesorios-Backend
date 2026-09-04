package com.ConsigueVentas.TiendaAccesorios.Product.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.Product.Dto.Admin.ProductResponseAdminDto;
import com.ConsigueVentas.TiendaAccesorios.Product.Dto.ProductRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Product.Entity.Product;

import java.util.List;

public interface IProductService {

    //Create
    Product createProduct(ProductRequestDto product);

    //Read
    List<Product> getAllProduct();
    List<ProductResponseAdminDto> getAllProductAdmin();
    Product getProductById(Long id);

    //Update
    Product updateProduct(Long id, ProductRequestDto product);

    //Delete
    void deleteProduct(Long id);
}
