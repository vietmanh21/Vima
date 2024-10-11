package com.manhnguyen21.common.dto.response;

import java.util.List;

public record ErrorVm(String statusCode, String title, String detail, List<String> fieldErrors) {

}
