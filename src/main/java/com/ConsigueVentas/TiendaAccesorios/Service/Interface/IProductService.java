package com.ConsigueVentas.TiendaAccesorios.Service.Interface;

import com.ConsigueVentas.TiendaAccesorios.Dto.Product.Admin.ProductResponseAdminDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Product.ProductRequestDto;
import com.ConsigueVentas.TiendaAccesorios.Dto.Product.ProductResponseDto;
import com.ConsigueVentas.TiendaAccesorios.Entity.Product;

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
