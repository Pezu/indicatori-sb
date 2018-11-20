package com.medivac.indicatori.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medivac.indicatori.domain.*;

import com.medivac.indicatori.service.CatalogService;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

	// folosim catalog pentru "nomenclator" cred ca asa ii zici tu. Ala de iti definesti unitati de masura, tipuri de chestii, etc
	
    private final CatalogService catalogService;
    
    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }  
	
    @GetMapping("/categories")
    public List<Categorie> getCategories() {
        return catalogService.getCategories();
    }
    
    @GetMapping("/units")
    public List<Unit> getUnits() {
        return catalogService.getUnits();
    }
    
}
