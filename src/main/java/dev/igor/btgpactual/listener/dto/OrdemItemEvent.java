package dev.igor.btgpactual.listener.dto;

import java.math.BigDecimal;

public record OrdemItemEvent(
        String produto,
        Integer quantidade,
        BigDecimal preco
) {
}
