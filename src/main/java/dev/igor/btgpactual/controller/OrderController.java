package dev.igor.btgpactual.controller;

import dev.igor.btgpactual.controller.dto.ApiResponse;
import dev.igor.btgpactual.controller.dto.OrderResponse;
import dev.igor.btgpactual.controller.dto.PaginationResponse;
import dev.igor.btgpactual.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/customers/{custerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @PathVariable("custerId") Long customerId
    ) {
        var response = service.findAllByCustomerId(customerId, PageRequest.of(page, size));
        var totalOnOrders = service.findTotalOnOrdersByCustomerId(customerId);
        return ResponseEntity.ok(new ApiResponse<>(
                PaginationResponse.fromPage(response),
                Map.of("totalOnOrders", totalOnOrders),
                response.getContent()
        ));
    }
}
