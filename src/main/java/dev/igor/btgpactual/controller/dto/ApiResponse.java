package dev.igor.btgpactual.controller.dto;

import java.util.List;
import java.util.Map;

public record ApiResponse<T>(
        PaginationResponse pagination,
        Map<String, Object> summary,
        List<T> data) {
}
