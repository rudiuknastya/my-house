package com.example.myhouse24user.controller;

import com.example.myhouse24user.entity.InvoiceStatus;
import com.example.myhouse24user.model.invoice.InvoiceResponse;
import com.example.myhouse24user.model.invoice.ViewInvoiceResponse;
import com.example.myhouse24user.service.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping("")
    public ModelAndView getInvoicesPage() {
        return new ModelAndView("invoices/invoices");
    }
    @GetMapping("/get")
    public @ResponseBody ResponseEntity<Page<InvoiceResponse>> getInvoices(@RequestParam Map<String, String> requestMap) {
        return new ResponseEntity<>(invoiceService.getInvoiceResponses(requestMap), HttpStatus.OK);
    }
    @GetMapping("/get-statuses")
    public @ResponseBody InvoiceStatus[] getStatuses() {
        return InvoiceStatus.values();
    }
    @GetMapping("/{id}")
    public ModelAndView getInvoicesForApartmentPage() {
        return new ModelAndView("invoices/invoices");
    }
    @GetMapping("/view-invoice/{id}")
    public ModelAndView getViewInvoicePage() {
        return new ModelAndView("invoices/view-invoice");
    }
    @GetMapping("/view-invoice/get/{id}")
    public @ResponseBody ViewInvoiceResponse getViewInvoice(@PathVariable Long id) {
        return invoiceService.getViewInvoiceResponse(id);
    }
    @GetMapping("/view-invoice/download-in-pdf/{id}")
    public @ResponseBody ResponseEntity<byte[]> downloadInPdf(@PathVariable Long id) throws UnsupportedEncodingException {
        byte[] file = invoiceService.createPdfFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename="+ URLEncoder.encode("invoice_"+ LocalDate.now()+".pdf", "UTF-8"))
                .body(file);
    }
}
