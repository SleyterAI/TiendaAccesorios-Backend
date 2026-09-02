package com.ConsigueVentas.TiendaAccesorios.Dto.OrderDetail;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDetailRequestDto {
    @NotNull
    private Long productoId;

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer quantity;
}
