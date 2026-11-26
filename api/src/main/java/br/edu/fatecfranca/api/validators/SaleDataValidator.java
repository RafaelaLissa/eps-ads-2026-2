package br.edu.fatecfranca.api.validators;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.dtos.CarRequest;

public class SaleDataValidator extends RequestValidator<CarRequest> {

    public SaleDataValidator(RequestValidator<CarRequest> next) {
        super(next);
    }

    @Override
    protected void check(CarRequest request, Long carId) {
        LocalDate sellingDate = request.sellingDate();
        BigDecimal sellingPrice = request.sellingPrice();

        boolean hasDate = sellingDate != null;
        boolean hasPrice = sellingPrice != null;

        // 1. sellingDate e sellingPrice devem ser preenchidos juntos
        if (hasDate != hasPrice) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "A data de venda e o preço de venda devem ser preenchidos juntos."
            );
        }

        // 2. Quando informado, o preço deve ser maior que zero (checagem direta para zerar o aviso)
        if (sellingPrice != null && sellingPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O preço de venda deve ser maior que zero."
            );
        }
    }
}