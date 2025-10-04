package com.example.backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.servicio.pdf.EntradasPDF;
import com.example.backend.servicio.pdf.ProductoPDF;
import com.example.backend.servicio.pdf.ProveedorPDF;
import com.example.backend.servicio.pdf.SalidasPDF;
import com.example.backend.servicio.pdf.UsuarioAdministradorPDF;
import com.example.backend.servicio.pdf.UsuarioOperadorPDF;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/pdf")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class PdfController {
	@Autowired
	private ProductoPDF pdfService;
	@Autowired
	private SalidasPDF salidasPDF;

	@Autowired
	private EntradasPDF entradasPDF;
	
	@Autowired
	private UsuarioAdministradorPDF administradorPDF;
	
	@Autowired
	private UsuarioOperadorPDF operadorPDF;
	
	@Autowired
	private ProveedorPDF proveedorPDF;
	
	@GetMapping("/generar-productos")
	public ResponseEntity<byte[]> generarInformePdfProdcutos() throws IOException, DocumentException {
		byte[] pdfBytes = pdfService.generarInformePdf();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("inline", "informe_productos_activos.pdf");

		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}

	@GetMapping("/generar-salidas")
	public ResponseEntity<byte[]> generarInformePdfSalidas() throws IOException, DocumentException {
		byte[] pdfBytes = salidasPDF.generarInformePdf();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("inline", "informe_detalle_salidas_productos.pdf");

		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}
	
	@GetMapping("/generar-entradas")
	public ResponseEntity<byte[]> generarInformePdfEntradas() throws IOException, DocumentException {
		byte[] pdfBytes = entradasPDF.generarInformePdf();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("inline", "informe_detalle_entradas_productos.pdf");

		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}
	
	
	@GetMapping("/generar-proveedor")
	public ResponseEntity<byte[]> generarInformePdfProveedor() throws IOException, DocumentException {
		byte[] pdfBytes = proveedorPDF.generarInformePdf();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("inline", "informe_detalle_proveedores.pdf");

		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}/*
	@GetMapping("/generar-administrador")
	public ResponseEntity<byte[]> generarInformePdfAdmin() throws IOException, DocumentException {
		byte[] pdfBytes = administradorPDF.generarInformePdf();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("inline", "informe_administradores.pdf");

		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}
	@GetMapping("/generar-operador")
	public ResponseEntity<byte[]> generarInformePdfOperador() throws IOException, DocumentException {
		byte[] pdfBytes = operadorPDF.generarInformePdf();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("inline", "informe_operador.pdf");

		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}*/
}
