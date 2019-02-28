package app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import app.domain.Sale;
import app.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sales")
public class SalesController {

    @Autowired
    private SalesService service;

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> getSales() {
        Map<String, Object> response = new HashMap<>();
        response.put("statistics", this.service.getStatisticsByLastMinute());

        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Sale> postSales(@Valid @RequestBody Sale body) {
        Sale sale = this.service.saveSale(body);

        if (sale != null)
            return new ResponseEntity<Sale>(sale, HttpStatus.CREATED);
        else
            return new ResponseEntity<Sale>(sale, HttpStatus.NO_CONTENT);
    }
}
